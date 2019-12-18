package excecao;

public class AlocacaoNaoEncontradaException extends Exception
{
	private final static long serialVersionUID = 1;

	private int codigo;

	public AlocacaoNaoEncontradaException(String msg)
	{	super(msg);
	}

	public AlocacaoNaoEncontradaException(int codigo, String msg)
	{	super(msg);
		this.codigo = codigo;
	}
	
	public int getCodigoDeErro()
	{	return codigo;
	}
}	