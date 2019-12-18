package dao.impl;

import dao.LoginDAO;
import modelo.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public abstract class LoginDAOImpl extends JPADaoGenerico<Usuario, Long> implements LoginDAO {
    public LoginDAOImpl() {
	    super(Usuario.class);
    }
}
