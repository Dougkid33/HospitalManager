package model;

import java.util.Date;
import java.util.Random;

import model.enums.TipoMovimento;

public class FinanceiroADM {

    private int id;
    TipoMovimento tipoMovimento;
    private double valor;
    private Unidade unidade;
    private String descritivoMovimento;
    private Date dataCriacao;
    private Date dataModificacao;

    public FinanceiroADM(int id, TipoMovimento tipoMovimento, double valor, Unidade unidade, String descritivoMovimento, Date dataCriacao, Date dataModificacao) {
        this.id = gerarNovoId();
        this.tipoMovimento = tipoMovimento;
        this.valor = valor;
        this.unidade = unidade;
        this.descritivoMovimento = descritivoMovimento;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoMovimento getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(TipoMovimento tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
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

    public String getDescritivoMovimento() {
        return descritivoMovimento;
    }

    public void setDescritivoMovimento(String descritivoMovimento) {
        this.descritivoMovimento = descritivoMovimento;
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
    private static int proximoId = 1;

    private static int gerarNovoId() {
        return proximoId++;
    }
//    public static FinanceiroADM gerarFinanceiroADMAleatorio() {
//        TipoMovimento[] tiposMovimento = TipoMovimento.values();
//        String[] unidades = {"Unidade A", "Unidade B", "Unidade C", "Unidade D"};
//
//        Random random = new Random();
//
//        TipoMovimento tipoMovimento = tiposMovimento[random.nextInt(tiposMovimento.length)];
//        double valor = random.nextDouble() * 1000;
//        String unidade = unidades[random.nextInt(unidades.length)];
//        String descritivoMovimento = "Movimento " + random.nextInt(1000);
//        Date dataCriacao = new Date();
//        Date dataModificacao = new Date();
//
//        FinanceiroADM financeiroADM = new FinanceiroADM(gerarNovoId(), tipoMovimento, valor, unidade, descritivoMovimento, dataCriacao, dataModificacao);
//
//        return financeiroADM;
//    }


}
