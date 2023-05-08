package model.DAO;

import java.util.Arrays;
import java.util.Date;

import model.Franquia;
import model.Pessoa;

public class FranquiaDao {

    private static Franquia[] franquias = new Franquia[100];
    private static int count = 0;

//    public FranquiaDao(int tamanho) {
//        franquias = new Franquia[tamanho];
//        qtdFranquias = 0;
//    }
    public boolean cadastrarFranquia(int id, String nome, String cnpj, String cidade, String endereco, Pessoa responsavelId) {

        if (FranquiaDao.count >= FranquiaDao.franquias.length) {
            FranquiaDao.franquias = Arrays.copyOf(FranquiaDao.franquias, FranquiaDao.franquias.length + 100);
        }
        Date dataCriacao = new Date();
        Franquia novaFranquia = new Franquia(nome, cnpj, cidade, endereco, responsavelId, dataCriacao, dataCriacao);
        FranquiaDao.franquias[FranquiaDao.count] = novaFranquia;
        FranquiaDao.count++;
        return true;
    }

    public boolean editarFranquia(int id, String cnpj, String novoNome, String novaCidade, String novoEndereco) {
        Franquia franquia = buscarFranquia(id);
        if (franquia == null) {
            return false; // Franquia não encontrada
        }
        franquia.setCnpj(cnpj);
        franquia.setNome(novoNome);
        franquia.setCidade(novaCidade);
        franquia.setEndereco(novoEndereco);
        franquia.setDataModificacao(new Date());
            
        for (int i = 0; i < FranquiaDao.count; i++) {
            if (FranquiaDao.franquias[i].getId() == id) {
                FranquiaDao.franquias[i] = franquia;
                return true;
            }
        }
        return true;
    }

    public boolean excluirFranquia(int idRemove) {
        for (int i = 0; i < FranquiaDao.count; i++) {
            if (FranquiaDao.franquias[i].getCnpj().equals(idRemove)) {
                FranquiaDao.franquias[i] = null;
                for (int j = i; j < FranquiaDao.count - 1; j++) {
                    FranquiaDao.franquias[j] = FranquiaDao.franquias[j + 1];
                }
                FranquiaDao.count--;
                return true;
            }
        }
        return false;
    }

    public Franquia buscarFranquia(int id) {
        for (int i = 0; i < FranquiaDao.count; i++) {
            if (FranquiaDao.franquias[i].getCnpj().equals(id)) {
                return FranquiaDao.franquias[i];
            }
        }
        return null;
    }

}
