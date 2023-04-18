package controller;

import java.util.Date;
import model.Consulta;
import model.Medico;
import model.Pessoa;
import model.Unidade;
import model.DAO.ConsultaDAO;
public class ConsultaController {
	public static void cadastrarConsulta(Date data, String hora, String estado, Medico medico, Pessoa paciente, double valor, Unidade unidade) {
	    ConsultaDAO.cadastrarConsulta(data, hora, estado, medico, paciente, valor, unidade);
	    System.out.println("Consulta cadastrada com sucesso!");
	}

	public static void atualizarConsulta(int id, Date data, String hora, String estado, Medico medico, Pessoa paciente, double valor, Unidade unidade) {
	    ConsultaDAO.atualizarConsulta(id, data, hora, estado, medico, paciente, valor, unidade);
	    System.out.println("Consulta atualizada com sucesso!");
	}

	public static void removerConsulta(int id) {
	    ConsultaDAO.removerConsulta(id);
	    System.out.println("Consulta removida com sucesso!");
	}

	public static Consulta buscarConsulta(int id) {
	    Consulta consulta = ConsultaDAO.buscarConsulta(id);
	    if (consulta == null) {
	        System.out.println("Consulta não encontrada.");
	    }
	    return consulta;
	}

	public static Consulta[] listarConsultas() {
	    Consulta[] consultas = ConsultaDAO.listarConsultas();
	    if (consultas.length == 0) {
	        System.out.println("Não há consultas cadastradas.");
	    }
	    return consultas;
	}

	public static Consulta[] listarConsultasPorMedico(Medico medico) {
	    Consulta[] consultas = ConsultaDAO.listarConsultasPorMedico(medico);
	    if (consultas.length == 0) {
	        System.out.println("Não há consultas cadastradas para esse médico.");
	    }
	    return consultas;
	}

	public static Consulta[] listarConsultasPorPaciente(Pessoa paciente) {
	    Consulta[] consultas = ConsultaDAO.listarConsultasPorPaciente(paciente);
	    if (consultas.length == 0) {
	        System.out.println("Não há consultas cadastradas para esse paciente.");
	    }
	    return consultas;
	}
}
