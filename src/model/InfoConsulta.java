package model;
import java.util.Date;

import model.enums.EstadoConsulta;

public class InfoConsulta extends Consulta {

    private int id;
    private String descricao;
    private Date dataCriacao;
    private Date dataModificacao;

    public InfoConsulta(int id, Date data, String hora, EstadoConsulta estado, Medico medico, Pessoa paciente, double valor, Unidade unidade, String descricao, Date dataCriacao, Date dataModificacao) {
        super(id, data, hora, estado, medico, paciente, valor, unidade, dataModificacao, dataModificacao);
        this.id = id;
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
}
