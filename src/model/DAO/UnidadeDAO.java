package model.DAO;


import java.util.Date;

import model.Pessoa;
import model.Unidade;

public class UnidadeDAO {
    private static Unidade[] unidades = new Unidade[100]; // vetor para armazenar as unidades
    private static int count = 0; // contador para controlar o número de unidades cadastradas
    
    // método para cadastrar uma nova unidade
    public static boolean cadastrarUnidade(Unidade unidade) {
        if (count < unidades.length) {
            unidades[count] = unidade;
            count++;
            return true;
        } else {
            return false;
        }
    }
    
    // método para atualizar uma unidade existente
    public static boolean atualizarUnidade(Unidade unidade) {
        for (int i = 0; i < count; i++) {
            if (unidades[i].getIdUnidade() == unidade.getIdUnidade()) {
                unidades[i] = unidade;
                return true;
            }
        }
        return false;
    }
    
    // método para excluir uma unidade existente
    public static boolean excluirUnidade(int idUnidade) {
        for (int i = 0; i < count; i++) {
            if (unidades[i].getIdUnidade() == idUnidade) {
                for (int j = i; j < count - 1; j++) {
                    unidades[j] = unidades[j + 1];
                }
                unidades[count - 1] = null;
                count--;
                return true;
            }
        }
        return false;
    }
    
    // método para buscar uma unidade pelo ID
    public static Unidade buscarUnidade(int idUnidade) {
        for (int i = 0; i < count; i++) {
            if (unidades[i].getIdUnidade() == idUnidade) {
                return unidades[i];
            }
        }
        return null;
    }
    
    // método para buscar todas as unidades cadastradas
    public static Unidade[] buscarTodasUnidades() {
        Unidade[] todasUnidades = new Unidade[count];
        for (int i = 0; i < count; i++) {
            todasUnidades[i] = unidades[i];
        }
        return todasUnidades;
    }
    
    // método para criar uma nova unidade
    public static Unidade criarUnidade(int id, String nome, String cnpj, String cidade, String endereco, 
            Pessoa responsavel, Date dataCriacao, Date dataModificacao, int idUnidade, String cidadeUnidade, 
            String enderecoUnidade, Pessoa responsavelUnidade, Date dataCriacaoUnidade, Date dataModificacaoUnidade) {
        Unidade unidade = new Unidade(id, nome, cnpj, cidade, endereco, responsavel, dataCriacao, dataModificacao,
                idUnidade, cidadeUnidade, enderecoUnidade, responsavelUnidade, dataCriacaoUnidade, dataModificacaoUnidade);
        cadastrarUnidade(unidade);
        return unidade;
    }
}
