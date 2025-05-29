package br.edu.ifsp.MicroSaaS.dao;

import java.util.List;

import br.edu.ifsp.MicroSaaS.model.Especialidade;
import br.edu.ifsp.MicroSaaS.model.Prestador;
import br.edu.ifsp.MicroSaaS.model.Servico;

public interface EspecialidadeDAO {
	boolean insert(Especialidade Especialidade);
	boolean insertServicoEspecialidade(Especialidade especialidade, int id_servico);
	Especialidade getById(int id);
	List<Especialidade> getByName(String name);
	List<Especialidade> getByIdPrestador(int id);
	List<Servico> getByEspecialidade(String especialidade_name);
}
