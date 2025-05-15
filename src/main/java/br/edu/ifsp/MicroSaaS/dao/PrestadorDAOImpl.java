package br.edu.ifsp.MicroSaaS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.MicroSaaS.dao.connection.DatabaseConnection;
import br.edu.ifsp.MicroSaaS.model.Prestador;

public class PrestadorDAOImpl implements PrestadorDAO {
	private static final String INSERT = "INSERT INTO Prestador (nome, email, usuário, telefone, senha, cpf, caminho_img) VALUES (?, ?, ?, ?, ?, ?, ?)";
	
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
	public Prestador getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
