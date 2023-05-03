package model.DAO;

import java.util.Arrays;
import java.util.Date;

import model.Consulta;
import model.Procedimento;

public class ProcedimentoDAO {

    private static Procedimento[] procedimentos = new Procedimento[0];
    private static int id = 1;

    public static void cadastrarProcedimento(String nome, Consulta consulta, Date diaHorario, String estado,
            double valor, String laudo) {
        Procedimento procedimento = new Procedimento(id++, nome, consulta, diaHorario, estado, valor, laudo, new Date(),
                new Date());
        procedimentos = Arrays.copyOf(procedimentos, procedimentos.length + 1);
        procedimentos[procedimentos.length - 1] = procedimento;
    }

    public static void atualizarProcedimento(int id, String nome, Consulta consulta, Date diaHorario, String estado,
            double valor, String laudo) {
        for (Procedimento procedimento : procedimentos) {
            if (procedimento.getId() == id) {
                procedimento.setNome(nome);
                procedimento.setConsulta(consulta);
                procedimento.setDiaHorario(diaHorario);
                procedimento.setEstado(estado);
                procedimento.setValor(valor);
                procedimento.setLaudo(laudo);
                procedimento.setDataModificacao(new Date());
                break;
            }
        }
    }

    public static void removerProcedimento(int id) {
        int removeIndex = -1;
        for (int i = 0; i < procedimentos.length; i++) {
            if (procedimentos[i].getId() == id) {
                removeIndex = i;
                break;
            }
        }
        if (removeIndex != -1) {
            for (int i = removeIndex; i < procedimentos.length - 1; i++) {
                procedimentos[i] = procedimentos[i + 1];
            }
            procedimentos = Arrays.copyOf(procedimentos, procedimentos.length - 1);
        }
    }

    public static Procedimento buscarProcedimento(int id) {
        for (Procedimento procedimento : procedimentos) {
            if (procedimento.getId() == id) {
                return procedimento;
            }
        }
        return null;
    }

    public static Procedimento[] listarProcedimentos() {
        return procedimentos;
    }

    public static Procedimento[] listarProcedimentosPorConsulta(Consulta consulta) {
        Procedimento[] procedimentosConsulta = new Procedimento[0];
        for (Procedimento procedimento : procedimentos) {
            if (procedimento.getConsulta().equals(consulta)) {
                procedimentosConsulta = Arrays.copyOf(procedimentosConsulta, procedimentosConsulta.length + 1);
                procedimentosConsulta[procedimentosConsulta.length - 1] = procedimento;
            }
        }
        return procedimentosConsulta;
    }
}
