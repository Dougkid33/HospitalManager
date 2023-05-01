package model;

import java.util.Date;

public class FinanceiroMedico {

    private int id;
    private double valorMedico;
    private String estado;
    private double franquia;
    private String descricao;
    private Date dataCriacao;
    private Date dataModificacao;

    public FinanceiroMedico(int id, double valorMedico, String estado, double franquia, String descricao, Date dataCriacao, Date dataModificacao) {
        this.id = id;
        this.valorMedico = valorMedico;
        this.estado = estado;
        this.franquia = franquia;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValorMedico() {
        return valorMedico;
    }

    public void setValorMedico(double valorMedico) {
        this.valorMedico = valorMedico;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getFranquia() {
        return franquia;
    }

    public void setFranquia(double franquia) {
        this.franquia = franquia;
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
