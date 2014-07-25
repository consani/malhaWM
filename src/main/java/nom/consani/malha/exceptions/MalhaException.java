package nom.consani.malha.exceptions;

public class MalhaException extends Exception {

	private static final long serialVersionUID = -193792068491209925L;

	public static final String NAO_EXISTE_MALHA = "Nao existe malha cadastrada";

	public static final String ERRO_AO_CADASTRAR_MALHA = "Erro ao registrar malha. Verifique os argumentos!" ;
	
	public MalhaException(){}
	public MalhaException(String msg){
		super(msg);
	}
}
