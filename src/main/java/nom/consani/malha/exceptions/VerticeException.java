package nom.consani.malha.exceptions;

public class VerticeException extends Exception {

	private static final long serialVersionUID = -5960373012142425625L;

	public static final String NAO_EXISTE_ID = "O id n�o existe";
	public static final String ID_DUPLICADO = "Id duplicado";
	public static final String SEM_IDENTIFICACAO = "Alguma identifica��o n�o informada";
	public static final String ID_INVALIDO = "ID inv�lido";
	public static final String NOME_INVALIDO = "Nome Inv�lido";
	
	public VerticeException(){}
	public VerticeException(String mensagem) {
		super(mensagem);
	}

}
