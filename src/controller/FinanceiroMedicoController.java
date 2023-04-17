package controller;
import model.FinanceiroMedico;
import model.FinanceiroMedicoDAO;
public class FinanceiroMedicoController {
	public static void cadastrarFinanceiroMedico(double valorMedico, String estado, double franquia) {
	    FinanceiroMedicoDAO.cadastrarFinanceiroMedico(valorMedico, estado, franquia);
	}

	public static void atualizarFinanceiroMedico(int id, double valorMedico, String estado, double franquia) {
	    FinanceiroMedicoDAO.atualizarFinanceiroMedico(id, valorMedico, estado, franquia);
	}

	public static void removerFinanceiroMedico(int id) {
	    FinanceiroMedicoDAO.removerFinanceiroMedico(id);
	}

	public static FinanceiroMedico[] listarFinanceirosMedicos() {
	    return FinanceiroMedicoDAO.listarFinanceirosMedicos();
	}

	public static double calcularMontantePagoMedicoNoUltimoMes() {
	    return FinanceiroMedicoDAO.calcularMontantePagoMedicoNoUltimoMes();
	}
}
