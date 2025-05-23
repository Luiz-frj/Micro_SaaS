package br.edu.ifsp.MicroSaaS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.MicroSaaS.dao.connection.DatabaseConnection;
import br.edu.ifsp.MicroSaaS.model.Portifolio;
import br.edu.ifsp.MicroSaaS.model.Servico;

public class PortifolioDAOImpl implements PortifolioDAO {
	private static final String INSERT = "INSERT INTO Portifolio (id_servico, caminho_img) VALUES (?, ?)";
	private static final String GET_BY_ID = "SELECT * FROM Portifolio WHERE id_img = ?";
	private static final String UPDATE = "UPDATE Portifolio SET id_servico = ?, caminho_img = ? WHERE id_img = ?;";
	private static final String GET_BY_ID_SERVICO = "SELECT * FROM Portifolio WHERE id_servico = ?";

	@Override
	public boolean insert(Portifolio portifolio) {
		int rows = 0;
		if(portifolio != null) {
			try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT)){
				
				statement.setString(1, Integer.toString(portifolio.getId_servico()));
				statement.setString(2, portifolio.getCaminho_img());
				
				rows = statement.executeUpdate();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return rows > 0;
	}

	@Override
	public boolean update(Portifolio portifolio_atual, Portifolio portifolio_novo) {
		int rows = 0;
		if(portifolio_atual != null && portifolio_novo != null) {
			try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE)){
				
				statement.setString(1, Integer.toString(portifolio_novo.getId_servico()));
				statement.setString(2, portifolio_novo.getCaminho_img());
				statement.setString(3, Integer.toString(portifolio_atual.getId_img()));
				
				rows = statement.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return rows > 0;
	}

	@Override
	public Portifolio getById(int id_img) {
		Portifolio portifolio = null;
		try ( Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
			
			statement.setString(1, Integer.toString(id_img));
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				portifolio = new Portifolio(resultSet.getInt("id_img"), resultSet.getInt("id_servico"), resultSet.getString("caminho_img"));
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
			portifolio = null;
		} 
			
		return portifolio;
	}

	@Override
	public List<Portifolio> getByIdServico(int id_servico) {
		List<Portifolio> list = new ArrayList<>();
		try {
			Connection connection = DatabaseConnection.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(GET_BY_ID_SERVICO);
			
			statement.setString(1, Integer.toString(id_servico));
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Portifolio portifolio = new Portifolio(resultSet.getInt("id_img"), resultSet.getInt("id_servico"), resultSet.getString("caminho_img"));
				
				list.add(portifolio);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
