package model.DAO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.Consulta;
import model.Procedimento;
import model.enums.EstadoProcedimento;

public class ProcedimentoDAO {

    private static List<Procedimento> procedimentos = new ArrayList<>();
    private static int id = 1;

    public static void cadastrarProcedimento(String nome, Consulta consulta, LocalDateTime diaHorario, EstadoProcedimento estado,
            double valor, String laudo) {
        Procedimento procedimento = new Procedimento(id++, nome, consulta, diaHorario, estado, valor, laudo, LocalDateTime.now(),
                LocalDateTime.now());
        procedimentos.add(procedimento);
    }

    public static void atualizarProcedimento(int id, String nome, Consulta consulta, LocalDateTime diaHorario, EstadoProcedimento estado,
            double valor, String laudo) {
        for (Procedimento procedimento : procedimentos) {
            if (procedimento.getId() == id) {
                procedimento.setNome(nome);
                procedimento.setConsulta(consulta);
                procedimento.setDiaHorario(diaHorario);
                procedimento.setEstado(estado);
                procedimento.setValor(valor);
                procedimento.setLaudo(laudo);
                procedimento.setDataModificacao(LocalDateTime.now());
                break;
            }
        }
    }

    public static void removerProcedimento(int id) {
        for (int i = 0; i < procedimentos.size(); i++) {
            if (procedimentos.get(i).getId() == id) {
                procedimentos.remove(i);
                break;
            }
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

    public static List<Procedimento> listarProcedimentos() {
        return procedimentos;
    }

    public static List<Procedimento> listarProcedimentosPorConsulta(Consulta consulta) {
        List<Procedimento> procedimentosConsulta = new ArrayList<>();
        for (Procedimento procedimento : procedimentos) {
            if (procedimento.getConsulta().equals(consulta)) {
                procedimentosConsulta.add(procedimento);
            }
        }
        return procedimentosConsulta;
    }

    public static List<Procedimento> pesquisarProcedimentosPorMedicoNoPeriodo(Consulta consulta, LocalDateTime dataInicio, LocalDateTime dataFim) {
        List<Procedimento> procedimentosConsulta = new ArrayList<>();
        for (Procedimento procedimento : procedimentos) {
            LocalDateTime diaHorario = procedimento.getDiaHorario();
            if (procedimento.getConsulta().equals(consulta) && diaHorario.isAfter(dataInicio) && diaHorario.isBefore(dataFim)) {
                procedimentosConsulta.add(procedimento);
            }
        }
        return procedimentosConsulta;
    }
}
