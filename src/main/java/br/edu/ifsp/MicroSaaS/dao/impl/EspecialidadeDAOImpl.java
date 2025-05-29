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
	private static final String INSERT_SERVICO_ESPECIALIDADE = "INSERT INTO ServicoEspecialidade (id_servico, id_especialidade) VALUES (?,?)";
	private static final String GET_BY_NAME = "SELECT * FROM Especialidade WHERE LOWER(nome) = ?";
	private static final String GET_BY_ID = "SELECT * FROM Especialidade WHERE id = ?";
	private static final String GET_BY_ID_PRESTADOR = "SELECT * FROM PrestadorEspecialidade WHERE id_prestador = ?";
	private static final String GET_SERVICO_BY_ESPECIALIDADE_NAME = "SELECT * FROM Servico s\r\n"
			+ "INNER JOIN ServicoEspecialidade se ON s.id_servico=se.id_servico\r\n"
			+ "INNER JOIN Especialidade e ON se.id_especialidade=e.id_especialidade\r\n"
			+ "WHERE e.nome LIKE ?";

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
	public List<Servico> getByEspecialidade(String especialidade_name) {
		List<Servico> list = new ArrayList<>();
		try {
			Connection connection = DatabaseConnection.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(GET_SERVICO_BY_ESPECIALIDADE_NAME);
			
			statement.setString(1, "%" + especialidade_name + "%");
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Servico servico = new Servico(resultSet.getInt("id_servico"), resultSet.getInt("id_prestador"), resultSet.getString("nome"), resultSet.getString("descricao"), resultSet.getInt("status_servico"), resultSet.getString("local"), resultSet.getInt("tempo_servico"));
				
				list.add(servico);
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

	@Override
	public boolean insertServicoEspecialidade(Especialidade especialidade, int id_servico) {
		int rows = 0;
		if(especialidade != null) {
			try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_SERVICO_ESPECIALIDADE)){
				
				statement.setInt(1, id_servico);
				statement.setInt(2, especialidade.getId());
				
				rows = statement.executeUpdate();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return rows > 0;
	}

}
