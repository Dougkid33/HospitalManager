package model;

import java.util.Date;

public class Medico extends Pessoa {
    private int crm;
    private String especialidade;
    private Date dataCriacao;
    private Date dataModificacao;

    public Medico(int id, String nome, String endereco, String cpf, String telefone, String login, String senha,
            int crm, String especialidade, Date dataCriacao, Date dataModificacao) {
        super(id, nome, endereco, cpf, telefone, login, senha, especialidade, dataModificacao, dataModificacao);
        this.crm = crm;
        this.especialidade = especialidade;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
    }

    public int getCrm() {
        return crm;
    }

    public void setCrm(int novoCrm) {
        this.crm = novoCrm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
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

