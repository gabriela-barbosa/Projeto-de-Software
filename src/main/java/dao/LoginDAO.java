package dao;

import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import anotacao.RecuperaUltimoOuPrimeiro;
import excecao.ObjetoNaoEncontradoException;
import modelo.Usuario;

import java.util.List;

public interface LoginDAO extends DaoGenerico<Usuario, Long> {

    @RecuperaLista
    List<Usuario> recuperaListaDeUsuarios();

    @RecuperaObjeto
    Usuario recuperaUsuario(String usuario);

}
