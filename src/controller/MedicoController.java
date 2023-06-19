package controller;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import model.Consulta;
import model.Medico;
import model.Pessoa;
import model.Procedimento;
import model.DAO.ConsultaDAO;
import model.DAO.MedicoDao;
import model.DAO.ProcedimentoDAO;
import view.Main;

import static view.Main.exibirMenu;

public class MedicoController {

    private MedicoDao medicoDao;

    ;

    public MedicoController() {
        medicoDao = new MedicoDao();
    }

    public boolean cadastrarMedico(String nome, String endereco, String cpf, String telefone, String login,
            String senha, String tipoUsuario, int crm, String especialidade) {
        return medicoDao.cadastrarMedico(nome, endereco, cpf, telefone, login, senha, tipoUsuario, crm, especialidade);
    }

    public boolean editarMedico(int id, String login, String novoNome, String novoEndereco, String novoCpf, String novoTelefone,
            int novoCrm, String novaEspecialidade) {
        return medicoDao.editarMedico(id, login, novoNome, novoEndereco, novoCpf, novoTelefone, novoCrm, novaEspecialidade);
    }

    public boolean excluirMedico(int id) {
        return medicoDao.excluirMedico(id);
    }

    public Medico buscarMedico(int id) {
        return medicoDao.buscarMedico(id);
    }

    public Medico buscarMedicoPorCRM(int crm) {
        return medicoDao.buscarMedicoPorCRM(crm);
    }

    public List<Medico> listarMedicos() {
        return medicoDao.listarMedicos();
    }

    public static void cadastrarMedicoAleatorias() {
        MedicoController controller = new MedicoController();
        for (int i = 0; i < 2; i++) {
            Medico medico = Medico.gerarMedicoAleatorio();
            controller.cadastrarMedico(medico.getNome(), medico.getEndereco(), medico.getCpf(), medico.getTelefone(), medico.getLogin(), medico.getTipoUsuario(), medico.getSenha(), medico.getCrm(), medico.getEspecialidade());
        }
    }

    public void pesquisarConsultasEProcedimentosUltimoMes(Medico medico) {
        // Obter a data atual
        Calendar dataAtual = Calendar.getInstance();

        // Definir a data de início como o primeiro dia do mês anterior
        Calendar dataInicio = Calendar.getInstance();
        dataInicio.set(Calendar.MONTH, dataAtual.get(Calendar.MONTH) - 1);
        dataInicio.set(Calendar.DAY_OF_MONTH, 1);
        dataInicio.set(Calendar.HOUR_OF_DAY, 0);
        dataInicio.set(Calendar.MINUTE, 0);
        dataInicio.set(Calendar.SECOND, 0);
        dataInicio.set(Calendar.MILLISECOND, 0);

        // Definir a data de fim como o último dia do mês anterior
        Calendar dataFim = Calendar.getInstance();
        dataFim.set(Calendar.MONTH, dataAtual.get(Calendar.MONTH) - 1);
        dataFim.set(Calendar.DAY_OF_MONTH, dataFim.getActualMaximum(Calendar.DAY_OF_MONTH));
        dataFim.set(Calendar.HOUR_OF_DAY, 23);
        dataFim.set(Calendar.MINUTE, 59);
        dataFim.set(Calendar.SECOND, 59);
        dataFim.set(Calendar.MILLISECOND, 999);

        // Pesquisar consultas do médico no último mês
        LocalDateTime dataInicioLocalDateTime = dataInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime dataFimLocalDateTime = dataFim.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        // Chamar o método com as datas convertidas
        List<Consulta> consultas = ConsultaDAO.pesquisarConsultasPorMedicoNoPeriodo(medico, dataInicioLocalDateTime, dataFimLocalDateTime);
        //List<Consulta> consultas = ConsultaDAO.pesquisarConsultasPorMedicoNoPeriodo(medico, dataInicio.getTime(), dataFim.getTime());
        if (consultas.size() == 0) {
            System.out.println("Nenhuma consulta encontrada no último mês para o médico " + medico.getNome());
        } else {
            System.out.println("Consultas do médico " + medico.getNome() + " no último mês:");
            for (Consulta consulta : consultas) {
                System.out.println(consulta);
            }
        }
    }

    @SuppressWarnings("null")
    public double calcularMontantePagoUltimoMes(Medico medico) {
        Consulta consultas = null;
        // Obter a data atual
        Date dataAtual = new Date(0);
        consultas.getMedico().equals(medico);

        // Calcular a data de início e fim do último mês
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataAtual);
        calendar.add(Calendar.MONTH, -1); // Subtrai 1 mês da data atual
        Date dataInicio = calendar.getTime();

        calendar.add(Calendar.MONTH, 1); // Adiciona 1 mês para obter o último dia do mês anterior
        calendar.add(Calendar.DAY_OF_MONTH, -1); // Subtrai 1 dia para obter o último dia do mês anterior
        Date dataFim = calendar.getTime();

        // Pesquisar consultas e procedimentos no último mês
        // Converter as datas para LocalDateTime
        LocalDateTime dataInicioLocalDateTime = dataInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime dataFimLocalDateTime = dataFim.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        // Chamar o método com as datas convertidas
        List<Consulta> consultasUltimoMes = ConsultaDAO.pesquisarConsultasPorMedicoNoPeriodo(medico, dataInicioLocalDateTime, dataFimLocalDateTime);

        //Consulta[] consultasUltimoMes = ConsultaDAO.pesquisarConsultasPorMedicoNoPeriodo(medico, dataInicio.getTime(), dataFim.getTime());
        List<Procedimento> procedimentosUltimoMes = null;
		try {
			procedimentosUltimoMes = ProcedimentoController.pesquisarProcedimentosPorMedicoNoPeriodo(consultas, dataInicioLocalDateTime, dataFimLocalDateTime);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Calcular o montante total pago ao médico
        double montanteTotalPago = 0.0;
        for (Consulta consulta : consultasUltimoMes) {
            montanteTotalPago += consulta.getValor() * 0.7; // Médico ganha 70% do valor da consulta
        }
        for (Procedimento procedimento : procedimentosUltimoMes) {
            montanteTotalPago += procedimento.getValor() * 0.5; // Médico ganha 50% do valor do procedimento
        }

        return montanteTotalPago;
    }

    public static void menuMedico() throws ParseException {
        try (Scanner sc = new Scanner(System.in)) {
            MedicoController medicoController = new MedicoController();
            boolean sair = false;

            //Pessoa pessoaLogin = new Pessoa( "Administrador", "Xablau", "validador", "33334380", "admin", "12345", "DonoFranquia", null, null);
            //System.out.println("ID da pessoa logada: " + pessoaLogin.getId());
            //System.out.println("Tipo de usuário da pessoa logada: " + pessoaLogin.getTipoUsuario());
            while (!sair) {
                System.out.println("\n==============================");
                System.out.println("       MENU DE OPERAÇÕES MÉDICO");
                System.out.println("==============================");
                System.out.println("1. Cadastrar Médico");
                System.out.println("2. Editar Médico");
                System.out.println("3. Buscar Médico");
                System.out.println("4. Remover Médico");
                System.out.println("5. Listar Todos Médicos");
                System.out.println("0. Sair");

                try {
                    int id = 0;
                    int idBuscaPessoa = 0;
                    boolean permissao = false;
                    int opcao = sc.nextInt();
                    sc.nextLine(); // para consumir a quebra de linha deixada pelo nextInt

                    if (opcao != 0) {
                        System.out.println("Digite o seu ID para conferir permissão: ");
                        idBuscaPessoa = sc.nextInt();
                        sc.nextLine(); // para consumir a quebra de linha deixada pelo nextInt
                        //PessoaController pessoaController = new PessoaController();
                        Pessoa buscarPessoa = null; // Inicializa o objeto como nulo

                        // Aqui você precisa buscar a pessoa pelo ID informado
                        if (Main.pessoa != null && Main.pessoa.getId() == idBuscaPessoa) {
                            buscarPessoa = Main.pessoa;
                        }

                        if (buscarPessoa != null) {
                            if (buscarPessoa.getTipoUsuario().equals("DonoFranquia") || buscarPessoa.getTipoUsuario().equals("DonoUnidade")) {
                                permissao = true;
                                System.out.println("Permissão Total.");
                            } else {
                                System.out.println("Usuário não tem permissão.");
                            }
                        } else {
                            System.out.println("ID de pessoa não encontrado.");
                        }
                    }

                    switch (opcao) {
                        case 1: //CADASTRAR
                            if (!permissao) {
                                System.out.println("Você não tem permissão para realizar esta ação.");
                            } else {
                                System.out.print("Digite o nome do médico: ");
                                String nome = sc.nextLine();

                                System.out.print("Digite o endereço do médico: ");
                                String endereco = sc.nextLine();

                                System.out.print("Digite o CPF do médico: ");
                                String cpf = sc.nextLine();

                                System.out.print("Digite o telefone do médico: ");
                                String telefone = sc.nextLine();

                                System.out.print("Digite o login do médico: ");
                                String login = sc.nextLine();

                                System.out.print("Digite a senha do médico: ");
                                String senha = sc.nextLine();

                                System.out.print("Digite O CRM do médico: ");
                                int crm = sc.nextInt();
                                sc.nextLine();

                                System.out.print("sc.nextLine();Digite a especialidade do médico: ");
                                String especialidade = sc.nextLine();

                                String tipoUsuario = "Médico";
                                boolean cadastrado = medicoController.cadastrarMedico(nome, endereco, cpf, login, senha,
                                        telefone, tipoUsuario, crm, especialidade);
                                if (cadastrado) {
                                    System.out.println("Médico  cadastrado com sucesso.");
                                } else {
                                    System.out.println("Não foi possível cadastrar o médico.");
                                }
                            }
                            break;
                        case 2: //EDITAR 
                            if (!permissao) {
                                System.out.println("Você não tem permissão para realizar esta ação.");
                            } else {
                                System.out.print("Digite o ID do médico que deseja editar: ");
                                id = sc.nextInt();
                                sc.nextLine();
                                Medico medico = medicoController.buscarMedico(id);

                                if (medico == null) {
                                    System.out.println("Médico não encontrado.");
                                } else {
                                    System.out.print("Digite o novo nome do médico (atual: " + medico.getNome() + "): ");
                                    String nome = sc.nextLine();
                                    System.out.print(
                                            "Digite o novo endereço do médico (atual: " + medico.getEndereco() + "): ");
                                    String endereco = sc.nextLine();
                                    System.out.print("Digite o novo CPF do médico (atual: " + medico.getCpf() + "): ");
                                    String cpf = sc.nextLine();
                                    System.out.print("Digite o novo login do médico (atual: " + medico.getLogin() + "): ");
                                    String login = sc.nextLine();
                                    System.out.print("Digite a nova senha do médico (atual: " + medico.getSenha() + "): ");
                                    String senha = sc.nextLine();
                                    System.out.print("Confirme a nova senha do médico: ");
                                    String confirmacaoSenha = sc.nextLine();

                                    if (!senha.equals(confirmacaoSenha)) {
                                        System.out.println("As senhas não coincidem. Tente novamente.");
                                        break;
                                    }
                                    System.out.print(
                                            "Digite o novo telefone do médico (atual: " + medico.getTelefone() + "): ");
                                    String telefone = sc.nextLine();

                                    System.out.print("Digite a nova especialidade do médico (atual: "
                                            + medico.getEspecialidade() + "): ");
                                    String especialidade = sc.nextLine();

                                    boolean atualizado = medicoController.editarMedico(id, login, nome, endereco, cpf, telefone,
                                            id, especialidade);

                                    if (atualizado) {
                                        System.out.println("Médico atualizado com sucesso!");
                                    } else {
                                        System.out.println("Não foi possível atualizar o médico.");
                                    }

                                    boolean atualizado1 = medicoController.editarMedico(id, login, nome, endereco, cpf, telefone,
                                            id, especialidade);

                                    if (atualizado1) {
                                        System.out.println("Médico atualizado com sucesso!");
                                    } else {
                                        System.out.println("Não foi possível atualizar o médico.");
                                    }
                                }
                            }
                            break;
                        case 3: //BUSCAR
                            System.out.print("Digite o ID do médico que deseja buscar: ");
                            id = sc.nextInt();
                            sc.nextLine();

                            Medico medico = medicoController.buscarMedico(id);

                            if (medico == null) {
                                System.out.println("Médico não encontrado.");
                            } else {
                                System.out.println("Nome: " + medico.getNome());
                                System.out.println("Endereço: " + medico.getEndereco());
                                System.out.println("CPF: " + medico.getCpf());
                                System.out.println("Login: " + medico.getLogin());
                                System.out.println("Senha: " + medico.getSenha());
                                System.out.println("Telefone: " + medico.getTelefone());
                                System.out.println("Tipo de usuário: " + medico.getTipoUsuario());
                                System.out.println("Especialidade: " + medico.getEspecialidade());
                            }
                            break;
                        case 4: //REMOVER
                            if (!permissao) {
                                System.out.println("Você não tem permissão para realizar esta ação.");
                            } else {
                                System.out.print("Digite o ID do médico que deseja remover: ");
                                int idMedico = sc.nextInt();
                                sc.nextLine();
                                boolean removido = medicoController.excluirMedico(idMedico);
                                if (removido) {
                                    System.out.println("Médico removido com sucesso.");
                                } else {
                                    System.out.println("Não foi possível remover o médico. ID do médico não encontrado.");
                                }
                            }
                            break;
                        case 5: //LISTAR
                            System.out.println("Listando todos os médicos:");
                            List<Medico> listaMedicos = medicoController.listarMedicos();
                            if (listaMedicos.size() == 0) {
                                System.out.println("Não há médicos cadastrados.");
                            } else {
                                for (int i = 0; i < listaMedicos.size(); i++) {
                                    System.out.println(listaMedicos);
                                }
                            }
                            break;

                        case 0:
                            sair = true;
                            break;
                        default:
                            System.out.println("Opção inválida.");
                    }
                    exibirMenu();
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Digite apenas números.");
                    sc.nextLine();
                }
            }
        }
    }

}
