package br.edu.ifsp.MicroSaaS.dao;

import java.util.List;

import br.edu.ifsp.MicroSaaS.model.Disponibilidade;

public interface DisponibilidadeDAO {
	boolean insert(Disponibilidade disponibilidade);
	boolean update(Disponibilidade disponibilidade_atual, Disponibilidade disponibilidade_novo);
	boolean delete(Disponibilidade disponibilidade);
	Disponibilidade getById(int id);
	List<Disponibilidade> getByIdServico(int id_servico);
}
