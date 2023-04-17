package controller;

import java.util.Date;
import model.Consulta;
import model.Medico;
import model.Pessoa;
import model.Unidade;
import model.ConsultaDAO;
public class ConsultaController {
	public static void cadastrarConsulta(Date data, String hora, String estado, Medico medico, Pessoa paciente, double valor, Unidade unidade) {
	    ConsultaDAO.cadastrarConsulta(data, hora, estado, medico, paciente, valor, unidade);
	}

	public static void atualizarConsulta(int id, Date data, String hora, String estado, Medico medico, Pessoa paciente, double valor, Unidade unidade) {
	    ConsultaDAO.atualizarConsulta(id, data, hora, estado, medico, paciente, valor, unidade);
	}

	public static void removerConsulta(int id) {
	    ConsultaDAO.removerConsulta(id);
	}

	public static Consulta buscarConsulta(int id) {
	    return ConsultaDAO.buscarConsulta(id);
	}

	public static Consulta[] listarConsultas() {
	    return ConsultaDAO.listarConsultas();
	}

	public static Consulta[] listarConsultasPorMedico(Medico medico) {
	    return ConsultaDAO.listarConsultasPorMedico(medico);
	}

	public static Consulta[] listarConsultasPorPaciente(Pessoa paciente) {
	    return ConsultaDAO.listarConsultasPorPaciente(paciente);
	}
}
