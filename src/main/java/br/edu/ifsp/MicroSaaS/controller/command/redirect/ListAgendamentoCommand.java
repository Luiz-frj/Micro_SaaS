package br.edu.ifsp.MicroSaaS.controller.command.redirect;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import br.edu.ifsp.MicroSaaS.controller.command.Command;
import br.edu.ifsp.MicroSaaS.dao.AgendamentoDAO;
import br.edu.ifsp.MicroSaaS.dao.ClienteDAO;
import br.edu.ifsp.MicroSaaS.dao.PrestadorDAO;
import br.edu.ifsp.MicroSaaS.dao.ServicoDAO;
import br.edu.ifsp.MicroSaaS.dao.factory.AgendamentoDAOFactory;
import br.edu.ifsp.MicroSaaS.dao.factory.ClienteDAOFactory;
import br.edu.ifsp.MicroSaaS.dao.factory.PrestadorDAOFactory;
import br.edu.ifsp.MicroSaaS.dao.factory.ServicoDAOFactory;
import br.edu.ifsp.MicroSaaS.model.Cliente;
import br.edu.ifsp.MicroSaaS.model.Prestador;
import br.edu.ifsp.MicroSaaS.model.Servico;
import br.edu.ifsp.MicroSaaS.model.Agendamento;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ListAgendamentoCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		AgendamentoDAO agendamentoDao = new AgendamentoDAOFactory().factory();
		ServicoDAO servicoDao = new ServicoDAOFactory().factory();
		ClienteDAO clienteDao = new ClienteDAOFactory().factory();
		PrestadorDAO prestadorDao = new PrestadorDAOFactory().factory();

		if (session != null && session.getAttribute("user") != null) {
			try {
	        	Cliente user = (Cliente) session.getAttribute("user");
	        	Cliente dbUser = clienteDao.getByEmail(user.getEmail());
	        	
	        	HashMap<Agendamento, Servico> servicoAgendamento = new HashMap<>();
	        	
	        	List<Agendamento> agendamentos = agendamentoDao.getByIdCliente(user.getId());
	        	
	        	for (Agendamento a : agendamentos) {
        			Servico servico = servicoDao.getById(a.getId_servico());
        			servicoAgendamento.put(a, servico);
        		}
	        	
	        	request.setAttribute("is_prestador", false);
	        	request.setAttribute("agendamentos", servicoAgendamento);
	        	
	        	return "/logged/lista_agendamentos.jsp";
			} catch (Exception e ) {
				e.printStackTrace();
			}
			try {
	        	Prestador user = (Prestador) session.getAttribute("user");
	        	Prestador dbUser = prestadorDao.getByEmail(user.getEmail());
	        	
	        	HashMap<Agendamento, Servico> servicoAgendamento = new HashMap<>();
	        	
	        	List<Servico> servicos = servicoDao.getByIdPrestador(user.getId());
	        	
	        	for (Servico s : servicos) {
	        		List<Agendamento> agendamentos = agendamentoDao.getByIdServico(s.getId());
	        		for (Agendamento a : agendamentos) {
	        			servicoAgendamento.put(a, s);
	        		}
	        	}
	        	
	        	request.setAttribute("is_prestador", true);
	        	request.setAttribute("agendamentos", servicoAgendamento);
	        	
	        	return "/logged/lista_agendamentos.jsp";
			} catch (Exception e ) {
				e.printStackTrace();
			}
			
		}
		
		return "logged.do?action=home";
	}

}
