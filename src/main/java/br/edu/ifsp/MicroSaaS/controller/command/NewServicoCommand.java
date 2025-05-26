package br.edu.ifsp.MicroSaaS.controller.command;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class NewServicoCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	String name = request.getParameter("name");
	String description = request.getParameter("description");
	String address = request.getParameter("address");
	
	String uploadPath = "C:\\Users\\guilh\\Documents\\Micro_SaaS\\uploads";
	File uploadDir = new File(uploadPath);
    if (!uploadDir.exists()) uploadDir.mkdir();

    int imagemCount = 0;
    for (Part part : request.getParts()) {
        if (part.getName().equals("images") && part.getSize() > 0) {
            imagemCount++;
            if (imagemCount > 5) break;

            String fileName = extractFileName(part);

            String filePath = uploadPath + File.separator + fileName;

            try (InputStream input = part.getInputStream()) {
                Files.copy(input, new File(filePath).toPath());
            }
        }
    }

	    System.out.println("Serviço '" + name + "' cadastrado com sucesso com " + imagemCount + " imagem(ns).");
	    
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
