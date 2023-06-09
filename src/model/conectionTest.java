package model;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe para testar a conexão com o banco de dados
 */
public class conectionTest {
    
    public static void main(String[] args){
        Connection connection = ConnectionFactory.getConnection();
        try (Statement statement = connection.createStatement()) {
            String sqlQuery = "SELECT * FROM pessoas";
            ResultSet res = statement.executeQuery(sqlQuery);

            while (res.next()) {
                int id = res.getInt("id");
                String nome = res.getString("nome");
                // ... processar os valores retornados da consulta
                System.out.println("ID: " + id + ", Nome: " + nome);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao executar consulta!");
            e.printStackTrace();
        }
    }
}  
