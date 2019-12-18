package servico;

import dao.LoginDAO;
import modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service()
public class LoginAppService {


    @Autowired
    private LoginDAO loginDAO;

    @Transactional
    public Usuario logar(String Conta, String Senha) {
        int existe = 0;
        List<Usuario> verificarUsu;
        verificarUsu = loginDAO.recuperaListaDeUsuarios();
        for (Usuario p : verificarUsu) {
            if (p.getConta().equals(Conta)) {
                existe = 1;
                break;
            }
        }
        if (existe == 1) {
            Usuario p = loginDAO.recuperaUsuario(Conta);
            if (!p.getSenha().equals(Senha)) {
                return null;
            } else {
                return p;
            }
        } else {
            return null;
        }
    }
}