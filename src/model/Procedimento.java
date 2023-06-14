package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.enums.EstadoProcedimento;


public class Procedimento {

    private int id;
    private String nome;
    private Consulta consulta;
    private LocalDateTime diaHorario;
    private EstadoProcedimento estado;
    private double valor;
    private String laudo;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public Procedimento(int id, String nome, Consulta consulta, LocalDateTime diaHorario, EstadoProcedimento estadoProcedimento, double valor, String laudo, LocalDateTime dataCriacao, LocalDateTime dataModificacao) {
        this.id = gerarNovoId();
        this.nome = nome;
        this.consulta = consulta;
        this.diaHorario = diaHorario;
        this.estado = estadoProcedimento;
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

    public LocalDateTime getDiaHorario() {
        return diaHorario;
    }

    public void setDiaHorario(LocalDateTime diaHorario) {
        this.diaHorario = diaHorario;
    }

    public EstadoProcedimento getEstado() {
        return estado;
    }

    public void setEstado(EstadoProcedimento estado) {
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

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(LocalDateTime dataModificacao) {
        this.dataModificacao = dataModificacao;
    }
    
    private static int proximoId = 1;

    private static int gerarNovoId() {
        return proximoId++;
    }
    
    @Override
    public String toString() {
        return "Procedimento{" +
                "Id: " + id +
                ", Nome: '" + nome + '\'' +
                ", Consulta: " + consulta +
                ", Dia/Horario: " + diaHorario +
                ", Estado: " + estado + '\'' +
                ", Valor: " + valor +
                ", Laudo: " + laudo + '\'' +
                ", Data de criação: " + dataCriacao +
                ", Data de Modificação: " + dataModificacao +
                '}';
    }
    
    private static EstadoProcedimento gerarEstadoProcedimentoAleatorio() {
        EstadoProcedimento[] estados = EstadoProcedimento.values();
        Random random = new Random();
        int index = random.nextInt(estados.length);
        return estados[index];
    }
    
    public static Procedimento gerarProcedimentoAleatorio() {
        Random random = new Random();

        String nomeProcedimento = "Procedimento " + random.nextInt(1000); // Gera um nome aleatório para o procedimento
        Consulta consulta = Consulta.gerarConsultaAleatoria();
        LocalDateTime diaHorario = LocalDateTime.now(); // Defina a data e o horário desejados para o procedimento
        EstadoProcedimento estadoProcedimento = gerarEstadoProcedimentoAleatorio(); // Gera um estado aleatório a partir dos valores possíveis na array 'estados'
        double valorProcedimento = random.nextDouble() * 500; // Gera um valor aleatório entre 0 e 500
        String laudo = "Laudo do procedimento"; // Defina o laudo desejado para o procedimento

        Procedimento procedimento = new Procedimento(gerarNovoId(), nomeProcedimento, consulta, diaHorario, estadoProcedimento, valorProcedimento, laudo, LocalDateTime.now(), LocalDateTime.now());

        return procedimento;
    }

}
