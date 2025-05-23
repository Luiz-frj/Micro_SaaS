package br.edu.ifsp.MicroSaaS.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.MicroSaaS.dao.EspecialidadeDAO;
import br.edu.ifsp.MicroSaaS.dao.connection.DatabaseConnection;
import br.edu.ifsp.MicroSaaS.model.Especialidade;
import br.edu.ifsp.MicroSaaS.model.Prestador;
import br.edu.ifsp.MicroSaaS.model.Servico;

public class EspecialidadeDAOImpl implements EspecialidadeDAO {
	private static final String INSERT = "INSERT INTO Especialidade (nome, descricao) VALUES (?, ?)";
	private static final String GET_BY_NAME = "SELECT * FROM Especialidade WHERE nome = ?";
	private static final String GET_BY_ID = "SELECT * FROM Especialidade WHERE id = ?";
	private static final String GET_BY_ID_PRESTADOR = "SELECT * FROM PrestadorEspecialidade WHERE id_prestador = ?";
	private static final String GET_PRESTADOR_BY_ID_ESPECIALIDADE = "SELECT * FROM Prestador p\r\n"
			+ "INNER JOIN PrestadorEspecialidade pe ON p.id_prestador=pe.id_prestador;";

	@Override
	public boolean insert(Especialidade especialidade) {
		int rows = 0;
		if(especialidade != null) {
			try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT)){
				
				statement.setString(1, especialidade.getName());
				statement.setString(2, especialidade.getDescription());
				
				rows = statement.executeUpdate();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return rows > 0;
	}

	@Override
	public List<Especialidade> getByName(String name) {
		List<Especialidade> list = new ArrayList<>();
		try {
			Connection connection = DatabaseConnection.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(GET_BY_NAME);
			
			statement.setString(1, name);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Especialidade especialidade = new Especialidade(resultSet.getInt("id_especialidade"), resultSet.getString("nome"), resultSet.getString("descricao"));
				
				list.add(especialidade);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Especialidade> getByIdPrestador(int id) {
		List<Especialidade> list = new ArrayList<>();
		try {
			Connection connection = DatabaseConnection.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(GET_BY_ID_PRESTADOR);
			
			statement.setString(1, Integer.toString(id));
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Especialidade especialidade = new Especialidade(resultSet.getInt("id_especialidade"), resultSet.getString("nome"), resultSet.getString("descricao"));
				
				list.add(especialidade);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Prestador> getByEspecialidade(Especialidade especialidade) {
		List<Prestador> list = new ArrayList<>();
		try {
			Connection connection = DatabaseConnection.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(GET_PRESTADOR_BY_ID_ESPECIALIDADE);
			
			statement.setString(1, Integer.toString(especialidade.getId()));
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Prestador prestador = new Prestador(resultSet.getInt("id_prestador"), resultSet.getString("nome"), resultSet.getString("usuário"), resultSet.getString("email"), resultSet.getString("telefone"), resultSet.getString("senha"), resultSet.getString("cpf"), resultSet.getString("caminho_img"), false);
				
				list.add(prestador);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Especialidade getById(int id) {
		Especialidade especialidade = null;
		try ( Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
			
			statement.setString(1, Integer.toString(id));
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				especialidade = new Especialidade(resultSet.getInt("id_especialidade"), resultSet.getString("nome"), resultSet.getString("descricao"));
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
			especialidade = null;
		} 
			
		return especialidade;
	}

}
