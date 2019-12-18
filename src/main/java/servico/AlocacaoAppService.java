package servico;

import anotacao.Perfil;
import dao.AlocacaoDAO;
import dao.CelaDAO;
import excecao.AlocacaoNaoEncontradaException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Alocacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class AlocacaoAppService {
    @Autowired
    private AlocacaoDAO alocacaoDAO;
    @Autowired
    private CelaDAO celaDAO;

    @Transactional
    @Perfil(nomes={"admin", "user"})
    public long inclui(Alocacao alocacao) {
        alocacaoDAO.inclui(alocacao);
        return alocacao.getId();
    }

    @Transactional(rollbackFor = { AlocacaoNaoEncontradaException.class })
    @Perfil(nomes={"admin", "user"})
    public void altera(Alocacao alocacao) throws AlocacaoNaoEncontradaException {
        try {
            alocacaoDAO.getPorIdComLock(alocacao.getId());
            alocacaoDAO.altera(alocacao);
        } catch (ObjetoNaoEncontradoException e) {
            throw new AlocacaoNaoEncontradaException("Alocação não encontrada");
        }
    }

    @Transactional (rollbackFor = {AlocacaoNaoEncontradaException.class})
    @Perfil(nomes={"admin"})
    public void exclui(Alocacao alocacao) throws AlocacaoNaoEncontradaException {
        try {
            Alocacao alocacao1 = alocacaoDAO.recuperaUmaAlocacaoERelacionados(alocacao.getId());
            alocacaoDAO.exclui(alocacao1);
        } catch (ObjetoNaoEncontradoException e) {
            throw new AlocacaoNaoEncontradaException("Alocação não encontrada");
        }
    }
    @Perfil(nomes={"admin", "user"})
    public Alocacao recuperaAlocacao(long id) throws AlocacaoNaoEncontradaException {
        try {
            return alocacaoDAO.getPorId(id);
        } catch (ObjetoNaoEncontradoException e) {
            throw new AlocacaoNaoEncontradaException("Alocação não encontrada");
        }
    }
    @Perfil(nomes={"admin", "user"})
    public List<Alocacao> recuperaAlocacoesERelacionados() {
        List<Alocacao> alocacoes = alocacaoDAO.recuperaAlocacoes();

        return alocacoes;
    }

}
