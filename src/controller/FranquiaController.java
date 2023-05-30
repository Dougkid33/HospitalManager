package controller;

import model.Pessoa;
import model.DAO.FranquiaDao;
import view.Main;

import java.sql.Date;

import java.util.InputMismatchException;
import java.util.Scanner;


import model.Franquia;
import static view.Main.exibirMenu;

public class FranquiaController {
	private FranquiaDao dao;

    private int id;

    public FranquiaController() {
        dao = new FranquiaDao();

        id = 0;
    }

    public boolean cadastrarFranquia(Franquia franquia) {
    	franquia.setDataCriacao(new Date(System.currentTimeMillis()));
        return dao.cadastrarFranquia(id, franquia.getNome(), franquia.getCnpj(), franquia.getCidade(), franquia.getEndereco(), franquia.getResponsavel());
    }

    public boolean editarFranquia(int id, String novoNome, String novoCnpj, String novaCidade, String novoEndereco, Pessoa novoResponsavel) {
        Franquia franquia = dao.buscarFranquia(id);
        if (franquia == null) {
            return false; // Franquia não encontrada
        }
        franquia.setNome(novoNome);
        franquia.setCnpj(novoCnpj);
        franquia.setCidade(novaCidade);
        franquia.setEndereco(novoEndereco);
        franquia.setResponsavel(novoResponsavel);
        franquia.setDataModificacao(new Date(System.currentTimeMillis()));
        return dao.editarFranquia(id, novoCnpj, novoNome, novaCidade, novoEndereco);
    }

    public boolean excluirFranquia(int idRemove) {
        return dao.excluirFranquia(idRemove);
    }


    public Franquia buscarFranquia(int id) {
        return dao.buscarFranquia(id);
    }
    public static void cadastrarFranquiasAleatorias() {
        FranquiaController controller = new FranquiaController(); // criando uma instância do controlador
        for (int i = 0; i < 10; i++) {
            Franquia franquia = Franquia.gerarFranquiaAleatoria();
            controller.cadastrarFranquia(franquia);
        }
    }
    public static Franquia[] listarFranquias() {
        FranquiaDao dao = new FranquiaDao();
        return dao.listarFranquias();
    }

 

    public static void menuFranquia() {
        try (Scanner scanner = new Scanner(System.in)) {
            FranquiaController franquiaController = new FranquiaController();
            PessoaController pessoaController = new PessoaController();
            System.out.println("Digite o seu ID:");
            int idPermissao = scanner.nextInt();
            boolean permissao = false;
            scanner.nextLine(); // para consumir a quebra de linha deixada pelo nextInt

            Pessoa pessoa = pessoaController.buscarPessoaPorId(idPermissao);
            
            if (Main.pessoa != null && Main.pessoa.getId() == idPermissao) {
                pessoa = Main.pessoa;
            }

            if (pessoa.getTipoUsuario().equals("DonoFranquia")) {
                permissao = true;
            }
            if (permissao) {
                try (Scanner sc = new Scanner(System.in)) {
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
                        System.out.println("5. Listar Franquias");
                        System.out.println("0. Sair");

                        try {
                            int opcao = sc.nextInt();
                            sc.nextLine(); // para consumir a quebra de linha deixada pelo nextInt

                            switch (opcao) {
                                case 1://CADASTRAR
                                    System.out.print("Digite o nome da franquia: \n");
                                    String nome = sc.nextLine();
                                    System.out.print("Digite o CNPJ da franquia: \n");
                                    String cnpj = sc.nextLine();
                                    System.out.print("Digite a cidade da franquia: \n");
                                    String cidade = sc.nextLine();
                                    System.out.print("Digite o endereço da franquia: \n");
                                    String endereco = sc.nextLine();

                                    System.out.println("Digite o ID do responsável pela franquia: \\n");
                                    int idResponsavel = sc.nextInt();
                                    sc.nextLine(); // para consumir a quebra de linha deixada pelo nextInt
                                    responsavel = pessoaController.buscarPessoaPorId(idResponsavel);

                                    if (responsavel == null) {
                                        System.out.println("\n Pessoa não encontrada.\n");
                                    } else {
                                        boolean cadastrado = franquiaController.cadastrarFranquia(new Franquia(nome, cnpj, cidade, endereco, responsavel, null, null));

                                        if (cadastrado) {
                                            System.out.println("Franquia cadastrada com sucesso.\n");
                                        } else {
                                            System.out.println("Não foi possível cadastrar a franquia.\n");
                                        }
                                    }

                                    break;
                                case 2://EDITAR
                                    System.out.print("Digite o ID da franquia que deseja editar: \n");
                                    int idEditar = sc.nextInt();
                                    Franquia franquia = franquiaController.buscarFranquia(idEditar);

                                    if (franquia == null) {
                                        System.out.println("Franquia não encontrada.\n");
                                    } else {
                                    	System.out.print("Digite o novo nome da franquia (atual: " + franquia.getNome() + "): ");
                                    	String novoNome = sc.nextLine();
                                    	System.out.print("Digite o novo CNPJ da franquia (atual: " + franquia.getCnpj() + "): ");
                                    	String novoCnpj = sc.nextLine();
                                    	System.out.print("Digite a nova cidade da franquia (atual: " + franquia.getCidade() + "): ");
                                    	String novaCidade = sc.nextLine();
                                    	System.out.print("Digite o novo endereço da franquia (atual: " + franquia.getEndereco() + "): ");
                                    	String novoEndereco = sc.nextLine();
                                    	System.out.print("Digite o ID do novo responsável pela franquia (atual: " + franquia.getResponsavel().getId() + "): ");
                                    	int idResponsavel1 = sc.nextInt();
                                    	sc.nextLine(); // consumir a nova linha pendente
                                        responsavel = pessoaController.buscarPessoaPorId(idResponsavel1);

                                        if (responsavel == null) {
                                            System.out.println("Pessoa não encontrada.");
                                        } else {
                                            boolean atualizado = franquiaController.editarFranquia(franquia.getId(), novoNome, novoCnpj,  novaCidade,
                                                    novoEndereco, responsavel);

                                            if (atualizado) {
                                                System.out.println("Franquia atualizada com sucesso.");
                                            } else {
                                                System.out.println("Não foi possível atualizar a franquia.");
                                            }
                                        }
                                    }

                                    break;
                                case 3://BUSCAR
                                    System.out.print("Digite o ID da franquia que deseja buscar: ");
                                    int idBusca = sc.nextInt();
                                    Franquia franquiaBusca = franquiaController.buscarFranquia(idBusca);

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
                                case 4://EXCLUIR
                                    System.out.print("Digite o ID da franquia que deseja excluir: ");
                                    int idRemove = sc.nextInt();
                                    Franquia franquiaExclusao = franquiaController.buscarFranquia(idRemove);

                                    if (franquiaExclusao == null) {
                                        System.out.println("Franquia não encontrada.");
                                    } else {
                                        boolean excluido = franquiaController.excluirFranquia(idRemove);

                                        if (excluido) {
                                            System.out.println("Franquia excluída com sucesso.");
                                        } else {
                                            System.out.println("Não foi possível excluir a franquia.");
                                        }
                                    }

                                    break;
                                case 5:
                                	Franquia[] franquias = FranquiaController.listarFranquias();
                                	if (franquias.length == 0) {
                                	    System.out.println("Não existem franquias cadastradas.");
                                	} else {
                                	    System.out.println("Lista de Franquias:");
                                	    for (int i = 0; i < franquias.length; i++) {
                                	        Franquia f = franquias[i];
                                	        System.out.println("ID: " + f.getId() + ", Nome: " + f.getNome() + ", CNPJ: " + f.getCnpj() +
                                	            ", Cidade: " + f.getCidade() + ", Endereço: " + f.getEndereco() +
                                	            ", Responsável: " + f.getResponsavel().getNome());
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
                            exibirMenu();
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida. Digite novamente.");
                            sc.nextLine(); // para limpar o buffer do scanner e evitar loop infinito
                        }
                    }
                }
            } else if (permissao == false) {
                System.out.println("Credenciais inválidas.");
            }
        }

    }

}
