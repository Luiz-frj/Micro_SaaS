package br.edu.ifsp.MicroSaaS.controller.command.logged;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

import br.edu.ifsp.MicroSaaS.controller.command.Command;
import br.edu.ifsp.MicroSaaS.dao.PortifolioDAO;
import br.edu.ifsp.MicroSaaS.dao.ServicoDAO;
import br.edu.ifsp.MicroSaaS.model.Cliente;
import br.edu.ifsp.MicroSaaS.model.Prestador;
import br.edu.ifsp.MicroSaaS.model.Servico;
import br.edu.ifsp.MicroSaaS.model.Portifolio;
import br.edu.ifsp.MicroSaaS.dao.factory.PortifolioDAOFactory;
import br.edu.ifsp.MicroSaaS.dao.factory.ServicoDAOFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

public class NewServicoCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
			
		ServicoDAO servicoDao = new ServicoDAOFactory().factory();
		PortifolioDAO portifolioDao = new PortifolioDAOFactory().factory();
			
		String name = request.getParameter("name").toString();
		String description = request.getParameter("description").toString();
		String address = request.getParameter("address").toString();
		
		try {
			String uploadPath = System.getProperty("user.home") + File.separator + "Micro_SaaS" + File.separator + "src" + File.separator + "main" + File.separator + "webapp" + File.separator + "uploads";
			File uploadDir = new File(uploadPath);
		    if (!uploadDir.exists()) uploadDir.mkdir();
		
		    int imagemCount = 0;
		    List<String> images = new ArrayList<>();
		    for (Part part : request.getParts()) {
		        if (part.getName().equals("images") && part.getSize() > 0) {
		            imagemCount++;
		            if (imagemCount > 5) break;
		
		            String fileName = extractFileName(part);
		            String fileNewName = UUID.randomUUID().toString() + fileName;
		
		            String filePath = uploadPath + File.separator + fileNewName;
		
		            try (InputStream input = part.getInputStream()) {
		                Files.copy(input, new File(filePath).toPath());
		                images.add(filePath);
		            }
		        }
		    }
	
		    if (session != null && session.getAttribute("user") != null) {
		        try {
		        	Prestador user = (Prestador) session.getAttribute("user");
			        
			        Servico servico = new Servico(user.getId(), name, description, 1, address);
			        servicoDao.insert(servico);
			        
			        List<Servico> servico_list = servicoDao.getByIdPrestador(user.getId());
			        
			        int servico_id = -1;
			        for (Servico s : servico_list) {
			        	if (s.getName().equals(name)) {
			        		if (s.getDescription().equals(description)) {
			        			if (s.getLocal().equals(address)) {
			        				servico_id = s.getId();
			        				System.out.println(servico_id);
			        			}
			        		}
			        	}
			        }
			        if (servico_id != -1) {
				        for (String p : images) {
				        	Portifolio portifolio = new Portifolio(servico_id, p);
				        	portifolioDao.insert(portifolio);
				        }
			        }
			        
				    System.out.println("Serviço '" + name + "' cadastrado com sucesso com " + imagemCount + " imagem(ns).");
		        } catch (Exception e) {
		        	e.printStackTrace();
		        }
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    return "logged.do?action=home";
	}
	
	private String extractFileName(Part part) {
	    String contentDisp = part.getHeader("content-disposition");
	    for (String token : contentDisp.split(";")) {
	        if (token.trim().startsWith("filename")) {
	            return new File(token.substring(token.indexOf('=') + 1).trim().replace("\"", "")).getName();
	        }
	    }
	    return "arquivo_desconhecido";
	}
}
