package br.edu.ifsp.MicroSaaS.controller.command.logged;

import java.io.IOException;

import br.edu.ifsp.MicroSaaS.controller.command.Command;
import br.edu.ifsp.MicroSaaS.dao.AgendamentoDAO;
import br.edu.ifsp.MicroSaaS.dao.factory.AgendamentoDAOFactory;
import br.edu.ifsp.MicroSaaS.model.Agendamento;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateStatusAgendamento implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AgendamentoDAO agendamentoDao = new AgendamentoDAOFactory().factory();
		
		int agendamento_id = Integer.parseInt(request.getParameter("agendamento").toString());
		int new_status = Integer.parseInt(request.getParameter("to").toString());
		
		try {
			Agendamento agendamento = agendamentoDao.getById(agendamento_id);
			Agendamento newAgendamento = new Agendamento(agendamento.getId_servico(), agendamento.getId_cliente(), new_status, agendamento.getHorario());
			
			agendamentoDao.update(agendamento, newAgendamento);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "logged.do?action=agendamentoList";
	}

}
