package model.DAO;

import java.util.Arrays;
import java.util.Date;

import controller.FinanceiroADMController;
import model.Consulta;
import model.Medico;
import model.Pessoa;
import model.Unidade;
import model.enums.EstadoConsulta;
import model.enums.TipoMovimento;

public class ConsultaDAO {

    private static Consulta[] consultas = new Consulta[0];
    private static int id = 1;

    public static void cadastrarConsulta(Date data, String hora, EstadoConsulta estadoConsulta, Medico medico, Pessoa paciente, double valor, Unidade unidade) {
        Consulta consulta = new Consulta(id++, data, hora, estadoConsulta, medico, paciente, valor, unidade, new Date(), new Date());
        consultas = Arrays.copyOf(consultas, consultas.length + 1);
        consultas[consultas.length - 1] = consulta;

        double entradaFranquia = consulta.calcularEntradaFranquia();
        FinanceiroADMController.cadastrarFinanceiro(TipoMovimento.ENTRADA, entradaFranquia, unidade.getNome(), "Consulta #" + consulta.getId());
    }

    public static void atualizarConsulta(int id, Date data, String hora, EstadoConsulta estado, Medico medico, Pessoa paciente, double valor, Unidade unidade) {
        for (int i = 0; i < consultas.length; i++) {
            if (consultas[i].getId() == id) {
                consultas[i].setData(data);
                consultas[i].setHora(hora);
                consultas[i].setEstado(estado);
                consultas[i].setMedico(medico);
                consultas[i].setPaciente(paciente);
                consultas[i].setValor(valor);
                consultas[i].setUnidade(unidade);
                consultas[i].setDataModificacao(new Date());
                break;
            }
        }
    }

    public static void removerConsulta(int id) {
        int removeIndex = -1;
        for (int i = 0; i < consultas.length; i++) {
            if (consultas[i].getId() == id) {
                removeIndex = i;
                break;
            }
        }
        if (removeIndex != -1) {
            for (int i = removeIndex; i < consultas.length - 1; i++) {
                consultas[i] = consultas[i + 1];
            }
            consultas = Arrays.copyOf(consultas, consultas.length - 1);
        }
    }

    public static Consulta buscarConsulta(int id) {
        for (Consulta consulta : consultas) {
            if (consulta.getId() == id) {
                return consulta;
            }
        }
        return null;
    }

    public static Consulta[] listarConsultas() {
        return consultas;
    }

    public static Consulta[] listarConsultasPorMedico(Medico medico) {
        Consulta[] consultasMedico = new Consulta[0];
        for (Consulta consulta : consultas) {
            if (consulta.getMedico().equals(medico)) {
                consultasMedico = Arrays.copyOf(consultasMedico, consultasMedico.length + 1);
                consultasMedico[consultasMedico.length - 1] = consulta;
            }
        }
        return consultasMedico;
    }
    public static Consulta[] pesquisarConsultasPorMedicoNoPeriodo(Medico medico, Date date, Date date2) {
        Consulta[] consultasMedico = new Consulta[0];
        for (Consulta consulta : consultas) {
            if (consulta.getMedico().equals(medico) && consulta.getData().after(date) && consulta.getData().before(date2)) {
                Consulta[] temp = new Consulta[consultasMedico.length + 1];
                System.arraycopy(consultasMedico, 0, temp, 0, consultasMedico.length);
                temp[consultasMedico.length] = consulta;
                consultasMedico = temp;
            }
        }
        return consultasMedico;
    }
    public static Consulta[] pesquisarConsultasPorMedicoNoPeriodo(Medico medico, long dataInicio, long dataFim) {
        Consulta[] consultasMedico = new Consulta[0];
        for (Consulta consulta : consultas) {
            if (consulta.getMedico().equals(medico) && consulta.getData().getTime() >= dataInicio && consulta.getData().getTime() <= dataFim) {
                Consulta[] temp = new Consulta[consultasMedico.length + 1];
                System.arraycopy(consultasMedico, 0, temp, 0, consultasMedico.length);
                temp[consultasMedico.length] = consulta;
                consultasMedico = temp;
            }
        }
        return consultasMedico;
    }
    public static Consulta[] listarConsultasPorPaciente(Pessoa paciente) {
        Consulta[] consultasPaciente = new Consulta[0];
        for (Consulta consulta : consultas) {
            if (consulta.getPaciente().equals(paciente)) {
                consultasPaciente = Arrays.copyOf(consultasPaciente, consultasPaciente.length + 1);
                consultasPaciente[consultasPaciente.length - 1] = consulta;
            }
        }
        return consultasPaciente;
    }
    
    public static Consulta[] listarConsultasPorFiltro(Date dataInicio, Date dataFim, Medico medico) {
        // Obtenha todas as consultas cadastradas
        Consulta[] todasConsultas = listarConsultas();

        // Vetor para armazenar as consultas filtradas
        Consulta[] consultasFiltradas = new Consulta[todasConsultas.length];
        int contador = 0;

        // Percorra todas as consultas
        for (Consulta consulta : todasConsultas) {
            // Verifique se a consulta está dentro do período especificado e se o médico é o desejado
            if (consulta.getData().compareTo(dataInicio) >= 0 && consulta.getData().compareTo(dataFim) <= 0 &&
                    consulta.getMedico().equals(medico)) {
                consultasFiltradas[contador] = consulta;
                contador++;
            }
        }

        // Redimensione o vetor para remover os espaços vazios
        Consulta[] consultasFinalizadas = new Consulta[contador];
        System.arraycopy(consultasFiltradas, 0, consultasFinalizadas, 0, contador);

        return consultasFinalizadas;
    }


}
