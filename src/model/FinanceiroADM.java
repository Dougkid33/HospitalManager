package model;


import java.util.Date;
import model.enums.TipoMovimento;

public class FinanceiroADM {

   
	private int id;
    TipoMovimento tipoMovimento;
    private double valor;
    private String unidade;
    private String descritivoMovimento;
    private Date dataCriacao;
    private Date dataModificacao;

    public FinanceiroADM(int id, TipoMovimento tipoMovimento, double valor, String unidade, String descritivoMovimento, Date dataCriacao, Date dataModificacao) {
        this.id = id;
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

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
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
    

    

}
