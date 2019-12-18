package dao;

import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import anotacao.RecuperaUltimoOuPrimeiro;
import excecao.ObjetoNaoEncontradoException;
import modelo.Cela;

import java.util.List;

public interface CelaDAO extends DaoGenerico<Cela, Long>{
    @RecuperaUltimoOuPrimeiro
    Cela recuperaPrimeiraCela()
            throws ObjetoNaoEncontradoException;

    @RecuperaObjeto
    Cela recuperaCela(long numero)
            throws ObjetoNaoEncontradoException;

    @RecuperaLista
    List<Cela> recuperaCelas();
}
