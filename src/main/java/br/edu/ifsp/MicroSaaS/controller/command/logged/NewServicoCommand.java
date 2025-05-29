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
import br.edu.ifsp.MicroSaaS.dao.DisponibilidadeDAO;
import br.edu.ifsp.MicroSaaS.dao.EspecialidadeDAO;
import br.edu.ifsp.MicroSaaS.dao.PortifolioDAO;
import br.edu.ifsp.MicroSaaS.dao.ServicoDAO;
import br.edu.ifsp.MicroSaaS.model.Cliente;
import br.edu.ifsp.MicroSaaS.model.Prestador;
import br.edu.ifsp.MicroSaaS.model.Servico;
import br.edu.ifsp.MicroSaaS.model.Disponibilidade;
import br.edu.ifsp.MicroSaaS.model.Especialidade;
import br.edu.ifsp.MicroSaaS.model.Portifolio;
import br.edu.ifsp.MicroSaaS.dao.factory.DisponibilidadeDAOFactory;
import br.edu.ifsp.MicroSaaS.dao.factory.EspecialidadeDAOFactory;
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
		DisponibilidadeDAO disponibilidadeDao = new DisponibilidadeDAOFactory().factory();
		PortifolioDAO portifolioDao = new PortifolioDAOFactory().factory();
		EspecialidadeDAO especialidadeDao = new EspecialidadeDAOFactory().factory();
			
		String name = request.getParameter("name").toString();
		String description = request.getParameter("description").toString();
		String address = request.getParameter("address").toString();
		String servico_time = request.getParameter("servico_time").toString();
		String especialidade_name = request.getParameter("especialidade");
		
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
			        
			        Servico servico = new Servico(user.getId(), name, description, 1, address, Integer.parseInt(servico_time.toString().split(":")[0]) + (Integer.parseInt(servico_time.toString().split(":")[1])/60));
			        servicoDao.insert(servico);
			        
			        List<Servico> servico_list = servicoDao.getByIdPrestador(user.getId());
			        
			        int servico_id = -1;
			        for (Servico s : servico_list) {
			        	if (s.getName().equals(name)) {
			        		if (s.getDescription().equals(description)) {
			        			if (s.getLocal().equals(address)) {
			        				servico_id = s.getId();
			        			}
			        		}
			        	}
			        }
			        if (servico_id != -1) {
			        	if (especialidade_name != null) {
				        	List<Especialidade> especialidades = especialidadeDao.getByName(especialidade_name);
				        	if (!especialidades.isEmpty()) {
				        		especialidadeDao.insertServicoEspecialidade(especialidades.get(0), servico_id);
				        	} else {
				        		Especialidade new_especialidade = new Especialidade(especialidade_name, "");
				        		especialidadeDao.insert(new_especialidade);
				        		
				        		especialidadeDao.insertServicoEspecialidade(new_especialidade, servico_id);
				        	}
				        }
			        	for (int i = 0; i < 7; i++) {
			    	        String inicio = request.getParameter("inicio_" + i);
			    	        String fim = request.getParameter("fim_" + i);
			    	        String almocoInicio = request.getParameter("almoco_inicio_" + i);
			    	        String almocoFim = request.getParameter("almoco_fim_" + i);
			    	        
			    	        if (inicio != null && fim != null && almocoInicio != null && almocoFim != null) {
			    	        	if (!inicio.isEmpty() && !fim.isEmpty() && !almocoInicio.isEmpty() && !almocoFim.isEmpty()) {
			    	        		Disponibilidade disponibilidade = new Disponibilidade(servico_id, i, almocoInicio + ":00", almocoFim + ":00", inicio + ":00", fim + ":00");
			    	        		disponibilidadeDao.insert(disponibilidade);
			    	        	}
			    	        }
			    	        System.out.println("Dia " + i + ": " + inicio + " - " + fim + " | Almoço: " + almocoInicio + " - " + almocoFim);
			    	    }
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
