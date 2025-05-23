package br.edu.ifsp.MicroSaaS.dao.factory;

import br.edu.ifsp.MicroSaaS.dao.DisponibilidadeDAO;
import br.edu.ifsp.MicroSaaS.dao.impl.DisponibilidadeDAOImpl;

public class DisponibilidadeDAOFactory {
	public DisponibilidadeDAO factory() {
		return new DisponibilidadeDAOImpl();
	}
}
