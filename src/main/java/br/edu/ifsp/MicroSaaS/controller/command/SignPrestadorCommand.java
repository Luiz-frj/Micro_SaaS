package br.edu.ifsp.MicroSaaS.controller.command;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import br.edu.ifsp.MicroSaaS.dao.ClienteDAO;
import br.edu.ifsp.MicroSaaS.dao.PrestadorDAO;
import br.edu.ifsp.MicroSaaS.dao.factory.ClienteDAOFactory;
import br.edu.ifsp.MicroSaaS.dao.factory.PrestadorDAOFactory;
import br.edu.ifsp.MicroSaaS.dao.impl.PrestadorDAOImpl;
import br.edu.ifsp.MicroSaaS.model.Prestador;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 2, maxRequestSize = 1024 * 1024 * 2)
public class SignPrestadorCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<String> tiposPermitidos = Arrays.asList("image/png", "image/jpeg");

		PrestadorDAO dao = new PrestadorDAOFactory().factory();

		String name = request.getParameter("name");
		String user = request.getParameter("username");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		String cpf = request.getParameter("cpf");
		Part fotoPart = request.getPart("foto");
		String contentType = fotoPart.getContentType();
		if (!tiposPermitidos.contains(contentType)) {
			throw new ServletException("Tipo de arquivo inválido: " + contentType);
		}
		String nomeArquivo = UUID.randomUUID().toString() + "_"
				+ Paths.get(fotoPart.getSubmittedFileName()).getFileName().toString();
		String uploadPath = "C:\\Users\\guilh\\Documents\\Micro_SaaS\\uploads";
		Files.createDirectories(Paths.get(uploadPath));
		fotoPart.write(uploadPath + File.separator + nomeArquivo);
		
		Prestador prestador = new Prestador(name, user, email, phone, password, cpf, uploadPath + File.separator + nomeArquivo, true);
		
		if (dao.insert(prestador)) {
			request.setAttribute("msg", "O seu usuário foi criado com sucesso");
			return "front.do?action=home";
		}
		request.setAttribute("msg", "Não foi possível criar um novo usuário");
		
		return "front.do?action=index";
	}

}
