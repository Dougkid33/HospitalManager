package model.DAO;

import java.util.Arrays;
import java.util.Date;

import model.FinanceiroMedico;

public class FinanceiroMedicoDAO {

    private static FinanceiroMedico[] financeirosMedicos = new FinanceiroMedico[0];
    private static int id = 1;

    public static void cadastrarFinanceiroMedico(double valorMedico, String estado, double franquia) {
        FinanceiroMedico financeiroMedico = new FinanceiroMedico(id++, valorMedico, estado, franquia, estado, new Date(), new Date());
        financeirosMedicos = Arrays.copyOf(financeirosMedicos, financeirosMedicos.length + 1);
        financeirosMedicos[financeirosMedicos.length - 1] = financeiroMedico;
    }

    public static void atualizarFinanceiroMedico(int id, double valorMedico, String estado, double franquia) {
        for (FinanceiroMedico financeiroMedico : financeirosMedicos) {
            if (financeiroMedico.getId() == id) {
                financeiroMedico.setValorMedico(valorMedico);
                financeiroMedico.setEstado(estado);
                financeiroMedico.setFranquia(franquia);
                financeiroMedico.setDataModificacao(new Date());
                break;
            }
        }
    }

    public static void removerFinanceiroMedico(int id) {
        int removeIndex = -1;
        for (int i = 0; i < financeirosMedicos.length; i++) {
            if (financeirosMedicos[i].getId() == id) {
                removeIndex = i;
                break;
            }
        }
        if (removeIndex != -1) {
            financeirosMedicos = removeElementFromArray(financeirosMedicos, financeirosMedicos[removeIndex]);
        }
    }

    public static FinanceiroMedico[] listarFinanceirosMedicos() {
        return financeirosMedicos;
    }

    public static double calcularMontantePagoMedicoNoUltimoMes() {
        double montanteTotalPago = 0;
        Date hoje = new Date();
        for (FinanceiroMedico financeiroMedico : financeirosMedicos) {
            if (financeiroMedico.getEstado().equals("pago") && hoje.getTime() - financeiroMedico.getDataCriacao().getTime() <= 30 * 24 * 60 * 60 * 1000) {
                double valorPago = 0;
                if (financeiroMedico.getDescricao().equals("consulta")) {
                    valorPago = financeiroMedico.getValorMedico() * 0.7;
                } else if (financeiroMedico.getDescricao().equals("procedimento")) {
                    valorPago = financeiroMedico.getValorMedico() * 0.5;
                }
                montanteTotalPago += valorPago;
            }
        }
        return montanteTotalPago;
    }
    
    

    private static FinanceiroMedico[] removeElementFromArray(FinanceiroMedico[] arr, FinanceiroMedico element) {
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(element)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            FinanceiroMedico[] newArray = new FinanceiroMedico[arr.length - 1];
            for (int i = 0, j = 0; i < arr.length; i++) {
                if (i != index) {
                    newArray[j++] = arr[i];
                }
            }
            return newArray;
        } else {
            return arr;
        }
    }
}
