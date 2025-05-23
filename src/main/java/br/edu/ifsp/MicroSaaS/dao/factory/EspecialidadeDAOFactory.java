package br.edu.ifsp.MicroSaaS.dao.factory;

import br.edu.ifsp.MicroSaaS.dao.EspecialidadeDAO;
import br.edu.ifsp.MicroSaaS.dao.impl.EspecialidadeDAOImpl;

public class EspecialidadeDAOFactory {
	public EspecialidadeDAO factory() {
		return new EspecialidadeDAOImpl();
	}
}
