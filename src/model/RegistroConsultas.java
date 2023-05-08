package model;


import java.time.LocalDate;
import java.time.Month;
import java.util.Scanner;

public class RegistroConsultas {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
			int maxConsultas = 1000; // máximo de consultas a serem registradas
			double[] valoresPagos = new double[maxConsultas]; // vetor para armazenar os valores pagos
			LocalDate[] datasConsultas = new LocalDate[maxConsultas]; // vetor para armazenar as datas das consultas
			int qtdConsultas = 0; // quantidade atual de consultas registradas
			
			while (true) {
			    System.out.println("Digite 1 para registrar uma consulta ou 0 para sair:");
			    int opcao = scanner.nextInt();
			    if (opcao == 0) {
			        break;
			    }
			    if (opcao == 1) {
			        System.out.println("Digite o valor pago na consulta:");
			        double valor = scanner.nextDouble();
			        System.out.println("Digite a data da consulta (no formato dia/mês/ano):");
			        String dataString = scanner.next();
			        LocalDate data = LocalDate.parse(dataString, 
			            java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			        
			        valoresPagos[qtdConsultas] = valor;
			        datasConsultas[qtdConsultas] = data;
			        qtdConsultas++;
			    }
			}
			
			// calcular o total de valores pagos no último mês
			LocalDate hoje = LocalDate.now();
			LocalDate umMesAtras = hoje.minusMonths(1);
			double totalPago = 0;
			for (int i = 0; i < qtdConsultas; i++) {
			    LocalDate dataConsulta = datasConsultas[i];
			    if (dataConsulta.isAfter(umMesAtras) && !dataConsulta.isAfter(hoje)) {
			        totalPago += valoresPagos[i];
			    }
			}
			System.out.println("O total pago no último mês foi de R$ " + totalPago);
		}
    }
}
