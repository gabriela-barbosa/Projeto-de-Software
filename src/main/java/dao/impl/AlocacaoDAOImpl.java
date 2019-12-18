package dao.impl;

import dao.AlocacaoDAO;
import modelo.Alocacao;
import org.springframework.stereotype.Repository;

@Repository
public abstract class AlocacaoDAOImpl extends JPADaoGenerico<Alocacao, Long> implements AlocacaoDAO {
    public AlocacaoDAOImpl() {
        super(Alocacao.class);
    }
}
