package br.edu.ifsp.MicroSaaS.dao;

public class EspecialidadeDAOFactory {
	public EspecialidadeDAO factory() {
		return new EspecialidadeDAOImpl();
	}
}
