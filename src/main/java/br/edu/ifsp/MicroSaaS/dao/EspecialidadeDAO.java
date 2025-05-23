package br.edu.ifsp.MicroSaaS.dao;

import java.util.List;

import br.edu.ifsp.MicroSaaS.model.Especialidade;
import br.edu.ifsp.MicroSaaS.model.Prestador;

public interface EspecialidadeDAO {
	boolean insert(Especialidade Especialidade);
	Especialidade getById(int id);
	List<Especialidade> getByName(String name);
	List<Especialidade> getByIdPrestador(int id);
	List<Prestador> getByEspecialidade(Especialidade especialidade);
}
