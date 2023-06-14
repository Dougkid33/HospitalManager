package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.Consulta;
import model.Medico;
import model.Pessoa;
import model.Unidade;
import model.DAO.ConsultaDAO;
import model.enums.EstadoConsulta;
import model.enums.TipoMovimento;


import static view.Main.exibirMenu;

public class ConsultaController {

    public  void cadastrarConsulta(Date data, String hora, EstadoConsulta estadoConsulta, Medico medico, Pessoa paciente, double valor, Unidade unidade) {
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
    public static void cadastrarConsultasAleatorias() {
        ConsultaController consultacontroller = new ConsultaController(); // criando uma instância do controlador
        for (int i = 0; i < 2; i++) {
            Consulta consulta = Consulta.gerarConsultaAleatoria();
            consultacontroller.cadastrarConsulta(consulta.getData(), consulta.getHora(), consulta.getEstado(), consulta.getMedico(), consulta.getPaciente(), consulta.getValor(), consulta.getUnidade());
        }
    }
    
    public static Consulta[] listarConsultasPorFiltro(Date dataInicio, Date dataFim, Medico medico) {
        Consulta[] todasConsultas = listarConsultas();
        Consulta[] consultasFiltradas = new Consulta[todasConsultas.length];
        int contador = 0;

        for (Consulta consulta : todasConsultas) {
            if (consulta.getData().compareTo(dataInicio) >= 0 && consulta.getData().compareTo(dataFim) <= 0 &&
                    consulta.getMedico().equals(medico)) {
                consultasFiltradas[contador] = consulta;
                contador++;
            }
        }

        Consulta[] consultasFinalizadas = new Consulta[contador];
        System.arraycopy(consultasFiltradas, 0, consultasFinalizadas, 0, contador);

        return consultasFinalizadas;
    }
 
    @SuppressWarnings({ "unused", "null" })
	public static void menuConsulta() {
        int opcao;
        
        try (Scanner input = new Scanner(System.in)) {
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
                int idBuscaPessoa = 0;
                
                boolean permissao = false;
                
                if (opcao != 0) {
                    System.out.println("Digite o seu ID para conferir permissão: ");
                    int idBuscaMedico = input.nextInt();
                    input.nextLine(); // para consumir a quebra de linha deixada pelo nextInt

                    MedicoController medicoController = new MedicoController();
                    Medico buscarMedico = medicoController.buscarMedico(idBuscaMedico);

                    if (buscarMedico != null) {
                        if (buscarMedico.getCrm() >0 ) {
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
                case 1: if(!permissao) {
                	System.out.println("Permissão Negada!");
                }
                else {
                	
                	//CADASTRAR
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
                    
                    System.out.print("Digite o ID do médico: \n");
                    System.out.println("Listando todos os médicos: \n");
                    MedicoController medicoController = new MedicoController();
                    List listaMedicos = medicoController.listarMedicos();
                    if (listaMedicos.size() == 0) {
                        System.out.println("Não há médicos cadastrados.");
                    } else {
                        for (int i = 0; i < listaMedicos.size(); i++) {
                            System.out.println(listaMedicos);
                        }
                    }
                    int idMedico = input.nextInt();
                    
                    Medico medico = medicoController.buscarMedico(idMedico);
                    if (medico == null) {
                        System.out.println("Médico não encontrado.");
                        return;
                    }
                    input.nextLine(); // Limpar o buffer
                    
                    
                    
                    System.out.print("Digite o ID do paciente: ");
                    int idPaciente = input.nextInt();
                    PessoaController pessoaController = new PessoaController();
                    Pessoa paciente = pessoaController.buscarPessoaPorId(idPaciente);
                    if (paciente == null) {
                        System.out.println("Paciente não encontrado.");
                        return;
                    }
                    System.out.print("Digite o valor da consulta: ");
                    double valorConsulta = input.nextDouble();
                    input.nextLine(); // Limpar o buffer
                    System.out.print("Digite o ID da unidade onde será realizada a consulta: ");
                    int idUnidade = input.nextInt();
                    UnidadeController unidadeController = new UnidadeController();
                    Unidade unidade = unidadeController.buscarUnidade(idUnidade);
                    String unidadeNome = unidade.getNome();
                    if (unidade == null) {
                        System.out.println("Unidade não encontrada.");
                        return;
                    }
                    ConsultaController controller = new ConsultaController();
                    controller.cadastrarConsulta(dataConsulta, horaConsulta, estadoConsulta, medico, paciente, valorConsulta, unidade);
                    System.out.println("Consulta cadastrada com sucesso!");
                    estadoConsulta = EstadoConsulta.AGENDADA;

                    FinanceiroADMController.cadastrarFinanceiro(TipoMovimento.ENTRADA, valorConsulta, unidadeNome, "Consulta");
                }
                    break;
                    case 2://EDITAR
                        if(!permissao) {
                        	System.out.println("Permissão Negada!");
                        }
                        else {
                        	EstadoConsulta estadoConsultaEdit = null;
                            System.out.println("----- ATUALIZAÇÃO DE CONSULTA -----");
                            System.out.print("Digite o código da consulta a ser atualizada: ");
                            int idConsulta = input.nextInt();
                            input.nextLine(); // consome a quebra de linha após o código digitado
                            Consulta consulta = null;
                            consulta = ConsultaController.buscarConsulta(idConsulta);
                            if (consulta == null) {
                                System.out.println("Consulta não encontrada.");
                                return;
                            }
                            SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
                            System.out.print("Digite a nova data da consulta (dd/mm/aaaa) ou deixe em branco para manter o valor atual): ");
                            String newDataConsultaStr = input.nextLine();
                            if (!newDataConsultaStr.trim().isEmpty()) {
                                SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                                Date newDataConsulta = dateFormat1.parse(newDataConsultaStr);
                                consulta.setDataCriacao(newDataConsulta);
                            }
                            System.out.print("Digite a nova hora da consulta (hh:mm) ou deixe em branco para manter o valor atual (" + consulta.getHora() + "): ");
                            String horaConsulta1 = "";
                            String horaConsultaedit = input.nextLine();
                            if (!horaConsulta1.trim().isEmpty()) {
                                consulta.setHora(horaConsultaedit);
                            }
                            System.out.print("Digite o novo estado da consulta (1 - Agendada, 2 - Realizada, 3 - Cancelada) ou deixe em branco para manter o valor atual (" + consulta.getEstado() + "): ");
                            String opcaoEstadoConsultaStr = input.nextLine();
                            if (opcaoEstadoConsultaStr.trim().isEmpty()) {
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
                            System.out.print("Digite o novo ID do médico ou deixe em branco para manter o valor atual (" + consulta.getMedico().getId() + "): ");
                            int idMedicoedit = input.nextInt();
                            input.nextLine();
                            if (idMedicoedit != 0) {
                                MedicoController medicoControlleredit = new MedicoController();
                                Medico medicoedit = medicoControlleredit.buscarMedico(idMedicoedit);
                                if (medicoedit == null) {
                                    System.out.println("Médico não encontrado.");
                                    return;
                                }
                                consulta.setMedico(medicoedit);
                            }
                            System.out.print("Digite o novo ID do paciente ou deixe em branco para manter o valor atual (" + consulta.getPaciente().getId() + "): ");
                            int idPacienteedit = input.nextInt();
                            input.nextLine();
                            if (idPacienteedit != 0) {
                                PessoaController pessoaControlleredit = new PessoaController();
                                Pessoa pacienteedit = pessoaControlleredit.buscarPessoaPorId(idPacienteedit );
                                if (pacienteedit  == null) {
                                    System.out.println("Paciente não encontrado.");
                                    return;
                                }
                                consulta.setPaciente(pacienteedit );
                            }
                            System.out.print("Digite o novo valor da consulta ou deixe em branco para manter o valor atual (" + consulta.getValor() + "): ");
                            String valorConsultaStr = input.nextLine();
                            if (!valorConsultaStr.trim().isEmpty()) {
                                double valorConsultaedit = Double.parseDouble(valorConsultaStr);
                                consulta.setValor(valorConsultaedit);
                            }
                            System.out.print("Digite o ID da nova unidade ou deixe em branco para manter a Unidade atual (" + consulta.getUnidade().getNome() + "): ");
                            int idvaUnidade = input.nextInt();
                            if (idvaUnidade != 0) {
                                Unidade unidade1 = null;
                                UnidadeController unidadeControlleredit = new UnidadeController();
                                Unidade unidadeedit = unidadeControlleredit.buscarUnidade(idvaUnidade);
                                if (unidade1 == null) {
                                    System.out.println("Unidade não encontrada.");
                                    return;
                                }
                                consulta.setUnidade(unidade1);
                            }
                            System.out.println("Consulta atualizada com sucesso!");
                        }
                        
                        break;
                    case 3://EXCLUIR
                    	if(!permissao) {
                        	System.out.println("Permissão Negada!");
                        }
                        else {
                        	System.out.println("----- REMOÇÃO DE CONSULTA-----");
                            System.out.print("Digite o código da consulta a ser removida: ");
                            int idremoverConsulta = input.nextInt();
                            input.nextLine(); // consome a quebra de linha após o código digitado
                            Consulta consulta1 = null;
                            Consulta consultaremover = ConsultaController.buscarConsulta(idremoverConsulta);
                            if (consulta1 == null) {
                                System.out.println("Consulta não encontrada.");
                                return;
                            }
                            ConsultaController.removerConsulta(idremoverConsulta);
                            System.out.println("Consulta removida com sucesso!");
                        	
                        }
                        
                        break;
                    case 4:
                        System.out.println("----- BUSCAR CONSULTA -----");
                        System.out.print("Digite o código da consulta desejada: ");
                        int idBusca = input.nextInt();
                        input.nextLine(); // consome a quebra de linha após o número digitado
                        Consulta consultaEncontrada = ConsultaController.buscarConsulta(idBusca);
                        if (consultaEncontrada != null) {
                            System.out.println(consultaEncontrada);
                        }
                        break;
                    case 5://LISTAR
                        System.out.println("----- LISTA DE CONSULTAS -----");
                        Consulta[] consultas = ConsultaController.listarConsultas();
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
                        break;
                    case 6://BUSCAR
                        System.out.print("Digite o CRM do médico para buscar suas consultas: ");
                        int crm = input.nextInt();
                        MedicoController medicoControllerconsulta = new MedicoController();
                        Medico medicoConsultas = medicoControllerconsulta.buscarMedicoPorCRM(crm);
                        if (medicoConsultas == null) {
                            System.out.println("Médico não encontrado.");
                            return;
                        }
                        Consulta[] consultasMedico = ConsultaController.listarConsultasPorMedico(medicoConsultas);
                        for (Consulta consultaPorMedico : consultasMedico) {
                            System.out.println(consultaPorMedico.toString());
                        }
                        break;
                    case 7://BUSCAR POR PACIENTE
                        System.out.print("Digite o CPF do paciente (seu CPF) para buscar suas consultas: ");
                        String cpf = input.nextLine();
                        Consulta consulta11 = null;
                        Pessoa pacienteConsultas = PessoaController.buscarPessoaPorCpf(cpf);
                        if (pacienteConsultas == null) {
                            System.out.println("Paciente não encontrado.");
                            return;
                        }
                        Consulta[] consultas1 = ConsultaController.listarConsultas();
                        for (Consulta consultas11 : consultas1) {
                            if (consulta11.getPaciente().equals(pacienteConsultas)) {
                                System.out.println(consulta11.toString());
                            }
                        }
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            } while (opcao != 0);
            exibirMenu();
        } catch (NumberFormatException | ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
