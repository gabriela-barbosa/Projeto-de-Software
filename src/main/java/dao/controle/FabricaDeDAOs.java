package dao.controle;

import dao.impl.*;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

@Configuration
public class FabricaDeDAOs {
    @Bean
    public static AlocacaoDAOImpl getAlocacaoDao() throws Exception {
        return getDao(dao.impl.AlocacaoDAOImpl.class);
    }

    @Bean
    public static CelaDAOImpl getCelaDao() throws Exception {
        return getDao(dao.impl.CelaDAOImpl.class);
    }

    @Bean
    public static PresidiarioDAOImpl getPresidiarioDao() throws Exception {
        return getDao(dao.impl.PresidiarioDAOImpl.class);
    }

    @Bean
    public static PrisaoDAOImpl getPrisaoDao() throws Exception {
        return getDao(dao.impl.PrisaoDAOImpl.class);
    }

    @Bean
    public static LoginDAOImpl getLoginDao() throws Exception {
        return getDao(dao.impl.LoginDAOImpl.class);
    }


    @SuppressWarnings("unchecked")
    public static <T> T getDao(Class<T> classeDoDao) throws Exception {
        return (T) Enhancer.create(classeDoDao, new InterceptadorDeDAO());
    }
}
