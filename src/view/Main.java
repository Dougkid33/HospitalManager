package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import controller.FranquiaController;
import controller.MedicoController;
import controller.PessoaController;
import controller.UnidadeController;
import model.Pessoa;

public class Main {
	Pessoa pessoa = new Pessoa( "Administrador", "Xablau", "validador", "33334380", "admin", "12345", "DonoFranquia", null, null);
	
	public void menuPrincipal(long id) {
	}
	// MENU INICIO
	@SuppressWarnings("null")
	public boolean login() {
	    Scanner sc = new Scanner(System.in);

	    System.out.println("==============================================");
	    System.out.println("Sistema de Gerenciamento de Hospitais - Login");
	    System.out.println("=============================================\n");

	    String usuario;
	    do {
	        System.out.print("Usuário: ");
	        usuario = sc.nextLine().trim();
	        if (usuario.isEmpty()) {
	            System.out.println("Usuário inválido. Tente novamente.");
	        }
	    } while (usuario.isEmpty());

	    String senha;
	    do {
	        System.out.print("Senha: ");
	        senha = sc.nextLine().trim();
	        if (senha.isEmpty()) {
	            System.out.println("Senha inválida. Tente novamente.");
	        }
	    } while (senha.isEmpty());

	    try {
	    	// this.nomeDeUsuarioPadrao == "admin" && this.senhaPadrao == "12345"
	    	PessoaController controller = new PessoaController();
	    	//Pessoa pessoa = new Pessoa( "Administrador", "Xablau", "validador", "33334380", "admin", "12345", "DonoFranquia", null, null);
	    	controller.cadastrarPessoa(this.pessoa);
	    	System.out.println("Este é seu ID: " + pessoa.getId()+" .");
	    	if (pessoa != null && pessoa.getSenha().equals("12345")) {
	    	    menuPrincipal(pessoa.getId());
	    	    exibirMenu();
	    	    return true;
	    	} else {
	            System.out.println("\n Usuário ou senha inválidos. Tente novamente.\n");
	            return false;
	        }
	    } catch (Exception e) {
	        System.out.println("\n Erro ao realizar o login. Tente novamente mais tarde.\n");
	        return false;
	    } finally {
	        sc.close();
	    }
	}

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
					PessoaController.MenuPessoas();

					break;
				case 2:
					System.out.println("Selecionou Médico.");
					MedicoController.menuMedico();
					break;
				case 3:
					
					System.out.println("Selecionou Franquia.");
					FranquiaController.menuFranquia();
					break;
				case 4:
					System.out.println("Selecionou Unidade de Franquia.");
					UnidadeController.menuUnidade();
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
