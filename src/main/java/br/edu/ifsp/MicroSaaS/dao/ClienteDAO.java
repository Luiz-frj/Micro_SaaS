package br.edu.ifsp.MicroSaaS.dao;

import java.sql.Connection;
import java.sql.SQLException;

import br.edu.ifsp.MicroSaaS.model.Cliente;
import br.edu.ifsp.MicroSaaS.model.Prestador;

public interface ClienteDAO {
	boolean insert(Cliente cliente);
	Cliente getByName(String name);
}
