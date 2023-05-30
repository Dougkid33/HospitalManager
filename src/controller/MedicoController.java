package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.Medico;
import model.Pessoa;
import model.DAO.MedicoDao;
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

    public Medico[] listarMedicos() {
        return medicoDao.listarMedicos();
    }
    public static void cadastrarMedicoAleatorias() {
        MedicoController controller = new MedicoController();
        for (int i = 0; i < 10; i++) {
            Medico medico = Medico.gerarMedicoAleatorio();
            controller.cadastrarMedico(medico.getNome(), medico.getEndereco(), medico.getCpf(), medico.getTelefone(), medico.getLogin(), medico.getTipoUsuario(),medico.getSenha(), medico.getCrm(), medico.getEspecialidade());
        }
    }
    



    public static void menuMedico() {
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
                                


                                System.out.print("Digite a especialidade do médico: ");
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
                            Medico[] listaMedicos = medicoController.listarMedicos();
                            if (listaMedicos.length == 0) {
                                System.out.println("Não há médicos cadastrados.");
                            } else {
                                for (int i = 0; i < listaMedicos.length; i++) {
                                    System.out.println(listaMedicos[i]);
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
