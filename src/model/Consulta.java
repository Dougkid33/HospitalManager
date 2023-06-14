package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;


import model.enums.EstadoConsulta;

public class Consulta {

    private int id;
    private LocalDateTime data;
    private String hora;
    private EstadoConsulta estado;
    private Medico medico;
    private Pessoa paciente;
    private double valor;
    private Unidade unidade;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public Consulta(LocalDateTime data, String hora, EstadoConsulta estado, Medico medico, Pessoa paciente, double valor, Unidade unidade, LocalDateTime dataCriacao, LocalDateTime dataModificacao) {
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

    public double calcularEntradaFranquia() {
        return this.valor * 0.2; // considerando que a unidade de franquia recebe 20% do valor da consulta/procedimento
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
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

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataModificacao() {
        return dataModificacao;
    }

    private static int proximoId = 1;

    private static int gerarNovoId() {
        return proximoId++;
    }

    public void setDataModificacao(LocalDateTime dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id).append("\n");
        sb.append("Data: ").append(dateFormat.format(data)).append("\n");
        sb.append("Hora: ").append(hora).append("\n");
        sb.append("Estado: ").append(estado).append("\n");
        sb.append("Médico: ").append(medico.getNome()).append("\n");
        sb.append("Paciente: ").append(paciente.getNome()).append("\n");
        sb.append("Valor: ").append(valor).append("\n");
        sb.append("Unidade: ").append(unidade.getNome()).append("\n");
        sb.append("Data de Criação: ").append(dateFormat.format(dataCriacao)).append("\n");
        sb.append("Data de Modificação: ").append(dateFormat.format(dataModificacao)).append("\n");

        return sb.toString();
    }

    public static Consulta gerarConsultaAleatoria() {
        String[] horas = {"08:00", "09:00", "10:00", "11:00", "13:00", "14:00", "15:00", "16:00"};

        Random random = new Random();

        LocalDateTime dataConsulta = LocalDateTime.now().plusDays(random.nextInt(30) + 1);
        String horaConsulta = horas[random.nextInt(horas.length)];
        EstadoConsulta estadoConsulta = EstadoConsulta.values()[random.nextInt(EstadoConsulta.values().length)];
        Medico medico = Medico.gerarMedicoAleatorio();
        Pessoa paciente = Pessoa.gerarPessoaAleatoria();
        double valorConsulta = random.nextDouble() * 300;

        Unidade unidade = new Unidade();
        
                //Unidade.gerarUnidadeAleatoria();

        Consulta consulta = new Consulta( dataConsulta, horaConsulta, estadoConsulta, medico, paciente, valorConsulta, unidade, LocalDateTime.now(), LocalDateTime.now());

        return consulta;
    }
}
