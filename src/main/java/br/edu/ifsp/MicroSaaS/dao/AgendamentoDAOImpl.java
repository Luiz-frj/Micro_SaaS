package br.edu.ifsp.MicroSaaS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.MicroSaaS.dao.connection.DatabaseConnection;
import br.edu.ifsp.MicroSaaS.model.Agendamento;
import br.edu.ifsp.MicroSaaS.model.Disponibilidade;
import br.edu.ifsp.MicroSaaS.model.Servico;

public class AgendamentoDAOImpl implements AgendamentoDAO {
	private static final String INSERT = "INSERT INTO Agendamento (id_servico, id_cliente, status_agendamento, horario) VALUES (?, ?, ?, ?)";
	private static final String GET_BY_ID = "SELECT * FROM Agendamento WHERE id_agendamento = ?";
	private static final String GET_BY_ID_SERVICO = "SELECT * FROM Agendamento WHERE id_servico = ?";
	private static final String GET_BY_ID_CLIENTE = "SELECT * FROM Agendamento WHERE id_cliente = ?";
	private static final String UPDATE = "UPDATE Agendamento SET status_agendamento = ?, horario = ? WHERE id_agendamento = ?;";

	@Override
	public boolean insert(Agendamento agendamento) {
		int rows = 0;
		if(agendamento != null) {
			try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT)){
				
				statement.setString(1, Integer.toString(agendamento.getId_servico()));
				statement.setString(2, Integer.toString(agendamento.getId_cliente()));
				statement.setString(3, agendamento.getStatus_agendamento());
				statement.setString(4, agendamento.getHorario());
				
				rows = statement.executeUpdate();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return rows > 0;
	}

	@Override
	public boolean update(Agendamento agendamento_atual, Agendamento agendamento_novo) {
		int rows = 0;
		if(agendamento_atual != null && agendamento_novo != null) {
			try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE)){
				
				statement.setString(1, agendamento_novo.getStatus_agendamento());
				statement.setString(2, agendamento_novo.getHorario());
				statement.setString(3, Integer.toString(agendamento_atual.getId()));
				
				rows = statement.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return rows > 0;
	}

	@Override
	public List<Agendamento> getByIdServico(int id_servico) {
		List<Agendamento> list = new ArrayList<>();
		try {
			Connection connection = DatabaseConnection.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(GET_BY_ID_SERVICO);
			
			statement.setString(1, Integer.toString(id_servico));
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Agendamento agendamento = new Agendamento(resultSet.getInt("id_agendamento"), resultSet.getInt("id_servico"), resultSet.getInt("id_cliente"), resultSet.getString("status_agendamento"), resultSet.getString("horario"));
				
				list.add(agendamento);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Agendamento> getByIdCliente(int id_cliente) {
		List<Agendamento> list = new ArrayList<>();
		try {
			Connection connection = DatabaseConnection.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(GET_BY_ID_CLIENTE);
			
			statement.setString(1, Integer.toString(id_cliente));
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Agendamento agendamento = new Agendamento(resultSet.getInt("id_agendamento"), resultSet.getInt("id_servico"), resultSet.getInt("id_cliente"), resultSet.getString("status_agendamento"), resultSet.getString("horario"));
				
				list.add(agendamento);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Agendamento getById(int id) {
		Agendamento agendamento = null;
		try ( Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
			
			statement.setString(1, Integer.toString(id));
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				agendamento = new Agendamento(resultSet.getInt("id_agendamento"), resultSet.getInt("id_servico"), resultSet.getInt("id_cliente"), resultSet.getString("status_agendamento"), resultSet.getString("horario"));
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
			agendamento = null;
		} 
			
		return agendamento;
	}

}
