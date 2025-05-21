package br.edu.ifsp.MicroSaaS.controller.command;

import java.io.IOException;

import br.edu.ifsp.MicroSaaS.dao.ClienteDAO;
import br.edu.ifsp.MicroSaaS.dao.ClienteDAOFactory;
import br.edu.ifsp.MicroSaaS.dao.PrestadorDAO;
import br.edu.ifsp.MicroSaaS.dao.PrestadorDAOFactory;
import br.edu.ifsp.MicroSaaS.model.Cliente;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginClienteCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ClienteDAO dao = new ClienteDAOFactory().factory();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Cliente user = dao.getByEmail(email);
		
		if (user != null) {
		
			if (user.verify(password)) { // verifica se a senha passada pelo usuário (criptografando-a) é a mesma que está armazenada seu usuário, que foi recebido pelo banco de dados sem ter sua senha criptografada novamente
				HttpSession session = request.getSession(true);
				session.setAttribute("user", user);
				session.setMaxInactiveInterval(3600);
				return "logged.do?action=home";
			}
		}
		request.setAttribute("msg", "Não foi possível entrar na sua conta");
		return "front.do?action=home";
	}

}
