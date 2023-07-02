package controller;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.Consulta;
import model.FinanceiroADM;
import model.Procedimento;
import model.Unidade;
import model.DAO.FinanceiroADMDAO;
import model.DAO.UnidadeDAO;
import model.enums.TipoMovimento;

public class FinanceiroADMController {

	public static void cadastrarFinanceiro(TipoMovimento tipoMovimento, double valor, Unidade unidade, String descritivoMovimento) {
	    FinanceiroADMDAO financeiroDAO = new FinanceiroADMDAO();
	    financeiroDAO.cadastrarFinanceiro(tipoMovimento, valor, unidade, descritivoMovimento);
	}

	public static void atualizarFinanceiro(int id, TipoMovimento tipoMovimento, double valor, int unidade,
			String descritivoMovimento) {
		FinanceiroADMDAO financeiroDAO = new FinanceiroADMDAO();
		financeiroDAO.atualizarFinanceiro(id, tipoMovimento, valor, unidade, descritivoMovimento);
	}

	public static void removerFinanceiro(int id) {
		FinanceiroADMDAO financeiroDAO = new FinanceiroADMDAO();
		financeiroDAO.removerFinanceiro(id);
	}

	public static List<FinanceiroADM> listarFinanceiros() {
		FinanceiroADMDAO financeiroDAO = new FinanceiroADMDAO();
		return financeiroDAO.listarFinanceiros();
	}

	public static FinanceiroADM buscarFinanceiroPorId(int id) {
		FinanceiroADMDAO financeiroDAO = new FinanceiroADMDAO();
		return financeiroDAO.buscarFinanceiroPorId(id);
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

		// Obter a Unidade pelo ID fornecido (por exemplo, ID = 1)
		int unidadeId = 0;
		UnidadeDAO unidadeDAO = new UnidadeDAO();
		Unidade unidade = unidadeDAO.buscarUnidade(unidadeId);

		// Registrar a movimentação financeira
		String descritivoMovimento = "Pagamento à administradora";
		cadastrarFinanceiro(TipoMovimento.SAIDA, valorPagamento, unidade, descritivoMovimento);

		// Exibir mensagem de confirmação
		System.out.println("Pagamento à administradora realizado com sucesso!");
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
					System.out.print("Tipo do movimento // Digite [1] - Entrada ou [2] - Saída: ");
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
					int unidadeId = scanner.nextInt();
					scanner.nextLine();

					// Obter a Unidade pelo ID fornecido
					UnidadeDAO unidadeDAO = new UnidadeDAO();
					Unidade unidade = unidadeDAO.buscarUnidade(unidadeId);
					System.out.println("\n Unidade encontrada" + unidade.toString());

					System.out.print("Descritivo do movimento: ");
					String descritivoMovimento = scanner.nextLine();

					FinanceiroADMController.cadastrarFinanceiro(tipoMovimento, valor, unidade, descritivoMovimento);

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
					System.out.print("Digite o tipo do movimento // Digite 1 - Entrada ou 2 - Saída: ");
					int tipoMovedit = scanner.nextInt();

					if (tipoMovedit == 1) {
						tipoMovimentoedit = TipoMovimento.ENTRADA;
					} else if (tipoMovedit == 2) {
						tipoMovimentoedit = TipoMovimento.SAIDA;
					}

					System.out.print("Valor (" + financeiro.getValor() + "): ");
					double valoredit = scanner.nextDouble();
					scanner.nextLine();

					System.out.print("Unidade (" + financeiro.getUnidade().getId() + "): ");
					int unidadeedit = scanner.nextInt();
					scanner.nextLine();

					// Obter a Unidade pelo ID fornecido
					UnidadeDAO unidadeDAO1 = new UnidadeDAO();
					Unidade unidade1 = unidadeDAO1.buscarUnidade(unidadeedit);

					System.out.print("Descritivo do movimento (" + financeiro.getDescritivoMovimento() + "): ");
					String descritivoMovimentoedit = scanner.nextLine();

					FinanceiroADMController.atualizarFinanceiro(idedit, tipoMovimentoedit, valoredit, unidade1.getId(),
							descritivoMovimentoedit);

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

					FinanceiroADMController.removerFinanceiro(idremove);

					System.out.println("Financeiro removido com sucesso!");

					break;
				case 4:
					List<FinanceiroADM> financeiros = FinanceiroADMController.listarFinanceiros();
					if (financeiros.isEmpty()) {
						System.out.println("Nenhum financeiro cadastrado!");
						return;
					}
					System.out.println("Lista de financeiros:");
					for (FinanceiroADM financeiro1 : financeiros) {
						System.out.println("ID: " + financeiro1.getId() + ", Tipo de movimento: "
								+ financeiro1.getTipoMovimento() + ", Valor: " + financeiro1.getValor() + ", Unidade: "
								+ financeiro1.getUnidade().getId() + ", Descritivo do movimento: "
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
