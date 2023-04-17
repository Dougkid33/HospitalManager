package controller;

import java.util.Arrays;
import java.util.Date;

import model.InfoConsulta;
import model.Medico;
import model.Pessoa;
import model.Unidade;

public class InfoConsultaController {

    private static InfoConsulta[] infos = new InfoConsulta[0];
    private static int id = 1;

    public static void cadastrarInfoConsulta(Date data, String hora, String estado, Medico medico, Pessoa paciente, double valor, Unidade unidade, String descricao) {
        InfoConsulta info = new InfoConsulta(id++, data, hora, estado, medico, paciente, valor, unidade, descricao, new Date(), new Date());
        infos = Arrays.copyOf(infos, infos.length + 1);
        infos[infos.length - 1] = info;
    }

    public static void atualizarInfoConsulta(int id, String descricao) {
        for (int i = 0; i < infos.length; i++) {
            if (infos[i].getId() == id) {
                infos[i].setDescricao(descricao);
                break;
            }
        }
    }

    public static void removerInfoConsulta(int id) {
        int removeIndex = -1;
        for (int i = 0; i < infos.length; i++) {
            if (infos[i].getId() == id) {
                removeIndex = i;
                break;
            }
        }
        if (removeIndex != -1) {
            for (int i = removeIndex; i < infos.length - 1; i++) {
                infos[i] = infos[i + 1];
            }
            infos = Arrays.copyOf(infos, infos.length - 1);
        }
    }

    public static InfoConsulta buscarInfoConsulta(int id) {
        for (InfoConsulta info : infos) {
            if (info.getId() == id) {
                return info;
            }
        }
        return null;
    }

    public static InfoConsulta[] listarInfosConsultaPorMedico(Medico medico) {
        InfoConsulta[] infosMedico = new InfoConsulta[0];
        for (InfoConsulta info : infos) {
            if (info.getMedico().equals(medico)) {
            	infosMedico = Arrays.copyOf(infosMedico, infosMedico.length + 1);
            	infosMedico[infosMedico.length - 1] = info;
            	}
            	}
            	return infosMedico;
            	}
    public static InfoConsulta[] listarInfosConsultaPorPaciente(Pessoa paciente) {
        InfoConsulta[] infosPaciente = new InfoConsulta[0];
        for (InfoConsulta info : infos) {
            if (info.getPaciente().equals(paciente)) {
                infosPaciente = Arrays.copyOf(infosPaciente, infosPaciente.length + 1);
                infosPaciente[infosPaciente.length - 1] = info;
            }
        }
        return infosPaciente;
    }

    public static InfoConsulta[] listarInfosConsulta() {
        return infos;
    }

    public static double calcularGastosMedicoPorPeriodo(Medico medico, Date dataInicio, Date dataFim) {
        double gastos = 0;
        for (InfoConsulta info : infos) {
            if (info.getMedico().equals(medico) && info.getData().after(dataInicio) && info.getData().before(dataFim)) {
                gastos += info.getValor();
            }
        }
        return gastos;
    }

    public static double calcularGastosUnidadePorPeriodo(Unidade unidade, Date dataInicio, Date dataFim) {
        double gastos = 0;
        for (InfoConsulta info : infos) {
            if (info.getUnidade().equals(unidade) && info.getData().after(dataInicio) && info.getData().before(dataFim)) {
                gastos += info.getValor();
            }
        }
        return gastos;
    }
}

