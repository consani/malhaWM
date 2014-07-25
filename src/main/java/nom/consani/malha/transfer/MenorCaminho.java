package nom.consani.malha.transfer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "respostaMenorCaminho", propOrder = {
	    "caminho",
	    "custo"
})
public class MenorCaminho{

	private String caminho;
	private double custo;
	
	@XmlElement(required = true)	
	public String getCaminho() {
		return caminho;
	}
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
	@XmlElement(required = true)
	public double getCusto() {
		return custo;
	}
	public void setCusto(double custo) {
		this.custo = custo;
	}
	
	
	
}
