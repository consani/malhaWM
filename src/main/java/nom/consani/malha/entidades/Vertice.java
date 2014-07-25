package nom.consani.malha.entidades;

import java.util.ArrayList;
import java.util.List;

import nom.consani.malha.core.GrafoUtil;
import nom.consani.malha.exceptions.ArestaException;
import nom.consani.malha.exceptions.VerticeException;

/**
 * Representa um vértice de um grafo.<p>
 * 
 * Um vértice possui um conjunto de arestas a ele associado representando os caminhos
 * entre o vértice em questão e outros vértices do grafo. Caso não haja caminho possível
 * entre o vértice e outro do grafo, a distância é considerada infinita (Integer.MAX_VALUE)<p>
 * 
 * Um vértice também possui um conjunto vértices adjacentes, ou seja, vértice atingíveis
 * a partir dele no grafo.
 * 
 * @author Braulio Consani Moura 
 *
 */
public class Vertice implements Comparable<Vertice> {

	private List<Vertice> adjacencias = new ArrayList<Vertice>();
	private List<Aresta> arestas = new ArrayList<Aresta>();
	private Integer id;
	private String nome;
	private int distancia = Integer.MAX_VALUE;
	private Vertice anterior;
	private String status = GrafoUtil.NAO_VISITADO;

	
	public Vertice(){}
	public Vertice(Integer id, String nome) throws VerticeException{
		this.setId(id);
		this.setNome(nome);
	}

	public int getNumeroAdjacencias() {
		return this.adjacencias.size();
	}

	public List<Vertice> getAdjacencias() {
		return adjacencias;
	}

	public void setAdjacencias(List<Vertice> adjacencias) {
		this.adjacencias = adjacencias;
	}

	public void addAdjacente(Vertice adjacente) {
		this.adjacencias.add(adjacente);
	}

	public List<Aresta> getArestas() {
		return arestas;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) throws VerticeException {
		if(id == null || id < 0){
			throw new VerticeException(VerticeException.ID_INVALIDO);
		}
		this.id = id;
	}

	/**
	 * Insere uma aresta na lista de arestas do vértice corrente.<p> 
	 * A aresta é composta pelo próprio vértice ligado ao vértice informado no parâmetro, associando à esta aresta um peso.
 	 * 
	 * @param vert Vértice com o qual o vértice corrente se liga
	 * @param peso Valor associado à aresta correspondente ao peso (pode estar associado à distância, índice de congestionamento ou qualquer outra ordem de grandeza dependendo do contexto de utilização)
	 * 
	 * @return Uma instância de Aresta 
	 * @throws ArestaException Se algum atributo inválido for atribuido à aresta
	 */
	public Aresta insereAresta(Vertice vert, Integer peso) throws ArestaException {
		Aresta aresta = new Aresta();
		aresta.setV1(this);
		aresta.setV2(vert);
		aresta.setPeso(peso);
		this.arestas.add(aresta);
		return aresta;
	}

	/**
	 * Insere a instância de aresta informada no parâmetro à lista de arestas do vértice corrente. 
	 * Esta instância de aresta contém o próprio vértice ligado ao vértice informado no parâmetro, associando à esta aresta um peso.
	 * 
	 * @param vert Vértice com o qual o vértice corrente se liga
	 * @param a Instância de Aresta que representa a ligação entre o vértice corrente e o informado no parãmetro
	 * @param peso Valor associado à aresta correspondente ao peso (pode estar associado à distância, índice de congestionamento ou qualquer outra ordem de grandeza dependendo do contexto de utilização)
	 *
	 * @return A instância de Aresta que associa o vértice corrente ao vértice informado no parâmetro
	 * @throws ArestaException  Se algum atributo inválido for atribuido à aresta
	 */
	public Aresta insereAresta(Vertice vert, Aresta a, Integer peso) throws ArestaException {
		a.setV1(vert);
		a.setV2(this);
		a.setPeso(peso);
		this.arestas.add(a);
		return a;
	}

	public void removerTodasArestas() {
		this.arestas = new ArrayList<Aresta>();
	}

	@Override //gerado pela ferramenta
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertice other = (Vertice) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override //gerado pela ferramenta
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "(Vertice ID:" + id + "-" + nome + ")";
	}

	public int compareTo(Vertice outroVertice) {
		return Double.compare(distancia, outroVertice.getDist());
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getDist() {
		return distancia;
	}

	public void setDist(int dist) {
		this.distancia = dist;
	}

	public Vertice getAnterior() {
		return anterior;
	}

	public void setAnterior(Vertice anterior) {
		this.anterior = anterior;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws VerticeException {
		if(nome == null || nome.trim().isEmpty()){
			throw new VerticeException(VerticeException.NOME_INVALIDO);
		}
		this.nome = nome;
	}
}
