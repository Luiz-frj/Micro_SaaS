package br.edu.ifsp.MicroSaaS.dao.factory;

import br.edu.ifsp.MicroSaaS.dao.ServicoDAO;
import br.edu.ifsp.MicroSaaS.dao.impl.ServicoDAOImpl;

public class ServicoDAOFactory {
	public ServicoDAO factory() {
		return new ServicoDAOImpl();
	}
}
