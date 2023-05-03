package model.DAO;

import java.util.Arrays;
import java.util.Date;

import model.InfoConsulta;
import model.Medico;
import model.Pessoa;
import model.Unidade;
import model.enums.EstadoConsulta;

public class InfoConsultaDAO {

    private static InfoConsulta[] infoConsultas = new InfoConsulta[0];
    private static int id = 1;

    public static void cadastrarInfoConsulta(Date data, String hora, EstadoConsulta estado, Medico medico, Pessoa paciente, double valor, Unidade unidade, String descricao) {
        InfoConsulta infoConsulta = new InfoConsulta(id++, data, hora, estado, medico, paciente, valor, unidade, descricao, new Date(), new Date());
        infoConsultas = Arrays.copyOf(infoConsultas, infoConsultas.length + 1);
        infoConsultas[infoConsultas.length - 1] = infoConsulta;
    }

    public static void atualizarInfoConsulta(int id, String descricao) {
        for (InfoConsulta infoConsulta : infoConsultas) {
            if (infoConsulta.getId() == id) {
                infoConsulta.setDescricao(descricao);
                break;
            }
        }
    }

    public static void removerInfoConsulta(int id) {
        int removeIndex = -1;
        for (int i = 0; i < infoConsultas.length; i++) {
            if (infoConsultas[i].getId() == id) {
                removeIndex = i;
                break;
            }
        }
        if (removeIndex != -1) {
            for (int i = removeIndex; i < infoConsultas.length - 1; i++) {
                infoConsultas[i] = infoConsultas[i + 1];
            }
            infoConsultas = Arrays.copyOf(infoConsultas, infoConsultas.length - 1);
        }
    }

    public static InfoConsulta buscarInfoConsulta(int id) {
        for (InfoConsulta infoConsulta : infoConsultas) {
            if (infoConsulta.getId() == id) {
                return infoConsulta;
            }
        }
        return null;
    }

    public static InfoConsulta[] listarInfoConsultas() {
        return infoConsultas;
    }

    public static InfoConsulta[] listarInfoConsultasPorMedico(Medico medico) {
        InfoConsulta[] infoConsultasMedico = new InfoConsulta[0];
        for (InfoConsulta infoConsulta : infoConsultas) {
            if (infoConsulta.getMedico().equals(medico)) {
                infoConsultasMedico = Arrays.copyOf(infoConsultasMedico, infoConsultasMedico.length + 1);
                infoConsultasMedico[infoConsultasMedico.length - 1] = infoConsulta;
            }
        }
        return infoConsultasMedico;
    }
}
