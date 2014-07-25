package nom.consani.malha.service;

import javax.xml.ws.Endpoint;

public class Publicador {
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8080/WS/MalhaService",new MalhaServiceImpl());	
	}
	
}
