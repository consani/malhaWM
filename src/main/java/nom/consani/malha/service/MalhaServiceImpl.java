package nom.consani.malha.service;

import java.util.List;

import javax.jws.WebService;

import nom.consani.malha.core.GrafoUtil;
import nom.consani.malha.entidades.Grafo;
import nom.consani.malha.entidades.Vertice;
import nom.consani.malha.exceptions.ArestaException;
import nom.consani.malha.exceptions.MalhaException;
import nom.consani.malha.exceptions.VerticeException;
import nom.consani.malha.transfer.MenorCaminho;
import nom.consani.malha.transfer.Trecho;

@WebService(endpointInterface="nom.consani.malha.service.MalhaService")
public class MalhaServiceImpl implements MalhaService{

		
	@Override
	public String criarMalha(Trecho[] trechos) throws MalhaException {
		String msg = "Malha Registrada com Sucesso!";
		try{
			GrafoUtil.criaGrafo(trechos);
		}catch(Exception e){
			msg = "ERRO : A Malha nao foi registrada !";
			throw e;
		}
		return msg;
	}

	@Override
	public MenorCaminho calcularMenorCaminho(String origem, String destino, double autonomia, double precoPorLitro) throws MalhaException{
		MenorCaminho menorCaminho = null;
		try {
			Grafo g = GrafoUtil.carregaGrafo();
			Vertice v1 = GrafoUtil.getVertice(origem,g);
			Vertice v2 = GrafoUtil.getVertice(destino,g);
			List<Vertice> trechos = GrafoUtil.recuperaMenorCaminho(g, v1,v2);

			int distanciaTotal =  v2.getDist();
			double custo = ( distanciaTotal/autonomia) * precoPorLitro; 

			menorCaminho = criarMenorCaminho(trechos,custo); 

		} catch (VerticeException | ArestaException e) {
			throw new MalhaException(e.getMessage());
		}
		
		return menorCaminho;
	}

	private MenorCaminho criarMenorCaminho(List<Vertice> trechos, double custo) {
		StringBuilder sb = new StringBuilder();
		MenorCaminho mc = new MenorCaminho();
		
		for(Vertice v : trechos){
			sb.append(v.getNome()).append(" ");
		}
		
		mc.setCusto(custo);
		mc.setCaminho(sb.toString());
		
		return mc;
	}

}
