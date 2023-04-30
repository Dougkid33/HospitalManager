package controller;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.Pessoa;
import model.DAO.PessoaDAO;

public class PessoaController {
    private PessoaDAO dao;

    public PessoaController() {
        dao = new PessoaDAO();
    }

    public boolean cadastrarPessoa(Pessoa pessoa) {
    	pessoa.setDataCriacao(new Date());
        return dao.cadastraPessoa(pessoa);
    }

    public boolean removerPessoa(int id) {
        return dao.removePessoa(id);
    }

    public Pessoa buscarPessoaPorId(int id) {
        return dao.buscarPorId(id);
    }

    public Pessoa buscarPessoaPorLogin(String login) {
        return dao.buscarPessoaPorLogin(login);
    }

    public boolean editarPessoa(int id, String login, String novoNome, String novoEndereco, String novoCpf, String novoTelefone) {
    	Pessoa pessoa = dao.buscarPorId(id);
        if (pessoa == null) {
            return false; // Pessoa não encontrada
        } 
        pessoa.setNome(novoNome);
        pessoa.setEndereco(novoEndereco);
        pessoa.setCpf(novoCpf);
        pessoa.setTelefone(novoTelefone);
        pessoa.setDataModificacao(new Date());
        
        return dao.editarPessoa(pessoa);
    }
    
    private boolean editarTipo(int id, String novoTipoUsuario) {
        Pessoa pessoa = dao.buscarPorId(id);
        if (pessoa == null) {
            return false; // Pessoa não encontrada
        }

        pessoa.setTipoUsuario(novoTipoUsuario);
        pessoa.setDataModificacao(new Date());
        return true;
    }

    public Pessoa[] listarPessoas() {
        return dao.listarPessoas();
    }

    public static Pessoa buscarPessoaPorCpf(String cpf) {
        return PessoaDAO.buscarPessoaPorCpf(cpf);
    }
    
	public static void MenuPessoas() {
		try (Scanner sc = new Scanner(System.in)) {
			PessoaController pessoaController = new PessoaController(); // inicializa a variável aqui
			boolean sair = false;
			int id = 0;

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
					int opcao = sc.nextInt();
					sc.nextLine(); // para consumir a quebra de linha deixada pelo nextInt

					switch (opcao) {
					case 1:

						System.out.print("Digite o nome da pessoa: ");
						String nome = sc.nextLine();
						System.out.print("Digite o endereço da pessoa: ");
						String endereco = sc.nextLine();
						System.out.print("Digite o CPF da pessoa: ");
						String cpf = sc.nextLine();
						System.out.print("Digite o telefone da pessoa: ");
						String telefone = sc.nextLine();
						System.out.print("Digite o login da pessoa: ");
						String login = sc.nextLine();
						System.out.print("Digite a senha da pessoa: ");
						String senha = sc.nextLine();
						System.out.print("Digite o tipo de usuário da pessoa: ");
						String tipoUsuario = sc.nextLine();
						boolean cadastrado = pessoaController.cadastrarPessoa(new Pessoa( nome, endereco, cpf, telefone, login, senha, tipoUsuario, null, null));
						if (cadastrado) {
							System.out.println("Pessoa cadastrada com sucesso.");
						} else {
							System.out.println("Não foi possível cadastrar a pessoa. Já existe uma pessoa com esse ID.");
						}
						break;
					case 2:
					    System.out.print("Digite o ID da pessoa a ser editada: ");
					    id = sc.nextInt();
					    sc.nextLine();
					    Pessoa editarPessoa = pessoaController.buscarPessoaPorId(id);
					    if (editarPessoa == null) {
					        System.out.println("Pessoa não encontrada.");
					        break;
					    }
					    System.out.print("Digite o novo nome da pessoa: ");
					    nome = sc.nextLine();
					    System.out.print("Digite o novo endereço da pessoa: ");
					    endereco = sc.nextLine();
					    System.out.print("Digite o novo CPF da pessoa: ");
					    cpf = sc.nextLine();
					    System.out.print("Digite o novo telefone da pessoa: ");
					    telefone = sc.nextLine();
					    System.out.print("Digite o novo login da pessoa: ");
					    login = sc.nextLine();
					    System.out.print("Digite a nova senha da pessoa: ");
					    senha = sc.nextLine();
					    System.out.print("Digite o novo tipo de usuário da pessoa: ");
					    tipoUsuario = sc.nextLine();
					    boolean editado = pessoaController.editarPessoa(id, login, nome, endereco, cpf, telefone);
					    if (editado) {
					        System.out.println("Pessoa editada com sucesso.");
					    } else {
					        System.out.println("Não foi possível editar a pessoa. ID da pessoa não encontrado.");
					    }
					    break;
					case 3:
					    System.out.print("Digite o ID da pessoa a ter o tipo de usuário alterado: ");
					    id = sc.nextInt();
					    sc.nextLine();
					    Pessoa alterarTipo = pessoaController.buscarPessoaPorId(id);
					    if (alterarTipo == null) {
					        System.out.println("Pessoa não encontrada.");
					        break;
					    }
					    System.out.print("Digite o novo tipo de usuário da pessoa: ");
					    tipoUsuario = sc.nextLine();
					    boolean tipoAlterado = pessoaController.editarTipo(id, tipoUsuario);
					    if (tipoAlterado) {
					        System.out.println("Tipo de usuário alterado com sucesso.");
					    } else {
					        System.out.println("Não foi possível alterar o tipo de usuário. ID da pessoa não encontrado.");
					    }
					    break;
					case 4:
					    System.out.print("Digite o ID da pessoa a ser buscada: ");
					    id = sc.nextInt();
					    sc.nextLine();
					    Pessoa buscarPessoa = pessoaController.buscarPessoaPorId(id);
					    if (buscarPessoa == null) {
					        System.out.println("Pessoa não encontrada.");
					    } else {
					        System.out.println(buscarPessoa);
					    }
					    break;
					case 5:
					    System.out.print("Digite o ID da pessoa a ser removida: ");
					    id = sc.nextInt();
					    sc.nextLine();
					    boolean removido = pessoaController.removerPessoa(id);
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
								System.out.println(pessoas[i]);
								
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
					sc.nextLine(); // para limpar o buffer do scanner
				}
			}
		}
	}

	public static Pessoa login(String usuario, String senha) {
	    PessoaController controller = new PessoaController();
	    Pessoa pessoa = controller.buscarPessoaPorLogin(usuario);
	    if (pessoa != null && pessoa.getSenha().equals(senha)) {
	        System.out.println("\n Login efetuado com sucesso!\n");
	        return pessoa;
	    } else {
	        System.out.println("\n Usuário ou senha inválidos. Tente novamente.\n");
	        return null;
	    }
	}

}
