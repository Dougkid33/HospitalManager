package model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.ConnectionFactory;

import model.Pessoa;

public class PessoaDAO {

    private Connection conexao = null;

    public PessoaDAO() {
        this.conexao = ConnectionFactory.getConnection();
    }
    private static List<Pessoa> pessoas = new ArrayList<>();

    public boolean cadastraPessoa(Pessoa pessoa) {

        String sql = "insert into pessoas "
                + "(nome, endereco, cpf, telefone, login,"
                + "senha, tipoUsuario, dataCriacao, dataModificacao)"
                + " values (?,?,?,?,?,?,?,?, ?)";

        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //seta os valores
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getEndereco());
            stmt.setString(3, pessoa.getCpf());
            stmt.setString(4, pessoa.getTelefone());
            stmt.setString(5, pessoa.getLogin());
            stmt.setString(6, pessoa.getSenha());
            stmt.setString(7, pessoa.getTipoUsuario());
            stmt.setDate(8, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setDate(9, java.sql.Date.valueOf(LocalDate.now()));
            stmt.execute();
            // Obtém o valor do id gerado pelo banco de dados
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                pessoa.setId(id);
            }

            // Adicionar a pessoa ao ArrayList
            PessoaDAO.pessoas.add(pessoa);
            System.out.println("Pessoa adicionada com sucesso.");
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar pessoa!");
            throw new RuntimeException(e);
        }
    }

    public boolean removePessoa(int id) {
        String sql = "DELETE FROM pessoas WHERE id = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();

            // Remover a pessoa do ArrayList
            for (int i = 0; i < pessoas.size(); i++) {
                Pessoa pessoa = pessoas.get(i);
                if (pessoa.getId() == id) {
                    pessoas.remove(i);
                    break;
                }
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao remover pessoa!");
        }
        return false;
    }

    public static Pessoa buscarPessoaPorCpf(String cpf) {
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getCpf().equals(cpf)) {
                return pessoa;
            }
        }
        return null;
    }

    public Pessoa buscarPorId(int id) {
        Pessoa pessoaArray = null;
        Pessoa pessoaBD = null;

        // Procurar a pessoa no array
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getId() == id) {
                pessoaArray = pessoa;
                break;
            }
        }

        // Procurar a pessoa no banco de dados
        String sql = "SELECT * FROM pessoas WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int idBD = rs.getInt("id");
                String nomeBD = rs.getString("nome");
                //pessoaBD = new Pessoa(idBD, nomeBD);

                // Atualizar o array caso os dados estejam desatualizados
                if (!pessoaBD.equals(pessoaArray)) {
                    int index = pessoas.indexOf(pessoaArray);
                    if (index != -1) {
                        pessoas.set(index, pessoaBD);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar pessoa por ID!");
            e.printStackTrace();
        }

        return pessoaArray;
    }

    public Pessoa buscarPessoaPorLogin(String login) {
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getLogin().equals(login)) {
                return pessoa;
            }
        }
        return null;
    }
    
    public boolean editarPessoa(Pessoa pessoa) {
        String sql = "UPDATE pessoas SET nome = ? WHERE id = ?";

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, pessoa.getNome());
            statement.setInt(2, pessoa.getId());
            int rowsUpdated = statement.executeUpdate();

            // Atualizar a pessoa no ArrayList
            for (int i = 0; i < pessoas.size(); i++) {
                Pessoa p = pessoas.get(i);
                if (p.getId() == pessoa.getId()) {
                    pessoas.set(i, pessoa);
                    break;
                }
            }

            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao editar pessoa!");
            e.printStackTrace();
            return false;
        }
    }

    public boolean altera(Pessoa pessoa) {
        try {
            PreparedStatement stmt = conexao.prepareStatement("update pessoas set endereco = ? where id = ?");
            stmt.setString(1, pessoa.getEndereco());
            stmt.setLong(2, pessoa.getId());

            stmt.execute();
            stmt.close();
            System.out.println("Elemento alterado com sucesso.");
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Pessoa> listarPessoas() {
        List<Pessoa> pessoas = new ArrayList<>();
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select * from pessoas");
            ResultSet rs;
            rs = stmt.executeQuery();
            // itera no ResultSet
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String endereco = rs.getString("endereco");
                String cpf = rs.getString("cpf");
                String telefone = rs.getString("telefone");
                String login = rs.getString("login");
                String tipoUsuario = rs.getString("tipoUsuario");
                Date dataCriacao = rs.getDate("DataCriacao");
                Date dataModificacao = rs.getDate("DataModificacao");

                Pessoa pessoa = new Pessoa();
                
                pessoa.setId(id);
                pessoa.setNome(nome);
                pessoa.setEndereco(endereco);
                pessoa.setCpf(cpf);
                pessoa.setTelefone(telefone);
                pessoa.setLogin(login);
                pessoa.setTipoUsuario(tipoUsuario);
                pessoa.setDataCriacao(dataCriacao);
                pessoa.setDataModificacao(dataModificacao);
                pessoas.add(pessoa);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pessoas;
    }

}
