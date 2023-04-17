package model;

import java.util.Date;


public class MedicoDao {
    private Medico[] medicos;
    private int qtdMedicos;

    public MedicoDao(int tamanho) {
        medicos = new Medico[tamanho];
        qtdMedicos = 0;
    }

    public boolean cadastrarMedico(String nome, String endereco, String cpf, String telefone, String login,
            String senha, String tipoUsuario, int crm, String especialidade) {
        if (buscarMedico(login) != null) {
            return false; // Já existe um médico com esse login
        }

        int id = gerarNovoId();
        Date dataCriacao = new Date();
        Medico novoMedico = new Medico(id, nome, endereco, cpf, telefone, login, senha, crm, especialidade, dataCriacao, dataCriacao);
        medicos[qtdMedicos] = novoMedico;
        qtdMedicos++;
        return true;
    }

    public boolean editarMedico(String login, String novoNome, String novoEndereco, String novoCpf, String novoTelefone, int novoCrm, String novaEspecialidade) {
        Medico medico = buscarMedico(login);
        if (medico == null) {
            return false; // Médico não encontrado
        }

        medico.setNome(novoNome);
        medico.setEndereco(novoEndereco);
        medico.setCpf(novoCpf);
        medico.setTelefone(novoTelefone);
        medico.setCrm(novoCrm);
        medico.setEspecialidade(novaEspecialidade);
        medico.setDataModificacao(new Date());
        return true;
    }

    public boolean excluirMedico(String login) {
        int indice = -1;
        for (int i = 0; i < qtdMedicos; i++) {
            if (medicos[i].getLogin().equals(login)) {
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

    public Medico buscarMedico(String login) {
        for (int i = 0; i < qtdMedicos; i++) {
            if (medicos[i].getLogin().equals(login)) {
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
}
