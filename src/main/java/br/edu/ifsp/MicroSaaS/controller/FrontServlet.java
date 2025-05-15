package br.edu.ifsp.MicroSaaS.controller;

import java.io.IOException;

import br.edu.ifsp.MicroSaaS.controller.command.Command;
import br.edu.ifsp.MicroSaaS.controller.command.FormSignClienteCommand;
import br.edu.ifsp.MicroSaaS.controller.command.FormSignPrestadorCommand;
import br.edu.ifsp.MicroSaaS.controller.command.HomeCommand;
import br.edu.ifsp.MicroSaaS.controller.command.SignClienteCommand;
import br.edu.ifsp.MicroSaaS.controller.command.SignPrestadorCommand;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/front.do")
public class FrontServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Command command = null;
		String action = request.getParameter("action");
		
		if("signCliente".equals(action)) {
			command = new SignClienteCommand();
		} else if("signPrestador".equals(action)) {
			command = new SignPrestadorCommand();
		} else if("loginCliente".equals(action)) {
		} else if("loginPrestador".equals(action)) {
		} else if("formSignCliente".equals(action)) {
			command = new FormSignClienteCommand();
		} else if("formSignPrestador".equals(action)) {
			command = new FormSignPrestadorCommand();
		} else if("formLoginPrestador".equals(action)) {
		} else if("formLoginPrestador".equals(action)) {
		} else {
			command = new HomeCommand();
		}
		
		String view = command.execute(request, response);
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}
