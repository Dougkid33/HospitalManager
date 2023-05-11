package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

import model.DAO.InfoConsultaDAO;
import model.InfoConsulta;
import model.Medico;
import model.Pessoa;
import model.Unidade;
import model.enums.EstadoConsulta;
import static view.Main.exibirMenu;

public class InfoConsultaController {

    public static void cadastrarInfoConsulta(Date data, String hora, EstadoConsulta estado, Medico medico, Pessoa paciente, double valor, Unidade unidade, String descricao) {
        InfoConsultaDAO.cadastrarInfoConsulta(data, hora, estado, medico, paciente, valor, unidade, descricao);
        System.out.println("Informações da consulta cadastradas com sucesso!");
    }

    public static void atualizarInfoConsulta(int id, String descricao) {
        InfoConsultaDAO.atualizarInfoConsulta(id, descricao);
        System.out.println("Informações da consulta atualizadas com sucesso!");
    }

    public static void removerInfoConsulta(int id) {
        InfoConsultaDAO.removerInfoConsulta(id);
        System.out.println("Informações da consulta removidas com sucesso!");
    }

    public static InfoConsulta buscarInfoConsulta(int id) {
        InfoConsulta infoConsulta = InfoConsultaDAO.buscarInfoConsulta(id);
        if (infoConsulta == null) {
            System.out.println("Informações da consulta não encontradas.");
        }
        return infoConsulta;
    }

    public static InfoConsulta[] listarInfoConsultas() {
        InfoConsulta[] infoConsultas = InfoConsultaDAO.listarInfoConsultas();
        if (infoConsultas.length == 0) {
            System.out.println("Não há informações de consultas cadastradas.");
        }
        return infoConsultas;
    }

    public static InfoConsulta[] listarInfoConsultasPorMedico(Medico medico) {
        InfoConsulta[] infoConsultas = InfoConsultaDAO.listarInfoConsultasPorMedico(medico);
        if (infoConsultas.length == 0) {
            System.out.println("Não há informações de consultas cadastradas para esse médico.");
        }
        return infoConsultas;
    }

    public static InfoConsulta[] listarInfoConsultasPorPaciente(Pessoa paciente) {
        InfoConsulta[] infoConsultas = new InfoConsulta[0];
        for (InfoConsulta infoConsulta : InfoConsultaDAO.listarInfoConsultas()) {
            if (infoConsulta.getPaciente().equals(paciente)) {
                infoConsultas = Arrays.copyOf(infoConsultas, infoConsultas.length + 1);
                infoConsultas[infoConsultas.length - 1] = infoConsulta;
            }
        }
        if (infoConsultas.length == 0) {
            System.out.println("Não há informações de consultas cadastradas para esse paciente.");
        }
        return infoConsultas;
    }
    
    public static void cadastrarInfoConsultasAleatorias() {
        for (int i = 0; i < 10; i++) {
            InfoConsulta infoConsulta = InfoConsulta.gerarInfoConsultaAleatoria();
            InfoConsultaDAO.cadastrarInfoConsulta(infoConsulta.getData(), infoConsulta.getHora(), infoConsulta.getEstado(),
                    infoConsulta.getMedico(), infoConsulta.getPaciente(), infoConsulta.getValor(), infoConsulta.getUnidade(),
                    infoConsulta.getDescricao());
        }
    }


    @SuppressWarnings("null")
	public static void menuInfoConsulta() {
        try (Scanner scanner = new Scanner(System.in)) {
            int opcao;
            do {
                System.out.println("----- MENU INFORMAÇÃO DE CONSULTA-----");
                System.out.println("1. Cadastrar informação de consulta");
                System.out.println("2. Atualizar informação de consulta");
                System.out.println("3. Remover informação de consulta");
                System.out.println("4. Buscar  informação de consulta");
                System.out.println("5. Listar informação de consultas");
                System.out.println("6. Listar informação de consultas por médico");
                System.out.println("7. Listar informação de consultas por paciente");
                System.out.println("0. Sair");
                System.out.print("Digite a opção desejada: ");
                opcao = scanner.nextInt();
                scanner.nextLine(); // consome a quebra de linha após a opção digitada

                switch (opcao) {
                    case 1:
                        System.out.println("Cadastro de nova consulta:\n\n");
                        System.out.print("Digite a data da consulta (dd/mm/aaaa): ");
                        String dataConsultaStr = scanner.nextLine();
                        Date dataConsulta = null;
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            dataConsulta = dateFormat.parse(dataConsultaStr);
                        } catch (ParseException e) {
                            System.out.println("Data inválida!");
                            return;
                        }

                        System.out.print("Hora (HH:mm): ");
                        String hora = scanner.nextLine();

                        System.out.print("Estado da consulta (AGENDADA, REALIZADA, CANCELADA): ");

                        System.out.print("ID do médico: ");
                        int idMedico = scanner.nextInt();
                        scanner.nextLine();
                        MedicoController medicoController = new MedicoController();
                        Medico medico = medicoController.buscarMedico(idMedico);

                        System.out.print("Nome do paciente: ");
                        int idPaciente = scanner.nextInt();
                        scanner.nextLine();
                        PessoaController pessoaController = new PessoaController();
                        Pessoa paciente = pessoaController.buscarPessoaPorId(idPaciente);

                        System.out.print("Valor: ");
                        double valor = scanner.nextDouble();
                        scanner.nextLine();

                        System.out.print("Nome da unidade: ");
                        int idUnidade = scanner.nextInt();
                        scanner.nextLine();
                        UnidadeController unidadeController = new UnidadeController();
                        Unidade unidade = unidadeController.buscarUnidade(idUnidade);

                        System.out.print("Descrição: ");
                        String descricao = scanner.nextLine();
                        EstadoConsulta estado = EstadoConsulta.AGENDADA;
                        InfoConsultaDAO.cadastrarInfoConsulta(dataConsulta, hora, estado, medico, paciente, valor, unidade, descricao);

                        System.out.println("InfoConsulta cadastrada com sucesso!");
                        break;
                    case 2:
                        System.out.print("ID da InfoConsulta a ser atualizada: \n\n");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        InfoConsulta infoConsulta = InfoConsultaDAO.buscarInfoConsulta(id);
                        if (infoConsulta == null) {
                            System.out.println("InfoConsulta não encontrada.");
                            return;
                        }
                        System.out.println("Atualização da InfoConsulta " + infoConsulta.getId() + ":");
                        System.out.print("Nova descrição: ");
                        String descricaoedit = scanner.nextLine();
                        InfoConsultaDAO.atualizarInfoConsulta(id, descricaoedit);
                        System.out.println("InfoConsulta atualizada com sucesso!");
                        break;
                    case 3:
                        System.out.print("ID da InfoConsulta a ser removida: ");
                        int idremover = scanner.nextInt();
                        scanner.nextLine();

                        InfoConsulta infoConsultaremove = InfoConsultaDAO.buscarInfoConsulta(idremover);
                        if (infoConsultaremove == null) {
                            System.out.println("InfoConsulta não encontrada.");
                            return;
                        }

                        InfoConsultaDAO.removerInfoConsulta(idremover);
                        System.out.println("InfoConsulta removida com sucesso!");
                        break;
                    case 4:
                        System.out.print("ID da InfoConsulta a ser buscada: ");
                        int idbusca = scanner.nextInt();
                        scanner.nextLine();

                        InfoConsulta infoConsultabusca = InfoConsultaDAO.buscarInfoConsulta(idbusca);
                        if (infoConsultabusca == null) {
                            System.out.println("InfoConsulta não encontrada.");
                            return;
                        }

                        System.out.println(infoConsultabusca);
                        break;
                    case 5:
                        InfoConsulta[] infoConsultaslista = InfoConsultaDAO.listarInfoConsultas();
                        if (infoConsultaslista.length == 0) {
                            System.out.println("Não há informações de consultas cadastradas.");
                            return;
                        }

                        System.out.println("----- LISTA DE INFOCONSULTAS -----");
                        for (InfoConsulta infoConsulta1 : infoConsultaslista) {
                            System.out.println("ID: " + infoConsulta1.getId());
                            System.out.println("Data: " + infoConsulta1.getData());
                            System.out.println("Hora: " + infoConsulta1.getHora());
                            System.out.println("Estado: " + infoConsulta1.getEstado());
                            System.out.println("Médico: " + infoConsulta1.getMedico().getNome());
                            System.out.println("Paciente: " + infoConsulta1.getPaciente().getNome());
                            System.out.println("Valor: " + infoConsulta1.getValor());
                            System.out.println("Unidade: " + infoConsulta1.getUnidade().getNome());
                            System.out.println("Descrição: " + infoConsulta1.getDescricao());
                            System.out.println("------------------------------------");
                        }

                        break;
                    case 0:
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
                exibirMenu();
            } while (opcao != 0);
        }
        catch(NumberFormatException  e){
        	   // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
