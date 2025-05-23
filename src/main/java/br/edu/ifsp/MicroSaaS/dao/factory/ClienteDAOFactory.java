package br.edu.ifsp.MicroSaaS.dao.factory;

import br.edu.ifsp.MicroSaaS.dao.ClienteDAO;
import br.edu.ifsp.MicroSaaS.dao.impl.ClienteDAOImpl;

public class ClienteDAOFactory {
	public ClienteDAO factory() {
		return new ClienteDAOImpl();
	}
}
