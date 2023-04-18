package controller;

import java.util.Date;

import model.Pessoa;
import model.DAO.PessoaDAO;

public class PessoaController {
	private Pessoa[] pessoas;
    private int qtdPessoas;

    public PessoaController(int tamanho) {
        pessoas = new Pessoa[tamanho];
        qtdPessoas = 0;
    }

    public boolean cadastrarPessoa(int id, String nome, String endereco, String cpf, String telefone, String login,
            String senha, String tipoUsuario) {
        if (buscarPessoa(id) != null) {
            return false; // Já existe uma pessoa com esse login
        }

        
        Date dataCriacao = new Date();
        Pessoa novaPessoa = new Pessoa(id, nome, endereco, cpf, telefone, login, senha, tipoUsuario, dataCriacao, dataCriacao);
        pessoas[qtdPessoas] = novaPessoa;
        qtdPessoas++;
        return true;
    }

    public boolean editarPessoa(int id, String login, String novoNome, String novoEndereco, String novoCpf, String novoTelefone) {
        Pessoa pessoa = buscarPessoa(id);
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

    public boolean alterarTipoUsuario(int id, String login, String novoTipoUsuario) {
        Pessoa pessoa = buscarPessoa(id);
        if (pessoa == null) {
            return false; // Pessoa não encontrada
        }

        pessoa.setTipoUsuario(novoTipoUsuario);
        return true;
    }

    public Pessoa buscarPessoa(int id) {
        for (int i = 0; i < qtdPessoas; i++) {
            if (pessoas[i].getLogin().equals(id)) {
                return pessoas[i];
            }
        }
        return null; // Pessoa não encontrada
    }
    public boolean removerPessoa(String login) {
        for (int i = 0; i < qtdPessoas; i++) {
            if (pessoas[i].getLogin().equals(login)) {
                for (int j = i; j < qtdPessoas - 1; j++) {
                    pessoas[j] = pessoas[j+1];
                }
                pessoas[qtdPessoas - 1] = null;
                qtdPessoas--;
                return true;
            }
        }
        return false; // Pessoa não encontrada
    }
    
    public static Pessoa login(String cpf, String senha) {
        Pessoa pessoa = PessoaDAO.buscarPessoaPorCpf(cpf);

        if (pessoa != null && pessoa.getSenha().equals(senha)) {
            return pessoa;
        }

        return null;
    }



    public Pessoa[] listarPessoas() {
        return pessoas;
    }

}
