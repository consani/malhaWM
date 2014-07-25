package nom.consani.malha.main;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import nom.consani.malha.core.GrafoUtil;
import nom.consani.malha.entidades.Aresta;
import nom.consani.malha.entidades.Grafo;
import nom.consani.malha.entidades.Vertice;
import nom.consani.malha.exceptions.ArestaException;
import nom.consani.malha.exceptions.MalhaException;
import nom.consani.malha.exceptions.VerticeException;

public class TesteGrafo {

	public static void main(String[] args) throws VerticeException,
			ArestaException, MalhaException {

		Grafo g = carregaGrafo();
		imprimeVertices(g);
		imprimeArestas(g);
		Vertice origem = g.getVertice(3);
		Vertice destino = g.getVertice(0);

		System.out.print("\n\nAplicando o algoritimo de DIJKSTRA no grafo - ");
		System.out.print(" Origem: " + origem);
		System.out.println(" Destino: " + destino);

		List<Vertice> menorCaminho = GrafoUtil.recuperaMenorCaminho(g, origem, destino);
		System.out.println(menorCaminho);
		System.out.println("Menor Distância até destino -> "
				+ destino.getDist());

	}

	private static void imprimeArestas(Grafo g) {
		System.out.println("Número de Arestas = " + g.numArestas());
		for (Aresta aresta : g.arestas()) {
			System.out.println("Aresta = " + aresta.getPeso() + " "
					+ aresta.getV1() + " - " + aresta.getV2());
		}
		System.out.println();
	}

	private static void imprimeVertices(Grafo g) {
		System.out.println("Número de Vertices do grafo = " + g.numVertices());
		for (Vertice vertice : g.vertices()) {
			System.out.print("Vertice :" + vertice.getId() + "-"
					+ vertice.getNome());
			System.out.print(" -->> Vizinhos");
			for (Vertice va : vertice.getAdjacencias()) {
				System.out.print(" - " + va.getId() + ":" + va.getNome());
			}
			System.out.println();
		}
	}

	/**
	 * Carrega uma malha na forma de grafo a partir de um arquivo chamado malha.txt
	 * contido no diretório raiz.<p>
	 * 
	 * Cada linha do arquivo contém um trecho ou aresta do grafo no formato X Y D.<p>
	 * Onde X = ponto de origem, Y = ponto de destino, D = distância entre X e Y 
	 * 
	 *  
	 * @return Grafo contendo todos os caminhos informados no arquivo malha.txt
	 * 
	 * @throws MalhaException Se o arquivo malha.txt não for encontrado para a geração do grafo.
	 * @throws VerticeException Se for encontrada alguma inconsistência na criacao do vértice
	 * @throws ArestaException Se for encontratda alguma inconsistência na criação da aresta.
	 */
	private static Grafo carregaGrafo() throws MalhaException, VerticeException, ArestaException {
		
		Grafo g = new Grafo();

		try (Scanner fileScan = new Scanner(new FileInputStream("malha.txt"));){
			
			int verticeId = 0;
			Vertice v1 = null;
			Vertice v2 = null;

			while (fileScan.hasNextLine()) {

				String trechoAsString = fileScan.nextLine();
				if(trechoAsString == null || trechoAsString.trim().isEmpty()){
					break;
				}
				
				String[] elementos = trechoAsString.split(" ");
				String nomeVertice1 = elementos[0];
				String nomeVertice2 = elementos[1];
				Integer distancia = Integer.parseInt(elementos[2]);

				if ((v1 = getVertice(nomeVertice1, g)) == null) {
					v1 = g.insereVertice(verticeId++, nomeVertice1);
				}

				if ((v2 = getVertice(nomeVertice2, g)) == null) {
					v2 = g.insereVertice(verticeId++, nomeVertice2);
				}

				g.insereAresta(v1, v2, distancia);

			}

		} catch (FileNotFoundException e) {
			throw new MalhaException(MalhaException.NAO_EXISTE_MALHA);
		}

		return g;
	}

	private static Vertice getVertice(String nomeVertice, Grafo grafo) {
		for (Vertice vertice : grafo.vertices())
			if (nomeVertice.equals(vertice.getNome())) {
				return vertice;
			}

		return null;
	}
}
