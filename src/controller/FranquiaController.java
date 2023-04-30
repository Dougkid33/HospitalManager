package controller;

import model.Pessoa;
import model.DAO.FranquiaDao;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.Franquia;

public class FranquiaController {
	private static FranquiaDao franquiaDao;

	public FranquiaController(FranquiaDao franquiaDao) {
		FranquiaController.franquiaDao = franquiaDao;
	}

	public boolean cadastrarFranquia(String nome, String cnpj, String cidade, String endereco, Pessoa responsavel) {
		return franquiaDao.cadastrarFranquia(nome, cnpj, cidade, endereco, responsavel);
	}

	public boolean editarFranquia(String novoNome, String novoCnpj, String novaCidade, String novoEndereco,
			Pessoa novoResponsavel) {
		return franquiaDao.editarFranquia(novoNome, novoCnpj, novaCidade, novoEndereco);
	}

	public boolean excluirFranquia(String cnpj) {
		return franquiaDao.excluirFranquia(cnpj);
	}

	public Franquia buscarFranquia(String cnpj) {
		return franquiaDao.buscarFranquia(cnpj);
	}

	public static void menuFranquia() {
		try (Scanner scanner = new Scanner(System.in)) {
			FranquiaController franquiaController = new FranquiaController(new FranquiaDao(100));
			PessoaController pessoaController = new PessoaController();
			System.out.println("Digite o seu ID:");
			int id = scanner.nextInt();
			scanner.nextLine(); // para consumir a quebra de linha deixada pelo nextInt

			Pessoa pessoa = pessoaController.buscarPessoaPorId(id);
			boolean permissao = false;

			if (pessoa.getTipoUsuario().equals("DonoFranquia")) {
				permissao = true;
			}
			if (permissao) {
				try (Scanner s = new Scanner(System.in)) {
					boolean sair = false;
					Pessoa responsavel = null;
					while (!sair) {
						System.out.println("\n==============================");
						System.out.println("         MENU DE FRANQUIAS");
						System.out.println("==============================");
						System.out.println("1. Cadastrar Franquia");
						System.out.println("2. Editar Franquia");
						System.out.println("3. Buscar Franquia");
						System.out.println("4. Excluir Franquia");
						System.out.println("0. Sair");

						try {
							int opcao = s.nextInt();
							s.nextLine(); // para consumir a quebra de linha deixada pelo nextInt

							switch (opcao) {
							case 1:
								System.out.print("Digite o nome da franquia: ");
								String nome = s.nextLine();
								System.out.print("Digite o CNPJ da franquia: ");
								String cnpj = s.nextLine();
								System.out.print("Digite a cidade da franquia: ");
								String cidade = s.nextLine();
								System.out.print("Digite o endereço da franquia: ");
								String endereco = s.nextLine();

								System.out.println("Digite o ID do responsável pela franquia: ");
								int idResponsavel = s.nextInt();
								s.nextLine(); // para consumir a quebra de linha deixada pelo nextInt
								responsavel = pessoaController.buscarPessoaPorId(idResponsavel);

								if (responsavel == null) {
									System.out.println("Pessoa não encontrada.");
								} else {
									boolean cadastrado = franquiaController.cadastrarFranquia(nome, cnpj, cidade,
											endereco, responsavel);

									if (cadastrado) {
										System.out.println("Franquia cadastrada com sucesso.");
									} else {
										System.out.println("Não foi possível cadastrar a franquia.");
									}
								}

								break;
							case 2:
								System.out.print("Digite o CNPJ da franquia que deseja editar: ");
								cnpj = s.nextLine();
								Franquia franquia = franquiaController.buscarFranquia(cnpj);

								if (franquia == null) {
									System.out.println("Franquia não encontrada.");
								} else {
									System.out.print(
											"Digite o novo nome da franquia (atual: " + franquia.getNome() + "): ");
									String novoNome = s.nextLine();
									System.out.print(
											"Digite o novo CNPJ da franquia (atual: " + franquia.getCnpj() + "): ");
									String novoCnpj = s.nextLine();
									System.out.print(
											"Digite a nova cidade da franquia (atual: " + franquia.getCidade() + "): ");
									String novaCidade = s.nextLine();
									System.out.print("Digite o novo endereço da franquia (atual: "
											+ franquia.getEndereco() + "): ");
									String novoEndereco = s.nextLine();
									System.out.println("Digite o ID do novo responsável pela franquia (atual: "
											+ franquia.getResponsavel().getId() + "): ");
									idResponsavel = s.nextInt();
									s.nextLine(); // para consumir a quebra de linha deixada pelo nextInt
									responsavel = pessoaController.buscarPessoaPorId(idResponsavel);

									if (responsavel == null) {
										System.out.println("Pessoa não encontrada.");
									} else {
										boolean atualizado = franquiaController.editarFranquia(novoNome, novoCnpj,
												novaCidade, novoEndereco, responsavel);

										if (atualizado) {
											System.out.println("Franquia atualizada com sucesso.");
										} else {
											System.out.println("Não foi possível atualizar a franquia.");
										}
									}
								}

								break;
							case 3:
								System.out.print("Digite o CNPJ da franquia que deseja buscar: ");
								cnpj = s.nextLine();
								Franquia franquiaBusca = franquiaController.buscarFranquia(cnpj);

								if (franquiaBusca == null) {
									System.out.println("Franquia não encontrada.");
								} else {
									System.out.println("Nome: " + franquiaBusca.getNome());
									System.out.println("CNPJ: " + franquiaBusca.getCnpj());
									System.out.println("Cidade: " + franquiaBusca.getCidade());
									System.out.println("Endereço: " + franquiaBusca.getEndereco());
									System.out.println("Responsável: " + franquiaBusca.getResponsavel().getNome());
								}

								break;
							case 4:
								System.out.print("Digite o CNPJ da franquia que deseja excluir: ");
								cnpj = s.nextLine();
								Franquia franquiaExclusao = franquiaController.buscarFranquia(cnpj);

								if (franquiaExclusao == null) {
									System.out.println("Franquia não encontrada.");
								} else {
									boolean excluido = franquiaController.excluirFranquia(cnpj);

									if (excluido) {
										System.out.println("Franquia excluída com sucesso.");
									} else {
										System.out.println("Não foi possível excluir a franquia.");
									}
								}

								break;
							case 0:
								sair = true;
								break;
							default:
								System.out.println("Opção inválida. Digite novamente.");
								break;
							}
						} catch (InputMismatchException e) {
							System.out.println("Entrada inválida. Digite novamente.");
							s.nextLine(); // para limpar o buffer do scanner e evitar loop infinito
						}
					}
				}
			} else if (permissao == false) {
				System.out.println("Credenciais inválidas.");
			}
		}

	}

}
