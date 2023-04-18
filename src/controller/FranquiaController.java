package controller;

import model.Pessoa;
import model.DAO.FranquiaDao;
import model.Franquia;

public class FranquiaController {
    private FranquiaDao franquiaDao;

    public FranquiaController(FranquiaDao franquiaDao) {
        this.franquiaDao = franquiaDao;
    }

    public boolean cadastrarFranquia(String nome, String cnpj, String cidade, String endereco, Pessoa responsavel) {
        return franquiaDao.cadastrarFranquia(nome, cnpj, cidade, endereco, responsavel);
    }

    public boolean editarFranquia(int id, String novoNome, String novoCnpj, String novaCidade, String novoEndereco, Pessoa novoResponsavel) {
        return franquiaDao.editarFranquia(novoNome, novoCnpj, novaCidade, novoEndereco);
    }

    public boolean excluirFranquia(String cnpj) {
        return franquiaDao.excluirFranquia(cnpj);
    }

    public Franquia buscarFranquia(String cnpj) {
        return franquiaDao.buscarFranquia(cnpj);
    }
}
