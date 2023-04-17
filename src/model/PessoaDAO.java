package model;

import java.util.Arrays;
public class PessoaDAO {
    private Pessoa[] pessoas;
    private int count;

    public PessoaDAO(int size) {
        this.pessoas = new Pessoa[size];
        this.count = 0;
    }

    public boolean cadastraPessoas(Pessoa pessoa) {
        if (this.count >= this.pessoas.length) {
            return false;
        }
        this.pessoas[this.count] = pessoa;
        this.count++;
        return true;
    }

    public boolean removePessoas(int id) {
        for (int i = 0; i < this.count; i++) {
            if (this.pessoas[i].getId() == id) {
                this.pessoas[i] = null;
                for (int j = i; j < this.count - 1; j++) {
                    this.pessoas[j] = this.pessoas[j + 1];
                }
                this.count--;
                return true;
            }
        }
        return false;
    }

    public Pessoa buscarPorId(int id) {
        for (int i = 0; i < this.count; i++) {
            if (this.pessoas[i].getId() == id) {
                return this.pessoas[i];
            }
        }
        return null;
    }

    public boolean editarPessoas(Pessoa pessoa) {
        for (int i = 0; i < this.count; i++) {
            if (this.pessoas[i].getId() == pessoa.getId()) {
                this.pessoas[i] = pessoa;
                return true;
            }
        }
        return false;
    }

    public Pessoa[] listarPessoas() {
        return Arrays.copyOf(this.pessoas, this.count);
    }
}