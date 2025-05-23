package br.edu.ifsp.MicroSaaS.controller.command;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import br.edu.ifsp.MicroSaaS.dao.ClienteDAO;
import br.edu.ifsp.MicroSaaS.dao.ClienteDAOFactory;
import br.edu.ifsp.MicroSaaS.dao.PrestadorDAO;
import br.edu.ifsp.MicroSaaS.dao.PrestadorDAOFactory;
import br.edu.ifsp.MicroSaaS.model.Cliente;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class SignClienteCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ClienteDAO dao = new ClienteDAOFactory().factory();

		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");

		Cliente cliente = new Cliente(name, address, email, phone, password, true);
		
		if (dao.insert(cliente)) {
			request.setAttribute("msg", "O seu usuário foi criado com sucesso");
			return "front.do?action=home";
		}
		request.setAttribute("msg", "Não foi possível criar um novo usuário");
		
		return "front.do?action=home";
	}

}
