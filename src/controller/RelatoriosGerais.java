package controller;

import java.util.Calendar;
import java.util.Date;

import static view.Main.exibirMenu;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import model.Consulta;
import model.Medico;
import view.Main;

public class RelatoriosGerais {
	Scanner input = new Scanner(System.in);

	public static void gerarRelatorioConsultas() {
		try (Scanner input = new Scanner(System.in)) {
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");

			System.out.println("----- RELATÓRIO DE CONSULTAS -----");

			// Escolha da data (opcional)
			System.out.print("Deseja filtrar por data? (S/N): ");
			String escolhaData = input.nextLine().trim();

			Date dataInicio = null;
			Date dataFim = null;

			if (escolhaData.equalsIgnoreCase("S")) {
				System.out.print("Digite a data de início (dd/mm/aaaa): ");
				String dataInicioStr = input.nextLine();

				System.out.print("Digite a data de fim (dd/mm/aaaa): ");
				String dataFimStr = input.nextLine();

				try {
					
					dataInicio = dateFormat1.parse(dataInicioStr);
					dataFim = dateFormat1.parse(dataFimStr);
				} catch (Exception e) {
					System.out.println("Formato de data inválido. Utilizando todas as consultas.");
				}
			}

			// Escolha do médico (opcional)
			System.out.print("Deseja filtrar por médico? (S/N): ");
			String escolhaMedico = input.nextLine().trim();
			Medico medico = null;

			if (escolhaMedico.equalsIgnoreCase("S")) {
				System.out.print("Digite o ID do médico: ");
				int idMedico = input.nextInt();
				input.nextLine(); // Consumir a quebra de linha após o nextInt

				MedicoController medicoController = new MedicoController();
				medico = medicoController.buscarMedico(idMedico);
			}

			System.out.println();

			Consulta[] consultas = ConsultaController.listarConsultasPorFiltro(dataInicio, dataFim, medico);

			if (consultas.length <= 0) {
				System.out.println("Não foram encontradas consultas com os filtros selecionados.");
			} else {
				System.out.println("Consultas encontradas:");
				for (Consulta consultalista : consultas) {
					System.out.println("ID: " + consultalista.getId());
					System.out.println("Data: " + consultalista.getData());
					System.out.println("Hora: " + consultalista.getHora());
					System.out.println("Estado: " + consultalista.getEstado());
					System.out.println("Médico: " + consultalista.getMedico().getNome());
					System.out.println("Paciente: " + consultalista.getPaciente().getNome());
					System.out.println("Valor: " + consultalista.getValor());
					System.out.println("Unidade: " + consultalista.getUnidade().getNome());
					System.out.println("------------------------------------");
				}
			}
		}
	}
	public static void relatorioADM() {
		try (Scanner scanner = new Scanner(System.in)) {
			boolean sair = false;
			int opcaoMenu = -1;

			do {
				System.out.println("----- Relatorio Administrativo -----");
				System.out.println("1. Relatório de pagamento de Medico - SOMENTE DIA 01 Mensal ");
				System.out.println("2. Relatório Financeiro da Unidade");
				System.out.println("0. Sair");
				System.out.print("Digite a opção desejada: ");
				opcaoMenu = scanner.nextInt();
				scanner.nextLine(); // para consumir a quebra de linha deixada pelo nextInt

				switch (opcaoMenu) {
				case 1: {
					Medico medico = null;
					 // Obter a data atual
			        Date dataAtual = new Date();

			        // Verificar se é dia 01 do mês
			        Calendar calendar = Calendar.getInstance();
			        calendar.setTime(dataAtual);
			        if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
			            MedicoController medicoController = new MedicoController();
			            double montanteTotalPago = medicoController.calcularMontantePagoUltimoMes(medico);
			            System.out.println("Montante total pago ao médico no último mês: R$ " + montanteTotalPago+ " .");
			        }
					break;
				}
				case 2: {
					break;
				}
				case 0: {
					sair = true;
					Main.exibirMenu();
				}
				}

			} while (!sair);
		}
	}

	public static void menuRelatorio() {
		try (Scanner scanner = new Scanner(System.in)) {
			boolean sair = false;
			int opcaoMenu = -1;

			do {
				System.out.println("----- Menu de Relatórios -----");
				System.out.println("1. Relatório de Consultas");
				System.out.println("2. Relatório Financeiro");
				System.out.println("0. Sair");
				System.out.print("Digite a opção desejada: ");
				opcaoMenu = scanner.nextInt();
				scanner.nextLine(); // para consumir a quebra de linha deixada pelo nextInt

				switch (opcaoMenu) {
				case 1: {
					gerarRelatorioConsultas();
					break;
				}
				case 2: {
					relatorioADM();
					break;
				}
				case 0: {
					sair = true;
					break;}
				 default:
                     System.out.println("Opção inválida.");
             }
             exibirMenu();

			} while (!sair);
		}

	}

}
