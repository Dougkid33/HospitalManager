package model.DAO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import controller.FinanceiroADMController;
import java.time.ZoneOffset;
import model.Consulta;
import model.Medico;
import model.Pessoa;
import model.Unidade;
import model.enums.EstadoConsulta;
import model.enums.TipoMovimento;

public class ConsultaDAO {

    private static List<Consulta> consultas = new ArrayList<>();

    public static void cadastrarConsulta(LocalDateTime data, String hora, EstadoConsulta estadoConsulta, Medico medico, Pessoa paciente, double valor, Unidade unidade) {
        Consulta consulta = new Consulta( data, hora, estadoConsulta, medico, paciente, valor, unidade, LocalDateTime.now(), LocalDateTime.now());
        consultas.add(consulta);

        double entradaFranquia = consulta.calcularEntradaFranquia();
        FinanceiroADMController.cadastrarFinanceiro(TipoMovimento.ENTRADA, entradaFranquia, unidade.getNome(), "Consulta #" + consulta.getId());
    }

    public static void atualizarConsulta(int id, LocalDateTime data, String hora, EstadoConsulta estado, Medico medico, Pessoa paciente, double valor, Unidade unidade) {
        for (Consulta consulta : consultas) {
            if (consulta.getId() == id) {
                consulta.setData(data);
                consulta.setHora(hora);
                consulta.setEstado(estado);
                consulta.setMedico(medico);
                consulta.setPaciente(paciente);
                consulta.setValor(valor);
                consulta.setUnidade(unidade);
                consulta.setDataModificacao(LocalDateTime.now());
                break;
            }
        }
    }

    public static void removerConsulta(int id) {
        consultas.removeIf(consulta -> consulta.getId() == id);
    }

    public static Consulta buscarConsulta(int id) {
        for (Consulta consulta : consultas) {
            if (consulta.getId() == id) {
                return consulta;
            }
        }
        return null;
    }

    public static List<Consulta> listarConsultas() {
        return consultas;
    }

    public static List<Consulta> listarConsultasPorMedico(Medico medico) {
        List<Consulta> consultasMedico = new ArrayList<>();
        for (Consulta consulta : consultas) {
            if (consulta.getMedico().equals(medico)) {
                consultasMedico.add(consulta);
            }
        }
        return consultasMedico;
    }

    public static List<Consulta> pesquisarConsultasPorMedicoNoPeriodo(Medico medico, LocalDateTime dataInicio, LocalDateTime dataFim) {
        List<Consulta> consultasMedico = new ArrayList<>();
        for (Consulta consulta : consultas) {
            LocalDateTime dataConsulta = consulta.getData();
            if (consulta.getMedico().equals(medico) && dataConsulta.isAfter(dataInicio) && dataConsulta.isBefore(dataFim)) {
                consultasMedico.add(consulta);
            }
        }
        return consultasMedico;
    }

    public static List<Consulta> pesquisarConsultasPorMedicoNoPeriodo(Medico medico, long dataInicio, long dataFim) {
        List<Consulta> consultasMedico = new ArrayList<>();
        for (Consulta consulta : consultas) {
            LocalDateTime dataConsulta = consulta.getData();
            if (consulta.getMedico().equals(medico) && dataConsulta.toEpochSecond(ZoneOffset.UTC) >= dataInicio && dataConsulta.toEpochSecond(ZoneOffset.UTC) <= dataFim) {
                consultasMedico.add(consulta);
            }
        }
        return consultasMedico;
    }

    public static List<Consulta> listarConsultasPorPaciente(Pessoa paciente) {
        List<Consulta> consultasPaciente = new ArrayList<>();
        for (Consulta consulta : consultas) {
            if (consulta.getPaciente().equals(paciente)) {
                consultasPaciente.add(consulta);
            }
        }
        return consultasPaciente;
    }

    public static List<Consulta> listarConsultasPorFiltro(LocalDateTime dataInicio, LocalDateTime dataFim, Medico medico) {
        List<Consulta> consultasFiltradas = new ArrayList<>();
        for (Consulta consulta : consultas) {
            LocalDateTime dataConsulta = consulta.getData();
            if (dataConsulta.compareTo(dataInicio) >= 0 && dataConsulta.compareTo(dataFim) <= 0 && consulta.getMedico().equals(medico)) {
                consultasFiltradas.add(consulta);
            }
        }
        return consultasFiltradas;
    }
}
