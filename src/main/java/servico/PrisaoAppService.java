package servico;

import anotacao.Perfil;
import dao.PrisaoDAO;
import excecao.PrisaoNaoEncontradaException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Prisao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class PrisaoAppService {

    @Autowired
    private PrisaoDAO prisaoDAO;

    @Transactional
    @Perfil(nomes = {"admin", "user"})
    public long inclui(Prisao prisao) {
        prisaoDAO.inclui(prisao);
        return prisao.getId();
    }

    @Transactional(rollbackFor = {PrisaoNaoEncontradaException.class})
    @Perfil(nomes = {"admin", "user"})
    public void altera(Prisao prisao) throws PrisaoNaoEncontradaException {
        try {
            prisaoDAO.getPorIdComLock(prisao.getId());
            prisaoDAO.altera(prisao);
        } catch (ObjetoNaoEncontradoException e) {
            throw new PrisaoNaoEncontradaException("Prisão não encontrada");
        }
    }

    @Transactional(rollbackFor = {PrisaoNaoEncontradaException.class})
    @Perfil(nomes = {"admin"})
    public void exclui(Prisao prisao) throws PrisaoNaoEncontradaException {
        try {
            Prisao prisao1 = prisaoDAO.recuperaPrisao(prisao.getId());

            if (!prisao1.getCelas().isEmpty()) {
                {
                    throw new PrisaoNaoEncontradaException("Esta prisão possui celas e não pode ser removido");
                }
            }
            prisaoDAO.exclui(prisao1);
        } catch (ObjetoNaoEncontradoException e) {
            throw new PrisaoNaoEncontradaException("Prisão não encontrada");
        }
    }

    @Perfil(nomes = {"admin", "user"})
    public Prisao recuperaPrisao(long id) throws PrisaoNaoEncontradaException {
        try {
            return prisaoDAO.recuperaPrisao(id);
        } catch (ObjetoNaoEncontradoException e) {
            throw new PrisaoNaoEncontradaException("Prisão não encontrada");
        }
    }

    @Perfil(nomes = {"admin", "user"})
    public List<Prisao> recuperaPrisoes() {
        List<Prisao> prisoes = prisaoDAO.recuperaPrisoes();

        return prisoes;
    }

}
