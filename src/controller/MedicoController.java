package controller;

import model.MedicoDao;
import model.Medico;

public class MedicoController {

    private MedicoDao medicoDao;

    public MedicoController(MedicoDao medicoDao) {
        this.medicoDao = medicoDao;
    }

    public boolean cadastrarMedico(String nome, String endereco, String cpf, String telefone, String login,
            String senha, String tipoUsuario, int crm, String especialidade) {
        return medicoDao.cadastrarMedico(nome, endereco, cpf, telefone, login, senha, tipoUsuario, crm, especialidade);
    }

    public boolean editarMedico(String login, String novoNome, String novoEndereco, String novoCpf, String novoTelefone,
            int novoCrm, String novaEspecialidade) {
        return medicoDao.editarMedico(login, novoNome, novoEndereco, novoCpf, novoTelefone, novoCrm, novaEspecialidade);
    }

    public boolean excluirMedico(String login) {
        return medicoDao.excluirMedico(login);
    }

    public Medico buscarMedico(String login) {
        return medicoDao.buscarMedico(login);
    }
}
