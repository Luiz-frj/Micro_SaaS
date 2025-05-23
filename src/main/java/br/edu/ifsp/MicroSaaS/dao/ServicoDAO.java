package br.edu.ifsp.MicroSaaS.dao;

import java.util.List;

import br.edu.ifsp.MicroSaaS.model.Servico;

public interface ServicoDAO {
	boolean insert(Servico servico);
	boolean update(Servico servico_atual, Servico servico_novo);
	boolean delete(Servico servico);
	Servico getById(int id);
	List<Servico> getByIdPrestador(int id);
	List<Servico> getPage(int page, int qtd);
}