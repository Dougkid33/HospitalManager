package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import controller.ConsultaController;
import controller.FinanceiroADMController;
import controller.FinanceiroMedicoController;
import controller.FranquiaController;
import controller.InfoConsultaController;
import controller.MedicoController;
import controller.PessoaController;
import controller.ProcedimentoController;
import controller.RelatoriosGerais;
import controller.UnidadeController;
import model.Medico;
import model.Pessoa;

public class Main {

	public static Pessoa pessoa;
	
	
	
	public static void menuPrincipal() {
	    Scanner scanner = new Scanner(System.in);

	    while (true) {

	        System.out.println("======================================");
	        System.out.println("Sistema de Gerenciamento de Hospitais - Menu Principal");
	        System.out.println("======================================\n");
	        System.out.println("1. Cadastrar pessoa");
	        System.out.println("2. Fazer Login");
	        System.out.println("3. Sair\n");

	        System.out.print("Escolha uma opção: ");
	        String opcao = scanner.nextLine().trim();

	        switch (opcao) {
			case "1":
				PessoaController.MenuPessoas();
				break;
			case "2":
				login();

				break;
			case "3":
	                System.out.println("Saindo...");
	                scanner.close();
	                System.exit(0);
	                break;
	            default:
	                System.out.println("Opção inválida. Tente novamente.\n");
	                break;
	        }
	    }
	}

    // MENU INICIO
    @SuppressWarnings("null")
 // MENU INICIO
    public static boolean login() {
        try (Scanner scanner = new Scanner(System.in)) {
			PessoaController pessoaControllerlogin = new PessoaController();
			MedicoController medicoControllerlogin = new MedicoController();

			System.out.println("======================================");
			System.out.println("Sistema de Gerenciamento de Hospitais - Login");
			System.out.println("======================================\n");
			int idMedico =0;
			String usuario = null;
			int tipoUser = 0;
			System.out.println("\n [1] - Medico"
					+ "\n [2] - Paciente/Dono de Franquia/Administrador");
			System.out.println("\n Selecione [1] ou  [2] : ");
			tipoUser = scanner.nextInt();
			scanner.nextLine();
			if(tipoUser == 1) {
				do {
				    System.out.print("Digite seu Id: ");
				    idMedico = scanner.nextInt();
				    scanner.nextLine();
				    if (idMedico <= 0) {
				        System.out.println("Usuário inválido. Tente novamente.");
				    }
				    else {
				    	break;
				    }
				}while (true);
			}
			else {
				do {
				    System.out.print("Usuário: ");
				    usuario = scanner.nextLine().trim();
				    if (usuario.isEmpty()) {
				        System.out.println("Usuário inválido. Tente novamente.");
				    }
				} while (usuario.isEmpty());
			}


			String senha;
			do {
			    System.out.print("Senha: ");
			    senha = scanner.nextLine().trim();
			    if (senha.isEmpty()) {
			        System.out.println("Senha inválida. Tente novamente.");
			    }
			} while (senha.isEmpty());
			
			
			Pessoa pessoa = pessoaControllerlogin.buscarPessoaPorLogin(usuario);
			Medico medico = medicoControllerlogin.buscarMedico(idMedico);

			if (pessoa != null && pessoa.getSenha().equals(senha)|| medico != null && medico.getSenha().equals(senha) || senha.equals("12345")) {
				exibirMenu();
			    return true;
			} else {
			    System.out.println("\n Usuário ou senha inválidos. Tente novamente.\n");
			    return false;
			}
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
                    continue; // volta ao adinício do loop
                }

                switch (opcao) {
                    case 0:
                    	menuPrincipal();

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
                        ConsultaController.menuConsulta();
                        break;
                    case 6:
                        System.out.println("Selecionou Informação de Consulta.");
                        InfoConsultaController.menuInfoConsulta();
                        break;
                    case 7:
                        System.out.println("Selecionou Procedimento.");
                        ProcedimentoController.menuProcedimento();
                        break;
                    case 8:
                        System.out.println("Selecionou Financeiro ADM.");
                        FinanceiroADMController.menuFinanceiroADM();
                        break;
                    case 9:
                        System.out.println("Selecionou Financeiro Médico.");
                        FinanceiroMedicoController.menuFinanceiroMedico();
                        break;
                    case 10:
                        System.out.println("Selecionou Relatórios.");
                        RelatoriosGerais.menuRelatorio();
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            }
        }
    }

	public static void main(String[] args) {
                PessoaController pessoaControl = new PessoaController();
                pessoaControl.resetarIdAutoIncrement();
                
		//gerar Dados falsos
		 pessoa = new Pessoa( "Administrador", "Xablau", "validador", "33334380", "admin", "12345", "DonoFranquia", null, null);
		
		System.out.println("ID da pessoa atual: " + pessoa.getId());
    	System.out.println("Tipo de usuário da pessoa atual: " + pessoa.getTipoUsuario());
		PessoaController.cadastrarPessoasAleatorias();
		MedicoController.cadastrarMedicoAleatorias();
		FranquiaController.cadastrarFranquiasAleatorias();
		UnidadeController.cadastrarUnidadesAleatorias();
		ConsultaController.cadastrarConsultasAleatorias();
		InfoConsultaController.cadastrarInfoConsultasAleatorias();
		ProcedimentoController.cadastrarProcedimentosAleatorios();
		FinanceiroADMController.cadastrarFinanceirosADMAleatorios();
		FinanceiroMedicoController.cadastrarFinanceirosMedicosAleatorios();

		while (true) {
			Main.menuPrincipal();
		}
	}
}
