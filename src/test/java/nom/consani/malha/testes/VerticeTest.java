package nom.consani.malha.testes;

import nom.consani.malha.entidades.Vertice;
import nom.consani.malha.exceptions.VerticeException;

import org.junit.Test;


public class VerticeTest {

	@Test
	public void testCriaVerticeValido() throws VerticeException  {
		new Vertice(0,"A");
	}

	@Test (expected=VerticeException.class)
	public void testCriaVerticeNomeVerticeNulo() throws VerticeException  {
		new Vertice(1,null);
	}
	
	@Test (expected=VerticeException.class)
	public void testCriaVerticeNomeVerticeEmBranco() throws VerticeException  {
		new Vertice(1,"   ");
	}
	
	@Test (expected=VerticeException.class)
	public void testCriaVerticeComIdVerticeNulo() throws VerticeException  {
		new Vertice(null,"A");
	}

	@Test (expected=VerticeException.class)
	public void testCriaVerticeComIdVerticeNegativo() throws VerticeException  {
		new Vertice(-1,"A");
	}
	

}
