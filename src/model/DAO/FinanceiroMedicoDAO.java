package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.ConnectionFactory;
import model.FinanceiroMedico;
import model.enums.PgtoMedico;

public class FinanceiroMedicoDAO {
    private Connection conexao = null;

    public FinanceiroMedicoDAO() {
        this.conexao = ConnectionFactory.getConnection();
    }

    public void cadastrarFinanceiroMedico(double valorMedico, PgtoMedico estado, double franquia) {
        String sql = "INSERT INTO financeiro_medico (valor_medico, estado, franquia, descricao, data_criacao, data_modificacao) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setDouble(1, valorMedico);
            statement.setString(2, estado.name());
            statement.setDouble(3, franquia);
            statement.setString(4, "Descrição");
            statement.setObject(5, new Date());
            statement.setObject(6, new Date());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar financeiro médico: " + e.getMessage());
        }
    }

    public void atualizarFinanceiroMedico(int id, double valorMedico, PgtoMedico estado, double franquia) {
        String sql = "UPDATE financeiro_medico SET valor_medico = ?, estado = ?, franquia = ?, data_modificacao = ? WHERE id = ?";

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setDouble(1, valorMedico);
            statement.setString(2, estado.name());
            statement.setDouble(3, franquia);
            statement.setObject(4, new Date());
            statement.setInt(5, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar financeiro médico: " + e.getMessage());
        }
    }

    public void removerFinanceiroMedico(int id) {
        String sql = "DELETE FROM financeiro_medico WHERE id = ?";

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao remover financeiro médico: " + e.getMessage());
        }
    }

    public List<FinanceiroMedico> listarFinanceirosMedicos() {
        List<FinanceiroMedico> financeiros = new ArrayList<>();
        String sql = "SELECT * FROM financeiro_medico";

        try (PreparedStatement statement = conexao.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                FinanceiroMedico financeiro = criarFinanceiroMedico(resultSet);
                financeiros.add(financeiro);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar financeiros médicos: " + e.getMessage());
        }

        return financeiros;
    }

    private FinanceiroMedico criarFinanceiroMedico(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        double valorMedico = resultSet.getDouble("valor_medico");
        PgtoMedico estado = PgtoMedico.valueOf(resultSet.getString("estado"));
        double franquia = resultSet.getDouble("franquia");
        String descricao = resultSet.getString("descricao");
        Date dataCriacao = resultSet.getDate("data_criacao");
        Date dataModificacao = resultSet.getDate("data_modificacao");

        return new FinanceiroMedico(id, valorMedico, estado, franquia, descricao, dataCriacao, dataModificacao);
    }

    
    public double registrarMontantePagoAoMedico() {
        // Obtenha a data do último mês
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        Date dataUltimoMes = calendar.getTime();

        // Consulta SQL para obter o montante total pago ao médico no último mês
        String sql = "SELECT SUM(valor_medico) AS montante FROM financeiro_medico WHERE data_criacao >= ?";

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(dataUltimoMes.getTime()));
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                double montanteTotal = resultSet.getDouble("montante");
                return montanteTotal;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao registrar montante pago ao médico: " + e.getMessage());
        }

        return 0.0;
    }
    
    public double calcularValorPagoAoMedicoConsultas(double montanteTotal) {
        double valorPagoMedicoConsultas = montanteTotal * 0.7; // 70% do montante total
        return valorPagoMedicoConsultas;
    }
    
    public double calcularValorPagoAoMedicoProcedimentos(double montanteTotal) {
        double valorPagoMedicoProcedimentos = montanteTotal * 0.5; // 50% do montante total
        return valorPagoMedicoProcedimentos;
    }
    
    public double calcularValorPagoAdministradora(double faturamentoTotal) {
        double valorPagoAdministradora = 1000.0 + (faturamentoTotal * 0.05); // R$1000,00 + 5% do faturamento total
        return valorPagoAdministradora;
    }
}






