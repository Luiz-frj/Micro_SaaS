package br.edu.ifsp.MicroSaaS.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.MicroSaaS.dao.DisponibilidadeDAO;
import br.edu.ifsp.MicroSaaS.dao.connection.DatabaseConnection;
import br.edu.ifsp.MicroSaaS.model.Disponibilidade;
import br.edu.ifsp.MicroSaaS.model.Servico;

public class DisponibilidadeDAOImpl implements DisponibilidadeDAO {
	private static final String INSERT = "INSERT INTO Disponibilidade (id_servico, dia_semana, comeco_descanso, fim_descanso, inicio_servico, fim_servico) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_BY_ID = "SELECT * FROM Disponibilidade WHERE id_disponibilidade = ?";
	private static final String GET_BY_ID_SERVICO = "SELECT * FROM Disponibilidade WHERE id_servico = ?";
	private static final String UPDATE = "UPDATE Disponibilidade SET dia_semana = ?, comeco_descanso = ?, fim_descanso = ?, inicio_servico = ?, fim_servico = ? WHERE id_disponibilidade = ?;";
	private static final String DELETE = "DELETE FROM Disponibilidade WHERE id_disponibilidade = ?";

	@Override
	public boolean insert(Disponibilidade disponibilidade) {
		int rows = 0;
		if(disponibilidade != null) {
			try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT)){
				
				statement.setString(1, Integer.toString(disponibilidade.getId_servico()));
				statement.setString(2, Integer.toString(disponibilidade.getDia_semana()));
				statement.setString(3, disponibilidade.getComeco_descanso());
				statement.setString(4, disponibilidade.getFim_descanso());
				statement.setString(5, disponibilidade.getInicio_servico());
				statement.setString(6, disponibilidade.getFim_servico());
				
				rows = statement.executeUpdate();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return rows > 0;
	}

	@Override
	public boolean update(Disponibilidade disponibilidade_atual, Disponibilidade disponibilidade_novo) {
		int rows = 0;
		if(disponibilidade_atual != null && disponibilidade_novo != null) {
			try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE)){
				
				statement.setString(1, Integer.toString(disponibilidade_novo.getDia_semana()));
				statement.setString(2, disponibilidade_novo.getComeco_descanso());
				statement.setString(3, disponibilidade_novo.getFim_descanso());
				statement.setString(4, disponibilidade_novo.getInicio_servico());
				statement.setString(5, disponibilidade_novo.getFim_servico());
				statement.setString(5, Integer.toString(disponibilidade_atual.getId()));
				
				rows = statement.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return rows > 0;
	}

	@Override
	public Disponibilidade getById(int id) {
		Disponibilidade disponibilidade = null;
		try ( Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
				
				statement.setString(1, Integer.toString(id));
				ResultSet resultSet = statement.executeQuery();
				if (resultSet.next()) {
					disponibilidade = new Disponibilidade(resultSet.getInt("id_disponibilidade"), resultSet.getInt("id_servico"), resultSet.getInt("dia_semana"), resultSet.getString("comeco_descanso"), resultSet.getString("fim_descanso"), resultSet.getString("inicio_servico"), resultSet.getString("fim_servico"));
				}
					
			} catch (SQLException e) {
				e.printStackTrace();
				disponibilidade = null;
			} 
				
			return disponibilidade;
	}

	@Override
	public List<Disponibilidade> getByIdServico(int id_servico) {
		List<Disponibilidade> list = new ArrayList<>();
		try {
			Connection connection = DatabaseConnection.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(GET_BY_ID_SERVICO);
			
			statement.setString(1, Integer.toString(id_servico));
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Disponibilidade disponibilidade = new Disponibilidade(resultSet.getInt("id_disponibilidade"), resultSet.getInt("id_servico"), resultSet.getInt("dia_semana"), resultSet.getString("comeco_descanso"), resultSet.getString("fim_descanso"), resultSet.getString("inicio_servico"), resultSet.getString("fim_servico"));
				
				list.add(disponibilidade);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean delete(Disponibilidade disponibilidade) {
		int rows = 0;
		if (disponibilidade != null) {
			try( Connection connection = DatabaseConnection.getConnection();
				 PreparedStatement statement = connection.prepareStatement(DELETE)){
				
				statement.setString(1, Integer.toString(disponibilidade.getId()));
				
				rows = statement.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rows > 0;
	}

}
