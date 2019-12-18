package servico;


import anotacao.Perfil;
import dao.PresidiarioDAO;
import excecao.ObjetoNaoEncontradoException;
import excecao.PresidiarioNaoEncontradoException;
import modelo.Presidiario;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class PresidiarioAppService {

    @Autowired
    private PresidiarioDAO presidiarioDAO;

    @Transactional
    @Perfil(nomes = {"admin", "user"})
    public long inclui(Presidiario presidiario) {
        presidiarioDAO.inclui(presidiario);
        return presidiario.getId();
    }

    @Transactional(rollbackFor = {PresidiarioNaoEncontradoException.class})
    @Perfil(nomes = {"admin", "user"})
    public void altera(Presidiario presidiario) throws PresidiarioNaoEncontradoException {
        try {
            presidiarioDAO.getPorIdComLock(presidiario.getId());
            presidiarioDAO.altera(presidiario);
        } catch (ObjetoNaoEncontradoException e) {
            throw new PresidiarioNaoEncontradoException("Presidiário não encontrado");
        }
    }

    @Transactional(rollbackFor = {PresidiarioNaoEncontradoException.class})
    @Perfil(nomes = {"admin"})
    public void exclui(Presidiario presidiario) throws PresidiarioNaoEncontradoException {
        try {
            Presidiario presidiario1 = presidiarioDAO.recuperaPresidiario(presidiario.getId());
            if (!presidiario.getAlocacoes().isEmpty()) {
                throw new PresidiarioNaoEncontradoException("Este presidiário possui alocações e não pode ser removido");
            }
            presidiarioDAO.exclui(presidiario1);
        } catch (ObjetoNaoEncontradoException e) {
            throw new PresidiarioNaoEncontradoException("Presidiário não encontrado");
        }
    }

    @Perfil(nomes = {"admin", "user"})
    public Presidiario recuperaPresidiario(long id) throws PresidiarioNaoEncontradoException {
        try {
            return presidiarioDAO.recuperaPresidiario(id);
        } catch (ObjetoNaoEncontradoException e) {
            throw new PresidiarioNaoEncontradoException("Presidiário não encontrado");
        }
    }

    @Perfil(nomes = {"admin", "user"})
    public List<Presidiario> recuperaPresidiarios() {
        List<Presidiario> presidiarios = presidiarioDAO.recuperaPresidiarios();

        return presidiarios;
    }

    @Perfil(nomes = {"admin", "user"})
    public int calculaTempoPrisao(Presidiario presidiario) {

        Days diasEntre = Days.daysBetween(new DateTime(presidiario.getDataPrisao()), new DateTime(presidiario.getDataSoltura()));

        return diasEntre.getDays();

    }

}