package controller;

import model.DAO.FinanceiroMedicoDAO;
import model.enums.PgtoMedico;

import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import model.FinanceiroMedico;

public class FinanceiroMedicoController {

	public static void cadastrarFinanceiroMedico(double valorMedico, PgtoMedico estado, double franquia) {
	    FinanceiroMedicoDAO financeiroDAO = new FinanceiroMedicoDAO();
	    financeiroDAO.cadastrarFinanceiroMedico(valorMedico, estado, franquia);
	}

	public static void atualizarFinanceiroMedico(int id, double valorMedico, PgtoMedico estado, double franquia) {
	    FinanceiroMedicoDAO financeiroDAO = new FinanceiroMedicoDAO();
	    financeiroDAO.atualizarFinanceiroMedico(id, valorMedico, estado, franquia);
	}

	public static void removerFinanceiroMedico(int id) {
	    FinanceiroMedicoDAO financeiroDAO = new FinanceiroMedicoDAO();
	    financeiroDAO.removerFinanceiroMedico(id);
	}

	public static List<FinanceiroMedico> listarFinanceirosMedicos() {
	    FinanceiroMedicoDAO financeiroDAO = new FinanceiroMedicoDAO();
	    return financeiroDAO.listarFinanceirosMedicos();
	}


	public static double calcularMontantePagoMedicoNoUltimoMes() {
	    FinanceiroMedicoDAO financeiroDAO = new FinanceiroMedicoDAO();
	    return financeiroDAO.registrarMontantePagoAoMedico();
	}

	private static void pagarAdministradora() {
	    Calendar calendar = Calendar.getInstance();
	    int mesAtual = calendar.get(Calendar.MONTH) + 1; // Adiciona 1 para exibir o mês atual corretamente
	    if (mesAtual == DIA_PAGAMENTO) {
	        FinanceiroMedicoDAO financeiroDAO = new FinanceiroMedicoDAO();
	        double faturamentoTotal = financeiroDAO.registrarMontantePagoAoMedico();
	        double valorPagoAdministradora = VALOR_ADMINISTRADORA + PERCENTUAL_FATURAMENTO * faturamentoTotal;
	        System.out.println("Valor pago à administradora: R$" + valorPagoAdministradora);
	    } else {
	        System.out.println("Hoje não é dia de pagamento à administradora.");
	    }
	}
//    public static void menuFinanceiroMedico() {
//
//    }
    private static final int VALOR_ADMINISTRADORA = 1000;
    private static final double PERCENTUAL_FATURAMENTO = 0.05;
    private static final int DIA_PAGAMENTO = 1;

    private static Scanner scanner = new Scanner(System.in);
    private static Calendar calendar = Calendar.getInstance();
    
//    public void cadastrarFinanceirosMedicosAleatorios() {
//        for (int i = 0; i < 2; i++) {
//            FinanceiroMedico financeiroMedico = FinanceiroMedico.gerarFinanceiroMedicoAleatorio();
//            FinanceiroMedicoDAO.cadastrarFinanceiroMedico(financeiroMedico.getValorMedico(), financeiroMedico.getEstado(),
//                    financeiroMedico.getFranquia());
//        }
//    }

    public static void menuFinanceiroMedico() {
        boolean sair = false;
        while (!sair) {
            System.out.println("Digite a opção desejada:");
            System.out.println("1 - Cadastrar financeiro médico");
            System.out.println("2 - Atualizar financeiro médico");
            System.out.println("3 - Remover financeiro médico");
            System.out.println("4 - Listar financeiros médicos");
            System.out.println("5 - Calcular montante pago no último mês");
            System.out.println("6 - Pagar administradora");
            System.out.println("7 - Sair");
            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                	System.out.println("Digite o valor médico:");
                	double valorMedico = scanner.nextDouble();
                	System.out.println("Digite o estado:");
                	String estadoStr = scanner.next();
                	PgtoMedico estado;
                	try {
                	    estado = PgtoMedico.valueOf(estadoStr);
                	} catch (IllegalArgumentException e) {
                	    System.out.println("Estado inválido");
                	    return;
                	}
                	System.out.println("Digite a franquia:");
                	double franquia = scanner.nextDouble();
                	FinanceiroMedicoController.cadastrarFinanceiroMedico(valorMedico, estado, franquia);
                	break;

                case 2:
                	System.out.println("Digite o id:");
                	int id = scanner.nextInt();
                	System.out.println("Digite o valor médico:");
                	double valorMedicoedit = scanner.nextDouble();

                	System.out.println("Digite o estado:");
                	String estadoStrEdit = scanner.next();
                	PgtoMedico estadoEdit;
                	try {
                	    estadoEdit = PgtoMedico.valueOf(estadoStrEdit);
                	} catch (IllegalArgumentException e) {
                	    System.out.println("Estado inválido");
                	    return;
                	}

                	System.out.println("Digite a franquia:");
                	double franquiaedit = scanner.nextDouble();
                	FinanceiroMedicoController.atualizarFinanceiroMedico(id, valorMedicoedit, estadoEdit, franquiaedit);
                	break;

                case 3:
                    System.out.println("Digite o id:");
                    int idRemove = scanner.nextInt();
                    FinanceiroMedicoController.removerFinanceiroMedico(idRemove);
                    break;
                case 4:
                	List<FinanceiroMedico> financeirosMedicos = FinanceiroMedicoController.listarFinanceirosMedicos();
                	for (FinanceiroMedico financeiroMedico : financeirosMedicos) {
                	    System.out.println(financeiroMedico);
                	}
                	break;

                case 5:
                    calcularMontantePagoMedicoNoUltimoMes();
                    break;
                case 6:
                    pagarAdministradora();
                    break;
                case 7:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }
}
