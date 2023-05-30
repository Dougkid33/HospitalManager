package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

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
        this.id = gerarNovoId();
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
    private static int proximoId = 1;

    private static int gerarNovoId() {
        return proximoId++;
    }

    public void setDataModificacao(Date dataModificacao) {
        this.dataModificacao = dataModificacao;
    }
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

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

        Date dataConsulta = new Date(System.currentTimeMillis() + (random.nextInt(30) + 1) * 24 * 60 * 60 * 1000); // Gera uma data aleatória nos próximos 30 dias
        String horaConsulta = horas[random.nextInt(horas.length)];
        EstadoConsulta estadoConsulta = EstadoConsulta.values()[random.nextInt(EstadoConsulta.values().length)]; // Gera um estado aleatório a partir dos valores possíveis na enumeração EstadoConsulta
        Medico medico = Medico.gerarMedicoAleatorio();
        Pessoa paciente = Pessoa.gerarPessoaAleatoria();
        double valorConsulta = random.nextDouble() * 300; // Gera um valor aleatório entre 0 e 300

        Unidade unidade = Unidade.gerarUnidadeAleatoria();

        Consulta consulta = new Consulta(gerarNovoId(), dataConsulta, horaConsulta, estadoConsulta, medico, paciente, valorConsulta, unidade, new Date(), new Date());

        return consulta;
    }
}
