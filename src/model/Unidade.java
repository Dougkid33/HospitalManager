package model;

import java.util.Date;

public class Unidade extends Franquia {
    private int idUnidade;
    private String cidadeUnidade;
    private String enderecoUnidade;
    private Pessoa responsavelUnidade;
    private Date dataCriacaoUnidade;
    private Date dataModificacaoUnidade;
    
    public Unidade(String nome, String cnpj, String cidade, String endereco, Pessoa responsavel,
            Date dataCriacao, Date dataModificacao, int idUnidade, String cidadeUnidade, String enderecoUnidade, 
            Pessoa responsavelUnidade, Date dataCriacaoUnidade, Date dataModificacaoUnidade) {
        super( nome, cnpj, cidade, endereco, responsavel, dataCriacao, dataModificacao);
        this.idUnidade = gerarNovoId();
        this.cidadeUnidade = cidadeUnidade;
        this.enderecoUnidade = enderecoUnidade;
        this.responsavelUnidade = responsavelUnidade;
        this.dataCriacaoUnidade = dataCriacaoUnidade;
        this.dataModificacaoUnidade = dataModificacaoUnidade;
    }

    public int getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(int idUnidade) {
        this.idUnidade = idUnidade;
    }

    public String getCidadeUnidade() {
        return cidadeUnidade;
    }

    public void setCidadeUnidade(String cidadeUnidade) {
        this.cidadeUnidade = cidadeUnidade;
    }

    public String getEnderecoUnidade() {
        return enderecoUnidade;
    }

    public void setEnderecoUnidade(String enderecoUnidade) {
        this.enderecoUnidade = enderecoUnidade;
    }

    public Pessoa getResponsavelUnidade() {
        return responsavelUnidade;
    }

    public void setResponsavelUnidade(Pessoa responsavelUnidade) {
        this.responsavelUnidade = responsavelUnidade;
    }

    public Date getDataCriacaoUnidade() {
        return dataCriacaoUnidade;
    }

    public void setDataCriacaoUnidade(Date dataCriacaoUnidade) {
        this.dataCriacaoUnidade = dataCriacaoUnidade;
    }

    public Date getDataModificacaoUnidade() {
        return dataModificacaoUnidade;
    }

    public void setDataModificacaoUnidade(Date dataModificacaoUnidade) {
        this.dataModificacaoUnidade = dataModificacaoUnidade;
    }
    private static int proximoId = 1;

    private static int gerarNovoId() {
        return proximoId++;
    }
}
