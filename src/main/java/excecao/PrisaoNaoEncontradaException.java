package excecao;

public class PrisaoNaoEncontradaException extends Exception
{
	private final static long serialVersionUID = 1;

	private int codigo;

	public PrisaoNaoEncontradaException(String msg)
	{	super(msg);
	}

	public PrisaoNaoEncontradaException(int codigo, String msg)
	{	super(msg);
		this.codigo = codigo;
	}
	
	public int getCodigoDeErro()
	{	return codigo;
	}
}	