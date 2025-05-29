package br.edu.ifsp.MicroSaaS.controller.command.redirect;

import java.io.IOException;

import br.edu.ifsp.MicroSaaS.controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FormNewAgendamentoCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String servico_id = request.getParameter("servico");
		
		request.setAttribute("servico", servico_id);
		
		System.out.println(servico_id);
		
		return "/logged/agendar_cliente.jsp";
	}

}
