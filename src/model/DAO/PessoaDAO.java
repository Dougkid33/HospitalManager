package model.DAO;

import java.util.Arrays;

import model.Pessoa;
public class PessoaDAO {
    private static Pessoa[] pessoas = new Pessoa[100];
    private static int count = 0;


    public boolean cadastraPessoas(Pessoa pessoa) {
        if (PessoaDAO.count >= PessoaDAO.pessoas.length) {
            PessoaDAO.pessoas = Arrays.copyOf(PessoaDAO.pessoas, PessoaDAO.pessoas.length + 100);
        }
        PessoaDAO.pessoas[PessoaDAO.count] = pessoa;
        PessoaDAO.count++;
        return true;
    }

    public boolean removePessoas(int id) {
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

    public boolean editarPessoas(Pessoa pessoa) {
        for (int i = 0; i < PessoaDAO.count; i++) {
            if (PessoaDAO.pessoas[i].getId() == pessoa.getId()) {
                PessoaDAO.pessoas[i] = pessoa;
                return true;
            }
        }
        return false;
    }

    public Pessoa[] listarPessoas() {
        return Arrays.copyOf(PessoaDAO.pessoas, PessoaDAO.count);
    }

    public static Pessoa buscarPessoaPorCpf(String cpf) {
        for(int i = 0; i < count; i++) {
            if (pessoas[i].getCpf().equals(cpf)) {
                return pessoas[i];
            }
        }
        return null;
    }
}