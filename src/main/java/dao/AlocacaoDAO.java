package dao;

import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import anotacao.RecuperaUltimoOuPrimeiro;
import excecao.ObjetoNaoEncontradoException;
import modelo.Alocacao;

import java.util.List;

public interface AlocacaoDAO extends DaoGenerico<Alocacao, Long>{

    @RecuperaObjeto
    Alocacao recuperaUmaAlocacaoERelacionados(long numero)
            throws ObjetoNaoEncontradoException;

    @RecuperaUltimoOuPrimeiro
    Alocacao recuperaPrimeiraAlocacao()
            throws ObjetoNaoEncontradoException;

    @RecuperaLista
    List<Alocacao> recuperaAlocacoes();


}
