package model;

import java.util.Date;
import java.util.Random;

import model.enums.EstadoConsulta;

public class InfoConsulta extends Consulta {

    private int id;
    private String descricao;
    private Date dataCriacao;
    private Date dataModificacao;

    public InfoConsulta(int id, Date data, String hora, EstadoConsulta estado, Medico medico, Pessoa paciente, double valor, Unidade unidade, String descricao, Date dataCriacao, Date dataModificacao) {
        super(id, data, hora, estado, medico, paciente, valor, unidade, dataModificacao, dataModificacao);
        this.id = gerarNovoId();
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
        this.dataModificacao = new Date();
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public Date getDataModificacao() {
        return dataModificacao;
    }
    private static int proximoId = 1;

    private static int gerarNovoId() {
        return proximoId++;
    }
    
    public static InfoConsulta gerarInfoConsultaAleatoria() {
        String[] horas = {"08:00", "09:00", "10:00", "11:00", "13:00", "14:00", "15:00", "16:00"};

        Random random = new Random();

        Date dataConsulta = new Date(System.currentTimeMillis() + (random.nextInt(30) + 1) * 24 * 60 * 60 * 1000); // Gera uma data aleatória nos próximos 30 dias
        String horaConsulta = horas[random.nextInt(horas.length)];
        EstadoConsulta estadoConsulta = EstadoConsulta.values()[random.nextInt(EstadoConsulta.values().length)]; // Gera um estado aleatório a partir dos valores possíveis na enumeração EstadoConsulta
        Medico medico = Medico.gerarMedicoAleatorio();
        Pessoa paciente = Pessoa.gerarPessoaAleatoria();
        double valorConsulta = random.nextDouble() * 300; // Gera um valor aleatório entre 0 e 300

        Unidade unidade = Unidade.gerarUnidadeAleatoria();

        String descricao = "Descrição da consulta " + gerarNovoId();
        Date dataCriacao = new Date();
        Date dataModificacao = new Date();

        InfoConsulta infoConsulta = new InfoConsulta(gerarNovoId(), dataConsulta, horaConsulta, estadoConsulta, medico, paciente, valorConsulta, unidade, descricao, dataCriacao, dataModificacao);

        return infoConsulta;
    }
}
