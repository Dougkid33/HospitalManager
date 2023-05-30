package model;

import java.util.Date;
import java.util.Random;

public class Pessoa {

    private int id;
    private String nome;
    private String endereco;
    private String cpf;
    private String telefone;
    private String login;
    private String senha;
    private String tipoUsuario;
    private Date dataCriacao;
    private Date dataModificacao;

    public Pessoa() {
    }

    public Pessoa(String nome, String endereco, String cpf, String telefone, String login, String senha,
            String tipoUsuario, Date dataCriacao, Date dataModificacao) {
        this.id = gerarNovoId();
        this.nome = nome;
        this.endereco = endereco;
        this.cpf = cpf;
        this.telefone = telefone;
        this.login = login;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
    }
    public static Pessoa gerarPessoaAleatoria() {
        String[] tiposUsuario = {"DonoFranquia", "DonoUnidadeFranquia", "Administrativo", "Médico", "Paciente"};
        String[] nomes = {"Ana", "Maria", "João", "Pedro", "Luiza", "Gabriel", "Lucas", "Juliana", "Renata", "Thiago"};
        String[] sobrenomes = {"Silva", "Souza", "Fernandes", "Almeida", "Costa", "Pereira", "Santos", "Lima", "Mendes", "Nascimento"};
        String[] logins = {"ana.silva", "joao.pereira", "lucia.costa", "renata.mendes", "thiago.nascimento"};
        String[] senhas = {"123456", "senha123", "senha1234", "senha12345", "senha123456"};

        Random random = new Random();

        String nome = nomes[random.nextInt(nomes.length)] + " " + sobrenomes[random.nextInt(sobrenomes.length)];
        String endereco = "Rua " + (random.nextInt(9999) + 1) + ", Bairro " + (random.nextInt(99) + 1);
        String cpf = String.format("%03d.%03d.%03d-%02d", random.nextInt(1000), random.nextInt(1000), random.nextInt(1000), random.nextInt(100));
        String telefone = "(" + (random.nextInt(99) + 1) + ") " + (random.nextInt(99999) + 1) + "-" + (random.nextInt(9999) + 1);
        String login = logins[random.nextInt(logins.length)] + (random.nextInt(99) + 1);
        String senha = senhas[random.nextInt(senhas.length)];
        String tipoUsuario = tiposUsuario[random.nextInt(tiposUsuario.length)];
        Date dataCriacao = new Date();
        Date dataModificacao = new Date();

        Pessoa pessoa = new Pessoa(nome, endereco, cpf, telefone, login, senha, tipoUsuario, dataCriacao, dataModificacao);

        return pessoa;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(Date dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    @Override
    public String toString() {
        return nome + ": {"
                + "id = " + id
                + ", endereco = " + endereco
                + ", cpf = " + cpf
                + ", telefone = " + telefone
                + ", login = " + login
                + ", senha = " + senha
                + ", tipoUsuario = " + tipoUsuario
                + ", dataCriacao = " + dataCriacao
                + ", dataModificacao = " + dataModificacao
                + "}";
    }

    private static int proximoId = 1;

    private static int gerarNovoId() {
        return proximoId++;
    }

}
