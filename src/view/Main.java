package view;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import controller.PessoaController;
import model.Pessoa;

public class Main {

    public void menuPrincipal(long id) {

    }

    // MENU INICIO
    public boolean login() {
        try (Scanner scanner = new Scanner(System.in)) {
        	//                               id,  nome,  endereco,  cpf,  telefone,  login,  senha, tipoUsuario,  dataCriacao,  dataModificacao
        	Pessoa novoUsuario = new Pessoa(0, "administrador", "ruaAlves", "12126272613", "33", "admin", "1234", null, null, null);
        	//PessoaController.cadastrarPessoa(novoUsuario);
        	String usuario = "admin";
        	String senha = "1234";
            boolean loginValido = false;

            while (!loginValido) {
                StringBuilder builder = new StringBuilder("");

                builder.append(" ======================================");
                builder.append(" Sistema de Gerenciamento de Hospitais - Login ");
                builder.append(" ====================================\n");
                builder.append("\n Entre com seus dados:\n");
                System.out.print(builder.toString());

                System.out.print("Usuário: ");
                 usuario = scanner.next();

                System.out.print("Senha: ");
                senha = scanner.next();

                Pessoa pessoa = PessoaController.login(usuario, senha);
                novoUsuario.getCpf();
                novoUsuario.getSenha();
                if (novoUsuario.getSenha().equals("1234")) {
                    loginValido = true;
                    menuPrincipal(novoUsuario.getId());
                    exibirMenu();
                } else {
                    System.out.println("\n Usuário ou senha inválidos. Tente novamente.\n");
                }

                System.out.println(builder.toString());

            }
            return loginValido;
        }
    }
	public static void MenuPessoas() {
        Scanner scanner = new Scanner(System.in);
        PessoaController pessoaController = new PessoaController(100); // inicializa a variável aqui
        boolean sair = false;
        
        while (!sair) {
            System.out.println("\n==============================");
            System.out.println("     MENU DE OPERAÇÕES PESSOAS");
            System.out.println("==============================");
            System.out.println("1. Cadastrar Pessoa");
            System.out.println("2. Editar Pessoa");
            System.out.println("3. Alterar Tipo de Usuário");
            System.out.println("4. Buscar Pessoa");
            System.out.println("5. Remover Pessoa");
            System.out.println("6. Listar Todas Pessoas");
            System.out.println("0. Sair");
            
            try {
                int opcao = scanner.nextInt();
                scanner.nextLine(); // para consumir a quebra de linha deixada pelo nextInt
                
                switch(opcao) {
                    case 1:
                    	 System.out.print("Digite o ID da pessoa: ");
                         int id = scanner.nextInt();
                         scanner.nextLine();
                         System.out.print("Digite o nome da pessoa: ");
                         String nome = scanner.nextLine();
                         System.out.print("Digite o endereço da pessoa: ");
                         String endereco = scanner.nextLine();
                         System.out.print("Digite o CPF da pessoa: ");
                         String cpf = scanner.nextLine();
                         System.out.print("Digite o telefone da pessoa: ");
                         String telefone = scanner.nextLine();
                         System.out.print("Digite o login da pessoa: ");
                         String login = scanner.nextLine();
                         System.out.print("Digite a senha da pessoa: ");
                         String senha = scanner.nextLine();
                         System.out.print("Digite o tipo de usuário da pessoa: ");
                         String tipoUsuario = scanner.nextLine();
                         boolean cadastrado = pessoaController.cadastrarPessoa(id, nome, endereco, cpf, telefone, login, senha, tipoUsuario);
                         if (cadastrado) {
                             System.out.println("Pessoa cadastrada com sucesso.");
                         } else {
                             System.out.println("Não foi possível cadastrar a pessoa. Já existe uma pessoa com esse ID.");
                         }
                        break;
                    case 2:
                    	System.out.print("Digite o ID da pessoa a ser editada: ");
                        id = scanner.nextInt();
                    	System.out.print("Digite o login da pessoa a ser removida: ");
                    	login = scanner.nextLine();
                        scanner.nextLine();
                         pessoaController = new PessoaController(100);
                        Pessoa editarPessoa = pessoaController.buscarPessoa(id);
                        if (editarPessoa == null) {
                            System.out.println("Pessoa não encontrada.");
                            break;
                        }
                        System.out.print("Digite o novo nome da pessoa: ");
                        nome = scanner.nextLine();
                        System.out.print("Digite o novo endereço da pessoa: ");
                        endereco = scanner.nextLine();
                        System.out.print("Digite o novo CPF da pessoa: ");
                        cpf = scanner.nextLine();
                        System.out.print("Digite o novo telefone da pessoa: ");
                        telefone = scanner.nextLine();
                        boolean editado = pessoaController.editarPessoa(id, login, nome, endereco, cpf, telefone);
                        if (editado) {
                            System.out.println("Pessoa editada com sucesso.");
                        } else {
                            System.out.println("Não foi possível editar a pessoa. ID da pessoa não encontrado.");
                        }
                        break;
                    case 3:
                    	System.out.print("Digite o ID da pessoa a ter o tipo de usuário alterado: ");
                        id = scanner.nextInt();
                        scanner.nextLine();
                        PessoaController pessoaController2 = new PessoaController(100);
                        Pessoa alterarTipo  = pessoaController2.buscarPessoa(id);
                        if (alterarTipo == null) {
                            System.out.println("Pessoa não encontrada.");
                            break;
                        }
                        System.out.print("Digite o novo tipo de usuário da pessoa: ");
                        tipoUsuario = scanner.nextLine();
                    	System.out.print("Digite o login da pessoa a ser removida: ");
                    	nome = scanner.nextLine();
                        boolean tipoAlterado = pessoaController2.alterarTipoUsuario(id, tipoUsuario, nome);
                        if (tipoAlterado) {
                            System.out.println("Tipo de usuário alterado com sucesso.");
                        } else {
                            System.out.println("Não foi possível alterar o tipo de usuário. ID da pessoa não encontrado.");
                        }
                        break;
                    case 4:
                    	System.out.print("Digite o ID da pessoa a ser buscada: ");
                        id = scanner.nextInt();
                        scanner.nextLine();
                        PessoaController pessoaController3 = new PessoaController(100);
                        Pessoa buscarPessoa = pessoaController3.buscarPessoa(id);
                        if (buscarPessoa == null) {
                            System.out.println("Pessoa não encontrada.");
                        } else {
                            System.out.println(buscarPessoa);
                        }
                        break;
                    case 5:
                    	System.out.print("Digite o ID da pessoa a ser removida: ");
                    	login = scanner.nextLine();
                        scanner.nextLine();
                        boolean removido = pessoaController.removerPessoa(login);
                        if (removido) {
                            System.out.println("Pessoa removida com sucesso.");
                        } else {
                            System.out.println("Não foi possível remover a pessoa. ID da pessoa não encontrado.");
                        }
                        break;
                    case 6:
                        Pessoa[] pessoas = pessoaController.listarPessoas();
                        boolean existePessoas = false;
                        for (int i = 0; i < pessoas.length; i++) {
                            if (pessoas[i] != null) {
                                if (!existePessoas) {
                                    System.out.println("Lista de pessoas cadastradas:");
                                    existePessoas = true;
                                    
                                }
                                System.out.println("ID: " + pessoas[i].getLogin()+ " - Nome: " + pessoas[i].getNome() + " - Telefone: " + pessoas[i].getTelefone() + " ." );
                                System.out.println("\n ------------------------------\n");
                            }
                        }
                        if (!existePessoas) {
                            System.out.println("Nenhuma pessoa cadastrada.");
                        }
                    	break;
                    case 0:
                        sair = true;
                        System.out.println("Encerrando o programa...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
                
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Tente novamente.");
                scanner.nextLine(); // para limpar o buffer do scanner
            }
        }
    }
    // MENU PRINCIPAL
    public static void exibirMenu() {
        boolean sair = false;
        
        while (!sair) {
            StringBuilder builder = new StringBuilder();

            builder.append("\n\n\n\n\n\n\n =============================================================\n");
            builder.append(" Sistema de Gerenciamento de Clínicas Médicas \n");
            builder.append(" ========================================================/Home\n");
            builder.append("\n Escolha a opção desejada:\n");
            builder.append("1. Pessoa \n");
            builder.append("2. Médico \n");
            builder.append("3. Franquia \n");
            builder.append("4. Unidade de Franquia \n");
            builder.append("5. Consulta \n");
            builder.append("6. Informação de Consulta \n");
            builder.append("7. Procedimento \n");
            builder.append("8. Financeiro ADM \n");
            builder.append("9. Financeiro Médico \n");
            builder.append("10. Relatórios Financeiros \n");
            builder.append("0. Sair \n");
            builder.append("------------------------------------ \n");

            System.out.println(builder.toString());

            try (Scanner scanner = new Scanner(System.in)) {
                int opcao = -1;
                try {
                    opcao = scanner.nextInt();
                } catch (InputMismatchException e) { // valida a opcao
                    System.out.println("Opção inválida. Tente novamente.");
                    continue; // volta ao início do loop
                }

                switch (opcao) {
                    case 0:
                        System.out.println("Saindo...");
                        sair = true;
                        break;
                    case 1:
                        System.out.println("Selecionou Pessoa.");
                        MenuPessoas();
                        
                        break;
                    case 2:
                        System.out.println("Selecionou Médico.");
                        // faça algo relacionado a Médico
                        break;
                    case 3:
                        System.out.println("Selecionou Franquia.");
                        // faça algo relacionado a Franquia
                        break;
                    case 4:
                        System.out.println("Selecionou Unidade de Franquia.");
                        // faça algo relacionado a Unidade de Franquia
                        break;
                    case 5:
                        System.out.println("Selecionou Consulta.");
                        // faça algo relacionado a Consulta
                        break;
                    case 6:
                        System.out.println("Selecionou Informação de Consulta.");
                        // faça algo relacionado a Informação de Consulta
                        break;
                    case 7:
                        System.out.println("Selecionou Procedimento.");
                        // faça algo relacionado a Procedimento
                        break;
                    case 8:
                        System.out.println("Selecionou Financeiro ADM.");
                        // faça algo relacionado a Financeiro ADM
                        break;
                    case 9:
                        System.out.println("Selecionou Financeiro Médico.");
                        // faça algo relacionado a Financeiro Médico
                        break;
                    case 10:
                        System.out.println("Selecionou Relatórios Financeiros.");
                        // faça algo relacionado a Relatórios Financeiros
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            }
        }
    }


    public static void main(String[] args) {
        // verificacao do login para entrar no menuPrincipal
        Main main = new Main();
        boolean loginValido = main.login();
        if (loginValido) {
            exibirMenu();
        }

    }

}
