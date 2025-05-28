package br.edu.ifsp.MicroSaaS.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import br.edu.ifsp.MicroSaaS.controller.command.*;
import br.edu.ifsp.MicroSaaS.controller.command.logged.DeleteServicoCommand;
import br.edu.ifsp.MicroSaaS.controller.command.logged.LoggedHomeCommand;
import br.edu.ifsp.MicroSaaS.controller.command.logged.LogoffCommand;
import br.edu.ifsp.MicroSaaS.controller.command.logged.NewAgendamentoCommand;
import br.edu.ifsp.MicroSaaS.controller.command.logged.NewServicoCommand;
import br.edu.ifsp.MicroSaaS.controller.command.logged.ServicoFilterCommand;
import br.edu.ifsp.MicroSaaS.controller.command.logged.ServicoPagination;
import br.edu.ifsp.MicroSaaS.controller.command.logged.UpdateAgendamentoCommand;
import br.edu.ifsp.MicroSaaS.controller.command.logged.UpdateServicoCommand;
import br.edu.ifsp.MicroSaaS.controller.command.redirect.AgendamentoDetailsCommand;
import br.edu.ifsp.MicroSaaS.controller.command.redirect.FormNewAgendamentoCommand;
import br.edu.ifsp.MicroSaaS.controller.command.redirect.FormNewServicoCommand;
import br.edu.ifsp.MicroSaaS.controller.command.redirect.FormUpdateAgendamentoCommand;
import br.edu.ifsp.MicroSaaS.controller.command.redirect.FormUpdateServicoCommand;
import br.edu.ifsp.MicroSaaS.controller.command.redirect.ListAgendamentoCommand;

@WebServlet("/logged.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 2, maxRequestSize = 1024 * 1024 * 2)
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
		} else if("newAgendamento".equals(action)) { 
			command = new NewAgendamentoCommand();
		} else if("newServico".equals(action)) { 
			command = new NewServicoCommand();
		} else if("updateServico".equals(action)) { 
			command = new UpdateServicoCommand();
		} else if("deleteServico".equals(action)) { 
			command = new DeleteServicoCommand();
		} else if("updateAgendamento".equals(action)) { 
			command = new UpdateAgendamentoCommand();
		} else if("formNewAgendamento".equals(action)) { 
			command = new FormNewAgendamentoCommand();
		} else if("formNewServico".equals(action)) { 
			command = new FormNewServicoCommand();
		} else if("formUpdateAgendamento".equals(action)) { 
			command = new FormUpdateAgendamentoCommand();
		} else if("formUpdateServico".equals(action)) { 
			command = new FormUpdateServicoCommand();
		} else if("agendamentoList".equals(action)) { 
			command = new ListAgendamentoCommand();
		} else if("agendamentoDetails".equals(action)) { 
			command = new AgendamentoDetailsCommand();
		} else if("servicoFilter".equals(action)) { 
			command = new ServicoFilterCommand();
		} else if("servicoPagination".equals(action)) { 
			command = new ServicoPagination();
		} else {
			command = new LoggedHomeCommand();
		}

        String view = command.execute(request, response);
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }
}