package br.edu.ifsp.MicroSaaS.controller;

import java.io.IOException;

import br.edu.ifsp.MicroSaaS.controller.command.*;
import br.edu.ifsp.MicroSaaS.controller.command.redirect.FormLoginClienteCommand;
import br.edu.ifsp.MicroSaaS.controller.command.redirect.FormLoginPrestadorCommand;
import br.edu.ifsp.MicroSaaS.controller.command.redirect.FormSignClienteCommand;
import br.edu.ifsp.MicroSaaS.controller.command.redirect.FormSignPrestadorCommand;
import br.edu.ifsp.MicroSaaS.controller.command.redirect.HomeCommand;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/front.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 2, maxRequestSize = 1024 * 1024 * 2)
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
			command = new LoginClienteCommand();
		} else if("loginPrestador".equals(action)) {
			command = new LoginPrestadorCommand();
		} else if("formSignCliente".equals(action)) {
			command = new FormSignClienteCommand();
		} else if("formSignPrestador".equals(action)) {
			command = new FormSignPrestadorCommand();
		} else if("formLoginPrestador".equals(action)) {
			command = new FormLoginPrestadorCommand();
		} else if("formLoginCliente".equals(action)) {
			command = new FormLoginClienteCommand();
		} else {
			command = new HomeCommand();
		}
		
		String view = command.execute(request, response);
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}
