package fixture;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mockito.Mockito;
import org.uqbar.geodds.Point;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import categoria.Categoria;
import cliente.Cliente;
import cliente.TipoDocumento;
import dispositivo.Dispositivo;
import dispositivo.DispositivoConcreto;
import dispositivo.gadgets.actuador.Actuador;
import dispositivo.gadgets.regla.CondicionSobreSensor;
import dispositivo.gadgets.regla.Regla;
import dispositivo.gadgets.regla.ReglaEstricta;
import dispositivo.gadgets.regla.ReglaPermisiva;
import repositorio.RepoCategorias;
import tipoDispositivo.DispositivoEstandar;
import tipoDispositivo.DispositivoInteligente;
import transformador.Transformador;
import zona.Zona;

public class Fixture extends AbstractPersistenceTest implements WithGlobalEntityManager, TransactionalOps {
	
	protected Categoria r1, r2, r3, r4, r5, r6, r7, r8, r9;
	protected Dispositivo candelabro, televisor, microondas, equipoMusica, dvd, play4, televisorSmart, tvNormal, pc, aireAcondicionado;
	protected List<Dispositivo> dispositivos = new ArrayList<>();
	protected Cliente alejandro, lio, pepe, nico, ricardo, yanina;
	protected DispositivoConcreto mockPcConcreta, mockAireConcreto, mockTelevisorSmartConcreto, mockTelevisorNormalConcreto, mockCandelabroConcreto, mockLampara, mockLavarropas, mockMicroondas, mockPlancha, mockVentilador,mockTv40;
	protected Regla unaReglaEstricta, unaReglaPermisiva;
	protected Actuador actuadorQueApaga, actuadorQueEnciende;
	protected CondicionSobreSensor mockCondicionSobreSensorQueCumple, mockCondicionSobreSensorQueNoCumple;
	protected Set<CondicionSobreSensor> condicionesSobreSensorQueNoCumplen = new HashSet<>(), condicionesSobreSensorQueCumplen = new HashSet<>();
	protected Set<Actuador> actuadores = new HashSet<>();
	protected Transformador transformadorLaMatanza, transformadorPalermo, transformadorCaballito;
	protected Point ubicacionLaMatanza = new Point(-34.762985, -58.631242);
	protected Point ubicacionCaballito = new Point(-34.616286, -58.442747);
	protected Point ubicacionLaPlata = new Point(-34.919116, -57.952484);
	protected Point ubicacionPalermo = new Point(-34.574704, -58.423419);
	public Zona palermo, laMatanza, caballito;
	
	public Fixture() {
		  //rollbackTransaction();
		 // beginTransaction();
		  r1 = new Categoria("R1", 0, 150, 18.76, 0.644);			
		  r2 = new Categoria("R2", 150, 325, 35.32, 0.644);			
		  r3 = new Categoria("R3", 326, 400, 60.71, 0.681);			
		  r4 = new Categoria("R4", 400, 450, 71.74, 0.783);			
		  r5 = new Categoria("R5", 450, 500, 110.38, 0.794);			
		  r6 = new Categoria("R6", 500, 600, 220.75, 0.832);			
		  r7 = new Categoria("R7", 600, 700, 443.59, 0.851);			
		  r8 = new Categoria("R8", 700, 1400, 545.19, 0.851);				
		  r9 = new Categoria("R9", 1400, 99999999, 545.19, 0.851); 
		  
		  mockPcConcreta = Mockito.mock(DispositivoConcreto.class);
		  mockAireConcreto = Mockito.mock(DispositivoConcreto.class);
		  mockTelevisorSmartConcreto = Mockito.mock(DispositivoConcreto.class);
		  mockTelevisorNormalConcreto = Mockito.mock(DispositivoConcreto.class);
		  
		  mockCandelabroConcreto = Mockito.mock(DispositivoConcreto.class);
		  
		  mockLampara = Mockito.mock(DispositivoConcreto.class);
		  mockLavarropas = Mockito.mock(DispositivoConcreto.class);
		  mockMicroondas = Mockito.mock(DispositivoConcreto.class);
		  mockPlancha = Mockito.mock(DispositivoConcreto.class);
		  mockVentilador = Mockito.mock(DispositivoConcreto.class);		  
		  
		  mockTv40 = Mockito.mock(DispositivoConcreto.class);
				
		  candelabro = new Dispositivo("Candelabro", new DispositivoEstandar(), 9);
		  televisor = new Dispositivo("Televisor", new DispositivoEstandar(), 67.5);	  
		  microondas = new Dispositivo("Microondas", new DispositivoEstandar(), 402.0);
		  equipoMusica = new Dispositivo ("Equipo de musica", new DispositivoEstandar(), 170.0);
		  dvd = new Dispositivo("DVD", new DispositivoEstandar(), 300.77);
		  play4 = new Dispositivo("Play station 4", new DispositivoEstandar(), 600.05);  
		  pc = new Dispositivo("PC", new DispositivoInteligente(mockPcConcreta), 100);
		  aireAcondicionado = new Dispositivo("Aire acondicionado", new DispositivoInteligente(mockAireConcreto), 120);
		  televisorSmart = new Dispositivo("Televisor Smart", new DispositivoInteligente(mockTelevisorSmartConcreto), 90);
		  tvNormal = new Dispositivo("Televisor Normal", new DispositivoInteligente(mockTelevisorNormalConcreto), 0.9);
		  
		  //TODO revisar si se puede sacar esto, ya esta arriba!
		  dispositivos = new ArrayList<Dispositivo>();	
		  dispositivos.add(candelabro);
		  
		  actuadorQueApaga = Actuador.ActuadorQueApaga;
		  actuadorQueEnciende = Actuador.ActuadorQueEnciende;
		  actuadores.add(actuadorQueApaga);
		  actuadores.add(actuadorQueEnciende);
		  
		  unaReglaEstricta = new ReglaEstricta(actuadores, new HashSet<>(), pc);
		  unaReglaPermisiva = new ReglaPermisiva(actuadores, new HashSet<>(), aireAcondicionado);
		  
		  mockCondicionSobreSensorQueCumple = Mockito.mock(CondicionSobreSensor.class);
		  mockCondicionSobreSensorQueNoCumple = Mockito.mock(CondicionSobreSensor.class);
		  
		  condicionesSobreSensorQueCumplen.add(mockCondicionSobreSensorQueCumple);
		  condicionesSobreSensorQueNoCumplen.add(mockCondicionSobreSensorQueNoCumple);
		  
		  palermo = new Zona(ubicacionPalermo, 3);
		  laMatanza = new Zona(ubicacionLaMatanza, 5);
		  caballito = new Zona (ubicacionCaballito, 5);
		
		  alejandro = new Cliente(null, null, "Alejandro", "Saez", TipoDocumento.DNI, 3876675, 43543245, "Macos Sastre 324", r1, dispositivos, ubicacionLaMatanza);
		  nico = new Cliente(null, null, "nico", "otamendi", TipoDocumento.DNI, 35102594, 42012594, "Av. Siempre Viva 20", r1, new ArrayList<Dispositivo>(), ubicacionPalermo);
		  pepe = new Cliente(null, null, "pepe", "argento", TipoDocumento.CI, 12549785, 40000001, "Manuel Rodriguez 1251", r1, new ArrayList<Dispositivo>(), ubicacionLaPlata);
		  lio = new Cliente(null, null, "lio", "messi", TipoDocumento.DNI, 40216458, 10101010, "Av. Catalunia 10", r2, new ArrayList<Dispositivo>(), ubicacionCaballito);
		  ricardo = new Cliente(null, null, "ricardo", "ruben", TipoDocumento.DNI, 45178257, 41013591, "Av. Lugones 1001", r2, new ArrayList<Dispositivo>(), ubicacionPalermo);
		  yanina = new Cliente(null, null, "yanina","latorre", TipoDocumento.DNI, 7865432,46498374, "Av. Constituyentes 2012",r1 , new ArrayList<Dispositivo>(), ubicacionCaballito);
		  
		  
		  
		  RepoCategorias.getInstance().agregarEntidad(r1);
		  RepoCategorias.getInstance().agregarEntidad(r2);
		  RepoCategorias.getInstance().agregarEntidad(r3);
		  RepoCategorias.getInstance().agregarEntidad(r4);
		  RepoCategorias.getInstance().agregarEntidad(r5);
		  RepoCategorias.getInstance().agregarEntidad(r6);
		  RepoCategorias.getInstance().agregarEntidad(r7);
		  RepoCategorias.getInstance().agregarEntidad(r8);
		  RepoCategorias.getInstance().agregarEntidad(r9);
		  
		  transformadorLaMatanza = new Transformador(ubicacionLaMatanza);
		  transformadorPalermo = new Transformador(ubicacionPalermo);
		  transformadorCaballito = new Transformador(ubicacionCaballito);
	}
}
