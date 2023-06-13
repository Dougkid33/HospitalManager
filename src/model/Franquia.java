package model;

import controller.PessoaController;
import java.util.Date;
import java.util.Random;

public class Franquia {

    private int id;
    private String nome;
    private String cnpj;
    private String cidade;
    private String endereco;
    private Pessoa responsavel;
    private Date dataCriacao;
    private Date dataModificacao;
    private FinanceiroADM[] despesas;
    private FinanceiroMedico[] despesasMedico;

    public FinanceiroMedico[] getDespesasMedico() {
        return despesasMedico;
    }

    public void setDespesasMedico(FinanceiroMedico[] despesasMedico) {
        this.despesasMedico = despesasMedico;
    }

    public void setDespesas(FinanceiroADM[] despesas) {
        this.despesas = despesas;
    }

    public Franquia(String nome, String cnpj, String cidade, String endereco, Pessoa responsavel,
            Date dataCriacao, Date dataModificacao) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.endereco = endereco;
        this.responsavel = responsavel;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
    }
    public Franquia() {
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Pessoa getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Pessoa responsavel) {
        this.responsavel = responsavel;
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

    public FinanceiroADM[] getDespesas() {
        return this.despesas;
    }
    public static Franquia gerarFranquiaAleatoria() {
        PessoaController controller = new PessoaController();
        String[] nomes = {"Franquia A", "Franquia B", "Franquia C", "Franquia D", "Franquia E"};
        String[] cnpjs = {"12.345.678/0001-01", "23.456.789/0001-02", "34.567.890/0001-03", "45.678.901/0001-04", "56.789.012/0001-05"};
        String[] cidades = {"São Paulo", "Rio de Janeiro", "Belo Horizonte", "Curitiba", "Fortaleza"};
        String[] enderecos = {"Rua 1", "Rua 2", "Rua 3", "Rua 4", "Rua 5"};
        Pessoa responsavel = Pessoa.gerarPessoaAleatoria();
        controller.cadastrarPessoa(responsavel);
        
        Random random = new Random();

        String nome = nomes[random.nextInt(nomes.length)];
        String cnpj = cnpjs[random.nextInt(cnpjs.length)];
        String cidade = cidades[random.nextInt(cidades.length)];
        String endereco = enderecos[random.nextInt(enderecos.length)];
        Date dataCriacao = new Date();
        Date dataModificacao = new Date();

        Franquia franquia = new Franquia(nome, cnpj, cidade, endereco, responsavel, dataCriacao, dataModificacao);

        return franquia;
    }

    @Override
    public String toString() {
        return "Franquia{" 
                + "id=" + id 
                + ", nome=" + nome 
                + ", cnpj=" + cnpj 
                + ", cidade=" + cidade 
                + ", endereco=" + endereco 
                + ", responsavel=" + responsavel.getNome()
                + ", dataCriacao=" + dataCriacao 
                + ", dataModificacao=" + dataModificacao 
                + ", despesas=" + despesas 
                + ", despesasMedico=" + despesasMedico + '}';
    }
   
}
