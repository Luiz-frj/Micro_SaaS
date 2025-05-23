package br.edu.ifsp.MicroSaaS.dao;

public class ServicoDAOFactory {
	public ServicoDAO factory() {
		return new ServicoDAOImpl();
	}
}
