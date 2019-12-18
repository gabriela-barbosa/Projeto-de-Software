package dao.impl;
import dao.CelaDAO;
import modelo.Cela;
import org.springframework.stereotype.Repository;


@Repository
public abstract class CelaDAOImpl extends JPADaoGenerico<Cela, Long> implements CelaDAO {
    public CelaDAOImpl() {
        super(Cela.class);
    }
}