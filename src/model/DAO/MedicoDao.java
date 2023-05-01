package model.DAO;

import java.util.Date;

import model.Medico;

public class MedicoDao {

    private static Medico[] medicos = new Medico[100];
    private static int qtdMedicos;

    public MedicoDao(int tamanho) {
        medicos = new Medico[tamanho];
        qtdMedicos = 0;
    }

    public boolean cadastrarMedico(String nome, String endereco, String cpf, String telefone, String login,
            String senha, String tipoUsuario, int crm, String especialidade) {
        int id = 0; // definindo um valor padrão para o id
        if (buscarMedico(id) != null) {
            return false; // Já existe um médico com esse login
        }
        id = gerarNovoId();
        Date dataCriacao = new Date();
        Medico novoMedico = new Medico(id, nome, endereco, cpf, telefone, login, senha, crm, especialidade, dataCriacao, dataCriacao);
        medicos[qtdMedicos] = novoMedico;
        qtdMedicos++;
        return true;
    }

    public boolean editarMedico(String login, String novoNome, String novoEndereco, String novoCpf, String novoTelefone, int novoCrm, String novaEspecialidade) {
        int id = 0;
        Medico medico = buscarMedico(id);
        if (medico == null) {
            return false; // Médico não encontrado
        }

        id = medico.getId(); // obtendo o id do médico retornado
        medico.setNome(novoNome);
        medico.setEndereco(novoEndereco);
        medico.setCpf(novoCpf);
        medico.setTelefone(novoTelefone);
        medico.setCrm(novoCrm);
        medico.setEspecialidade(novaEspecialidade);
        medico.setDataModificacao(new Date());
        return true;
    }

    public boolean excluirMedico(int id) {
        int indice = -1;
        for (int i = 0; i < qtdMedicos; i++) {
            if (Integer.valueOf(medicos[i].getId()).equals(id)) {
                indice = i;
                break;
            }
        }

        if (indice == -1) {
            return false; // Médico não encontrado
        }

        for (int i = indice; i < qtdMedicos - 1; i++) {
            medicos[i] = medicos[i + 1];
        }
        medicos[qtdMedicos - 1] = null;
        qtdMedicos--;
        return true;
    }

    public Medico buscarMedico(int id) {
        for (int i = 0; i < qtdMedicos; i++) {
            if (Integer.valueOf(medicos[i].getId()).equals(id)) {
                return medicos[i];
            }
        }
        return null; // Médico não encontrado
    }

    public Medico buscarMedicoPorCRM(int crm) {
        for (int i = 0; i < qtdMedicos; i++) {
            if (medicos[i].getCrm() == crm) {
                return medicos[i];
            }
        }
        return null; // Médico não encontrado
    }

    private int gerarNovoId() {
        int novoId = 1;
        for (int i = 0; i < qtdMedicos; i++) {
            if (medicos[i].getId() >= novoId) {
                novoId = medicos[i].getId() + 1;
            }
        }
        return novoId;
    }

    public Medico[] listarMedicos() {
        Medico[] medicos = new Medico[MedicoDao.qtdMedicos];
        for (int i = 0; i < MedicoDao.qtdMedicos; i++) {
            medicos[i] = MedicoDao.medicos[i];
        }
        return medicos;
    }
}
