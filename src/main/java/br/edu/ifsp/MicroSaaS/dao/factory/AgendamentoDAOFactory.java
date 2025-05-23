package br.edu.ifsp.MicroSaaS.dao.factory;

import br.edu.ifsp.MicroSaaS.dao.AgendamentoDAO;
import br.edu.ifsp.MicroSaaS.dao.impl.AgendamentoDAOImpl;

public class AgendamentoDAOFactory {
	public AgendamentoDAO factory() {
		return new AgendamentoDAOImpl();
	}
}
