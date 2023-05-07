package model.DAO;

import java.util.Arrays;
import java.util.Date;

import model.FinanceiroADM;
import model.enums.TipoMovimento;

public class FinanceiroADMDAO {

    private static FinanceiroADM[] financeiros = new FinanceiroADM[0];
    private static int id = 1;

    public static void cadastrarFinanceiro(TipoMovimento tipoMovimento, double valor, String unidade, String descritivoMovimento) {
        FinanceiroADM financeiro = new FinanceiroADM(id++, tipoMovimento, valor, unidade, descritivoMovimento, new Date(), new Date());
        financeiros = Arrays.copyOf(financeiros, financeiros.length + 1);
        financeiros[financeiros.length - 1] = financeiro;
    }

    public static void atualizarFinanceiro(int id, TipoMovimento tipoMovimento, double valor, String unidade, String descritivoMovimento) {
        for (FinanceiroADM financeiro : financeiros) {
            if (financeiro.getId() == id) {
                financeiro.setTipoMovimento(tipoMovimento);
                financeiro.setValor(valor);
                financeiro.setUnidade(unidade);
                financeiro.setDescritivoMovimento(descritivoMovimento);
                financeiro.setDataModificacao(new Date());
                break;
            }
        }
    }

    public static void removerFinanceiro(int id) {
        int removeIndex = -1;
        for (int i = 0; i < financeiros.length; i++) {
            if (financeiros[i].getId() == id) {
                removeIndex = i;
                break;
            }
        }
        if (removeIndex != -1) {
            financeiros = remove(financeiros, removeIndex);
        }
    }

    public static FinanceiroADM[] listarFinanceiros() {
        return financeiros;
    }

    private static FinanceiroADM[] remove(FinanceiroADM[] arr, int index) {
        FinanceiroADM[] newArr = new FinanceiroADM[arr.length - 1];
        for (int i = 0, j = 0; i < arr.length; i++) {
            if (i != index) {
                newArr[j++] = arr[i];
            }
        }
        return newArr;
    }
}
