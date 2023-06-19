package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.ConnectionFactory;
import model.Consulta;
import model.Procedimento;
import model.enums.EstadoProcedimento;

public class ProcedimentoDAO {
	private Connection conexao = null;

	public ProcedimentoDAO() {
		this.conexao = ConnectionFactory.getConnection();
	}

	private static List<Procedimento> procedimentos = new ArrayList<>();
	//private static int id = 1;

	public void cadastrarProcedimento(String nome, int idConsulta, LocalDateTime diaHorario,
	        EstadoProcedimento estado, double valor, String laudo) {
	    String sql = "INSERT INTO procedimento (nome, consulta_id, dia_horario, estado, valor, laudo, data_criacao, data_modificacao) "
	            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	    try (PreparedStatement statement = conexao.prepareStatement(sql)) {

	        statement.setString(1, nome);
	        statement.setInt(2, idConsulta);
	        statement.setObject(3, diaHorario);
	        statement.setString(4, estado.name());
	        statement.setDouble(5, valor);
	        statement.setString(6, laudo);
	        statement.setObject(7, LocalDateTime.now());
	        statement.setObject(8, LocalDateTime.now());

	        statement.executeUpdate();

	    } catch (SQLException e) {
	        System.out.println("Erro ao cadastrar procedimento: " + e.getMessage());
	    }
	}

	public void atualizarProcedimento(int id, String nome, Consulta consulta, LocalDateTime diaHorario,
			EstadoProcedimento estado, double valor, String laudo) {
		String sql = "UPDATE procedimento SET nome = ?, consulta_id = ?, dia_horario = ?, estado = ?, valor = ?, laudo = ?, "
				+ "data_modificacao = ? WHERE id = ?";

		try (PreparedStatement statement = conexao.prepareStatement(sql)) {

			statement.setString(1, nome);
			statement.setInt(2, consulta.getId());
			statement.setObject(3, diaHorario);
			statement.setString(4, estado.name());
			statement.setDouble(5, valor);
			statement.setString(6, laudo);
			statement.setObject(7, LocalDateTime.now());
			statement.setInt(8, id);

			statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Erro ao atualizar procedimento: " + e.getMessage());
		}
	}
	
    public void removerProcedimento(int id) {
        String sql = "DELETE FROM procedimento WHERE id = ?";

        try (
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao remover procedimento: " + e.getMessage());
        }
    }

    
    public Procedimento buscarProcedimento(int id) {
        String sql = "SELECT * FROM procedimento WHERE id = ?";

        try (
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int procedimentoId = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                int consultaId = resultSet.getInt("consulta_id");
                LocalDateTime diaHorario = resultSet.getObject("dia_horario", LocalDateTime.class);
                EstadoProcedimento estado = EstadoProcedimento.valueOf(resultSet.getString("estado"));
                double valor = resultSet.getDouble("valor");
                String laudo = resultSet.getString("laudo");
                LocalDateTime dataCriacao = resultSet.getObject("data_criacao", LocalDateTime.class);
                LocalDateTime dataModificacao = resultSet.getObject("data_modificacao", LocalDateTime.class);

                Consulta consulta = ConsultaDAO.buscarConsulta(consultaId);

                return new Procedimento(procedimentoId, nome, consulta, diaHorario, estado, valor, laudo, dataCriacao, dataModificacao);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar procedimento: " + e.getMessage());
        }

        return null;
    }
    public List<Procedimento> listarProcedimentos() {
        List<Procedimento> procedimentos = new ArrayList<>();
        String sql = "SELECT * FROM procedimento";

        try (
             PreparedStatement statement = conexao.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Procedimento procedimento = criarProcedimento(resultSet);
                procedimentos.add(procedimento);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar procedimentos: " + e.getMessage());
        }

        return procedimentos;
    }

    public List<Procedimento> listarProcedimentosPorConsulta(Consulta consulta) {
        List<Procedimento> procedimentosConsulta = new ArrayList<>();
        String sql = "SELECT * FROM procedimento WHERE consulta_id = ?";

        try (
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setInt(1, consulta.getId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Procedimento procedimento = criarProcedimento(resultSet);
                procedimentosConsulta.add(procedimento);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar procedimentos por consulta: " + e.getMessage());
        }

        return procedimentosConsulta;
    }

    public List<Procedimento> pesquisarProcedimentosPorMedicoNoPeriodo(Consulta consulta, LocalDateTime dataInicio, LocalDateTime dataFim) {
        List<Procedimento> procedimentosConsulta = new ArrayList<>();
        String sql = "SELECT * FROM procedimento WHERE consulta_id = ? AND dia_horario BETWEEN ? AND ?";

        try (
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setInt(1, consulta.getId());
            statement.setObject(2, dataInicio);
            statement.setObject(3, dataFim);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Procedimento procedimento = criarProcedimento(resultSet);
                procedimentosConsulta.add(procedimento);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar procedimentos por médico no período: " + e.getMessage());
        }

        return procedimentosConsulta;
    }

    private Procedimento criarProcedimento(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String nome = resultSet.getString("nome");
        int consultaId = resultSet.getInt("consulta_id");
        LocalDateTime diaHorario = resultSet.getObject("dia_horario", LocalDateTime.class);
        EstadoProcedimento estado = EstadoProcedimento.valueOf(resultSet.getString("estado"));
        double valor = resultSet.getDouble("valor");
        String laudo = resultSet.getString("laudo");
        LocalDateTime dataCriacao = resultSet.getObject("data_criacao", LocalDateTime.class);
        LocalDateTime dataModificacao = resultSet.getObject("data_modificacao", LocalDateTime.class);

        //ConsultaDAO consultaDAO = new ConsultaDAO();
        Consulta consulta = ConsultaDAO.buscarConsulta(consultaId);

        return new Procedimento(id, nome, consulta, diaHorario, estado, valor, laudo, dataCriacao, dataModificacao);
    }
}
