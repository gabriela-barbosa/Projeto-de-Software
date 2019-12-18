package aspecto;


import anotacao.Perfil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import singleton.SingletonPerfis;

import java.lang.reflect.Method;

@Aspect
public class AspectoParaPerfil {

    @Pointcut("execution(* servico.AlocacaoAppService.*(..)) ||  "
    		+ "execution(* servico.PrisaoAppService.*(..)) ||  "
    		+ "execution(* servico.PresidiarioAppService.*(..)) ||  "
    		+ "execution(* servico.CelaAppService.*(..))")
    public void verificaPerfis() {
    }


	@Around("verificaPerfis()")
    public Object verificaPerfilUsuario(ProceedingJoinPoint joinPoint) throws Throwable {
        
    	String[] perfisDoUsuarioLogado = SingletonPerfis.getSingletonPerfis().getPerfis();

    	System.out.println("Perfis do Usuário Logado");
    	for(String perfil : perfisDoUsuarioLogado) {
    		System.out.println(perfil);
    	}
    	
    	final String methodName = joinPoint.getSignature().getName();
    	
    	final MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
    	
    	Method method = methodSignature.getMethod();

    	    try {
				method = joinPoint.getTarget().getClass().getDeclaredMethod(methodName, method.getParameterTypes());
			} catch (NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
    	
    	
    	Perfil anotacaoPerfil = method.getAnnotation(Perfil.class);
    	
    	//Significa que o método buscado não possui a anotaçao de Perfil, 
    	//logo pode ser executado normalmente
    	if(anotacaoPerfil == null) {
    		return joinPoint.proceed();	
    	}
    	else {
    		String[] perfisPermitidos = method.getAnnotation(Perfil.class).nomes();
        	System.out.println("Primeiro perfil: "+perfisPermitidos[0]);

        	System.out.println("Perfis Permitidos Pelo Método: ");
	    	for(String perfil : perfisPermitidos) {
	    		System.out.println(perfil);
	    	}
	    	
	
	    	for(String perfilMetodo : perfisPermitidos) {
	    		for(String perfilUser : perfisDoUsuarioLogado) {
	    			if(perfilMetodo.equals(perfilUser)) {
	    				return joinPoint.proceed();
	    				//Significa que o usuário tem permissão para acessar o método
	    			}
	    		}
	    	}
	    	System.out.println("Usuário não possui permissão");
	    	throw new Throwable("Usuário logado no momento não possui permissão de acesso ao método");
    	}
    }
}
