package nom.consani.malha.transfer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "trecho", propOrder = {
	    "origem",
	    "destino",
	    "distancia"
})
public class Trecho {
	
	private String origem;
	private String destino;
	private int distancia;
	
	@XmlElement(required = true)
	public String getOrigem() {
		return origem;
	}
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	@XmlElement(required = true)
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	@XmlElement(required = true)
	public int getDistancia() {
		return distancia;
	}
	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

}
