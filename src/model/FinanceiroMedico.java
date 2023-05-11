package model;

import java.util.Date;
import java.util.Random;

import model.enums.PgtoMedico;

public class FinanceiroMedico {

    private int id;
    private double valorMedico;
    private PgtoMedico estado;
    private double franquia;
    private String descricao;
    private Date dataCriacao;
    private Date dataModificacao;

    public FinanceiroMedico(int id, double valorMedico, PgtoMedico estado2, double franquia, String descricao, Date dataCriacao, Date dataModificacao) {
        this.id = id;
        this.valorMedico = valorMedico;
        this.estado = estado2;
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

    public PgtoMedico getEstado() {
        return estado;
    }

    public void setEstado(PgtoMedico estado) {
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
    
    private static PgtoMedico gerarEstadoProcedimentoAleatorio() {
    	PgtoMedico[] estados = PgtoMedico.values();
        Random random = new Random();
        int index = random.nextInt(estados.length);
        return estados[index];
    }
    
    private static String gerarDescricaoAleatoria() {
        String[] descricoes = {"Consulta", "Exame", "Procedimento", "Cirurgia"};
        Random random = new Random();
        int index = random.nextInt(descricoes.length);
        return descricoes[index];
    }
    
    private static int proximoId = 1;

    private static int gerarNovoId() {
        return proximoId++;
    }
    @Override
    public String toString() {
        return "ID: " + id +
               ", Valor Médico: " + valorMedico +
               ", Estado: " + estado +
               ", Franquia: " + franquia +
               ", Descrição: " + descricao +
               ", Data de Criação: " + dataCriacao +
               ", Data de Modificação: " + dataModificacao;
    }
    public static FinanceiroMedico gerarFinanceiroMedicoAleatorio() {
    	
    	
    	Random random = new Random();
        int id = gerarNovoId();
        double valorMedico = random.nextDouble() * 1000; // Gera um valor aleatório entre 0 e 1000
        PgtoMedico estado = gerarEstadoProcedimentoAleatorio();
        double franquia = random.nextDouble() * 500; // Gera um valor aleatório entre 0 e 500
        String descricao = gerarDescricaoAleatoria();
        Date dataCriacao = new Date();
        Date dataModificacao = new Date();

        FinanceiroMedico financeiroMedico = new FinanceiroMedico(id, valorMedico, estado, franquia, descricao, dataCriacao, dataModificacao);
        return financeiroMedico;
    }
}
