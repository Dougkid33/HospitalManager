package model;

import java.util.Date;
import java.util.Random;

public class Unidade extends Franquia {

    private int idUnidade;
    private String cidadeUnidade;
    private String enderecoUnidade;
    private Pessoa responsavelUnidade;
    private Date dataCriacaoUnidade;
    private Date dataModificacaoUnidade;

    public Unidade(String nome, String cnpj, String cidade, String endereco, Pessoa responsavel,
            Date dataCriacao, Date dataModificacao, int idUnidade, String cidadeUnidade, String enderecoUnidade,
            Pessoa responsavelUnidade, Date dataCriacaoUnidade, Date dataModificacaoUnidade) {
        super(nome, cnpj, cidade, endereco, responsavel, dataCriacao, dataModificacao);
        this.idUnidade = gerarNovoId();
        this.cidadeUnidade = cidadeUnidade;
        this.enderecoUnidade = enderecoUnidade;
        this.responsavelUnidade = responsavelUnidade;
        this.dataCriacaoUnidade = dataCriacaoUnidade;
        this.dataModificacaoUnidade = dataModificacaoUnidade;
    }

    public int getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(int idUnidade) {
        this.idUnidade = idUnidade;
    }

    public String getCidadeUnidade() {
        return cidadeUnidade;
    }

    public void setCidadeUnidade(String cidadeUnidade) {
        this.cidadeUnidade = cidadeUnidade;
    }

    public String getEnderecoUnidade() {
        return enderecoUnidade;
    }

    public void setEnderecoUnidade(String enderecoUnidade) {
        this.enderecoUnidade = enderecoUnidade;
    }

    public Pessoa getResponsavelUnidade() {
        return responsavelUnidade;
    }

    public void setResponsavelUnidade(Pessoa responsavelUnidade) {
        this.responsavelUnidade = responsavelUnidade;
    }

    public Date getDataCriacaoUnidade() {
        return dataCriacaoUnidade;
    }

    public void setDataCriacaoUnidade(Date dataCriacaoUnidade) {
        this.dataCriacaoUnidade = dataCriacaoUnidade;
    }

    public Date getDataModificacaoUnidade() {
        return dataModificacaoUnidade;
    }

    public void setDataModificacaoUnidade(Date dataModificacaoUnidade) {
        this.dataModificacaoUnidade = dataModificacaoUnidade;
    }

    @Override
    public String toString() {
        return "Unidade{"
                + "ID Unidade: " + idUnidade
                + ", Cidade: " + cidadeUnidade
                + ", Endereço: " + enderecoUnidade
                + ", Responsável pela Unidade: " + responsavelUnidade
                + ", Data de Criação: " + dataCriacaoUnidade
                + ", Data de Modificação: " + dataModificacaoUnidade + '}';
    }

    private static int proximoId = 1;

    private static int gerarNovoId() {
        return proximoId++;
    }
    public static Unidade gerarUnidadeAleatoria() {
        String[] cidades = {"São Paulo", "Rio de Janeiro", "Belo Horizonte", "Curitiba", "Fortaleza"};
        String[] enderecos = {"Rua 1", "Rua 2", "Rua 3", "Rua 4", "Rua 5"};
        Pessoa responsavelUnidade = Pessoa.gerarPessoaAleatoria();

        Random random = new Random();

        String cidadeUnidade = cidades[random.nextInt(cidades.length)];
        String enderecoUnidade = enderecos[random.nextInt(enderecos.length)];
        Date dataCriacaoUnidade = new Date();
        Date dataModificacaoUnidade = new Date();

        Unidade unidade = new Unidade("Unidade " + gerarNovoId(), "12.345.678/0001-01", "São Paulo", "Rua 1", responsavelUnidade,
                new Date(), new Date(), gerarNovoId(), cidadeUnidade, enderecoUnidade,
                responsavelUnidade, dataCriacaoUnidade, dataModificacaoUnidade);

        return unidade;
    }
}
