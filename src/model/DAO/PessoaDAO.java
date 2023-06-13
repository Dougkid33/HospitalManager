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

    private Connection connection = ConnectionFactory.getConnection();

    public PessoaDAO() {
        this.connection = ConnectionFactory.getConnection();
    }
    private static List<Pessoa> pessoas = new ArrayList<>();

    public boolean cadastraPessoa(Pessoa pessoa) {

        String sql = "insert into pessoas "
                + "(nome, endereco, cpf, telefone, login,"
                + "senha, tipoUsuario, dataCriacao, dataModificacao)"
                + " values (?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement stmt;
            stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
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
    
    public int cadastrarPessoaMedico(Pessoa pessoa) {
        int id = 0;
                
        String sql = "insert into pessoas "
                + "(nome, endereco, cpf, telefone, login,"
                + "senha, tipoUsuario, dataCriacao, dataModificacao)"
                + " values (?,?,?,?,?,?,?,?, ?)";

        try {
            PreparedStatement stmt;
            stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
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
                id = rs.getInt(1);
                pessoa.setId(id);
            }

            // Adicionar a pessoa ao ArrayList
            PessoaDAO.pessoas.add(pessoa);
            System.out.println("Pessoa adicionada com sucesso.");
            return id;
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar pessoa!");
            throw new RuntimeException(e);
        }
    }
    
    public void resetarIdAuto() {
        List<String> tabelas = new ArrayList<>();
        tabelas.add("pessoas");
        tabelas.add("medicos");
        tabelas.add("franquias");
        tabelas.add("unidades");
        tabelas.add("unidades");
        tabelas.add("consultas");
        tabelas.add("procedimentos");
        tabelas.add("infoconsultas");
        tabelas.add("financeiromedico");
        tabelas.add("financeiroadm");
        
        for (String tabela : tabelas) {
            String sql = "ALTER TABLE " + tabela + " AUTO_INCREMENT = 1";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Erro ao resetar o ID AUTO_INCREMENT da tabela: " + tabela);
                e.printStackTrace();
                
            }
        }
    }

    public boolean removePessoa(int id) {
        String sql = "DELETE FROM pessoas WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
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
        String sql = "SELECT * FROM pessoas WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setEndereco(rs.getString("endereco"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setLogin(rs.getString("login"));
                pessoa.setSenha(rs.getString("senha"));
                pessoa.setTipoUsuario(rs.getString("tipoUsuario"));
                pessoa.setDataCriacao(rs.getDate("dataCriacao"));
                pessoa.setDataModificacao(rs.getDate("dataModificacao"));
                
                
                return pessoa;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar pessoa por ID!");
        }
        return null;
    }

    public Pessoa buscarPessoaPorLogin(String login) {
        String sql = "SELECT * FROM pessoas WHERE login = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa = buscarPorId(rs.getInt("id"));
//                pessoa.setId(rs.getInt("id"));
//                pessoa.setNome(rs.getString("login"));
//                pessoa.setNome(rs.getString("senha"));
                
                return pessoa;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar pessoa por ID!");
        }

//        for (Pessoa pessoa : pessoas) {
//            if (pessoa.getLogin().equals(login)) {
//                return pessoa;
//            }
//        }
        return null;
    }
    
    public boolean editarPessoa(Pessoa pessoa) {
        String sql = "UPDATE pessoas SET "
                + "nome = ?, "
                + "endereco = ?,"
                + "cpf = ?,"
                + "telefone = ?,"
                + "login = ?,"
                + "senha = ?,"
                + "tipoUsuario = ?,"
                + "dataModificacao = ?"
                + "WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getEndereco());
            stmt.setString(3, pessoa.getCpf());
            stmt.setString(4, pessoa.getTelefone());
            stmt.setString(5, pessoa.getLogin());
            stmt.setString(6, pessoa.getSenha());
            stmt.setString(7, pessoa.getTipoUsuario());
            stmt.setDate(8, (Date) pessoa.getDataModificacao());
            stmt.setInt(9, pessoa.getId());
            stmt.executeUpdate();
            // Atualizar a pessoa no ArrayList
            for (int i = 0; i < pessoas.size(); i++) {
                Pessoa p = pessoas.get(i);
                if (p.getId() == pessoa.getId()) {
                    pessoas.set(i, pessoa);
                    break;
                }
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao editar pessoa!");
            e.printStackTrace();
            return false;
        }
    }

    public boolean altera(Pessoa pessoa) {
        try {
            PreparedStatement stmt = connection.prepareStatement("update pessoas set endereco = ? where id = ?");
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
            stmt = connection.prepareStatement("select * from pessoas");
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
                String senha = rs.getString("senha");
                String tipoUsuario = rs.getString("tipoUsuario");
                Date dataCriacao = rs.getDate("DataCriacao");
                Date dataModificacao = rs.getDate("DataModificacao");

                Pessoa p = new Pessoa();
                
                p.setId(id);
                p.setNome(nome);
                p.setEndereco(endereco);
                p.setCpf(cpf);
                p.setTelefone(telefone);
                p.setLogin(login);
                p.setSenha(senha);
                p.setTipoUsuario(tipoUsuario);
                p.setDataCriacao(dataCriacao);
                p.setDataModificacao(dataModificacao);
                pessoas.add(p);
            }
            return pessoas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
