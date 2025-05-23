package br.edu.ifsp.MicroSaaS.dao;

public class DisponibilidadeDAOFactory {
	public DisponibilidadeDAO factory() {
		return new DisponibilidadeDAOImpl();
	}
}
