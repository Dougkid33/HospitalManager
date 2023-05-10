package model;

import java.util.Date;
import java.util.Random;


public class Procedimento {

    private int id;
    private String nome;
    private Consulta consulta;
    private Date diaHorario;
    private String estado;
    private double valor;
    private String laudo;
    private Date dataCriacao;
    private Date dataModificacao;

    public Procedimento(int id, String nome, Consulta consulta, Date diaHorario, String estado, double valor, String laudo, Date dataCriacao, Date dataModificacao) {
        this.id = gerarNovoId();
        this.nome = nome;
        this.consulta = consulta;
        this.diaHorario = diaHorario;
        this.estado = estado;
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

    public Date getDiaHorario() {
        return diaHorario;
    }

    public void setDiaHorario(Date diaHorario) {
        this.diaHorario = diaHorario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
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
    
    
    
    public static Procedimento gerarProcedimentoAleatorio() {
        String[] estados = {"Pendente", "Concluído", "Cancelado"};

        Random random = new Random();

        String nomeProcedimento = "Procedimento " + random.nextInt(1000); // Gera um nome aleatório para o procedimento
        Consulta consulta = Consulta.gerarConsultaAleatoria();
        Date diaHorario = new Date(); // Defina a data e o horário desejados para o procedimento
        String estadoProcedimento = estados[random.nextInt(estados.length)]; // Gera um estado aleatório a partir dos valores possíveis na array 'estados'
        double valorProcedimento = random.nextDouble() * 500; // Gera um valor aleatório entre 0 e 500
        String laudo = "Laudo do procedimento"; // Defina o laudo desejado para o procedimento

        Procedimento procedimento = new Procedimento(gerarNovoId(), nomeProcedimento, consulta, diaHorario, estadoProcedimento, valorProcedimento, laudo, new Date(), new Date());

        return procedimento;
    }

}
