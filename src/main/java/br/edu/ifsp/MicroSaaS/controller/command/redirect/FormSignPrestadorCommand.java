package br.edu.ifsp.MicroSaaS.controller.command.redirect;

import java.io.IOException;

import br.edu.ifsp.MicroSaaS.controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FormSignPrestadorCommand implements Command {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		return "/form_sign_prestador.jsp";
	}
}
