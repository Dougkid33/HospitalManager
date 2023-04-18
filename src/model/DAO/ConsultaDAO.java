package model.DAO;

import java.util.Arrays;
import java.util.Date;

import model.Consulta;
import model.Medico;
import model.Pessoa;
import model.Unidade;



public class ConsultaDAO {

    private static Consulta[] consultas = new Consulta[0];
    private static int id = 1;

    public static void cadastrarConsulta(Date data, String hora, String estado, Medico medico, Pessoa paciente, double valor, Unidade unidade) {
        Consulta consulta = new Consulta(id++, data, hora, estado, medico, paciente, valor, unidade, new Date(), new Date());
        consultas = Arrays.copyOf(consultas, consultas.length + 1);
        consultas[consultas.length - 1] = consulta;
    }

    public static void atualizarConsulta(int id, Date data, String hora, String estado, Medico medico, Pessoa paciente, double valor, Unidade unidade) {
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
}

