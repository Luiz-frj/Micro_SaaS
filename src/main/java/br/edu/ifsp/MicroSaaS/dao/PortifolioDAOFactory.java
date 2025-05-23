package br.edu.ifsp.MicroSaaS.dao;

public class PortifolioDAOFactory {
	public PortifolioDAO factory() {
		return new PortifolioDAOImpl();
	}
}
