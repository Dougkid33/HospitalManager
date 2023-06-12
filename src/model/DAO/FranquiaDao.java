package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.ConnectionFactory;

import model.Franquia;
import model.Pessoa;

public class FranquiaDao {
    private Connection connection = null;

    public FranquiaDao() {
        this.connection = ConnectionFactory.getConnection();
    }
    private static List<Franquia> franquias = new ArrayList<>();
    
    public boolean cadastrarFranquia(Franquia franquia) {
        
        String sql = "insert into franquias"
                + "(nome, cnpj, cidade, endereco, responsavel, dataCriacao, dataModificacao)"                
                + " values (?,?,?,?,?,?,?)";

        try {
            PreparedStatement stmt;
            stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            Pessoa responsavel = franquia.getResponsavel();//pegando responsável pela franquia
            //seta os valores
            stmt.setString(1, franquia.getNome());
            stmt.setString(2, franquia.getCnpj());
            stmt.setString(3, franquia.getCidade());
            stmt.setString(4, franquia.getEndereco());
            stmt.setInt(5, responsavel.getId());
            stmt.setDate(6, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setDate(7, java.sql.Date.valueOf(LocalDate.now()));
            stmt.execute();
            // Obtém o valor do id gerado pelo banco de dados
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                franquia.setId(id);
            }

            // Adicionar a pessoa ao ArrayList
            FranquiaDao.franquias.add(franquia);
            System.out.println("Franquia adicionada com sucesso.");
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar franquia!");
            throw new RuntimeException(e);
        }
    }

    public boolean editarFranquia(Franquia franquia) {
        String sql = "UPDATE franquias SET "
                + "nome = ?, "
                + "endereco = ?,"
                + "cnpj = ?,"
                + "cidade = ?,"
                + "idResponsavel = ?,"
                + "WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, franquia.getNome());
            stmt.setString(2, franquia.getEndereco());
            stmt.setString(3, franquia.getCnpj());
            stmt.setString(4, franquia.getCidade());
            stmt.setString(5, String.valueOf(franquia.getResponsavel()));
            stmt.setInt(6, franquia.getId());
            stmt.executeUpdate();

            // Atualizar a pessoa no ArrayList
            for (int i = 0; i < franquias.size(); i++) {
                Franquia f = franquias.get(i);
                if (f.getId() == franquia.getId()) {
                    franquias.set(i, franquia);
                    break;
                }
            }

            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao editar franquia!");
            e.printStackTrace();
            return false ;
        }
    }

    public boolean excluirFranquia(int id) {
       String sql = "DELETE FROM franquias WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();

            // Remover a pessoa do ArrayList
            for (int i = 0; i < franquias.size(); i++) {
                Franquia franquia = franquias.get(i);
                if (franquia.getId() == id) {
                    franquias.remove(i);
                    break;
                }
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao remover franquia!");
        }
        return false;
    }

    public Franquia buscarFranquia(int id) {
        String sql = "SELECT * FROM franquias WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Franquia franquia = new Franquia();
                franquia.setId(rs.getInt("id"));
                franquia.setNome(rs.getString("nome"));
                franquia.setEndereco(rs.getString("endereco"));
                franquia.setCnpj(rs.getString("cnpj"));
                franquia.setCidade(rs.getString("cidade"));
                franquia.setDataCriacao(rs.getDate("dataCriacao"));
                franquia.setDataModificacao(rs.getDate("dataModificacao"));
                
                return franquia;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar franquia por ID!");
        }
        return null;
    }

    public List<Franquia> listarFranquias() {
        List<Franquia> franquias = new ArrayList<>();
        try {
            PreparedStatement stmt;
            stmt = connection.prepareStatement("select * from franquias");
            ResultSet rs;
            rs = stmt.executeQuery();
            // itera no ResultSet
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String endereco = rs.getString("endereco");
                String cnpj = rs.getString("cnpj");
                Date dataCriacao = rs.getDate("DataCriacao");
                Date dataModificacao = rs.getDate("DataModificacao");

                Franquia f = new Franquia();
                
                f.setId(id);
                f.setNome(nome);
                f.setEndereco(endereco);
                f.setCnpj(cnpj);
                f.setDataCriacao(dataCriacao);
                f.setDataModificacao(dataModificacao);
                franquias.add(f);
            }
            return franquias;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
