package dao;

import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import anotacao.RecuperaUltimoOuPrimeiro;
import excecao.ObjetoNaoEncontradoException;
import modelo.Prisao;

import java.util.List;

public interface PrisaoDAO extends DaoGenerico<Prisao, Long>{
    @RecuperaObjeto
    Prisao recuperaPrisao(long numero)
            throws ObjetoNaoEncontradoException;

    @RecuperaUltimoOuPrimeiro
    Prisao recuperaPrimeiraPrisao()
            throws ObjetoNaoEncontradoException;

    @RecuperaLista
    List<Prisao> recuperaPrisoes();
}
