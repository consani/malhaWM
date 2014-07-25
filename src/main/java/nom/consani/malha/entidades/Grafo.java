package nom.consani.malha.entidades;

import java.util.ArrayList;
import java.util.List;

import nom.consani.malha.exceptions.ArestaException;
import nom.consani.malha.exceptions.VerticeException;

/**
 * Representa um grafo ou um conjunto de arestas interligadas entre si:
 * 	- Um vértice é um determinado ponto ou elemento contido no grafo
 *  - Uma aresta é associação entre dois vértices presentes no grafo
 * 
 * 
 * @author Braulio Consani Moura
 *
 */
public class Grafo {

	private int numVertices = 0;
	private int numArestas = 0;
	private List<Vertice> vertices = new ArrayList<Vertice>();
	private List<Aresta> arestas = new ArrayList<Aresta>();

	/**
	 * Retorna a instância do vértice com identificado id no grafo corrente.
	 * 
	 * @param id Identificado do vértice
	 * 
	 * @return Vértice contido no grafo com identificado igual a id ou null caso não encontre.
	 */
	public Vertice getVertice(Integer id) {
		for (Vertice v : vertices) {
			if (v.getId().equals(id)) {
				return v;
			}
		}
		return null;
	}

	/**
	 * Retorna número de vértices contido no grafo.
	 * 
	 * @return Número de vértices do grafo.
	 */
	public int numVertices() {
		return numVertices;
	}

	/**
	 * Retorna o número de arestas do grafo
	 * 
	 * @return Número de arestas do grafo.
	 */
	public int numArestas() {
		return numArestas;
	}

	/**
	 * Retorna o conjunto de vértices contido no grafo.
	 * 
	 * @return Lista de vértices do grafo.
	 */
	public List<Vertice> vertices() {
		return vertices;
	}

	/**
	 * Retorna o conjunto de arestas contido no grafo.
	 * 
	 * @return Lista de arestas do grafo.
	 */
	public List<Aresta> arestas() {
		return arestas;
	}

	public Vertice umVertice() {
		return null;
	}

	public int numeroDeVizinhos(Vertice v) {
		return v.getNumeroAdjacencias();
	}

	public List<Vertice> verticesAdjacentes(Vertice v) {
		return v.getAdjacencias();
	}

	public List<Aresta> arestasIncidentes(Vertice v) {
		return v.getArestas();
	}

	public List<Vertice> endVertices(Aresta a) {
		List<Vertice> vs = new ArrayList<Vertice>();
		vs.add(a.getV1());
		vs.add(a.getV2());
		return vs;
	}

	/**
	 * Retorna o vértice oposto ao vértice v na aresta a. 
	 * 
	 * @param v Vértice informado como base para obter o vértice oposto na aresta a.
	 * @param a Aresta onde se encontra o vértice v e o vértice oposto que será retornado no método.
	 * 
	 * @return Vértice oposto a v na aresta a.
	 * 
	 * @throws VerticeException Caso o vértice v não exista.
	 */
	public static Vertice getVerticeOposto(Vertice v, Aresta a) throws VerticeException {
		for (Aresta aresta : v.getArestas()) {
			if (aresta.equals(a)) {
				if (v.equals(aresta.getV1())) {
					return aresta.getV2();
				} else {
					return aresta.getV1();
				}
			}
		}
		throw new VerticeException(VerticeException.NAO_EXISTE_ID);
	}

	/**
	 * Verifica se dois vértices são adjancentes no grafo corrente.
	 * 
	 * @param v Um vértice
	 * @param w Outro vértice
	 * 
	 * @return true se os dois vértices informados no parâmetro são adjacentes.
	 */
	public boolean saoAdjacentes(Vertice v, Vertice w) {
		for (Vertice vertice : v.getAdjacencias()) {
			if (vertice.equals(w)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Insere uma nova aresta ao grafo corrente associando os vértices v e w informados no conjunto de parâmetros. <p>
	 * O peso da aresta é informado no parâmetro peso. 
	 * 
	 * @param v Primeiro vértice da aresta 
	 * @param w Segundo vértice da aresta
	 * @param peso Peso associado à aresta 
	 * 
	 * @return Uma instância de Aresta.
	 */
	public Aresta insereAresta(Vertice v, Vertice w, Integer peso) throws ArestaException {
		
		if(v == null || w == null){
			throw new ArestaException(ArestaException.SEM_VERTICE);
		}
		
		if(peso == null || peso < 0){
			throw new ArestaException(ArestaException.PESO_INVALIDO + " : " + peso);
		}
		
		if(v.equals(w) || v.getNome().equals(w.getNome())){
			throw new ArestaException(ArestaException.VERTICE_DUPLICADO + " : " + v);
		}
		
		Aresta a = v.insereAresta(w, peso);
		w.insereAresta(v, a, peso);
		v.addAdjacente(w);
		w.addAdjacente(v);
		arestas.add(a);
		numArestas++;
		return a;
	}

	/**
	 * Insere um novo vértice ao grafo. 
	 * 
	 * @param id Identificado do vértice 
	 * @param nome Nome do vértice
	 * 
	 * @return O vértice criado e adicionado ao grafo
	 *  
	 * @throws VerticeException Caso o vértice já tenha sido inserido no grafo anteriormente.
	 * 							Caso o id ou nome associado informado sejam nulos
	 * 							Caso o número associado ao id seja nulo						
	 */
	public Vertice insereVertice(Integer id, String nome)throws VerticeException {
		
		if (id == null || nome == null){
			throw new VerticeException(VerticeException.SEM_IDENTIFICACAO);
		}
		
		if(id < 0){
			throw new VerticeException(VerticeException.ID_INVALIDO);
		}
		
		for (Vertice vertice : vertices) {
			if (vertice.getId().equals(id) || vertice.getNome().equals(nome)) {
				throw new VerticeException(VerticeException.ID_DUPLICADO + " : " + id);
			}
		}
		
		Vertice v = new Vertice(id, nome);
		vertices.add(v);
		numVertices++;
		return v;
	}
	
	/**
	 * Remove um vértice do conjunto de vértices do grafo.
	 * 
	 * @param v Vértice a ser removido
	 */
	public void removeVertice(Vertice v) {
		vertices.remove(v);
		numVertices--;
		v.removerTodasArestas();
	}

	/**
	 * Remove uma determinada aresta do grafo corrente.
	 * 
	 * @param a Aresta a ser removida do grafo.
	 */
	public void removeAresta(Aresta a) {
		a.setV1(null);
		a.setV2(null);
		arestas.remove(a);
	}
}
