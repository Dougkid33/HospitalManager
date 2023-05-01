package model;

import java.util.Date;

public class Franquia {

    private int id;
    private String nome;
    private String cnpj;
    private String cidade;
    private String endereco;
    private Pessoa responsavel;
    private Date dataCriacao;
    private Date dataModificacao;

    public Franquia(String nome, String cnpj, String cidade, String endereco, Pessoa responsavel,
            Date dataCriacao, Date dataModificacao) {
        super();
        this.id = gerarNovoId();
        this.nome = nome;
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.endereco = endereco;
        this.responsavel = responsavel;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
    }
    private static int proximoId = 1;

    private static int gerarNovoId() {
        return proximoId++;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Pessoa getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Pessoa responsavel) {
        this.responsavel = responsavel;
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

}
