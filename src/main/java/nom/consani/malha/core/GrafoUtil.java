package nom.consani.malha.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

import nom.consani.malha.entidades.Aresta;
import nom.consani.malha.entidades.Grafo;
import nom.consani.malha.entidades.Vertice;
import nom.consani.malha.exceptions.ArestaException;
import nom.consani.malha.exceptions.MalhaException;
import nom.consani.malha.exceptions.VerticeException;
import nom.consani.malha.transfer.Trecho;

/**
 * Contém os algoritmos que realizam operações em grafos.
 * <p>
 * 
 * @author Braulio Consani Moura
 * 
 */
public class GrafoUtil {

	public static final String NAO_VISITADO = "NAO VISITADO";
	public static String VISITADO = "VISITADO";
	public static String RETORNO = "RETORNO";
	public static String CROSS = "CROSS";
	public static List<String> pilha = new ArrayList<String>();
	private static boolean go = true;
	private static boolean voltar = true;

	/**
	 * Inicia a execução do caminhamento em profundidade no grafo a partir do vertice informado.
	 * 
	 * @param vertice Vértice inicial
	 * 
	 * @throws VerticeException Se alguma inconsistência relacionada a algum vértice for encontrada.
	 */
	public static void caminhadaEmProfundidade(Vertice vertice) throws VerticeException {
		System.out.println("\nCaminhando em profundidade no grafo - Vértice de Início: " + vertice.getId());
		System.out.print("INICIO V:" + vertice.getId() + "-" + vertice.getNome());
		vertice.setStatus(VISITADO);
		executaCaminhadaEmProfundidade(vertice);
	}

	/**
	 * Realiza uma caminhanda em profundidade a partir de um vértice inicial 
	 * A estrutura de dados mais adequada para essa situação é uma pilha. 
	 * 
	 * -A partir de um vértice qualquer é escolhido um vizinho não visitado. 
	 * 		-- Esta operação é feita até não existir mais vértices vizinhos para visitar. 
	 * 
	 * - Depois da primeira parte, o caminhamento a realizar é para o último caminho realizado e ocorre a tentativa de achar um vizinho que não foi visitado. 
	 * 		-- Caso não seja encontrado um vértice, deve-se retornar pelo caminho já realizado para nova opção de caminhamento a partir do vértice.
	 * 
	 * @param vertice Vértice inicial do caminhamento
	 * 
	 * @throws VerticeException Se alguma inconsistência relacionada a algum vértice for encontrada.
	 */
	public static void executaCaminhadaEmProfundidade(Vertice vertice)	throws VerticeException {

		for (Aresta aresta : vertice.getArestas()) {
			if (aresta.getStatus().equals(NAO_VISITADO)) {
				Vertice w = Grafo.getVerticeOposto(vertice, aresta);
				if (w.getStatus().equals(NAO_VISITADO)) {
					aresta.setStatus(VISITADO);
					w.setStatus(VISITADO);
					if (go) {
						System.out.println();
						go = false;
					}
					System.out.print("-> GO A:" + aresta.getPeso() + " V:"	+ w.getId() + "-" + w.getNome() + " ");
					voltar = true;
					executaCaminhadaEmProfundidade(w);
					if (voltar) {
						System.out.println();
						voltar = false;
					}
					System.out.print("-> VOLTA A:" + aresta.getPeso() + " V:" + vertice.getId() + "-" + vertice.getNome() + " ");
					go = true;
				}
			}
		}
	}

	/**
	 *  Realiza um caminhamento em largura no grafo dos vértices associados a todas as arestas em que o vertice (parâmetro) participa.
	 *  Nesta situação, a estrutura de dados mais apropriada é uma fila.
	 *  
	 * 	- O vertice (parametro) é colocado numa fila. 
	 *  - Os vértices vizinhos (contidos nas arestas associadas ao vertice) são marcados como visitados e colocados na fila. 
	 *  - O primeiro vértice da fila é retirado e seus vizinhos não visitados são visitados e colocados na fila. 
	 *  	-- O passo anterior se repete até que a fila esvazie.
	 *  
	 * @param vertice Vértice de início do caminhamento 
	 * 
	 * @throws VerticeException Se alguma inconsistência relacionada a algum vértice for encontrada.
	 */
	public static void caminhadaEmLargura(Vertice vertice) throws VerticeException {
		System.out.println("\n\nCaminhando em largura no grafo - Vértice Inicial : " + vertice.getId());
		
		Queue<Vertice> queue = new LinkedList<Vertice>();
		queue.add(vertice);
		
		System.out.print("INICIO = V:" + vertice.getId() + "-"	+ vertice.getNome());
		
		while (!queue.isEmpty()) {
			Vertice v = queue.remove();
			
			System.out.print("\nVOLTA = V:" + v.getId() + "-" + v.getNome());
			
			for (Aresta a : v.getArestas()) {
				if (a.getStatus().equals(NAO_VISITADO)) {
					Vertice w = Grafo.getVerticeOposto(v, a);
					if (w.getStatus().equals(NAO_VISITADO)) {
						a.setStatus(VISITADO);
						w.setStatus(VISITADO);
						queue.add(w);
						System.out.print(" -> GO = A:" + a.getPeso() + " V:" + w.getId() + "-" + w.getNome());
					}
				}
			}
		}
		
		System.out.println();
	}

	/**
	 * Marca todos os vértices e arestas do grafo como não visitados.
	 * 
	 * @param grafo O grafo.
	 */
	public static void resetStatus(Grafo grafo) {
		for (int i = 0; i < grafo.arestas().size(); i++) {
			grafo.arestas().get(i).setStatus(NAO_VISITADO);
		}
		for (int i = 0; i < grafo.vertices().size(); i++) {
			grafo.vertices().get(i).setStatus(NAO_VISITADO);
		}
	}

	/**
	 * Implementa o algoritmo de Dijkstra que encontra caminhos mais curtos entre dois vértices em um grafo.
	 *   
	 * @param verticeInicial Vértice Inicial do cálculo 
	 * 
	 * @throws VerticeException Quando alguma inconsistência em relação aos vértices envolvidos é identificado.
	 */
	public static void dijkstra(Vertice verticeInicial) throws VerticeException {
		verticeInicial.setDist(0);
		PriorityQueue<Vertice> fila = new PriorityQueue<Vertice>();
		fila.add(verticeInicial);

		while (!fila.isEmpty()) {
			Vertice u = fila.poll();

			for (Aresta e : u.getArestas()) {
				Vertice v = Grafo.getVerticeOposto(u, e);
				int peso = e.getPeso();
				int distanciaAteU = u.getDist() + peso;
				if (distanciaAteU < v.getDist()) {
					fila.remove(v);
					v.setDist(distanciaAteU);
					v.setAnterior(u);
					fila.add(v);
				}
			}
		}
	}
	

	/**
	 * Retorna um conjunto de vértices associados aos caminhos possíveis no grafo a partir do vértice de origem  <p>
	 * Um caminho é uma lista ordenada de vértices.
	 * 
	 * @param origem Vértice de origem 
	 * 
	 * @return Lista de vértices correspondentes aos caminhos no grafo referentes ao vértice de origem informado. 
	 */
	public static List<Vertice> getCaminhos(Vertice origem) {
		List<Vertice> caminho = new ArrayList<Vertice>();
		for (Vertice vertice = origem; vertice != null; vertice = vertice.getAnterior()) {
			caminho.add(vertice);
		}
		Collections.reverse(caminho);
		return caminho;
	}
	
	/**
	 * Exibe os caminhos do grafo considerando cada um dos vértices a partir da origem determinada para o grafo. 
	 * 
	 * @param grafo O grafo.
	 * 
	 */
	public static void mostraCaminhos(Grafo grafo) {
		System.out.println("EXIBIR CAMINHOS do grafo ");
		for (Vertice v : grafo.vertices()) {
			System.out.println("Distância até " + v.getNome() + ": " + v.getDist());
			List<Vertice> caminho = getCaminhos(v);
			System.out.println("Caminho: " + caminho);
		}
	}

	
	/**
	 * Calcula o menor caminho entre um vértice de origem e um vértice de destino contidos no grafo.
	 * 
	 * @param grafo Grafo 
	 * @param origem Vértice de origem 
	 * @param destino Vértice de destino  
	 * 
	 * @return Lista de vértices representando o caminho mais curto no grafo entre a origem e o destino.
	 * 
	 * @throws VerticeException Quando alguma inconsistência em relação aos vértices envolvidos é identificado.
	 */
	public static List<Vertice> recuperaMenorCaminho(Grafo grafo, Vertice origem, Vertice destino) throws VerticeException {
		List<Vertice> menorCaminho = new ArrayList<Vertice>();
		dijkstra(origem);
		for (Vertice v : grafo.vertices()) {
			if(v.equals(destino)){
				menorCaminho = getCaminhos(v);
			}
		}
		return menorCaminho;
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
	public static Grafo carregaGrafo() throws MalhaException, VerticeException, ArestaException {
		
		Grafo g = new Grafo();

		try (Scanner fileScan = new Scanner(new FileInputStream("malha.txt"));){
			
			int verticeId = 0;
			Vertice v1 = null;
			Vertice v2 = null;
			
			while (fileScan.hasNextLine()) {
				
				String trechoAsString = fileScan.nextLine();
				if(trechoAsString == null){
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
	
	
	public static Vertice getVertice(String nomeVertice, Grafo grafo) {
		for (Vertice vertice : grafo.vertices())
			if (nomeVertice.equals(vertice.getNome())) {
				return vertice;
			}

		return null;
	}

	/**
	 *  Cria um malha a partir de trechos informados no parametro 
	 *  Cada trecho é representado pelos atributos origem, destino e distancia"
	 * 	- origem  : nome do nó de origem do trecho
	 *  - destino : nome do nó de destino do trecho
	 *  - distancia : valor inteiro representando a distância entre origem e destino
	 *    
	 * @param trechos Trechos componentes da malha
	 * 
	 * @throws MalhaException Inconsistencia na String de trechos informada.
	 */
	public static void criaGrafo(Trecho[] trechos) throws MalhaException {
		
		try(PrintStream out = new PrintStream(new FileOutputStream("malha.txt"));)
		{
			for(Trecho trecho : trechos){
				out.println(trecho.getOrigem() + " " + trecho.getDestino() + " " + trecho.getDistancia() );
			}

		} catch (FileNotFoundException e) {
			throw new MalhaException(MalhaException.ERRO_AO_CADASTRAR_MALHA);
		}
	}


}
