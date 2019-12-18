package excecao;

public class CelaNaoEncontradaException extends Exception {

    private final static long serialVersionUID = 1;

    private int codigo;

    public CelaNaoEncontradaException(String msg)
    {	super(msg);
    }

    public CelaNaoEncontradaException(int codigo, String msg)
    {	super(msg);
        this.codigo = codigo;
    }

    public int getCodigoDeErro()
    {	return codigo;
    }
}
