package cliente;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import fixture.Fixture;
import tipoDispositivo.DispositivoInteligente;
import categoria.Categoria;
import dispositivo.Dispositivo;
import dispositivo.gadgets.regla.NoSePuedeEvaluarReglaADispositivoNoInteligenteException;
import fabricante.Fabricante;

public class TestCliente extends Fixture {	
	
	// Test entrega 0
	@Test
	public void elClienteAlejandroTiene1DispositivoInicialmente() {
		assertEquals(1, alejandro.cantidadDispositivos());
	}
		
	@Test
	public void con1DispositivoEstandarMasAlejandroTiene2() {
		alejandro.agregarDispositivo(televisor);
		assertEquals(2, alejandro.cantidadDispositivos());
	}
	
	@Test
	public void inicialmenteAlejandroEsCategoriaR1() {
		assertEquals("R1",alejandro.categoria().getNombre());
	}	

	@Test
	public void alAgregarleAAlejandroUnDispositivoEstandarConMuchoConsumoPasaAR8() {
		alejandro.agregarDispositivo(microondas);
		alejandro.recategorizarSegunUso(2);
		assertEquals("R8", alejandro.categoria().getNombre());
	}
	@Test
	public void alAgregarleAAlejandroUnDispositivoEstandarSuCategoriaCambia() {
		Categoria categoriaActual = alejandro.categoria();
		alejandro.agregarDispositivo(microondas);
		alejandro.recategorizarSegunUso(2);
		assertFalse(alejandro.categoria().getNombre() == categoriaActual.getNombre());
	}
	@Test
	public void alAgregarleTelevisorAAleandroEsCategoriarR2() {
		alejandro.agregarDispositivo(televisor); 
		alejandro.recategorizarSegunUso(2);
		assertEquals("R2" ,alejandro.categoria().getNombre());
	}
	
	@Test
	public void seAgregaEquipoDeMusicaAAlejandroYAlRecategorizarEsR3() {
		alejandro.agregarDispositivo(equipoMusica); 
		alejandro.recategorizarSegunUso(2);
		assertEquals("R3", alejandro.categoria().getNombre());
	}
	
	@Test
	public void seAgregaDvdAAlejandroYAlRecategorizarEsR7() {
		alejandro.agregarDispositivo(dvd);
		alejandro.recategorizarSegunUso(2);
		assertEquals("R7", alejandro.categoria().getNombre());
	}
	
	@Test
	public void seAgregaPlay4AAlejandroYAlRecategorizarEsR8() {
		alejandro.agregarDispositivo(play4);
		alejandro.recategorizarSegunUso(2);
		assertEquals("R8", alejandro.categoria().getNombre());
	}
	
//test entrega 1
	
	@Test
	public void seAgregaTVSmartAAlejandroConFabricanteQueRetorna180DeConsumoPorHoraYAlRecategorizarEsR2() {
		Fabricante mockFabricanteRetorna180 = mock(Fabricante.class);
		televisorSmart = new Dispositivo("Televisor Smart", new DispositivoInteligente(123456, mockFabricanteRetorna180));
		when(mockFabricanteRetorna180.consumoDuranteLasUltimas(2,123456)).thenReturn(180.0);
		alejandro.agregarDispositivo(televisorSmart);
		alejandro.recategorizarSegunUso(2);
		assertEquals("R2", alejandro.categoria().getNombre());
	}
	@Test
	public void seAgregaSmartTVAAlejandroYTiene15Puntos() {
	
		televisorSmart = new Dispositivo("Televisor Smart", new DispositivoInteligente(123456, mockFabricante));
		alejandro.agregarDispositivo(televisorSmart);
	
		assertTrue(15.0== alejandro.getPuntos());
	}
	@Test
	public void seConvierteElCandelabroAInteligenteYAlejandroTiene10Puntos() {
	
		alejandro.convertirAInteligente(candelabro,123,mockFabricante);
		assertTrue(10.0== alejandro.getPuntos());
	}
	@Test
	public void seAgregaSmartTVAAlejandroYTiene1DispositivoInteligente() {
	
		televisorSmart = new Dispositivo("Televisor Smart", new DispositivoInteligente(123456, mockFabricante));
		alejandro.agregarDispositivo(televisorSmart);
	
		assertEquals(1,alejandro.cantidadDispositivosInteligentes());
	}
	
	 @Test(expected = NoPuedeAfectarAUnDispositivoQueNoLePertenece.class)
	 public void alejandroNoPuedeConvertirAInteligenteElTelevisor() {
		 alejandro.convertirAInteligente(televisor,111,mockFabricante);
	  }
}