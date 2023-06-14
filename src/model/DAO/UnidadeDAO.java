package model.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.ConnectionFactory;
import model.Franquia;
import model.Pessoa;
import model.Unidade;

public class UnidadeDAO {
    private Connection connection = null;

    public UnidadeDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    // método para cadastrar uma nova unidade
    public boolean cadastrarUnidade(Unidade unidade) {
        FranquiaDao franquiaDao = new FranquiaDao();
        Franquia franquia = franquiaDao.buscarFranquia(unidade.getFranquia().getId());

        PessoaDAO pessoaDAO = new PessoaDAO();
        Pessoa pessoaResponsavel = pessoaDAO.buscarPorId(unidade.getResponsavel().getId());

        String query = "INSERT INTO unidade (nome, cidade, endereco, dataCriacao, responsavel, id_franquia) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, unidade.getNome());
            statement.setString(2, unidade.getCidade());
            statement.setString(3, unidade.getEndereco());
            statement.setTimestamp(4, Timestamp.valueOf(unidade.getDataCriacao()));
            statement.setInt(5, pessoaResponsavel.getId());
            statement.setInt(6, franquia.getId());

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizarUnidade(Unidade unidade) {
        String query = "UPDATE unidade SET nome=?, cidade=?, endereco=?, dataModificacao=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, unidade.getNome());
            statement.setString(2, unidade.getCidade());
            statement.setString(3, unidade.getEnderecoUnidade());
            statement.setTimestamp(4, Timestamp.valueOf(unidade.getDataModificacaoUnidade()));
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
                    LocalDateTime dataCriacao = resultSet.getTimestamp("dataCriacao").toLocalDateTime();
                    LocalDateTime dataModificacao = resultSet.getTimestamp("dataModificacao").toLocalDateTime();
                    String cidadeUnidade = resultSet.getString("cidade");
                    String enderecoUnidade = resultSet.getString("endereco");
                    int responsavelUnidadeId = resultSet.getInt("responsavel");
                    int franquiaUnidadeId = resultSet.getInt("id_franquia");
                    LocalDateTime dataCriacaoUnidade = resultSet.getTimestamp("dataCriacao").toLocalDateTime();
                    LocalDateTime dataModificacaoUnidade = resultSet.getTimestamp("dataModificacao").toLocalDateTime();

                    PessoaDAO pessoaDAO = new PessoaDAO();
                    Pessoa responsavelUnidade = pessoaDAO.buscarPorId(responsavelUnidadeId);
                    FranquiaDao franquiaDao = new FranquiaDao();
                    Franquia franquia = franquiaDao.buscarFranquia(franquiaUnidadeId);

                    return new Unidade(nome, null, cidade, endereco, null, dataCriacao, dataModificacao, id,
                            cidadeUnidade, enderecoUnidade, responsavelUnidade, franquia, dataCriacaoUnidade,
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
                LocalDateTime dataCriacao = resultSet.getTimestamp("dataCriacao").toLocalDateTime();
                LocalDateTime dataModificacao = resultSet.getTimestamp("dataModificacao").toLocalDateTime();
                String cidadeUnidade = resultSet.getString("cidade");
                String enderecoUnidade = resultSet.getString("endereco");
                int franquiaUnidadeId = resultSet.getInt("id_franquia");
                int responsavelUnidadeId = resultSet.getInt("responsavel");
                LocalDateTime dataCriacaoUnidade = resultSet.getTimestamp("dataCriacao").toLocalDateTime();
                LocalDateTime dataModificacaoUnidade = resultSet.getTimestamp("dataModificacao").toLocalDateTime();

                PessoaDAO pessoaDAO = new PessoaDAO();
                Pessoa responsavelUnidade = pessoaDAO.buscarPorId(responsavelUnidadeId);
                FranquiaDao franquiaDao = new FranquiaDao();
                Franquia franquia = franquiaDao.buscarFranquia(franquiaUnidadeId);

                Unidade unidade = new Unidade(nome, null, cidade, endereco, null, dataCriacao, dataModificacao, id,
                        cidadeUnidade, enderecoUnidade, responsavelUnidade, franquia, dataCriacaoUnidade,
                        dataModificacaoUnidade);
                unidades.add(unidade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return unidades;
    }
}
