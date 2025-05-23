package br.edu.ifsp.MicroSaaS.dao.factory;

import br.edu.ifsp.MicroSaaS.dao.PrestadorDAO;
import br.edu.ifsp.MicroSaaS.dao.impl.PrestadorDAOImpl;

public class PrestadorDAOFactory {
	public PrestadorDAO factory() {
		return new PrestadorDAOImpl();
	}
}
