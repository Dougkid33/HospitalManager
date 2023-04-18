package model.DAO;

import java.util.Date;

import model.Franquia;
import model.Pessoa;

public class FranquiaDao {
    private Franquia[] franquias;
    private int qtdFranquias;

    public FranquiaDao(int tamanho) {
        franquias = new Franquia[tamanho];
        qtdFranquias = 0;
    }

    public boolean cadastrarFranquia(String nome, String cnpj, String cidade, String endereco, Pessoa responsavelId) {
        if (buscarFranquia(cnpj) != null) {
            return false; // Já existe uma franquia com esse CNPJ
        }

        int id = gerarNovoId();
        Date dataCriacao = new Date();
        Franquia novaFranquia = new Franquia(id, nome, cnpj, cidade, endereco, responsavelId, dataCriacao, dataCriacao);
        franquias[qtdFranquias] = novaFranquia;
        qtdFranquias++;
        return true;
    }

    public boolean editarFranquia(String cnpj, String novoNome, String novaCidade, String novoEndereco) {
        Franquia franquia = buscarFranquia(cnpj);
        if (franquia == null) {
            return false; // Franquia não encontrada
        }

        franquia.setNome(novoNome);
        franquia.setCidade(novaCidade);
        franquia.setEndereco(novoEndereco);
        franquia.setDataModificacao(new Date());
        return true;
    }

    public boolean excluirFranquia(String cnpj) {
        int indice = -1;
        for (int i = 0; i < qtdFranquias; i++) {
            if (franquias[i].getCnpj().equals(cnpj)) {
                indice = i;
                break;
            }
        }

        if (indice == -1) {
            return false; // Franquia não encontrada
        }

        for (int i = indice; i < qtdFranquias - 1; i++) {
            franquias[i] = franquias[i + 1];
        }
        franquias[qtdFranquias - 1] = null;
        qtdFranquias--;
        return true;
    }

    public Franquia buscarFranquia(String cnpj) {
        for (int i = 0; i < qtdFranquias; i++) {
            if (franquias[i].getCnpj().equals(cnpj)) {
                return franquias[i];
            }
        }
        return null; // Franquia não encontrada
    }

    private int gerarNovoId() {
        int novoId = 1;
        for (int i = 0; i < qtdFranquias; i++) {
            if (franquias[i].getId() >= novoId) {
                novoId = franquias[i].getId() + 1;
            }
        }
        return novoId;
    }
}
