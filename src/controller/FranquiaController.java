package controller;

import model.Pessoa;
import model.DAO.FranquiaDao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.FinanceiroADM;
import model.FinanceiroMedico;
import model.Franquia;
import static view.Main.exibirMenu;

public class FranquiaController {
	private static FranquiaDao dao;

	public FranquiaController() {
	    dao = new FranquiaDao();
	}

	public boolean cadastrarFranquia(String nome, String cnpj, String cidade, String endereco, Pessoa responsavel) {
	    return dao.cadastrarFranquia(nome, cnpj, cidade, endereco, responsavel);
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
	    franquia.setDataModificacao(new Date());
	    return dao.editarFranquia(id, novoCnpj, novoNome, novaCidade, novoEndereco);
	}

	public boolean excluirFranquia(int idRemove) {
	    return dao.excluirFranquia(idRemove);
	}

	public Franquia buscarFranquia(int id) {
	    return dao.buscarFranquia(id);
	}
    public static void gerarRelatorioFinanceiro() {
        FranquiaController franquiaController = new FranquiaController();
        Franquia franquia = franquiaController.buscarFranquia(); 
        FinanceiroMedico[] pagamentos = franquia.getPagamentosMedico(); // Obter os pagamentos aos médicos da franquia
        FinanceiroADM[] administrativos = franquia.getAdministrativos(); // Obter as despesas administrativas da franquia
        float[] totalDespesas = {0};
        float[] totalPagamentos = {0};
        float[] totalAdministrativos = {0};

        // Calcula o total de despesas médicas
        for (int i = 0; i < pagamentos.length; i++) {
            if (pagamentos[i].getData().getMonth() == LocalDate.now().getMonth()) { // Verificar se o pagamento foi registrado no mês atual
                totalPagamentos[0] += pagamentos[i].getValor();
            }
        }

        // Calcula o total de despesas administrativas
        for (int i = 0; i < administrativos.length; i++) {
            if (administrativos[i].getData().getMonth() == LocalDate.now().getMonth()) { // Verificar se a despesa administrativa foi registrada no mês atual
                totalAdministrativos[0] += administrativos[i].getValor();
            }
        }

        float totalReceitas = totalPagamentos[0];
        float totalDespesasMensais = totalDespesas[0] + totalAdministrativos[0];
        float lucroOuPrejuizo = totalReceitas - totalDespesasMensais;

        System.out.println("Relatório Financeiro Mensal");
        System.out.println("----------------------------");
        System.out.println("Receitas: R$" + totalReceitas);
        System.out.println("Despesas: R$" + totalDespesasMensais);
        System.out.println("Lucro/Prejuízo: R$" + lucroOuPrejuizo);
    }



    public static void menuFranquia() {
        try (Scanner scanner = new Scanner(System.in)) {
            FranquiaController franquiaController = new FranquiaController();
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
                        System.out.println("0. Sair");

                        try {
                            int opcao = sc.nextInt();
                            sc.nextLine(); // para consumir a quebra de linha deixada pelo nextInt

                            switch (opcao) {
                                case 1://CADASTRAR
                                    System.out.print("Digite o nome da franquia: ");
                                    String nome = sc.nextLine();
                                    System.out.print("Digite o CNPJ da franquia: ");
                                    String cnpj = sc.nextLine();
                                    System.out.print("Digite a cidade da franquia: ");
                                    String cidade = sc.nextLine();
                                    System.out.print("Digite o endereço da franquia: ");
                                    String endereco = sc.nextLine();

                                    System.out.println("Digite o ID do responsável pela franquia: ");
                                    int idResponsavel = sc.nextInt();
                                    sc.nextLine(); // para consumir a quebra de linha deixada pelo nextInt
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
                                case 2://EDITAR
                                    System.out.print("Digite o CNPJ da franquia que deseja editar: ");
                                    int idEditar = sc.nextInt();
                                    Franquia franquia = franquiaController.buscarFranquia(idEditar);

                                    if (franquia == null) {
                                        System.out.println("Franquia não encontrada.");
                                    } else {
                                        System.out.print(
                                                "Digite o novo nome da franquia (atual: " + franquia.getNome() + "): ");
                                        String novoNome = sc.nextLine();
                                        System.out.print(
                                                "Digite o novo CNPJ da franquia (atual: " + franquia.getCnpj() + "): ");
                                        String novoCnpj = sc.nextLine();
                                        System.out.print(
                                                "Digite a nova cidade da franquia (atual: " + franquia.getCidade() + "): ");
                                        String novaCidade = sc.nextLine();
                                        System.out.print("Digite o novo endereço da franquia (atual: "
                                                + franquia.getEndereco() + "): ");
                                        String novoEndereco = sc.nextLine();
                                        System.out.println("Digite o ID do novo responsável pela franquia (atual: "
                                                + franquia.getResponsavel().getId() + "): ");
                                        idResponsavel = sc.nextInt();
                                        sc.nextLine(); // para consumir a quebra de linha deixada pelo nextInt
                                        responsavel = pessoaController.buscarPessoaPorId(idResponsavel);

                                        if (responsavel == null) {
                                            System.out.println("Pessoa não encontrada.");
                                        } else {
                                            boolean atualizado = franquiaController.editarFranquia(franquia.getId(),franquia.getCnpj(), novoNome, novoCnpj,
                                                    novaCidade,  responsavel);

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
                                    System.out.print("Digite o CNPJ da franquia que deseja excluir: ");
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
