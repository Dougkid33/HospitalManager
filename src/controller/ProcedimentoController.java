package controller;

import java.util.Date;

import model.Consulta;
import model.Procedimento;
import model.ProcedimentoDAO;

public class ProcedimentoController {

    public static void cadastrarProcedimento(String nome, Consulta consulta, Date diaHorario, String estado,
        double valor, String laudo) {
        ProcedimentoDAO.cadastrarProcedimento(nome, consulta, diaHorario, estado, valor, laudo);
    }

    public static void atualizarProcedimento(int id, String nome, Consulta consulta, Date diaHorario, String estado,
        double valor, String laudo) {
        ProcedimentoDAO.atualizarProcedimento(id, nome, consulta, diaHorario, estado, valor, laudo);
    }

    public static void removerProcedimento(int id) {
        ProcedimentoDAO.removerProcedimento(id);
    }

    public static Procedimento buscarProcedimento(int id) {
        return ProcedimentoDAO.buscarProcedimento(id);
    }

    public static Procedimento[] listarProcedimentos() {
        return ProcedimentoDAO.listarProcedimentos();
    }

    public static Procedimento[] listarProcedimentosPorConsulta(Consulta consulta) {
        return ProcedimentoDAO.listarProcedimentosPorConsulta(consulta);
    }
}
