package br.edu.ifsp.MicroSaaS.dao;

public class ClienteDAOFactory {
	public ClienteDAO factory() {
		return new ClienteDAOImpl();
	}
}
