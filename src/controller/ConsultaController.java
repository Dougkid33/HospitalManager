package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.Consulta;
import model.Medico;
import model.Pessoa;
import model.Unidade;
import model.DAO.ConsultaDAO;
import model.enums.EstadoConsulta;
public class ConsultaController {
	public static void cadastrarConsulta(Date data, String hora, EstadoConsulta estadoConsulta, Medico medico, Pessoa paciente, double valor, Unidade unidade) {
	    ConsultaDAO.cadastrarConsulta(data, hora, estadoConsulta, medico, paciente, valor, unidade);
	    System.out.println("Consulta cadastrada com sucesso!");
	}

	public static void atualizarConsulta(int id, Date data, String hora, EstadoConsulta estado, Medico medico, Pessoa paciente, double valor, Unidade unidade) {
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
	
	public static void menuConsulta() {
        int opcao;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("----- MENU -----");
            System.out.println("1. Cadastrar consulta");
            System.out.println("2. Atualizar consulta");
            System.out.println("3. Remover consulta");
            System.out.println("4. Buscar consulta");
            System.out.println("5. Listar consultas");
            System.out.println("6. Listar consultas por médico");
            System.out.println("7. Listar consultas por paciente");
            System.out.println("0. Sair");
            System.out.print("Digite a opção desejada: ");
            opcao = input.nextInt();
            input.nextLine(); // consome a quebra de linha após a opção digitada

            switch (opcao) {
                case 1:
                	EstadoConsulta estadoConsulta = null;
                	System.out.println("----- CADASTRO DE CONSULTA -----");
                    System.out.print("Digite a data da consulta (dd/mm/aaaa): ");
                    String dataConsultaStr = input.nextLine();
                    Date dataConsulta = null;
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        dataConsulta = dateFormat.parse(dataConsultaStr);
                    } catch (ParseException e) {
                        System.out.println("Data inválida!");
                        return;
                    }
                    System.out.print("Digite a hora da consulta (hh:mm): ");
                    String horaConsulta = input.nextLine();
                    System.out.print("Digite o CRM do médico: ");
                    int crmMedico = input.nextInt();
                    MedicoController medicoController = new MedicoController(null);
                    Medico medico = medicoController.buscarMedicoPorCRM(crmMedico);
                    if (medico == null) {
                        System.out.println("Médico não encontrado.");
                        return;
                    }
                    System.out.print("Digite o CPF do paciente: ");
                    String cpfPaciente = input.nextLine();
                    Pessoa paciente = PessoaController.buscarPessoaPorCpf(cpfPaciente);
                    if (paciente == null) {
                        System.out.println("Paciente não encontrado.");
                        return;
                    }
                    System.out.print("Digite o valor da consulta: ");
                    double valorConsulta = input.nextDouble();
                    input.nextLine();
                    System.out.print("Digite o nome da unidade onde será realizada a consulta: ");
                    int idUnidade = input.nextInt();
                    UnidadeController unidadeController = new UnidadeController(null);
                    Unidade unidade = unidadeController.buscarUnidade(idUnidade);
                    if (unidade == null) {
                        System.out.println("Unidade não encontrada.");
                        return;
                    }
                    ConsultaController.cadastrarConsulta(dataConsulta, horaConsulta, estadoConsulta, medico, paciente, valorConsulta, unidade);
                    System.out.println("Consulta cadastrada com sucesso!");
                    estadoConsulta = EstadoConsulta.AGENDADA;
                    break;
                case 2:
                	EstadoConsulta estadoConsultaEdit = null;
                    System.out.println("----- ATUALIZAÇÃO DE CONSULTA -----");
                    System.out.print("Digite o código da consulta a ser atualizada: ");
                    int idConsulta = input.nextInt();
                    input.nextLine(); // consome a quebra de linha após o código digitado
                    Consulta consulta = ConsultaController.buscarConsulta(idConsulta);
                    if (consulta == null) {
                        System.out.println("Consulta não encontrada.");
                        return;
                    }
                    SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
                    System.out.print("Digite a nova data da consulta (dd/mm/aaaa) ou deixe em branco para manter o valor atual): ");
                    String newDataConsultaStr = input.nextLine();
                    if (!newDataConsultaStr.isBlank()) {
                        Date newDataConsulta = dateFormat.parse(newDataConsultaStr);
                        consulta.setDataCriacao(newDataConsulta);
                    }
                    System.out.print("Digite a nova hora da consulta (hh:mm) ou deixe em branco para manter o valor atual (" + consulta.getHora() + "): ");
                    String horaConsultaedit = input.nextLine();
                    if (!horaConsulta.isBlank()) {
                        consulta.setHora(horaConsultaedit);
                    }
                    System.out.print("Digite o novo estado da consulta (1 - Agendada, 2 - Realizada, 3 - Cancelada) ou deixe em branco para manter o valor atual (" + consulta.getEstado() + "): ");
                    String opcaoEstadoConsultaStr = input.nextLine();
                    if (opcaoEstadoConsultaStr.isBlank()) {
                        estadoConsultaEdit = consulta.getEstado();
                    } else {
                        int opcaoEstadoConsulta = Integer.parseInt(opcaoEstadoConsultaStr);
                        switch (opcaoEstadoConsulta) {
                            case 1:
                                estadoConsultaEdit = EstadoConsulta.AGENDADA;
                                break;
                            case 2:
                                estadoConsultaEdit = EstadoConsulta.REALIZADA;
                                break;
                            case 3:
                                estadoConsultaEdit = EstadoConsulta.CANCELADA;
                                break;
                            default:
                                System.out.println("Opção inválida.");
                                return;
                        }
                    }
                    System.out.print("Digite o novo CRM do médico ou deixe em branco para manter o valor atual (" + consulta.getMedico().getCrm() + "): ");
                    int crmMedicoedit = input.nextInt();
                    input.nextLine();
                    if (crmMedicoedit != 0) {
                        MedicoController medicoControlleredit = new MedicoController(null);
                        Medico medicoedit = medicoControlleredit.buscarMedicoPorCRM(crmMedicoedit);
                        if (medicoedit == null) {
                            System.out.println("Médico não encontrado.");
                            return;
                        }
                        consulta.setMedico(medicoedit);
                    }
                    System.out.print("Digite o novo CPF do paciente ou deixe em branco para manter o valor atual (" + consulta.getPaciente().getCpf() + "): ");
                    String cpfPaciente = input.nextLine();
                    if (!cpfPaciente.isBlank()) {
                        Pessoa paciente = PessoaController.buscarPorCpf(cpfPaciente);
                        if (paciente == null) {
                            System.out.println("Paciente não encontrado.");
                            return;
                        }
                        consulta.setPaciente(paciente);
                    }
                    System.out.print("Digite o novo valor da consulta ou deixe em branco para manter o valor atual (" + consulta.getValorConsulta() + "): ");
                    String valorConsultaStr = input.nextLine();
                    if (!valorConsultaStr.isBlank()) {
                        double valorConsulta = Double.parseDouble(valorConsultaStr);
                        consulta.setValorConsulta(valorConsulta);
                    }
                    System.out.print("Digite o novo nome da unidade ou deixe em branco para manter o valor atual (" + consulta.getUnidade().getNome() + "): ");
                    String nomeUnidade = input.nextLine();
                    if (!nomeUnidade.isBlank()) {
                        Unidade unidade = UnidadeController.buscarPorNome(nomeUnidade);
                        if (unidade == null) {
                            System.out.println("Unidade não encontrada.");
                            return;
                        }
                        consulta.setUnidade(unidade);
                    }
                    System.out.println("Consulta atualizada com sucesso!");
                    break;
                case 3:
                    removerConsulta(opcao);
                    break;
                case 4:
                    buscarConsulta();
                    break;
                case 5:
                    listarConsultas();
                    break;
                case 6:
                    listarConsultasPorMedico();
                    break;
                case 7:
                    listarConsultasPorPaciente();
                    break;
                case 0:
                    System.out.println("Saindo do programa.");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (opcao != 0);
		
	}
}
