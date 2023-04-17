package view;

import java.util.Scanner;

import controller.PessoaController;
import model.Pessoa;
import model.PessoaDAO;

public class Main {
    private PessoaController pessoaController;

    public Main() {
        pessoaController = new PessoaController(10); // criar controller com capacidade para 10 pessoas
    }

    public boolean login() {
    	
        try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Digite seu login:");
			String login = scanner.nextLine();

			System.out.println("Digite sua senha:");
			String senha = scanner.nextLine();

			PessoaDAO pessoaDAO = new PessoaDAO(10); // criar DAO com capacidade para 10 pessoas
			Pessoa[] pessoas = pessoaDAO.listarPessoas();

			for (Pessoa pessoa : pessoas) {
			    if (pessoa.getLogin().equals(login) && pessoa.getSenha().equals(senha)) {
			        System.out.println("Login realizado com sucesso!");
			        return true;
			    }
			}
		}

        System.out.println("Login ou senha incorretos!");
        return false;
    }

    public void cadastrarPessoa() {
        try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Digite o nome:");
			String nome = scanner.nextLine();

			System.out.println("Digite o e-mail:");
			String email = scanner.nextLine();

			System.out.println("Digite o telefone:");
			String telefone = scanner.nextLine();

			Pessoa pessoa = new Pessoa(0, nome, email, telefone, telefone, telefone, telefone, telefone, null, null);
			pessoaController.buscarPessoa(pessoa.getId());
		}

        System.out.println("Pessoa cadastrada com sucesso!");
    }

    public void listarPessoas() {
        Pessoa[] pessoas = pessoaController.listarPessoas();

        if (pessoas.length == 0) {
            System.out.println("Nenhuma pessoa cadastrada!");
        } else {
            for (Pessoa pessoa : pessoas) {
                System.out.println(pessoa);
            }
        }
    }

    public void atualizarPessoa() {
        try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Digite o ID da pessoa que deseja atualizar:");
			int id = scanner.nextInt();
			scanner.nextLine();

			Pessoa pessoa = pessoaController.buscarPessoa(id);

			if (pessoa == null) {
			    System.out.println("Pessoa não encontrada!");
			    return;
			}

			System.out.println("Digite o novo nome (ou deixe em branco para manter o nome atual):");
			String nome = scanner.nextLine();

			System.out.println("Digite o novo e-mail (ou deixe em branco para manter o e-mail atual):");
			String email = scanner.nextLine();

			System.out.println("Digite o novo telefone (ou deixe em branco para manter o telefone atual):");
			String telefone = scanner.nextLine();    if (!nome.isEmpty()) {
			    pessoa.setNome(nome);
			}


			if (!telefone.isEmpty()) {
			    pessoa.setTelefone(telefone);
			}

			pessoaController.editarPessoa(id, telefone, nome, nome, email, telefone);
		}

        System.out.println("Pessoa atualizada com sucesso!");
    }

    public void excluirPessoa() {
        try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Digite o ID da pessoa que deseja excluir:");
			int id = scanner.nextInt();
			scanner.nextLine();

			Pessoa pessoa = pessoaController.buscarPessoa(id);

			if (pessoa == null) {
			    System.out.println("Pessoa não encontrada!");
			    return;
			}

			pessoaController.removerPessoa(pessoa.getLogin());
		}

        System.out.println("Pessoa excluída com sucesso!");
    }

    public void menu() {
        try (Scanner scanner = new Scanner(System.in)) {
			while (true) {
			    System.out.println("=== Menu ===");
			    System.out.println("1. Cadastrar pessoa");
			    System.out.println("2. Listar pessoas");
			    System.out.println("3. Atualizar pessoa");
			    System.out.println("4. Excluir pessoa");
			    System.out.println("0. Sair");

			    int opcao = scanner.nextInt();
			    scanner.nextLine();

			    switch (opcao) {
			        case 1:
			            cadastrarPessoa();
			            break;
			        case 2:
			            listarPessoas();
			            break;
			        case 3:
			            atualizarPessoa();
			            break;
			        case 4:
			            excluirPessoa();
			            break;
			        case 0:
			            System.out.println("Saindo...");
			            return;
			        default:
			            System.out.println("Opção inválida!");
			            break;
			    }
			}
		}
    }

    public static void main(String[] args) {
        Main main = new Main();

        if (main.login()) {
            main.menu();
        }
    }
}
