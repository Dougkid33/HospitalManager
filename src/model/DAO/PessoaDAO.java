package model.DAO;

import java.util.Arrays;

import model.Pessoa;

public class PessoaDAO {
    private static Pessoa[] pessoas = new Pessoa[100];
    private static int count = 0;

    public boolean cadastraPessoa(Pessoa pessoa) {
        if (PessoaDAO.count >= PessoaDAO.pessoas.length) {
            PessoaDAO.pessoas = Arrays.copyOf(PessoaDAO.pessoas, PessoaDAO.pessoas.length + 100);
        }
        PessoaDAO.pessoas[PessoaDAO.count] = pessoa;
        PessoaDAO.count++;
        return true;
    }

    public boolean removePessoa(int id) {
        for (int i = 0; i < PessoaDAO.count; i++) {
            if (PessoaDAO.pessoas[i].getId() == id) {
                PessoaDAO.pessoas[i] = null;
                for (int j = i; j < PessoaDAO.count - 1; j++) {
                    PessoaDAO.pessoas[j] = PessoaDAO.pessoas[j + 1];
                }
                PessoaDAO.count--;
                return true;
            }
        }
        return false;
    }

    public Pessoa buscarPorId(int id) {
        for (int i = 0; i < PessoaDAO.count; i++) {
            if (PessoaDAO.pessoas[i].getId() == id) {
                return PessoaDAO.pessoas[i];
            }
        }
        return null;
    }

    public Pessoa buscarPessoaPorLogin(String login) {
        for (int i = 0; i < PessoaDAO.count; i++) {
            if (PessoaDAO.pessoas[i].getLogin().equals(login)) {
                return PessoaDAO.pessoas[i];
            }
        }
        return null;
    }

    public boolean editarPessoa(Pessoa pessoa) {
        for (int i = 0; i < PessoaDAO.count; i++) {
            if (PessoaDAO.pessoas[i].getId() == pessoa.getId()) {
                PessoaDAO.pessoas[i] = pessoa;
                return true;
            }
        }
        return false;
    }

    public Pessoa[] listarPessoas() {
        Pessoa[] pessoas = new Pessoa[PessoaDAO.count];
        for (int i = 0; i < PessoaDAO.count; i++) {
            pessoas[i] = PessoaDAO.pessoas[i];
        }
        return pessoas;
    }

    public static Pessoa buscarPessoaPorCpf(String cpf) {
        for (int i = 0; i < count; i++) {
            if (pessoas[i].getCpf().equals(cpf)) {
                return pessoas[i];
            }
        }
        return null;
    }
}
