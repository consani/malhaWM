
package nom.consani.malha.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import nom.consani.malha.exceptions.MalhaException;
import nom.consani.malha.transfer.MenorCaminho;
import nom.consani.malha.transfer.Trecho;


/**
 * Servico de implementacao da malha logistica 
 */
@WebService(name = "MalhaService")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface MalhaService {


    /**
     * Registra uma malha logistica a partir de um conjunto de trechos informados.
     * Cada execucao do servico substitui a malha registrada anteriormente.
     * 
     * @param trechos Lista de trechos que compoem a malha logistica
     * 
     * @return Mensagem de criacao da malha 
     * 
     * @throws MalhaException Ocorre caso a malha esteja inconsistente
     */
    @WebMethod
    public String criarMalha(@WebParam(name = "trechos", partName = "trechos") Trecho[] trechos) throws MalhaException;

    /**
     * O pre-requisito deste servico e haver registro anterior de algum malha logistica
     * 
     * A partir dos dados informados nos parametros, ocorre o calculo do menor caminho 
     * e o calculo do custo considerando a ultima malha logistica registrada.
     * 
     * 
     * @param origem Ponto de origem na malha
     * @param destino Ponto de destino na malha
     * @param precoLitro Preco do litro de combustivel utilizado
     * @param autonomia Autonomia do veiculo 
     * @return
     *     MenorCaminho contendo o caminho encontrado e o custo total do transporte
     * @throws MalhaException Pontos inexistentes, valores invalidos na malha.
     */
    @WebMethod
    public MenorCaminho calcularMenorCaminho(
        @WebParam(name = "origem", partName = "origem")
        String origem,
        @WebParam(name = "destino", partName = "destino")
        String destino,
        @WebParam(name = "autonomia", partName = "autonomia")
        double autonomia,
        @WebParam(name = "precoLitro", partName = "precoLitro")
        double precoLitro)
        throws MalhaException;

}
