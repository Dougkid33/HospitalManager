package model;

import java.util.Date;

import model.enums.EstadoConsulta;

public class Consulta {
    private int id;
    private Date data;
    private String hora;
    private EstadoConsulta estado;
    private Medico medico;
    private Pessoa paciente;
    private double valor;
    private Unidade unidade;
    private Date dataCriacao;
    private Date dataModificacao;

    public Consulta(int id, Date data, String hora, EstadoConsulta estado, Medico medico, Pessoa paciente, double valor, Unidade unidade, Date dataCriacao, Date dataModificacao) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.estado = estado;
        this.medico = medico;
        this.paciente = paciente;
        this.valor = valor;
        this.unidade = unidade;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public EstadoConsulta getEstado() {
        return estado;
    }

    public void setEstado(EstadoConsulta estado) {
        this.estado = estado;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Pessoa getPaciente() {
        return paciente;
    }

    public void setPaciente(Pessoa paciente) {
        this.paciente = paciente;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
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

