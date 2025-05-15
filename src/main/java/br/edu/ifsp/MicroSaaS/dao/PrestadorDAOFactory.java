package br.edu.ifsp.MicroSaaS.dao;

public class PrestadorDAOFactory {
	public PrestadorDAO factory() {
		return new PrestadorDAOImpl();
	}
}
