package model;

import java.util.Date;

public class Procedimento {

    private int id;
    private String nome;
    private Consulta consulta;
    private Date diaHorario;
    private String estado;
    private double valor;
    private String laudo;
    private Date dataCriacao;
    private Date dataModificacao;

    public Procedimento(int id, String nome, Consulta consulta, Date diaHorario, String estado, double valor, String laudo, Date dataCriacao, Date dataModificacao) {
        this.id = id;
        this.nome = nome;
        this.consulta = consulta;
        this.diaHorario = diaHorario;
        this.estado = estado;
        this.valor = valor;
        this.laudo = laudo;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
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

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public Date getDiaHorario() {
        return diaHorario;
    }

    public void setDiaHorario(Date diaHorario) {
        this.diaHorario = diaHorario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getLaudo() {
        return laudo;
    }

    public void setLaudo(String laudo) {
        this.laudo = laudo;
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
