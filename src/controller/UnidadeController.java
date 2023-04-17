package controller;

import model.Unidade;

public class UnidadeController {

    private Unidade[] unidades;
    private int qtdUnidades;

    public UnidadeController(int qtdMaximaUnidades) {
        unidades = new Unidade[qtdMaximaUnidades];
        qtdUnidades = 0;
    }

    public boolean cadastrarUnidade(Unidade unidade) {
        if (qtdUnidades < unidades.length) {
            unidades[qtdUnidades] = unidade;
            qtdUnidades++;
            return true;
        } else {
            return false;
        }
    }

    public Unidade buscarUnidade(int idUnidade) {
        for (int i = 0; i < qtdUnidades; i++) {
            if (unidades[i].getIdUnidade() == idUnidade) {
                return unidades[i];
            }
        }
        return null;
    }

    public boolean atualizarUnidade(Unidade unidadeAtualizada) {
        for (int i = 0; i < qtdUnidades; i++) {
            if (unidades[i].getIdUnidade() == unidadeAtualizada.getIdUnidade()) {
                unidades[i] = unidadeAtualizada;
                return true;
            }
        }
        return false;
    }

    public boolean excluirUnidade(int idUnidade) {
        for (int i = 0; i < qtdUnidades; i++) {
            if (unidades[i].getIdUnidade() == idUnidade) {
                for (int j = i; j < qtdUnidades - 1; j++) {
                    unidades[j] = unidades[j + 1];
                }
                unidades[qtdUnidades - 1] = null;
                qtdUnidades--;
                return true;
            }
        }
        return false;
    }

    public Unidade[] listarUnidades() {
        Unidade[] unidadesCadastradas = new Unidade[qtdUnidades];
        for (int i = 0; i < qtdUnidades; i++) {
            unidadesCadastradas[i] = unidades[i];
        }
        return unidadesCadastradas;
    }
}

