package br.edu.ifsp.MicroSaaS.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import br.edu.ifsp.MicroSaaS.dao.ServicoDAO;
import br.edu.ifsp.MicroSaaS.dao.connection.DatabaseConnection;
import br.edu.ifsp.MicroSaaS.model.Prestador;
import br.edu.ifsp.MicroSaaS.model.Servico;

public class ServicoDAOImpl implements ServicoDAO {
	private static final String INSERT = "INSERT INTO Servico (nome, descricao, id_prestador, status_servico, local) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_BY_ID = "SELECT * FROM Servico WHERE id_servico = ?";
	private static final String GET_PAGE = "SELECT * FROM Servico ORDER BY ? LIMIT ? OFFSET ?;";
	private static final String GET_BY_ID_PRESTADOR = "SELECT * FROM Servico WHERE id_prestador = ?";
	private static final String UPDATE = "UPDATE Servico SET nome = ?, descricao = ?, status_servico = ?, local = ? WHERE id_servico = ?;";
	private static final String DELETE = "DELETE FROM Servico WHERE id_servico = ?";

	@Override
	public boolean insert(Servico servico) {
		int rows = 0;
		if(servico != null) {
			try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT)){
				
				statement.setString(1, servico.getName());
				statement.setString(2, servico.getDescription());
				statement.setString(3, Integer.toString(servico.getId_prestador()));
				statement.setString(4, Integer.toString(servico.getStatus_servico()));
				statement.setString(5, servico.getLocal());
				
				rows = statement.executeUpdate();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return rows > 0;
	}

	@Override
	public boolean update(Servico servico_atual, Servico servico_novo) {
		int rows = 0;
		if(servico_atual != null && servico_novo != null) {
			try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE)){
				
				statement.setString(1, servico_novo.getName());
				statement.setString(2, servico_novo.getDescription());
				statement.setString(3, Integer.toString(servico_novo.getStatus_servico()));
				statement.setString(4, servico_novo.getLocal());
				statement.setString(5, Integer.toString(servico_atual.getId()));
				
				rows = statement.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return rows > 0;
	}

	@Override
	public boolean delete(Servico servico) {
		int rows = 0;
		if (servico != null) {
			try( Connection connection = DatabaseConnection.getConnection();
				 PreparedStatement statement = connection.prepareStatement(DELETE)){
				
				statement.setString(1, Integer.toString(servico.getId()));
				
				rows = statement.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rows > 0;
	}

	@Override
	public Servico getById(int id) {
		Servico servico = null;
		try ( Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
			
			statement.setString(1, Integer.toString(id));
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				servico = new Servico(resultSet.getInt("id_prestador"), resultSet.getString("nome"), resultSet.getString("descricao"), resultSet.getInt("status_servico"), resultSet.getString("local"));
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
			servico = null;
		} 
			
		return servico;
	}

	@Override
	public List<Servico> getByIdPrestador(int id) {
		List<Servico> list = new ArrayList<>();
		try {
			Connection connection = DatabaseConnection.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(GET_BY_ID_PRESTADOR);
			
			statement.setString(1, Integer.toString(id));
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Servico servico = new Servico(resultSet.getInt("id_servico"), resultSet.getInt("id_prestador"), resultSet.getString("nome"), resultSet.getString("descricao"), resultSet.getInt("status_servico"), resultSet.getString("local"));
				
				list.add(servico);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Servico> getPage(int page, int qtd) {
		List<Servico> list = new ArrayList<>();
		try {
			Connection connection = DatabaseConnection.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(GET_PAGE);
			
			statement.setString(1, "id_servico");
			statement.setInt(2, qtd);
			statement.setInt(3, page);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Servico servico = new Servico(resultSet.getInt("id_servico"), resultSet.getInt("id_prestador"), resultSet.getString("nome"), resultSet.getString("descricao"), resultSet.getInt("status_servico"), resultSet.getString("local"));
				list.add(servico);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
