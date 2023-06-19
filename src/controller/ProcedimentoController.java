package controller;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import model.Consulta;
import model.Medico;
import model.Procedimento;
import model.DAO.ProcedimentoDAO;
import model.enums.EstadoProcedimento;

import static view.Main.exibirMenu;

public class ProcedimentoController {

	public static void cadastrarProcedimento(String nome, int idConsulta, LocalDateTime diaHorario,
	        EstadoProcedimento estado, double valor, String laudo) {
	    ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();
	    Consulta consulta = ConsultaController.buscarConsulta(idConsulta);
	    procedimentoDAO.cadastrarProcedimento(nome, idConsulta, diaHorario, estado, valor, laudo);
	}

	// Ajuste o método cadastrarProcedimento existente para utilizar o novo método no DAO


	public static void atualizarProcedimento(int id, String nome, Consulta consulta, LocalDateTime diaHorario,
	        EstadoProcedimento estado, double valor, String laudo) {
	    ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();
	    procedimentoDAO.atualizarProcedimento(id, nome, consulta, diaHorario, estado, valor, laudo);
	}

	public static void removerProcedimento(int id) {
	    ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();
	    procedimentoDAO.removerProcedimento(id);
	}

	public static Procedimento buscarProcedimento(int id) {
	    ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();
	    return procedimentoDAO.buscarProcedimento(id);
	}

	public static List<Procedimento> listarProcedimentos() {
	    ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();
	    return procedimentoDAO.listarProcedimentos();
	}

	public static List<Procedimento> listarProcedimentosPorConsulta(Consulta consulta) {
	    ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();
	    return procedimentoDAO.listarProcedimentosPorConsulta(consulta);
	}

	public static List<Procedimento> pesquisarProcedimentosPorMedicoNoPeriodo(Consulta consulta, LocalDateTime dataInicio, LocalDateTime dataFim) {
	    ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();
	    return procedimentoDAO.pesquisarProcedimentosPorMedicoNoPeriodo(consulta, dataInicio, dataFim);
	}




    public static void menuProcedimento() throws ParseException {
    try (Scanner scanner = new Scanner(System.in)) {
        int opcao = 0;

        do {
            System.out.println("====== Menu Procedimento ======");
            System.out.println("1 - Cadastrar Procedimento");
            System.out.println("2 - Atualizar Procedimento");
            System.out.println("3 - Remover Procedimento");
            System.out.println("4 - Buscar Procedimento");
            System.out.println("5 - Listar Procedimentos");
            System.out.println("6 - Listar Procedimentos por Consulta");
            System.out.println("0 - Sair");
            System.out.print("Digite uma opção: ");
            opcao = scanner.nextInt();
            boolean permissao = false;

            if (opcao != 0) {
                System.out.println("Digite o seu ID para conferir permissão: ");
                int idBuscaMedico = scanner.nextInt();
                scanner.nextLine(); // para consumir a quebra de linha deixada pelo nextInt

                MedicoController medicoController = new MedicoController();
                Medico buscarMedico = medicoController.buscarMedico(idBuscaMedico);

                if (buscarMedico != null) {
                    if (buscarMedico.getCrm() > 0) {
                        permissao = true;
                        System.out.println("Permissão Medico concedida.");
                    } else {
                        System.out.println("Usuário não tem permissão.");
                    }
                } else {
                    System.out.println("ID de médico não encontrado.");
                }
            }
            switch (opcao) {
                case 1:
                    if (!permissao) {
                        System.out.println("Usuário não tem permissão.");
                    } else {
                    	System.out.println("== Cadastrar Procedimento ==");

                    	// Ler informações do usuário
                    	System.out.print("Nome: ");
                    	String nome = scanner.nextLine();
                    	System.out.print("ID da Consulta: ");
                    	int idConsulta = scanner.nextInt();
                    	System.out.print("Dia/Horário (dd/MM/yyyy HH:mm): ");
                    	String dataConsultaStr = scanner.nextLine();
                    	LocalDateTime diaHorario = null;
                    	try {
                    	    diaHorario = LocalDateTime.parse(dataConsultaStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                    	} catch (DateTimeParseException e) {
                    	    System.out.println("Data/hora inválida!");
                    	    return;
                    	}
                    	System.out.println("Digite o estado do Procedimento:\n"
                    	        + "1 - AGENDADO,\n"
                    	        + "2 - CANCELADO,\n"
                    	        + "3 - REALIZADO;");
                    	String estadoStr = scanner.next();

                    	EstadoProcedimento estado;
                    	try {
                    	    estado = EstadoProcedimento.valueOf(estadoStr);
                    	} catch (IllegalArgumentException e) {
                    	    System.out.println("Estado inválido");
                    	    return;
                    	}
                    	System.out.print("Valor: ");
                    	double valor = scanner.nextDouble();
                    	System.out.print("Laudo: ");
                    	String laudo = scanner.nextLine();

                    	// Chamar o método cadastrarProcedimento do controller
                    	ProcedimentoController.cadastrarProcedimento(nome, idConsulta, diaHorario, estado, valor, laudo);
                    }
                    break;
                case 2:
                    if (!permissao) {
                        System.out.println("Usuário não tem permissão.");
                    } else {
                        System.out.println("== Atualizar Procedimento ==");

                        // Ler informações do usuário
                        System.out.print("ID do Procedimento: ");
                        int id = scanner.nextInt();
                        System.out.print("Novo Nome: ");
                        String novoNome = scanner.nextLine();
                        System.out.print("ID da Consulta: ");
                        int novoIdConsulta = scanner.nextInt();
                        Consulta novoConsulta = ConsultaController.buscarConsulta(novoIdConsulta);
                        System.out.print("Dia/Horário (dd/MM/yyyy HH:mm): ");
                        String dataConsultaStr1 = scanner.nextLine();
                        LocalDateTime novoDiaHorario = null;
                        try {
                            novoDiaHorario = LocalDateTime.parse(dataConsultaStr1, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                        } catch (DateTimeParseException e) {
                            System.out.println("Data/hora inválida!");
                            return;
                        }
                        System.out.println("Digite o estado do Procedimento:     1 - AGENDADO,\r\n"
                                + "    2 - CANCELADO,\r\n" + "    3 - REALIZADO;");
                        String estadoStrEdit = scanner.next();

                        EstadoProcedimento estadoEdit;
                        try {
                            estadoEdit = EstadoProcedimento.valueOf(estadoStrEdit);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Estado inválido");
                            return;
                        }
                        System.out.print("Novo Valor: ");
                        double novoValor = scanner.nextDouble();
                        System.out.print("Novo Laudo: ");
                        String novoLaudo = scanner.nextLine();

                        // Chamar o método atualizarProcedimento do controller
                        ProcedimentoController.atualizarProcedimento(id, novoNome, novoConsulta, novoDiaHorario,
                                estadoEdit, novoValor, novoLaudo);
                    }

                    break;
                case 3:
                    if (!permissao) {
                        System.out.println("Usuário não tem permissão.");
                    } else {
                        System.out.println("== Remover Procedimento ==");

                        // Ler informações do usuário
                        System.out.print("ID do Procedimento: ");
                        int idProcedimento = scanner.nextInt();

                        // Chamar o método removerProcedimento do controller
                        ProcedimentoController.removerProcedimento(idProcedimento);
                    }

                    break;
                case 4:
                    System.out.println("== Buscar Procedimento ==");

                    // Ler informações do usuário
                    System.out.print("ID do Procedimento: ");
                    int idBusca = scanner.nextInt();

                    // Chamar o método buscarProced
                    Procedimento procedimento = ProcedimentoController.buscarProcedimento(idBusca);
                    if (procedimento != null) {
                        System.out.println("Procedimento encontrado:");
                        System.out.println(procedimento.toString());
                    } else {
                        System.out.println("Procedimento não encontrado.");
                    }
                    break;
                case 5:
                    System.out.println("== Listar Procedimentos ==");

                    // Chamar o método listarProcedimentos do controller
                    List <Procedimento> procedimentos = ProcedimentoController.listarProcedimentos();

                    if (procedimentos.size() > 0) {
                        for (Procedimento p : procedimentos) {
                            System.out.println(p.toString());
                        }
                    } else {
                        System.out.println("Não há procedimentos cadastrados.");
                    }

                    break;
                case 6:
                    System.out.println("== Listar Procedimentos por Consulta ==");

                    // Ler informações do usuário
                    System.out.print("ID da Consulta: ");
                    int idConsultaBusca = scanner.nextInt();
                    Consulta consultaBusca = ConsultaController.buscarConsulta(idConsultaBusca);

                    if (consultaBusca != null) {
                        // Chamar o método listarProcedimentosPorConsulta do controller
                        List <Procedimento> procedimentosConsulta = ProcedimentoController
                                .listarProcedimentosPorConsulta(consultaBusca);

                        if (procedimentosConsulta.size() > 0) {
                            for (Procedimento p : procedimentosConsulta) {
                                System.out.println(p.toString());
                            }
                        } else {
                            System.out.println("Não há procedimentos cadastrados para essa consulta.");
                        }
                    } else {
                        System.out.println("Consulta não encontrada.");
                    }
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
            exibirMenu();
        } while (opcao != 0);
    }
}
}
