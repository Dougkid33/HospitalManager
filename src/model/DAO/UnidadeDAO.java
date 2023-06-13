package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.ConnectionFactory;
import model.Pessoa;
import model.Unidade;

public class UnidadeDAO {
	private Connection connection = null;
	
	public UnidadeDAO() {
		this.connection = ConnectionFactory.getConnection();
	}
	 private static List<Unidade> unidades = new ArrayList<>();

	// método para cadastrar uma nova unidade
	    public boolean cadastrarUnidade(Unidade unidade) {
	        String query = "INSERT INTO unidade (nome, cidade, endereco, dataCriacao) VALUES (?, ?, ?, ?)";
	        try (PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setString(1, unidade.getNome());
	            statement.setString(2, unidade.getCidade());
	            statement.setString(3, unidade.getEnderecoUnidade());
	            statement.setDate(4, new java.sql.Date(unidade.getDataCriacaoUnidade().getTime()));
	            statement.executeUpdate();
	            return true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }


	    // método para atualizar uma unidade existente
	    public boolean atualizarUnidade(Unidade unidade) {
	        String query = "UPDATE unidade SET nome=?, cidade=?, endereco=?, dataModificacao=? WHERE id=?";
	        try (PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setString(1, unidade.getNome());
	            statement.setString(2, unidade.getCidade());
	            statement.setString(3, unidade.getEnderecoUnidade());
	            statement.setDate(4, new java.sql.Date(unidade.getDataModificacaoUnidade().getTime()));
	            statement.setInt(5, unidade.getIdUnidade());
	            int rowsUpdated = statement.executeUpdate();
	            return rowsUpdated > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    // método para excluir uma unidade existente
	    public boolean excluirUnidade(int idUnidade) {
	        String query = "DELETE FROM unidade WHERE id=?";
	        try (PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setInt(1, idUnidade);
	            int rowsDeleted = statement.executeUpdate();
	            return rowsDeleted > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    public Unidade buscarUnidade(int idUnidade) {
	        String query = "SELECT * FROM unidade WHERE id=?";
	        try (PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setInt(1, idUnidade);
	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    int id = resultSet.getInt("id");
	                    String nome = resultSet.getString("nome");
	                    String cidade = resultSet.getString("cidade");
	                    String endereco = resultSet.getString("endereco");
	                    Date dataCriacao = resultSet.getDate("dataCriacao");
	                    Date dataModificacao = resultSet.getDate("data_modificacao");
	                    String cidadeUnidade = resultSet.getString("cidade_unidade");
	                    String enderecoUnidade = resultSet.getString("endereco_unidade");
	                    int responsavelUnidadeId = resultSet.getInt("responsavel_unidade_id");
	                    Date dataCriacaoUnidade = resultSet.getDate("dataCriacao");
	                    Date dataModificacaoUnidade = resultSet.getDate("dataModificacao");

	                    PessoaDAO pessoaDAO = new PessoaDAO();
	                    Pessoa responsavelUnidade = pessoaDAO.buscarPorId(responsavelUnidadeId);

	                    return new Unidade(nome, null, cidade, endereco, null, dataCriacao, dataModificacao,
	                            id, cidadeUnidade, enderecoUnidade, responsavelUnidade, dataCriacaoUnidade,
	                            dataModificacaoUnidade);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    // método para buscar todas as unidades cadastradas
	    public List<Unidade> listarUnidades() {
	        List<Unidade> unidades = new ArrayList<>();
	        String query = "SELECT * FROM unidade";
	        try (PreparedStatement statement = connection.prepareStatement(query);
	             ResultSet resultSet = statement.executeQuery(query)) {
	            while (resultSet.next()) {
	                int id = resultSet.getInt("id");
	                String nome = resultSet.getString("nome");
	                String cidade = resultSet.getString("cidade");
	                String endereco = resultSet.getString("endereco");
	                Date dataCriacao = resultSet.getDate("data_criacao");
	                Date dataModificacao = resultSet.getDate("data_modificacao");
	                String cidadeUnidade = resultSet.getString("cidade_unidade");
	                String enderecoUnidade = resultSet.getString("endereco_unidade");
	                int responsavelUnidadeId = resultSet.getInt("responsavel_unidade_id");
	                Date dataCriacaoUnidade = resultSet.getDate("dataCriacao");
	                Date dataModificacaoUnidade = resultSet.getDate("dataModificacao");

	                PessoaDAO pessoaDAO = new PessoaDAO();
	                Pessoa responsavelUnidade = pessoaDAO.buscarPorId(responsavelUnidadeId);

	                Unidade unidade = new Unidade(nome, null, cidade, endereco, null, dataCriacao, dataModificacao,
	                        id, cidadeUnidade, enderecoUnidade, responsavelUnidade, dataCriacaoUnidade,
	                        dataModificacaoUnidade);
	                unidades.add(unidade);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return unidades;
	    }


}
