package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Medico;
import model.ConnectionFactory;

public class MedicoDao {

    public boolean cadastrarMedico(String nome, String endereco, String cpf, String telefone, String login,
            String senha, String tipoUsuario, int crm, String especialidade) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO medicos (nome, endereco, cpf, telefone, login, senha, crm, especialidade) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
            statement.setString(1, nome);
            statement.setString(2, endereco);
            statement.setString(3, cpf);
            statement.setString(4, telefone);
            statement.setString(5, login);
            statement.setString(6, senha);
            statement.setInt(7, crm);
            statement.setString(8, especialidade);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar médico!");
            e.printStackTrace();
            return false;
        }
    }

    public boolean editarMedico(int id, String login, String novoNome, String novoEndereco, String novoCpf, String novoTelefone, int novoCrm, String novaEspecialidade) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE medicos SET nome = ?, endereco = ?, cpf = ?, telefone = ?, crm = ?, especialidade = ? WHERE id = ?")) {
            statement.setString(1, novoNome);
            statement.setString(2, novoEndereco);
            statement.setString(3, novoCpf);
            statement.setString(4, novoTelefone);
            statement.setInt(5, novoCrm);
            statement.setString(6, novaEspecialidade);
            statement.setInt(7, id);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao editar médico!");
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluirMedico(int id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM medicos WHERE id = ?")) {
            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir médico!");
            e.printStackTrace();
            return false;
        }
    }

    public Medico buscarMedico(int id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM medicos WHERE id = ?")) {
            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return criarMedicoAPartirDoResultSet(result);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar médico!");
            e.printStackTrace();
        }
        return null; // Médico não encontrado
    }

    public Medico buscarMedicoPorCRM(int crm) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM medicos WHERE crm = ?")) {
            statement.setInt(1, crm);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return criarMedicoAPartirDoResultSet(result);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar médico por CRM!");
            e.printStackTrace();
        }
        return null; // Médico não encontrado
    }

    public List<Medico> listarMedicos() {
        List<Medico> medicos = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM medicos")) {

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    Medico medico = criarMedicoAPartirDoResultSet(result);
                    medicos.add(medico);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar médicos!");
            e.printStackTrace();
        }

        return medicos;
    }

    private Medico criarMedicoAPartirDoResultSet(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String nome = result.getString("nome");
        String endereco = result.getString("endereco");
        String cpf = result.getString("cpf");
        String telefone = result.getString("telefone");
        String login = result.getString("login");
        String senha = result.getString("senha");
        int crm = result.getInt("crm");
        Date dataCriacao = result.getDate("dataCriacao");
        Date dataModificacao = result.getDate("dataModificacao");
        String especialidade = result.getString("especialidade");

        return new Medico(id, nome, endereco, cpf, telefone, login, senha, crm, dataCriacao, dataModificacao, especialidade);
    }
}
