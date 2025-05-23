package br.edu.ifsp.MicroSaaS.controller.command.logged;

import java.io.IOException;

import br.edu.ifsp.MicroSaaS.controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogoffCommand implements Command{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		
		return "front.do?action=home";
	}
}
