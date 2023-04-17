package controller;

import java.util.Date;

import model.Pessoa;

public class PessoaController {
	private Pessoa[] pessoas;
    private int qtdPessoas;

    public PessoaController(int tamanho) {
        pessoas = new Pessoa[tamanho];
        qtdPessoas = 0;
    }

    public boolean cadastrarPessoa(String nome, String endereco, String cpf, String telefone, String login,
            String senha, String tipoUsuario) {
        if (buscarPessoa(login) != null) {
            return false; // Já existe uma pessoa com esse login
        }

        int id = gerarNovoId();
        Date dataCriacao = new Date();
        Pessoa novaPessoa = new Pessoa(id, nome, endereco, cpf, telefone, login, senha, tipoUsuario, dataCriacao, dataCriacao);
        pessoas[qtdPessoas] = novaPessoa;
        qtdPessoas++;
        return true;
    }

    public boolean editarPessoa(String login, String novoNome, String novoEndereco, String novoCpf, String novoTelefone) {
        Pessoa pessoa = buscarPessoa(login);
        if (pessoa == null) {
            return false; // Pessoa não encontrada
        }

        pessoa.setNome(novoNome);
        pessoa.setEndereco(novoEndereco);
        pessoa.setCpf(novoCpf);
        pessoa.setTelefone(novoTelefone);
        pessoa.setDataModificacao(new Date());
        return true;
    }

    public boolean alterarTipoUsuario(String login, String novoTipoUsuario) {
        Pessoa pessoa = buscarPessoa(login);
        if (pessoa == null) {
            return false; // Pessoa não encontrada
        }

        pessoa.setTipoUsuario(novoTipoUsuario);
        return true;
    }

    public Pessoa buscarPessoa(String login) {
        for (int i = 0; i < qtdPessoas; i++) {
            if (pessoas[i].getLogin().equals(login)) {
                return pessoas[i];
            }
        }
        return null; // Pessoa não encontrada
    }

    private int gerarNovoId() {
        int novoId = 1;
        for (int i = 0; i < qtdPessoas; i++) {
            if (pessoas[i].getId() >= novoId) {
                novoId = pessoas[i].getId() + 1;
            }
        }
        return novoId;
    }

}
