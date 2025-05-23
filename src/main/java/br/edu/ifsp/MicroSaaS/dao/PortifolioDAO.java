package br.edu.ifsp.MicroSaaS.dao;

import java.util.List;

import br.edu.ifsp.MicroSaaS.model.Portifolio;

public interface PortifolioDAO {
	boolean insert(Portifolio portifolio);
	boolean update(Portifolio portifolio_atual, Portifolio portifolio_novo);
	List<Portifolio> getByIdServico(int id_servico);
	Portifolio getById(int id_img);
}
