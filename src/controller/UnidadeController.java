package controller;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.Pessoa;
import model.Unidade;
import model.DAO.UnidadeDAO;

public class UnidadeController {

    private UnidadeDAO unidadeDAO;

    public UnidadeController(UnidadeDAO unidadeDAO) {
        this.unidadeDAO = unidadeDAO;
    }

    public boolean cadastrarUnidade(Unidade unidade) {
        return UnidadeDAO.cadastrarUnidade(unidade);
    }

    public Unidade buscarUnidade(int idUnidade) {
        return unidadeDAO.buscarUnidade(idUnidade);
    }

    public boolean atualizarUnidade(Unidade unidadeAtualizada) {
        return unidadeDAO.atualizarUnidade(unidadeAtualizada);
    }

    public boolean excluirUnidade(int idUnidade) {
        return unidadeDAO.excluirUnidade(idUnidade);
    }

    public Unidade[] listarUnidades() {
        return unidadeDAO.listarUnidades();
    }
    
    public static void menuUnidade() {
    	try (Scanner scanner = new Scanner(System.in)) {
            UnidadeController unidadeController = new UnidadeController(new UnidadeDAO());
            System.out.println("\n==============================");
            System.out.println("       MENU DE UNIDADES");
            System.out.println("==============================");
            boolean sair = false;
            while (!sair) {
                System.out.println("\nEscolha uma opção:");
                System.out.println("1. Cadastrar Unidade");
                System.out.println("2. Editar Unidade");
                System.out.println("3. Buscar Unidade");
                System.out.println("4. Excluir Unidade");
                System.out.println("5. Listar Unidades");
                System.out.println("0. Sair");

                try {
                    int opcao = scanner.nextInt();
                    scanner.nextLine(); // para consumir a quebra de linha deixada pelo nextInt

                    switch (opcao) {
                        case 1:
                            System.out.print("Digite o nome da unidade: ");
                            String nome = scanner.nextLine();
                            System.out.print("Digite a cidade da unidade: ");
                            String cidade = scanner.nextLine();
                            System.out.print("Digite o endereço da unidade: ");
                            String endereco = scanner.nextLine();

                            boolean cadastrado = unidadeController.cadastrarUnidade(new Unidade(nome, cidade, endereco, nome, null, null, null, opcao, nome, nome, null, null, null));

                            if (cadastrado) {
                                System.out.println("Unidade cadastrada com sucesso.");
                            } else {
                                System.out.println("Não foi possível cadastrar a unidade.");
                            }
                            break;
                        case 2:
                        	System.out.print("Digite o ID da unidade que deseja editar: ");
                        	int idUnidade = scanner.nextInt();
                        	scanner.nextLine(); // para consumir a quebra de linha deixada pelo nextInt
                        	Unidade unidade = unidadeController.buscarUnidade(idUnidade);

                        	if (unidade == null) {
                        	    System.out.println("Unidade não encontrada.");
                        	} else {
                        	    System.out.print("Digite o novo nome da unidade (atual: " + unidade.getNome() + "): ");
                        	    String novoNome = scanner.nextLine();
                        	    System.out.print("Digite a nova cidade da unidade (atual: " + unidade.getCidade() + "): ");
                        	    String novaCidade = scanner.nextLine();
                        	    System.out.print("Digite o novo endereço da unidade (atual: " + unidade.getEndereco() + "): ");
                        	    String novoEndereco = scanner.nextLine();
                        	    Pessoa responsavelUnidade = null; // defina a pessoa responsável pela unidade
                        	    Date dataCriacaoUnidade = null; // defina a data de criação da unidade
                        	    Date dataModificacaoUnidade = null; // defina a data de modificação da unidade

                        	    boolean atualizado = unidadeController.atualizarUnidade(new Unidade(novoNome, unidade.getCnpj(), novaCidade, novoEndereco, null, null, null, idUnidade, unidade.getCidadeUnidade(), unidade.getEnderecoUnidade(), responsavelUnidade, dataCriacaoUnidade, dataModificacaoUnidade));

                        	    if (atualizado) {
                        	        System.out.println("Unidade atualizada com sucesso.");
                        	    } else {
                        	        System.out.println("Não foi possível atualizar a unidade.");
                        	    }
                        	}
                        	break;
                        case 3:
                            System.out.print("Digite o ID da unidade que deseja buscar: ");
                            idUnidade = scanner.nextInt();
                            scanner.nextLine(); // para consumir a quebra de linha deixada pelo nextInt
                            unidade = unidadeController.buscarUnidade(idUnidade);

                            if (unidade == null) {
                                System.out.println("Unidade não encontrada.");
                            } else {
                                System.out.println("Nome: " + unidade.getNome());
                                System.out.println("Cidade: " + unidade.getCidade());
                                System.out.println("Endereço: " + unidade.getEndereco());
                            }
                            break;
                        case 4:
                        	System.out.print("Digite o ID da unidade que deseja excluir: ");
                        	idUnidade = scanner.nextInt();
                        	scanner.nextLine(); // para consumir a quebra de linha deixada pelo nextInt
                            boolean excluido = unidadeController.excluirUnidade(idUnidade);

                            if (excluido) {
                                System.out.println("Unidade excluída com sucesso.");
                            } else {
                                System.out.println("Não foi possível excluir a unidade.");
                            }
                            break;
                        case 5:
                        	Unidade[] unidades = unidadeController.listarUnidades();

                        	if (unidades.length == 0) {
                        	    System.out.println("Não há unidades cadastradas.");
                        	} else {
                        	    System.out.println("Unidades cadastradas:\n");
                        	    for (int i = 0; i < unidades.length; i++) {
                        	        System.out.println("ID: " + unidades[i].getId());
                        	        System.out.println("Nome: " + unidades[i].getNome());
                        	        System.out.println("Cidade: " + unidades[i].getCidade());
                        	        System.out.println("Endereço: " + unidades[i].getEndereco());
                        	        System.out.println("------------------------------");
                        	    }
                        	}
                            break;
                        case 0:
                            System.out.println("Saindo do menu de unidades...");
                            sair = true;
                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Tente novamente.");
                    scanner.nextLine(); // para consumir a entrada inválida
                }
            }
        }
    }
    
    
}
