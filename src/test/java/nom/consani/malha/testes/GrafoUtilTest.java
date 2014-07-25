package nom.consani.malha.testes;

import java.util.ArrayList;
import java.util.List;

import nom.consani.malha.core.GrafoUtil;
import nom.consani.malha.entidades.Grafo;
import nom.consani.malha.entidades.Vertice;
import nom.consani.malha.exceptions.ArestaException;
import nom.consani.malha.exceptions.VerticeException;

import org.junit.Assert;
import org.junit.Test;


public class GrafoUtilTest {
	
	@Test
	public void testMenorCaminhoGrafoOrigem6Destino0Sample1() throws VerticeException, ArestaException{
		Grafo g = criaGrafoSample1();
		
		Vertice origem = g.getVertice(6);
		Vertice destino = g.getVertice(0);
		Vertice vertice3 = g.getVertice(3);
		
		List<Vertice> caminhoEsperado = new ArrayList<Vertice>(); // Esperado V6-V3-V0
		caminhoEsperado.add(origem);
		caminhoEsperado.add(vertice3);
		caminhoEsperado.add(destino);

		List<Vertice> menorCaminho = GrafoUtil.recuperaMenorCaminho(g, origem, destino);
		int distancia = destino.getDist(); // Esperado 5
		
		Assert.assertEquals(distancia,5);
		Assert.assertEquals(caminhoEsperado,menorCaminho);
		
	}

	@Test
	public void testMenorCaminhoGrafoOrigem0Destino5Sample1() throws VerticeException, ArestaException{
		Grafo g = criaGrafoSample1();
		
		Vertice origem = g.getVertice(0);
		Vertice destino = g.getVertice(5);
		Vertice vertice3 = g.getVertice(3);
		Vertice vertice6 = g.getVertice(6);
		
		List<Vertice> caminhoEsperado = new ArrayList<Vertice>(); // Esperado V0-V3-V6-V5
		caminhoEsperado.add(origem);
		caminhoEsperado.add(vertice3);
		caminhoEsperado.add(vertice6);
		caminhoEsperado.add(destino);

		List<Vertice> menorCaminho = GrafoUtil.recuperaMenorCaminho(g, origem, destino);
		int distancia = destino.getDist(); // Esperado 7
		
		Assert.assertEquals(distancia,7);
		Assert.assertEquals(caminhoEsperado,menorCaminho);
		
	}
	
	
	@Test
	public void testMenorCaminhoGrafoOrigem0Destino3Sample2() throws VerticeException, ArestaException{
		Grafo g = criaGrafoSample2();
		
		Vertice origem = g.getVertice(0);
		Vertice destino = g.getVertice(3);
		Vertice vertice1 = g.getVertice(1);
		
		List<Vertice> caminhoEsperado = new ArrayList<Vertice>(); // Esperado A-B-D
		caminhoEsperado.add(origem);
		caminhoEsperado.add(vertice1);
		caminhoEsperado.add(destino);

		List<Vertice> menorCaminho = GrafoUtil.recuperaMenorCaminho(g, origem, destino);
		int distancia = destino.getDist(); // Esperado 25
		
		Assert.assertEquals(distancia,25);
		Assert.assertEquals(caminhoEsperado,menorCaminho);
		
	}

	@Test
	public void testMenorCaminhoGrafoMesmaOrigemEDestinoSample1() throws VerticeException, ArestaException{
		Grafo g = criaGrafoSample1();
		
		Vertice origem = g.getVertice(0);
		Vertice destino = g.getVertice(0);
		
		List<Vertice> caminhoEsperado = new ArrayList<Vertice>(); // Esperado V0
		caminhoEsperado.add(origem);

		List<Vertice> menorCaminho = GrafoUtil.recuperaMenorCaminho(g, origem, destino);
		int distancia = destino.getDist(); // Esperado 0
		
		Assert.assertEquals(distancia,0);
		Assert.assertEquals(caminhoEsperado,menorCaminho);
		
	}


	private Grafo criaGrafoSample1() throws ArestaException, VerticeException {
		Grafo g = new Grafo();

		Vertice v0 = g.insereVertice(0, "V0");
		Vertice v1 = g.insereVertice(1, "V1");
		Vertice v2 = g.insereVertice(2, "V2");
		Vertice v3 = g.insereVertice(3, "V3");
		Vertice v4 = g.insereVertice(4, "V4");
		Vertice v5 = g.insereVertice(5, "V5");
		Vertice v6 = g.insereVertice(6, "V6");

		g.insereAresta(v0, v1, 2);
		g.insereAresta(v0, v2, 3);
		g.insereAresta(v0, v3, 4);
		g.insereAresta(v1, v4, 7);
		g.insereAresta(v1, v2, 1);
		g.insereAresta(v2, v5, 9);
		g.insereAresta(v2, v3, 2);
		g.insereAresta(v3, v6, 1);
		g.insereAresta(v4, v5, 2);
		g.insereAresta(v5, v6, 2);
		
		return g;
	}
	
	private Grafo criaGrafoSample2() throws ArestaException, VerticeException {
		Grafo g = new Grafo();

		Vertice v0 = g.insereVertice(0, "A");
		Vertice v1 = g.insereVertice(1, "B");
		Vertice v2 = g.insereVertice(2, "C");
		Vertice v3 = g.insereVertice(3, "D");
		Vertice v4 = g.insereVertice(4, "E");

		g.insereAresta(v0, v1, 10);
		g.insereAresta(v1, v3, 15);
		g.insereAresta(v0, v2, 20);
		g.insereAresta(v2, v3, 30);
		g.insereAresta(v1, v4, 50);
		g.insereAresta(v3, v4, 30);
		
		return g;
	}

	

}
