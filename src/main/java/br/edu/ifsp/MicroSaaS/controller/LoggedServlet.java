package br.edu.ifsp.MicroSaaS.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import br.edu.ifsp.MicroSaaS.controller.command.*;
import br.edu.ifsp.MicroSaaS.controller.command.logged.LoggedHomeCommand;
import br.edu.ifsp.MicroSaaS.controller.command.logged.LogoffCommand;

@WebServlet("/logged.do")
public class LoggedServlet extends HttpServlet {
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
        
        if("logoff".equals(action)) {
			command = new LogoffCommand();
		} else {
			command = new LoggedHomeCommand();
		}

        String view = command.execute(request, response);
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }
}