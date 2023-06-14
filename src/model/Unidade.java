package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Unidade extends Franquia {

    private int idUnidade;
    private String cidadeUnidade;
    private String enderecoUnidade;
    private Pessoa responsavelUnidade;
    private Franquia franquia;
    private LocalDateTime dataCriacaoUnidade;
    private LocalDateTime dataModificacaoUnidade;
    private List<Procedimento> procedimentos;

    public Unidade() {
        procedimentos = new ArrayList<>();
    }

    public Unidade(String nome, String cnpj, String cidade, String endereco, Pessoa responsavel,
                   LocalDateTime dataCriacao, LocalDateTime dataModificacao, int idUnidade,
                   String cidadeUnidade, String enderecoUnidade, Pessoa responsavelUnidade,
                   Franquia franquia, LocalDateTime dataCriacaoUnidade,
                   LocalDateTime dataModificacaoUnidade) {
        super(nome, cnpj, cidade, endereco, responsavel, dataCriacao, dataModificacao);
        this.idUnidade = idUnidade;
        this.cidadeUnidade = cidadeUnidade;
        this.enderecoUnidade = enderecoUnidade;
        this.responsavelUnidade = responsavelUnidade;
        this.franquia = franquia;
        this.dataCriacaoUnidade = dataCriacaoUnidade;
        this.dataModificacaoUnidade = dataModificacaoUnidade;
        this.procedimentos = new ArrayList<>();
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

    public Franquia getFranquia() {
        return franquia;
    }

    public void setFranquia(Franquia franquia) {
        this.franquia = franquia;
    }

    public LocalDateTime getDataCriacaoUnidade() {
        return dataCriacaoUnidade;
    }

    public void setDataCriacaoUnidade(LocalDateTime dataCriacaoUnidade) {
        this.dataCriacaoUnidade = dataCriacaoUnidade;
    }

    public LocalDateTime getDataModificacaoUnidade() {
        return dataModificacaoUnidade;
    }

    public void setDataModificacaoUnidade(LocalDateTime dataModificacaoUnidade) {
        this.dataModificacaoUnidade = dataModificacaoUnidade;
    }

    public List<Procedimento> getProcedimentos() {
        return procedimentos;
    }

    public void setProcedimentos(List<Procedimento> procedimentos) {
        this.procedimentos = procedimentos;
    }

    public void adicionarProcedimento(Procedimento procedimento) {
        procedimentos.add(procedimento);
    }

    public void removerProcedimento(Procedimento procedimento) {
        procedimentos.remove(procedimento);
    }

    @Override
    public String toString() {
        return "Unidade{" +
                "ID Unidade: " + idUnidade +
                ", Cidade: " + cidadeUnidade +
                ", Endereço: " + enderecoUnidade +
                ", Responsável pela Unidade: " + responsavelUnidade +
                ", Data de Criação: " + dataCriacaoUnidade +
                ", Data de Modificação: " + dataModificacaoUnidade +
                '}';
    }

    private static int proximoId = 1;

    private static int gerarNovoId() {
        return proximoId++;
    }
}
