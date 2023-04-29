package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.Medico;
import model.Pessoa;
import model.DAO.MedicoDao;

public class MedicoController {
	private Medico[] medicos;
	private MedicoDao medicoDao;

	public MedicoController(MedicoDao medicoDao) {
		this.medicoDao = medicoDao;
	}

	public boolean cadastrarMedico(String nome, String endereco, String cpf, String telefone, String login,
			String senha, String tipoUsuario, int crm, String especialidade) {
		return medicoDao.cadastrarMedico(nome, endereco, cpf, telefone, login, senha, tipoUsuario, crm, especialidade);
	}

	public boolean editarMedico(String login, String novoNome, String novoEndereco, String novoCpf, String novoTelefone,
			int novoCrm, String novaEspecialidade) {
		return medicoDao.editarMedico(login, novoNome, novoEndereco, novoCpf, novoTelefone, novoCrm, novaEspecialidade);
	}

	public boolean excluirMedico(int id) {
		return medicoDao.excluirMedico(id);
	}

	public Medico buscarMedico(int id) {
		return medicoDao.buscarMedico(id);
	}

	public Medico[] listarMedicos() {
		return medicos;
	}

	public static void menuMedico() {
		try (Scanner scanner = new Scanner(System.in)) {
			MedicoController medicoController = new MedicoController(new MedicoDao(100));
			boolean sair = false;

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
					int opcao = scanner.nextInt();
					scanner.nextLine(); // para consumir a quebra de linha deixada pelo nextInt
					
					
					System.out.println("Digite o seu ID :");
					int id = scanner.nextInt();
					scanner.nextLine(); // para consumir a quebra de linha deixada pelo nextInt
					

					PessoaController pessoaController = new PessoaController();
					Pessoa pessoa = pessoaController.buscarPessoaPorId(id);
					boolean permissao = false;

					if (pessoa.getTipoUsuario().equals("DonoFranquia") || pessoa.getTipoUsuario().equals("DonoUnidade")) {
					    permissao = true;
					}
					switch (opcao) {
					case 1:
						if (!permissao) {
							System.out.println("Você não tem permissão para realizar esta ação.");
						} else {
							System.out.print("Digite o ID do médico: ");
							 id = scanner.nextInt();
							scanner.nextLine();
							System.out.print("Digite o nome do médico: ");
							String nome = scanner.nextLine();
							System.out.print("Digite o endereço do médico: ");
							String endereco = scanner.nextLine();
							System.out.print("Digite o CPF do médico: ");
							String cpf = scanner.nextLine();
							System.out.print("Digite o login do médico: ");
							String login = scanner.nextLine();
							System.out.print("Digite a senha do médico: ");
							String senha = scanner.nextLine();
							System.out.print("Digite o telefone do médico: ");
							String telefone = scanner.nextLine();
							System.out.print("Digite O tipo de usuário: ");
							String tipoUsuario = scanner.nextLine();

							System.out.print("Digite a especialidade do médico: ");
							String especialidade = scanner.nextLine();
							boolean cadastrado = medicoController.cadastrarMedico(nome, endereco, cpf, login, senha,
									telefone, tipoUsuario, id, especialidade);
							if (cadastrado) {
								System.out.println("Médico  cadastrado com sucesso.");
							} else {
								System.out.println("Não foi possível cadastrar o médico.");
							}
						}
						break;
					case 2:
						if (!permissao) {
							System.out.println("Você não tem permissão para realizar esta ação.");
						} else {
							System.out.print("Digite o ID do médico que deseja editar: ");
							 id = scanner.nextInt();
							scanner.nextLine();
							Medico medico = medicoController.buscarMedico(id);

							if (medico == null) {
								System.out.println("Médico não encontrado.");
							} else {
								System.out.print("Digite o novo nome do médico (atual: " + medico.getNome() + "): ");
								String nome = scanner.nextLine();
								System.out.print(
										"Digite o novo endereço do médico (atual: " + medico.getEndereco() + "): ");
								String endereco = scanner.nextLine();
								System.out.print("Digite o novo CPF do médico (atual: " + medico.getCpf() + "): ");
								String cpf = scanner.nextLine();
								System.out.print("Digite o novo login do médico (atual: " + medico.getLogin() + "): ");
								String login = scanner.nextLine();
								System.out.print("Digite a nova senha do médico (atual: " + medico.getSenha() + "): ");
								String senha = scanner.nextLine();
								System.out.print("Confirme a nova senha do médico: ");
								String confirmacaoSenha = scanner.nextLine();

								if (!senha.equals(confirmacaoSenha)) {
								    System.out.println("As senhas não coincidem. Tente novamente.");
								    break;
								}
								System.out.print(
										"Digite o novo telefone do médico (atual: " + medico.getTelefone() + "): ");
								String telefone = scanner.nextLine();
								System.out.print("Digite o novo tipo de usuário do médico (atual: "
										+ medico.getTipoUsuario() + "): ");
								String tipoUsuario = scanner.nextLine();
								System.out.print("Digite a nova especialidade do médico (atual: "
										+ medico.getEspecialidade() + "): ");
								String especialidade = scanner.nextLine();

								boolean atualizado = medicoController.editarMedico(login, nome, endereco, cpf, telefone,
										id, especialidade);

								if (atualizado) {
									System.out.println("Médico atualizado com sucesso!");
								} else {
									System.out.println("Não foi possível atualizar o médico.");
								}

								boolean atualizado1 = medicoController.editarMedico(login, nome, endereco, cpf, telefone,
										id, especialidade);

								if (atualizado1) {
									System.out.println("Médico atualizado com sucesso!");
								} else {
									System.out.println("Não foi possível atualizar o médico.");
								}
							}
						}
						break;
					case 3:
						System.out.print("Digite o ID do médico que deseja buscar: ");
						 id = scanner.nextInt();
						scanner.nextLine();

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
					case 4:
						if (!permissao) {
							System.out.println("Você não tem permissão para realizar esta ação.");
						} else {
							System.out.print("Digite o ID do médico que deseja remover: ");
							int idMedico = scanner.nextInt();
							scanner.nextLine();
							boolean removido = medicoController.excluirMedico(idMedico);
							if (removido) {
								System.out.println("Médico removido com sucesso.");
							} else {
								System.out.println("Não foi possível remover o médico. ID do médico não encontrado.");
							}
						}

						break;
					case 5:
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
				} catch (InputMismatchException e) {
					System.out.println("Entrada inválida. Digite apenas números.");
					scanner.nextLine();
				}
			}
		}
	}

}
