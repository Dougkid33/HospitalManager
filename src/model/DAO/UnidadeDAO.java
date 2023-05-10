package model.DAO;

import java.util.Arrays;
import java.util.Date;


import model.Unidade;

public class UnidadeDAO {
	private static Unidade[] unidades = new Unidade[100]; // vetor para armazenar as unidades
	private static int count = 0; // contador para controlar o número de unidades cadastradas

	// método para cadastrar uma nova unidade
	public  boolean cadastrarUnidade(Unidade unidade) {
	    if (count >= unidades.length) {
	        unidades = Arrays.copyOf(unidades, unidades.length + 100);
	    }
	    unidade.setDataCriacaoUnidade(new Date());
	    unidades[count] = unidade;
	    count++;
	    return true;
	}

    // método para atualizar uma unidade existente
    public boolean atualizarUnidade(int idUnidade, String nome, String cidade, String endereco) {
        Unidade unidade = buscarUnidade(idUnidade);
        if (unidade == null) {
            return false; // Unidade não encontrada
        }

        unidade.setNome(nome);
        unidade.setCidade(cidade);
        unidade.setEnderecoUnidade(endereco);
        unidade.setDataModificacaoUnidade(new Date());

        for (int i = 0; i < UnidadeDAO.count; i++) {
            if (UnidadeDAO.unidades[i].getId() == idUnidade) {
                UnidadeDAO.unidades[i] = unidade;
                return true;
            }
        }
        return false;
    }

    // método para excluir uma unidade existente
    public boolean excluirUnidade(int idUnidade) {
        for (int i = 0; i < UnidadeDAO.count; i++) {
            if (UnidadeDAO.unidades[i].getId() == idUnidade) {
                UnidadeDAO.unidades[i] = null;
                for (int j = i; j < UnidadeDAO.count - 1; j++) {
                    UnidadeDAO.unidades[j] = UnidadeDAO.unidades[j + 1];
                }
                UnidadeDAO.count--;
                return true;
            }
        }
        return false;
    }

    // método para buscar uma unidade pelo ID
    public Unidade buscarUnidade(int idUnidade) {
        for (int i = 0; i < UnidadeDAO.count; i++) {
            if (UnidadeDAO.unidades[i].getIdUnidade() == idUnidade) {
                return UnidadeDAO.unidades[i];
            }
        }
        return null;
    }

    // método para buscar todas as unidades cadastradas
    public Unidade[] listarUnidades() {
        Unidade[] todasUnidades = new Unidade[UnidadeDAO.count];
        for (int i = 0; i < UnidadeDAO.count; i++) {
            todasUnidades[i] = UnidadeDAO.unidades[i];
        }
        return todasUnidades;
    }

//    // método para criar uma nova unidade
//    public static Unidade criarUnidade(String nome, String cnpj, String cidade, String endereco,
//            Pessoa responsavel, Date dataCriacao, Date dataModificacao, int idUnidade, String cidadeUnidade,
//            String enderecoUnidade, Pessoa responsavelUnidade, Date dataCriacaoUnidade, Date dataModificacaoUnidade) {
//        Unidade unidade = new Unidade(nome, cnpj, cidade, endereco, responsavel, dataCriacao, dataModificacao,
//                idUnidade, cidadeUnidade, enderecoUnidade, responsavelUnidade, dataCriacaoUnidade, dataModificacaoUnidade);
//        cadastrarUnidade(unidade);
//        return unidade;
//    }
}
