package br.edu.ifsp.MicroSaaS.dao;

import java.util.List;

import br.edu.ifsp.MicroSaaS.model.Agendamento;

public interface AgendamentoDAO {
	boolean insert(Agendamento agendamento);
	boolean update(Agendamento agendamento_atual, Agendamento agendamento_novo);
	List<Agendamento> getByIdServico(int id_servico);
	List<Agendamento> getByIdCliente(int id_cliente);
	Agendamento getById(int id);
}
