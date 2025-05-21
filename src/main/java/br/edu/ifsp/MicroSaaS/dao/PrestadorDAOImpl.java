package br.edu.ifsp.MicroSaaS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.MicroSaaS.dao.connection.DatabaseConnection;
import br.edu.ifsp.MicroSaaS.model.Cliente;
import br.edu.ifsp.MicroSaaS.model.Prestador;

public class PrestadorDAOImpl implements PrestadorDAO {
	private static final String INSERT = "INSERT INTO Prestador (nome, email, usuário, telefone, senha, cpf, caminho_img) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_BY_EMAIL = "SELECT * FROM Prestador WHERE email = ?";
	
	@Override
	public boolean insert(Prestador prestador) {
		int rows = 0;
		if(prestador != null) {
			try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT)){
				
				statement.setString(1, prestador.getName());
				statement.setString(2, prestador.getEmail());
				statement.setString(3, prestador.getUser());
				statement.setString(4, prestador.getPhone());
				statement.setString(5, prestador.getPassword());
				statement.setString(6, prestador.getCpf());
				statement.setString(7, prestador.getImg_dir());
				
				rows = statement.executeUpdate();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return rows > 0;
	}

	@Override
	public Prestador getByEmail(String email) {
		Prestador user = null;
		try ( Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(GET_BY_EMAIL)) {
			
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				user = new Prestador(resultSet.getString("nome"), resultSet.getString("usuário"), resultSet.getString("email"), resultSet.getString("telefone"), resultSet.getString("senha"), resultSet.getString("cpf"), resultSet.getString("caminho_img"), false);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
			user = null;
		} 
			
		return user;
	}
}
