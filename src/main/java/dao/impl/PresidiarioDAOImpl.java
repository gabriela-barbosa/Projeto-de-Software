package dao.impl;
import modelo.Presidiario;
import dao.PresidiarioDAO;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.stereotype.Repository;


@Repository
public abstract class PresidiarioDAOImpl extends JPADaoGenerico<Presidiario, Long> implements PresidiarioDAO {
    public PresidiarioDAOImpl() {
        super(Presidiario.class);
    }

    public final int calculaTempoPrisao(Presidiario presidiario){

        Days diasEntre = Days.daysBetween(new DateTime(presidiario.getDataPrisao()), new DateTime(presidiario.getDataSoltura()));

        return diasEntre.getDays();

    }

}