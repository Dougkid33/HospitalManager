package controller;

import model.DAO.FinanceiroMedicoDAO;

import java.util.Calendar;
import java.util.Scanner;

import model.FinanceiroMedico;

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

    private static void pagarAdministradora() {
        int mesAtual = calendar.get(Calendar.MONTH) + 1; // Adiciona 1 para exibir o mês atual corretamente
        if (mesAtual == DIA_PAGAMENTO) {
            double faturamentoTotal = FinanceiroMedicoDAO.calcularMontantePagoMedicoNoUltimoMes();
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
                    String estado = scanner.next();
                    System.out.println("Digite a franquia:");
                    double franquia = scanner.nextDouble();
                    FinanceiroMedicoDAO.cadastrarFinanceiroMedico(valorMedico, estado, franquia);
                    break;
                case 2:
                    System.out.println("Digite o id:");
                    int id = scanner.nextInt();
                    System.out.println("Digite o valor médico:");
                    double valorMedicoedit = scanner.nextDouble();
                    System.out.println("Digite o estado:");
                    String estadoedit = scanner.next();
                    System.out.println("Digite a franquia:");
                    double franquiaedit = scanner.nextDouble();
                    FinanceiroMedicoDAO.atualizarFinanceiroMedico(id, valorMedicoedit, estadoedit, franquiaedit);
                    break;
                case 3:
                    System.out.println("Digite o id:");
                    int idRemove = scanner.nextInt();
                    FinanceiroMedicoDAO.removerFinanceiroMedico(idRemove);
                    break;
                case 4:
                    FinanceiroMedico[] financeirosMedicos = FinanceiroMedicoDAO.listarFinanceirosMedicos();
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
