package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import controller.PessoaController;
import model.Pessoa;

public class PessoaView {
	
    private static PessoaController pessoaController;
    
    public static void main(String[] args) {
        pessoaController = new PessoaController(100);
        
    }

	public void MenuPessoas() {
        Scanner scanner = new Scanner(System.in);
        PessoaController pessoaController = new PessoaController(100); // inicializa a variável aqui
        boolean sair = false;
        
        while (!sair) {
            System.out.println("\n==============================");
            System.out.println("     MENU DE OPERAÇÕES");
            System.out.println("==============================");
            System.out.println("1. Cadastrar Pessoa");
            System.out.println("2. Editar Pessoa");
            System.out.println("3. Alterar Tipo de Usuário");
            System.out.println("4. Buscar Pessoa");
            System.out.println("5. Remover Pessoa");
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
                                System.out.println("ID: " + pessoas[i].getLogin());
                                System.out.println("Nome: " + pessoas[i].getNome());
                                System.out.println("Telefone: " + pessoas[i].getTelefone());
                                System.out.println("---------------------------");
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
	}


