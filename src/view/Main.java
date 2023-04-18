package view;

import java.util.InputMismatchException;
import java.util.Scanner;
import controller.PessoaController;
import model.Pessoa;

public class Main {

    public void menuPrincipal(long id) {

    }

    // MENU INICIO
    public boolean login() {
        try (Scanner scanner = new Scanner(System.in)) {
            String usuario, senha;
            boolean loginValido = false;

            while (!loginValido) {
                StringBuilder builder = new StringBuilder("");

                builder.append(" ======================================");
                builder.append(" Sistema de Gerenciamento de Hospitais - Login ");
                builder.append(" ====================================\n");
                builder.append("\n Entre com seus dados:\n");
                System.out.print(builder.toString());

                System.out.print("Usuário: ");
                usuario = scanner.nextLine();

                System.out.print("Senha: ");
                senha = scanner.nextLine();

                Pessoa pessoa = PessoaController.login(usuario, senha);
                if (pessoa != null) {
                    loginValido = true;
                    menuPrincipal(pessoa.getId());
                } else {
                    System.out.println("\nUsuário ou senha inválidos. Tente novamente.\n");
                }

                System.out.println(builder.toString());

            }
            return loginValido;
        }
    }

    // MENU PRINCIPAL
    public static int exibirMenu() {
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
            }
            return opcao;
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
