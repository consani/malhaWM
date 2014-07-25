package nom.consani.malha.exceptions;

public class ArestaException extends Exception {

	private static final long serialVersionUID = -7580396659084624748L;

	public static final String PESO_INVALIDO = "Valor Inválido atribuído ao peso da aresta";
	public static final String SEM_VERTICE = "Vértice não informado";
	public static final String VERTICE_DUPLICADO = "Vértice duplicado na aresta";

	public ArestaException(){}
	public ArestaException(String mensagem) {
		super(mensagem);
	}

}
