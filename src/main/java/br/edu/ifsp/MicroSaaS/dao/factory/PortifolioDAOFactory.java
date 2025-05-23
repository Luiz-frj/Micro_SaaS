package br.edu.ifsp.MicroSaaS.dao.factory;

import br.edu.ifsp.MicroSaaS.dao.PortifolioDAO;
import br.edu.ifsp.MicroSaaS.dao.impl.PortifolioDAOImpl;

public class PortifolioDAOFactory {
	public PortifolioDAO factory() {
		return new PortifolioDAOImpl();
	}
}
