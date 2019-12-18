package dao;

import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import anotacao.RecuperaUltimoOuPrimeiro;
import excecao.ObjetoNaoEncontradoException;
import modelo.Presidiario;
import java.util.List;


public interface PresidiarioDAO  extends DaoGenerico<Presidiario, Long>{

    @RecuperaObjeto
    Presidiario recuperaPresidiario(long numero)
            throws ObjetoNaoEncontradoException;

    @RecuperaUltimoOuPrimeiro
    Presidiario recuperaPrimeiroPresidiario()
            throws ObjetoNaoEncontradoException;

    @RecuperaLista
    List<Presidiario> recuperaPresidiarios();

    int calculaTempoPrisao(Presidiario presidiario);
}