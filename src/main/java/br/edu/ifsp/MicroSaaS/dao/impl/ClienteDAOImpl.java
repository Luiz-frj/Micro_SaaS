package br.edu.ifsp.MicroSaaS.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.MicroSaaS.dao.ClienteDAO;
import br.edu.ifsp.MicroSaaS.dao.connection.DatabaseConnection;
import br.edu.ifsp.MicroSaaS.model.Cliente;
import br.edu.ifsp.MicroSaaS.model.Prestador;

public class ClienteDAOImpl implements ClienteDAO {
	private static final String INSERT = "INSERT INTO Cliente (nome, email, endereco, telefone, senha) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_BY_EMAIL = "SELECT * FROM Cliente WHERE email = ?";
	
	@Override
	public boolean insert(Cliente cliente) {
		int rows = 0;
		if(cliente != null) {
			try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT)){
				
				statement.setString(1, cliente.getName());
				statement.setString(2, cliente.getEmail());
				statement.setString(3, cliente.getAddress());
				statement.setString(4, cliente.getPhone());
				statement.setString(5, cliente.getPassword());
				
				rows = statement.executeUpdate();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return rows > 0;
	}

	@Override
	public Cliente getByEmail(String email) {
		Cliente user = null;
		try ( Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(GET_BY_EMAIL)) {
			
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				user = new Cliente(resultSet.getString("nome"), resultSet.getString("endereco"), resultSet.getString("email"), resultSet.getString("telefone"), resultSet.getString("senha"), false);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
			user = null;
		} 
			
		return user;
	}
}