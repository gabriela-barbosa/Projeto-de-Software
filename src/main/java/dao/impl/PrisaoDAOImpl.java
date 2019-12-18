package dao.impl;

import dao.PrisaoDAO;
import modelo.Prisao;
import org.springframework.stereotype.Repository;

@Repository
public abstract class PrisaoDAOImpl extends JPADaoGenerico<Prisao, Long> implements PrisaoDAO {
    public PrisaoDAOImpl() {
        super(Prisao.class);
    }
}
