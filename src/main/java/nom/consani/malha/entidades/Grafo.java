package nom.consani.malha.entidades;

import java.util.ArrayList;
import java.util.List;

import nom.consani.malha.exceptions.ArestaException;
import nom.consani.malha.exceptions.VerticeException;

/**
 * Representa um grafo ou um conjunto de arestas interligadas entre si:
 * 	- Um v�rtice � um determinado ponto ou elemento contido no grafo
 *  - Uma aresta � associa��o entre dois v�rtices presentes no grafo
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
	 * Retorna a inst�ncia do v�rtice com identificado id no grafo corrente.
	 * 
	 * @param id Identificado do v�rtice
	 * 
	 * @return V�rtice contido no grafo com identificado igual a id ou null caso n�o encontre.
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
	 * Retorna n�mero de v�rtices contido no grafo.
	 * 
	 * @return N�mero de v�rtices do grafo.
	 */
	public int numVertices() {
		return numVertices;
	}

	/**
	 * Retorna o n�mero de arestas do grafo
	 * 
	 * @return N�mero de arestas do grafo.
	 */
	public int numArestas() {
		return numArestas;
	}

	/**
	 * Retorna o conjunto de v�rtices contido no grafo.
	 * 
	 * @return Lista de v�rtices do grafo.
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
	 * Retorna o v�rtice oposto ao v�rtice v na aresta a. 
	 * 
	 * @param v V�rtice informado como base para obter o v�rtice oposto na aresta a.
	 * @param a Aresta onde se encontra o v�rtice v e o v�rtice oposto que ser� retornado no m�todo.
	 * 
	 * @return V�rtice oposto a v na aresta a.
	 * 
	 * @throws VerticeException Caso o v�rtice v n�o exista.
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
	 * Verifica se dois v�rtices s�o adjancentes no grafo corrente.
	 * 
	 * @param v Um v�rtice
	 * @param w Outro v�rtice
	 * 
	 * @return true se os dois v�rtices informados no par�metro s�o adjacentes.
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
	 * Insere uma nova aresta ao grafo corrente associando os v�rtices v e w informados no conjunto de par�metros. <p>
	 * O peso da aresta � informado no par�metro peso. 
	 * 
	 * @param v Primeiro v�rtice da aresta 
	 * @param w Segundo v�rtice da aresta
	 * @param peso Peso associado � aresta 
	 * 
	 * @return Uma inst�ncia de Aresta.
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
	 * Insere um novo v�rtice ao grafo. 
	 * 
	 * @param id Identificado do v�rtice 
	 * @param nome Nome do v�rtice
	 * 
	 * @return O v�rtice criado e adicionado ao grafo
	 *  
	 * @throws VerticeException Caso o v�rtice j� tenha sido inserido no grafo anteriormente.
	 * 							Caso o id ou nome associado informado sejam nulos
	 * 							Caso o n�mero associado ao id seja nulo						
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
	 * Remove um v�rtice do conjunto de v�rtices do grafo.
	 * 
	 * @param v V�rtice a ser removido
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
