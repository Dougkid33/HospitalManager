package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import controller.FinanceiroADMController;
import java.time.ZoneOffset;

import model.ConnectionFactory;
import model.Consulta;
import model.Medico;
import model.Pessoa;
import model.Unidade;
import model.enums.EstadoConsulta;
import model.enums.TipoMovimento;

public class ConsultaDAO {
	
	private Connection conexao = null;

    public ConsultaDAO() {
        this.conexao = ConnectionFactory.getConnection();
    }

    private static List<Consulta> consultas = new ArrayList<>();
    
    
    public void cadastrarConsulta(LocalDateTime data, String hora, EstadoConsulta estadoConsulta, Medico medico, Pessoa paciente, double valor, Unidade unidade) {
        String sql = "INSERT INTO consulta (data, hora, estado, medico_id, paciente_id, valor, unidade_id, data_cadastro, data_modificacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setObject(1, data);
            stmt.setString(2, hora);
            stmt.setString(3, estadoConsulta.toString());
            stmt.setInt(4, medico.getId());
            stmt.setInt(5, paciente.getId());
            stmt.setDouble(6, valor);
            stmt.setInt(7, unidade.getId());
            stmt.setObject(8, LocalDateTime.now());
            stmt.setObject(9, LocalDateTime.now());
            stmt.executeUpdate();
            
            Consulta consulta = new Consulta(); 
            double entradaFranquia = calcularEntradaFranquia(valor);
            FinanceiroADMController.cadastrarFinanceiro(TipoMovimento.ENTRADA, entradaFranquia, unidade.getNome(), "Consulta #" + consulta.getId());
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar a consulta no banco de dados: " + e.getMessage());
        }
    }



    private double calcularEntradaFranquia(double valor) {

    	return valor * 0.2;
	}



	public void atualizarConsulta(int id, LocalDateTime data, String hora, EstadoConsulta estado, Medico medico, Pessoa paciente, double valor, Unidade unidade) {
        String sql = "UPDATE consulta SET data = ?, hora = ?, estado = ?, medico_id = ?, paciente_id = ?, valor = ?, unidade_id = ?, data_modificacao = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setObject(1, data);
            stmt.setString(2, hora);
            stmt.setString(3, estado.toString());
            stmt.setInt(4, medico.getId());
            stmt.setInt(5, paciente.getId());
            stmt.setDouble(6, valor);
            stmt.setInt(7, unidade.getId());
            stmt.setObject(8, LocalDateTime.now());
            stmt.setInt(9, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar a consulta no banco de dados: " + e.getMessage());
        }
    }

    public static void removerConsulta(int id) {
        consultas.removeIf(consulta -> consulta.getId() == id);
    }

    public static Consulta buscarConsulta(int id) {
        for (Consulta consulta : consultas) {
            if (consulta.getId() == id) {
                return consulta;
            }
        }
        return null;
    }

    public static List<Consulta> listarConsultas() {
        return consultas;
    }

    public static List<Consulta> listarConsultasPorMedico(Medico medico) {
        List<Consulta> consultasMedico = new ArrayList<>();
        for (Consulta consulta : consultas) {
            if (consulta.getMedico().equals(medico)) {
                consultasMedico.add(consulta);
            }
        }
        return consultasMedico;
    }

    public static List<Consulta> pesquisarConsultasPorMedicoNoPeriodo(Medico medico, LocalDateTime dataInicio, LocalDateTime dataFim) {
        List<Consulta> consultasMedico = new ArrayList<>();
        for (Consulta consulta : consultas) {
            LocalDateTime dataConsulta = consulta.getData();
            if (consulta.getMedico().equals(medico) && dataConsulta.isAfter(dataInicio) && dataConsulta.isBefore(dataFim)) {
                consultasMedico.add(consulta);
            }
        }
        return consultasMedico;
    }

    public static List<Consulta> pesquisarConsultasPorMedicoNoPeriodo(Medico medico, long dataInicio, long dataFim) {
        List<Consulta> consultasMedico = new ArrayList<>();
        for (Consulta consulta : consultas) {
            LocalDateTime dataConsulta = consulta.getData();
            if (consulta.getMedico().equals(medico) && dataConsulta.toEpochSecond(ZoneOffset.UTC) >= dataInicio && dataConsulta.toEpochSecond(ZoneOffset.UTC) <= dataFim) {
                consultasMedico.add(consulta);
            }
        }
        return consultasMedico;
    }

    public static List<Consulta> listarConsultasPorPaciente(Pessoa paciente) {
        List<Consulta> consultasPaciente = new ArrayList<>();
        for (Consulta consulta : consultas) {
            if (consulta.getPaciente().equals(paciente)) {
                consultasPaciente.add(consulta);
            }
        }
        return consultasPaciente;
    }

    public static List<Consulta> listarConsultasPorFiltro(LocalDateTime dataInicio, LocalDateTime dataFim, Medico medico) {
        List<Consulta> consultasFiltradas = new ArrayList<>();
        for (Consulta consulta : consultas) {
            LocalDateTime dataConsulta = consulta.getData();
            if (dataConsulta.compareTo(dataInicio) >= 0 && dataConsulta.compareTo(dataFim) <= 0 && consulta.getMedico().equals(medico)) {
                consultasFiltradas.add(consulta);
            }
        }
        return consultasFiltradas;
    }
}
