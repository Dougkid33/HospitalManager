package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.ConnectionFactory;
import model.FinanceiroADM;
import model.Franquia;
import model.Unidade;
import model.enums.TipoMovimento;

public class FinanceiroADMDAO {
    private Connection conexao = null;

    public FinanceiroADMDAO() {
        this.conexao = ConnectionFactory.getConnection();
    }

    public void cadastrarFinanceiro(TipoMovimento tipoMovimento, double valor, Unidade unidade, String descritivoMovimento) {
        String sql = "INSERT INTO financeiroadm (tipoMovimento, valor, unidade, descritivoMovimento, dataCriacao, dataModificacao) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        
        UnidadeDAO unidadeDAO = new UnidadeDAO();
        Unidade unidadecad =  unidadeDAO.buscarUnidade(unidade.getIdUnidade());            
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, tipoMovimento.name());
            statement.setDouble(2, valor);
            statement.setInt(3, unidadecad.getIdUnidade());
            System.out.println("ID da unidade " + unidadecad.getIdUnidade());
            statement.setString(4, descritivoMovimento);
            LocalDateTime now = LocalDateTime.now();
            java.sql.Timestamp dateNow = java.sql.Timestamp.valueOf(now);
            statement.setTimestamp(5, dateNow);
            statement.setTimestamp(6, dateNow);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar financeiro: " + e.getMessage());
        }
    }

    public void atualizarFinanceiro(int id, TipoMovimento tipoMovimento, double valor, int unidade, String descritivoMovimento) {
        String sql = "UPDATE financeiroadm SET tipoMovimento = ?, valor = ?, unidade = ?, descritivoMovimento = ?, dataModificacao = ? WHERE id = ?";

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, tipoMovimento.name());
            statement.setDouble(2, valor);
            statement.setInt(3, unidade);
            statement.setString(4, descritivoMovimento);
            statement.setObject(5, new Date());
            statement.setInt(6, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar financeiro: " + e.getMessage());
        }
    }

    public void removerFinanceiro(int id) {
        String sql = "DELETE FROM financeiroadm WHERE id = ?";

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao remover financeiro: " + e.getMessage());
        }
    }

    public List<FinanceiroADM> listarFinanceiros() {
        List<FinanceiroADM> financeiros = new ArrayList<>();
        String sql = "SELECT * FROM financeiroadm";

        try (PreparedStatement statement = conexao.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                FinanceiroADM financeiro = criarFinanceiroADM(resultSet);
                financeiros.add(financeiro);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar financeiros: " + e.getMessage());
        }

        return financeiros;
    }
    
    public FinanceiroADM buscarFinanceiroPorId(int id) {
        String sql = "SELECT * FROM financeiroadm WHERE id = ?";

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return criarFinanceiroADM(resultSet);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar financeiro por ID: " + e.getMessage());
        }

        return null;
    }

    private FinanceiroADM criarFinanceiroADM(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        TipoMovimento tipoMovimento = TipoMovimento.valueOf(resultSet.getString("tipoMovimento"));
        double valor = resultSet.getDouble("valor");
        int unidadeId = resultSet.getInt("unidade");
        String descritivoMovimento = resultSet.getString("descritivoMovimento");
        Date dataCriacao = resultSet.getDate("dataCriacao");
        Date dataModificacao = resultSet.getDate("dataModificacao");

        UnidadeDAO unidadeDAO = new UnidadeDAO();
        Unidade unidade = unidadeDAO.buscarUnidade(unidadeId);

        return new FinanceiroADM(id, tipoMovimento, valor, unidade, descritivoMovimento, dataCriacao, dataModificacao);
    }
}