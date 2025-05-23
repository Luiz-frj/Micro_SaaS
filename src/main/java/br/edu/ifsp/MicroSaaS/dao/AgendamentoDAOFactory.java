package br.edu.ifsp.MicroSaaS.dao;

public class AgendamentoDAOFactory {
	public AgendamentoDAO factory() {
		return new AgendamentoDAOImpl();
	}
}
