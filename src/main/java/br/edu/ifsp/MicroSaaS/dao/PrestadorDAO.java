package br.edu.ifsp.MicroSaaS.dao;

import java.sql.Connection;

import br.edu.ifsp.MicroSaaS.model.Prestador;

public interface PrestadorDAO {
	boolean insert(Prestador prestador);
	Prestador getByEmail(String email);
	Prestador getById(int id);
}
