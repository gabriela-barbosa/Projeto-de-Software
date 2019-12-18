package servico;

import anotacao.Perfil;
import dao.CelaDAO;
import excecao.CelaNaoEncontradaException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Cela;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CelaAppService {
    @Autowired
    private CelaDAO celaDAO;

    @Transactional
    @Perfil(nomes = {"admin", "user"})
    public long inclui(Cela cela) {

        celaDAO.inclui(cela);

        return cela.getId();
    }

    @Transactional(rollbackFor = {CelaNaoEncontradaException.class})
    @Perfil(nomes = {"admin", "user"})
    public void altera(Cela cela) throws CelaNaoEncontradaException {
        try {
            celaDAO.getPorIdComLock(cela.getId());
            celaDAO.altera(cela);
        } catch (ObjetoNaoEncontradoException e) {
            throw new CelaNaoEncontradaException("Cela não encontrada");
        }
    }

    @Transactional(rollbackFor = {CelaNaoEncontradaException.class})
    @Perfil(nomes = {"admin"})
    public void exclui(Cela cela) throws CelaNaoEncontradaException {
        try {
            Cela cela1 = celaDAO.recuperaCela(cela.getId());
            if (!cela1.getAlocacoes().isEmpty()) {
                throw new CelaNaoEncontradaException("Esta cela possui alocações e não pode ser removido");
            }

            celaDAO.exclui(cela1);
        } catch (ObjetoNaoEncontradoException e) {
            throw new CelaNaoEncontradaException("Cela não encontrada");
        }
    }

    @Perfil(nomes = {"admin", "user"})
    public Cela recuperaCela(long id) throws CelaNaoEncontradaException {
        try {
            return celaDAO.recuperaCela(id);
        } catch (ObjetoNaoEncontradoException e) {
            throw new CelaNaoEncontradaException("Cela não encontrada");
        }
    }

    @Perfil(nomes = {"admin", "user"})
    public List<Cela> recuperaCelas() {
        List<Cela> celas = celaDAO.recuperaCelas();
        return celas;
    }

}
