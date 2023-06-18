package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import controller.FinanceiroADMController;
import model.ConnectionFactory;
import model.InfoConsulta;
import model.Medico;
import model.Pessoa;
import model.Unidade;
import model.enums.EstadoConsulta;
import model.enums.TipoMovimento;

public class InfoConsultaDAO {
	private Connection conexao = null;

    public InfoConsultaDAO() {
        this.conexao = ConnectionFactory.getConnection();
    }
	private static List<InfoConsulta> infoConsultas = new ArrayList<>();
    private static int id = 1;

    public void cadastrarInfoConsulta(LocalDateTime data, String hora, EstadoConsulta estado, Medico medico, Pessoa paciente, double valor, Unidade unidade, String descricao) {
        String sql = "INSERT INTO info_consulta (data, hora, estado, medico_id, paciente_id, valor, unidade_id, descricao) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setObject(1, data);
            stmt.setString(2, hora);
            stmt.setString(3, estado.toString());
            stmt.setInt(4, medico.getId());
            stmt.setInt(5, paciente.getId());
            stmt.setDouble(6, valor);
            stmt.setInt(7, unidade.getId());
            stmt.setString(8, descricao);
            stmt.executeUpdate();

            InfoConsulta infoConsulta = new InfoConsulta(id++, data, hora, estado, medico, paciente, valor, unidade, descricao, null, null);
            infoConsultas.add(infoConsulta);

            double entradaFranquia = infoConsulta.calcularEntradaFranquia();
            FinanceiroADMController.cadastrarFinanceiro(TipoMovimento.ENTRADA, entradaFranquia, unidade.getNome(), "InfoConsulta #" + infoConsulta.getId());
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar a infoConsulta no banco de dados: " + e.getMessage());
        }
    }


    public void atualizarInfoConsulta(int id, String descricao) {
        String sql = "UPDATE info_consulta SET descricao = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, descricao);
            stmt.setInt(2, id);
            stmt.executeUpdate();

            for (InfoConsulta infoConsulta : infoConsultas) {
                if (infoConsulta.getId() == id) {
                    infoConsulta.setDescricao(descricao);
                    break;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar a infoConsulta no banco de dados: " + e.getMessage());
        }
    }

    public void removerInfoConsulta(int id) {
        String sql = "DELETE FROM info_consulta WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();

            infoConsultas.removeIf(infoConsulta -> infoConsulta.getId() == id);
        } catch (SQLException e) {
            System.out.println("Erro ao remover a infoConsulta no banco de dados: " + e.getMessage());
        }
    }

    public InfoConsulta buscarInfoConsulta(int id) {
        String sql = "SELECT * FROM info_consulta WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                LocalDateTime data = rs.getObject("data", LocalDateTime.class);
                String hora = rs.getString("hora");
                EstadoConsulta estado = EstadoConsulta.valueOf(rs.getString("estado"));
                int medicoId = rs.getInt("medico_id");
                int pacienteId = rs.getInt("paciente_id");
                double valor = rs.getDouble("valor");
                int unidadeId = rs.getInt("unidade_id");
                String descricao = rs.getString("descricao");
                
                MedicoDao medicoDao = new MedicoDao();
                Medico medico = medicoDao.buscarMedico(medicoId);
                
                PessoaDAO pessoaDAO = new PessoaDAO();
                Pessoa paciente = pessoaDAO.buscarPorId(pacienteId);
                
                UnidadeDAO unidadeDAO = new UnidadeDAO();
                Unidade unidade = unidadeDAO.buscarUnidade(unidadeId);

                return new InfoConsulta(id, data, hora, estado, medico, paciente, valor, unidade, descricao, null, null);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar a infoConsulta no banco de dados: " + e.getMessage());
        }
        return null;
    }

    public static List<InfoConsulta> listarInfoConsultas() {
        return infoConsultas;
    }

    public static List<InfoConsulta> listarInfoConsultasPorMedico(Medico medico) {
        List<InfoConsulta> infoConsultasMedico = new ArrayList<>();
        for (InfoConsulta infoConsulta : infoConsultas) {
            if (infoConsulta.getMedico().equals(medico)) {
                infoConsultasMedico.add(infoConsulta);
            }
        }
        return infoConsultasMedico;
    }
    
    public static List<InfoConsulta> listarInfoConsultasPorPessoa(Pessoa pessoa) {
        List<InfoConsulta> infoConsultasPessoa = new ArrayList<>();
        for (InfoConsulta infoConsulta : infoConsultas) {
            if (infoConsulta.getPaciente().equals(pessoa)) {
                infoConsultasPessoa.add(infoConsulta);
            }
        }
        return infoConsultasPessoa;
    }
}
