package br.edu.ifsp.MicroSaaS.controller.command.logged;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import br.edu.ifsp.MicroSaaS.controller.command.Command;
import br.edu.ifsp.MicroSaaS.dao.AgendamentoDAO;
import br.edu.ifsp.MicroSaaS.dao.ClienteDAO;
import br.edu.ifsp.MicroSaaS.dao.DisponibilidadeDAO;
import br.edu.ifsp.MicroSaaS.dao.ServicoDAO;
import br.edu.ifsp.MicroSaaS.dao.factory.AgendamentoDAOFactory;
import br.edu.ifsp.MicroSaaS.dao.factory.ClienteDAOFactory;
import br.edu.ifsp.MicroSaaS.dao.factory.DisponibilidadeDAOFactory;
import br.edu.ifsp.MicroSaaS.dao.factory.ServicoDAOFactory;
import br.edu.ifsp.MicroSaaS.model.Prestador;
import br.edu.ifsp.MicroSaaS.model.Servico;
import br.edu.ifsp.MicroSaaS.model.Agendamento;
import br.edu.ifsp.MicroSaaS.model.Cliente;
import br.edu.ifsp.MicroSaaS.model.Disponibilidade;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class NewAgendamentoCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		ServicoDAO servicoDao = new ServicoDAOFactory().factory();
		ClienteDAO clienteDao = new ClienteDAOFactory().factory();
		DisponibilidadeDAO disponibilidadeDao = new DisponibilidadeDAOFactory().factory();
		AgendamentoDAO agendamentoDao = new AgendamentoDAOFactory().factory();
		
		String time = request.getParameter("time");
		String date = request.getParameter("date");
		String servico_id = request.getParameter("servico");
		
		try {
			if (servico_id != null) {
				Servico servico = servicoDao.getById(Integer.parseInt(servico_id));
				
				if (servico != null ) {
					if (session != null && session.getAttribute("user") != null) {
			        	Cliente user = (Cliente) session.getAttribute("user");
			        	Cliente dbUser = clienteDao.getByEmail(user.getEmail());
			        	System.out.println(dbUser);
			        	
			        	List<Disponibilidade> disponibilidade = disponibilidadeDao.getByIdServico(servico.getId());
			        	
			        	LocalDate localDate = LocalDate.parse(date);
			        	System.out.println(date);
			        	System.out.println(localDate);
			        	System.out.println(localDate.getDayOfWeek().getValue());
			        	
			        	for (Disponibilidade d : disponibilidade) {
			        		System.out.println(d);
			        		if (d.getDia_semana() == localDate.getDayOfWeek().getValue()-1) {
			        			LocalTime disponibilidadeTimeInicio = LocalTime.parse(d.getInicio_servico());
			        			LocalTime agendamentoTime = LocalTime.parse(time + ":00");
			        			System.out.println(disponibilidadeTimeInicio);
			        			System.out.println(agendamentoTime);
			        			
			        			System.out.println(agendamentoTime.compareTo(disponibilidadeTimeInicio));
			        			
			        			if (agendamentoTime.compareTo(disponibilidadeTimeInicio) >= 0) {
			        				LocalTime disponibilidadeTimeFim = LocalTime.parse(d.getFim_servico());
			        				LocalTime agendamentoTimeFim = agendamentoTime.plusHours(servico.getTempo_servico());
			        				System.out.println(servico.getTempo_servico());
			        				System.out.println(agendamentoTimeFim);
			        				System.out.println(disponibilidadeTimeFim);
			        				if (disponibilidadeTimeFim.compareTo(agendamentoTimeFim) >= 0) {
			        					System.out.println("Tá daora");
			        					List<Agendamento> agendamentos = agendamentoDao.getByIdServico(servico.getId());
			        					
			        					boolean ok = true;
			        					if (!agendamentos.isEmpty()) {
				        					for (Agendamento a : agendamentos) {
				        						if (a.getStatus_agendamento() == 1) {
				        							System.out.println(a.getHorario());
				        							LocalDateTime aDateTime = LocalDateTime.parse(a.getHorario().replace(" ", "T"));
				        							LocalTime aLocalTimeInicio = LocalTime.parse(a.getHorario().split(" ")[1]);
				        							LocalTime aLocalTimeFim = aLocalTimeInicio.plusHours(servico.getTempo_servico());
				        							LocalDate aLocalDate = LocalDate.parse(a.getHorario().split(" ")[0]);
				        							
				        							if (aLocalDate.compareTo(localDate) == 0) {
					        							if (agendamentoTime.compareTo(aLocalTimeInicio) < 0) {
					        								if (agendamentoTimeFim.compareTo(aLocalTimeInicio) > 0) {
					        									ok = false;
					        									System.out.println("FOI AQUI");
					        								}
					        							} else {
					        								if (agendamentoTime.compareTo(aLocalTimeFim) < 0) {
					        									ok = false;
					        									System.out.println("N, FOI AQUI");
					        								}
					        							}
				        							}
				        						}
				        					}
			        					}
			        					
			        					if (ok) {
			        						Agendamento agendamento = new Agendamento(servico.getId(), dbUser.getId(), 0, localDate + " " + time + ":00");
			        						System.out.println(agendamento);
			        						if (agendamentoDao.insert(agendamento)) {
			        							System.out.println("FOI");
			        						}
			        					} else {
			        						System.out.println("N FOI");
			        					}
			        				}
			        			}
			        		}
			        	}
			        	
					}
				}
			}
		} catch (Exception e ) {
			e.printStackTrace();
		}
		
		return null;
	}

}
