package br.edu.ifsp.MicroSaaS.controller.command;

import java.io.File;
import java.io.IOException;
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
import br.edu.ifsp.MicroSaaS.dao.ClienteDAOFactory;
import br.edu.ifsp.MicroSaaS.dao.PrestadorDAO;
import br.edu.ifsp.MicroSaaS.dao.PrestadorDAOFactory;
import br.edu.ifsp.MicroSaaS.dao.PrestadorDAOImpl;
import br.edu.ifsp.MicroSaaS.model.Prestador;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class SignPrestadorCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<String> tiposPermitidos = Arrays.asList("image/png", "image/jpeg");

		PrestadorDAO dao = new PrestadorDAOFactory().factory();

		String name = request.getParameter("name");
		String user = request.getParameter("user");
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
		String uploadPath = "/home/aluno/eclipse-workspace/MicroSaaS/uploads";
		Files.createDirectories(Paths.get(uploadPath));
		fotoPart.write(uploadPath + File.separator + nomeArquivo);
		
		Prestador prestador = new Prestador(name, user, email, phone, password, cpf, uploadPath + File.separator + nomeArquivo, false);
		
		if (dao.insert(prestador)) {
			request.setAttribute("msg", "O seu usuário foi criado com sucesso");
			return "front.do?action=home";
		}
		request.setAttribute("msg", "Não foi possível criar um novo usuário");
		
		return "front.do?action=home";
	}

}
