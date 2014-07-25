package nom.consani.malha.testes;

import java.util.ArrayList;
import java.util.List;

import nom.consani.malha.entidades.Aresta;
import nom.consani.malha.entidades.Grafo;
import nom.consani.malha.entidades.Vertice;
import nom.consani.malha.exceptions.ArestaException;
import nom.consani.malha.exceptions.VerticeException;

import org.junit.Assert;
import org.junit.Test;


public class GrafoTest {

	@Test
	public void testAssociaArestaValida() throws ArestaException, VerticeException  {
		Grafo grafo = new Grafo();
		Vertice v1 = new Vertice(0,"A");
		Vertice v2 = new Vertice(1,"B");
		
		Aresta aresta = grafo.insereAresta(v1, v2, 10);
		Assert.assertTrue(this.verificaArestaPresente(grafo,aresta));
	}

	@Test
	public void testAssociaArestaEBuscaInexistenteValida() throws ArestaException, VerticeException  {
		Grafo grafo = new Grafo();
		Vertice v1 = new Vertice(0,"A");
		Vertice v2 = new Vertice(1,"B");
		grafo.insereAresta(v1, v2, 10);
		
		Vertice v3 =  new Vertice(2,"C");
		Aresta arestaFora = grafo.insereAresta(v1, v3, 20);
				
		Assert.assertFalse(this.verificaArestaPresente(grafo,arestaFora));
	}
	
	@Test
	public void TestArestasEVerticesDoGrafoValidos() throws VerticeException, ArestaException {
		Grafo g = new Grafo();

		Vertice v0 = g.insereVertice(0, "V0");
		Vertice v1 = g.insereVertice(1, "V1");
		Vertice v2 = g.insereVertice(2, "V2");
		Vertice v3 = g.insereVertice(3, "V3");
		Vertice v4 = g.insereVertice(4, "V4");
		Vertice v5 = g.insereVertice(5, "V5");
		Vertice v6 = g.insereVertice(6, "V6");
		
		List<Vertice> verticesInseridos = new ArrayList<Vertice>();
		
		verticesInseridos.add(v0);
		verticesInseridos.add(v1);
		verticesInseridos.add(v2);
		verticesInseridos.add(v3);
		verticesInseridos.add(v4);
		verticesInseridos.add(v5);
		verticesInseridos.add(v6);

		Aresta a1 = g.insereAresta(v0, v1, 2);
		Aresta a2 = g.insereAresta(v0, v2, 3);
		Aresta a3 = g.insereAresta(v0, v3, 4);
		Aresta a4 = g.insereAresta(v1, v4, 7);
		Aresta a5 = g.insereAresta(v1, v2, 1);
		Aresta a6 = g.insereAresta(v2, v5, 9);
		Aresta a7 = g.insereAresta(v2, v3, 2);
		Aresta a8 = g.insereAresta(v3, v6, 1);
		Aresta a9 = g.insereAresta(v4, v5, 2);
		Aresta a10 = g.insereAresta(v5, v6, 2);
		
		List<Aresta> arestasInseridas = new ArrayList<Aresta>();
		
		arestasInseridas.add(a1);
		arestasInseridas.add(a2);
		arestasInseridas.add(a3);
		arestasInseridas.add(a4);
		arestasInseridas.add(a5);
		arestasInseridas.add(a6);
		arestasInseridas.add(a7);
		arestasInseridas.add(a8);
		arestasInseridas.add(a9);
		arestasInseridas.add(a10);
		
		List<Vertice> verticesDoGrafo = g.vertices();
		List<Aresta> arestasDoGrafo = g.arestas();
		
		Assert.assertEquals(verticesDoGrafo, verticesInseridos);
		Assert.assertEquals(arestasDoGrafo, arestasInseridas);

	}
	
	private boolean verificaArestaPresente(Grafo grafo, Aresta aresta) {
		List<Aresta> arestas = grafo.arestas();
		Aresta recuperada = arestas.get(0);
		return aresta.equals(recuperada);
	}

}
