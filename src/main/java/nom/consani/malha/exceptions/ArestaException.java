package nom.consani.malha.exceptions;

public class ArestaException extends Exception {

	private static final long serialVersionUID = -7580396659084624748L;

	public static final String PESO_INVALIDO = "Valor Inv�lido atribu�do ao peso da aresta";
	public static final String SEM_VERTICE = "V�rtice n�o informado";
	public static final String VERTICE_DUPLICADO = "V�rtice duplicado na aresta";

	public ArestaException(){}
	public ArestaException(String mensagem) {
		super(mensagem);
	}

}
