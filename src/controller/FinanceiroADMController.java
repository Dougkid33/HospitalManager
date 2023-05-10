package controller;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import model.Consulta;
import model.FinanceiroADM;
import model.Procedimento;
import model.DAO.FinanceiroADMDAO;
import model.enums.TipoMovimento;

public class FinanceiroADMController {

    public static void cadastrarFinanceiro(TipoMovimento tipoMovimento, double valor, String unidade, String descritivoMovimento) {
        FinanceiroADMDAO.cadastrarFinanceiro(tipoMovimento, valor, unidade, descritivoMovimento);
    }

    public static void atualizarFinanceiro(int id, TipoMovimento tipoMovimento, double valor, String unidade, String descritivoMovimento) {
        FinanceiroADMDAO.atualizarFinanceiro(id, tipoMovimento, valor, unidade, descritivoMovimento);
    }

    public static void removerFinanceiro(int id) {
        FinanceiroADMDAO.removerFinanceiro(id);
    }

    public static void listarFinanceiros() {
        FinanceiroADM[] financeiros = FinanceiroADMDAO.listarFinanceiros();
        if (financeiros.length == 0) {
            System.out.println("Não há registros de movimentos financeiros.");
        } else {
            System.out.println("Lista de movimentos financeiros:");
            for (FinanceiroADM financeiro : financeiros) {
                System.out.println(financeiro);
            }
        }
    }

    private static FinanceiroADM buscarFinanceiroPorId(int id) {
        FinanceiroADM[] financeiros = FinanceiroADMDAO.listarFinanceiros();
        for (FinanceiroADM financeiro : financeiros) {
            if (financeiro.getId() == id) {
                return financeiro;
            }
        }
        return null;
    }

    public static void realizarPagamentoAdministradora() {
        // Obter a data atual
        Date dataAtual = new Date();

        // Verificar se é dia 01 do mês
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataAtual);
        if (calendar.get(Calendar.DAY_OF_MONTH) != 1) {
            return;
        }

        // Calcular o valor a ser pago
        double valorBase = 1000.0;
        double faturamentoTotal = 0.0;
        for (Consulta consulta : ConsultaController.listarConsultas()) {
            faturamentoTotal += consulta.getValor();
        }
        for (Procedimento procedimento : ProcedimentoController.listarProcedimentos()) {
            faturamentoTotal += procedimento.getValor();
        }
        double valorPagamento = valorBase + (faturamentoTotal * 0.05);

        // Registrar a movimentação financeira
        String unidade = "Administração";
        String descritivoMovimento = "Pagamento à administradora";
        FinanceiroADMController.cadastrarFinanceiro(TipoMovimento.SAIDA, valorPagamento, unidade, descritivoMovimento);

        // Exibir mensagem de confirmação
        System.out.println("Pagamento à administradora realizado com sucesso!");
    }
    public static void cadastrarFinanceirosADMAleatorios() {

        for (int i = 0; i < 10; i++) {
            FinanceiroADM financeiroADM = FinanceiroADM.gerarFinanceiroADMAleatorio();
            FinanceiroADMDAO.cadastrarFinanceiro(financeiroADM.getTipoMovimento(), financeiroADM.getValor(),
                    financeiroADM.getUnidade(), financeiroADM.getDescritivoMovimento());
        }
    }

    public static void menuFinanceiroADM() {

        try (Scanner scanner = new Scanner(System.in)) {
            int opcao = 0;
            do {// verifica toda vez se eh o primeiro dia do mes para realizar o pagamento
                Calendar calendar = Calendar.getInstance();
                if (calendar.get(Calendar.DAY_OF_MONTH) != 1) {
                    realizarPagamentoAdministradora();
                }

                System.out.println("Selecione uma opção:");
                System.out.println("1 - Cadastrar financeiro");
                System.out.println("2 - Atualizar financeiro");
                System.out.println("3 - Remover financeiro");
                System.out.println("4 - Listar financeiros");
                System.out.println("0 - Sair");

                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        TipoMovimento tipoMovimento = null;
                        System.out.print("Tipo do movimento// Digite [1] - Entrada ou  [2] - Saida:\n ");
                        int tipoMov = scanner.nextInt();

                        if (tipoMov == 1) {
                            tipoMovimento = TipoMovimento.ENTRADA;
                        } else if (tipoMov == 2) {
                            tipoMovimento = TipoMovimento.SAIDA;
                        }

                        System.out.print("Valor: ");
                        double valor = scanner.nextDouble();
                        scanner.nextLine();

                        System.out.print("Unidade: ");
                        String unidade = scanner.nextLine();

                        System.out.print("Descritivo do movimento: ");
                        String descritivoMovimento = scanner.nextLine();

                        FinanceiroADMDAO.cadastrarFinanceiro(tipoMovimento, valor, unidade, descritivoMovimento);

                        System.out.println("Financeiro cadastrado com sucesso!");
                        break;
                    case 2:
                        System.out.print("ID do financeiro a ser atualizado: ");
                        int idedit = scanner.nextInt();
                        scanner.nextLine();

                        FinanceiroADM financeiro = buscarFinanceiroPorId(idedit);
                        if (financeiro == null) {
                            System.out.println("Financeiro não encontrado!");
                            return;
                        }

                        TipoMovimento tipoMovimentoedit = null;
                        System.out.print("Digite o tipo do movimento// Digite 1 - Entrada ou  2 -Saida:\n ");
                        int tipoMovedit = scanner.nextInt();

                        if (tipoMovedit == 1) {
                            tipoMovimentoedit = TipoMovimento.ENTRADA;
                        } else if (tipoMovedit == 2) {
                            tipoMovimentoedit = TipoMovimento.SAIDA;
                        }

                        System.out.print("Valor (" + financeiro.getValor() + "): ");
                        double valoredit = scanner.nextDouble();
                        scanner.nextLine();

                        System.out.print("Unidade (" + financeiro.getUnidade() + "): ");
                        String unidadeedit = scanner.nextLine();

                        System.out.print("Descritivo do movimento (" + financeiro.getDescritivoMovimento() + "): ");
                        String descritivoMovimentoedit = scanner.nextLine();

                        FinanceiroADMDAO.atualizarFinanceiro(idedit, tipoMovimentoedit, valoredit, unidadeedit, descritivoMovimentoedit);

                        System.out.println("Financeiro atualizado com sucesso!");
                        break;
                    case 3:
                        System.out.print("ID do financeiro a ser removido: ");
                        int idremove = scanner.nextInt();
                        scanner.nextLine();

                        FinanceiroADM financeiroremove = buscarFinanceiroPorId(idremove);
                        if (financeiroremove == null) {
                            System.out.println("Financeiro não encontrado!");
                            return;
                        }

                        FinanceiroADMDAO.removerFinanceiro(idremove);

                        System.out.println("Financeiro removido com sucesso!");

                        break;
                    case 4:
                        FinanceiroADM[] financeiros = FinanceiroADMDAO.listarFinanceiros();
                        if (financeiros.length == 0) {
                            System.out.println("Nenhum financeiro cadastrado!");
                            return;
                        }
                        System.out.println("Lista de financeiros:");
                        for (FinanceiroADM financeiro1 : financeiros) {
                            System.out.println("ID: " + financeiro1.getId() + ", Tipo de movimento: "
                                    + financeiro1.getTipoMovimento() + ", Valor: " + financeiro1.getValor() + ", Unidade: "
                                    + financeiro1.getUnidade() + ", Descritivo do movimento: "
                                    + financeiro1.getDescritivoMovimento());
                        }
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } while (opcao != 0);
        }

    }
}
