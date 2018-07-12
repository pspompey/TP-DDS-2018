import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.*;

import test.dispositivo.HeladeraMock;
import test.dispositivo.LavarropasMock;
import test.dispositivo.LicuadoraMock;
import test.dispositivo.TostadoraMock;
import tp0.modelo.Categoria;
import tp0.modelo.Cliente;
import tp0.modelo.dispositivo.DispositivoEstandar;
import tp0.modelo.dispositivo.DispositivoInteligente;
import tp0.modelo.dispositivo.TipoDispositivoEnum;
import tp0.modelo.dispositivo.estado.Apagado;
import tp0.modelo.dispositivo.estado.Encendido;
import tp0.modelo.repositorios.Repositorio;
import tp0.modelo.repositorios.RepositorioEnMemoria;

public class ClienteTest {

	// Tests para probar el comportamiento del cliente

	Cliente nico;
	DispositivoInteligente dispositivoInteligente1;
	DispositivoInteligente dispositivoInteligente2;
	DispositivoInteligente dispositivoInteligente3;
	DispositivoInteligente dispositivoInteligente4;
	DispositivoInteligente dispositivoInteligente5;
	DispositivoEstandar dispositivoEstandar1;
	DispositivoEstandar dispositivoEstandar2;
	DispositivoEstandar dispositivoEstandar3;
	DispositivoEstandar dispositivoEstandar4;
	Categoria categoria1;
	Categoria categoria2;
	Repositorio<Categoria> categorias;
	DateTime fechaActual;
	HeladeraMock heladeraMock = new HeladeraMock();
	LavarropasMock lavarropasMock = new LavarropasMock();
	TostadoraMock tostadoraMock = new TostadoraMock();
	LicuadoraMock licuadoraMock = new LicuadoraMock();
	List<DispositivoEstandar> dispositivosEstandares = new ArrayList<DispositivoEstandar>();
	List<DispositivoInteligente> dispositivosInteligentes = new ArrayList<DispositivoInteligente>();

	@Before
	public void setUp() throws Exception {
		//TODO FALTA MODIFICAR LOS MOCKS
		dispositivoInteligente1 = new DispositivoInteligente(TipoDispositivoEnum.HELADERA_CON_FREEZER);
		dispositivoInteligente1.setEstado(new Encendido());
		dispositivoInteligente1.setDispositivoFisico(heladeraMock);

		dispositivoInteligente2 = new DispositivoInteligente(TipoDispositivoEnum.LAVARROPAS_AUTOMATICO_5_KG);
		dispositivoInteligente2.setEstado(new Apagado());
		dispositivoInteligente2.setDispositivoFisico(lavarropasMock);

		dispositivoInteligente3 = new DispositivoInteligente(TipoDispositivoEnum.TELEVISOR_LED_24);
		dispositivoInteligente3.setEstado(new Apagado());
		dispositivoInteligente3.setDispositivoFisico(tostadoraMock);

		dispositivoInteligente4 = new DispositivoInteligente(TipoDispositivoEnum.AIRE_ACONDICIONADO_2200_FRIGORIAS);
		dispositivoInteligente4.setEstado(new Apagado());
		dispositivoInteligente4.setDispositivoFisico(licuadoraMock);

		dispositivoInteligente5 = new DispositivoInteligente(TipoDispositivoEnum.LAMPARA_11_W);
		dispositivoInteligente5.setEstado(new Encendido());
		dispositivoInteligente5.setDispositivoFisico(licuadoraMock);

		dispositivosInteligentes.add(dispositivoInteligente1);
		dispositivosInteligentes.add(dispositivoInteligente2);
		dispositivosInteligentes.add(dispositivoInteligente3);
		dispositivosInteligentes.add(dispositivoInteligente4);

		dispositivoEstandar1 = new DispositivoEstandar(TipoDispositivoEnum.TELEVISOR_COLOR_TUBO_21, 1);

		dispositivoEstandar2 = new DispositivoEstandar(TipoDispositivoEnum.LAVARROPAS_AUTOMATICO_5_KG_CALENTAMIENTO_AGUA, 2);

		dispositivoEstandar3 = new DispositivoEstandar(TipoDispositivoEnum.VENTILADOR_PIE, 3);

		dispositivoEstandar4 = new DispositivoEstandar(TipoDispositivoEnum.MICROONDAS_CONVENCIONAL, 4);

		dispositivosEstandares.add(dispositivoEstandar1);
		dispositivosEstandares.add(dispositivoEstandar2);
		dispositivosEstandares.add(dispositivoEstandar3);
		dispositivosEstandares.add(dispositivoEstandar4);

		categoria1 = new Categoria("R1", 18.76, 0.644, 0, 100);

		categoria2 = new Categoria("R2", 25.0, 0.85, 100, Double.POSITIVE_INFINITY);

		categorias = new RepositorioEnMemoria<Categoria>();
		categorias.agregar(Arrays.asList(categoria1, categoria2));

		nico = new Cliente("Nicolás", "Fonseca", "DNI", 39068888, "1141693939", "Calle Falsa 123", "2018-01-01", "R1",
				dispositivosEstandares, dispositivosInteligentes, 0);
		nico.setRepositorioCategorias(categorias);
		nico.obtenerCategoria();
	}

	@Test
	public void testNicoTieneAlgunDispositivoEncendido() {
		Assert.assertTrue(nico.tieneAlgunDispositivoEncendido());
	}

	@Test
	public void testNicoTiene8Dispositivos() {
		Assert.assertEquals(8, nico.cantidadDispositivosTotal());
	}

	@Test
	public void testNicoTiene1DispositivoEncendido() {
		Assert.assertEquals(1, nico.cantidadDispositivosEncendidos());
	}

	@Test
	public void testNicoTiene3DispositivosApagados() {
		Assert.assertEquals(3, nico.cantidadDispositivosApagados());
	}

	@Test
	public void testNicoConsume10HorasLosDispositivosEstandares() {
		Assert.assertEquals(10, nico.getDispositivosEstandar().stream()
				.mapToDouble(dispositivo -> dispositivo.getHorasDeConsumo()).sum(), 0);
	}

	@Test
	public void testNicoConsume1Con68KWxHoraDispositivosEstandares() {
		Assert.assertEquals(1.68,
				nico.getDispositivosEstandar().stream().mapToDouble(dispositivo -> dispositivo.getkWXHora()).sum(), 0);
	}

	@Test
	public void testNicoConsume1Con329KWxHoraDispositivosInteligentes() {
		//TODO testNicoConsume1Con329KWxHoraDispositivosInteligentes
		//ESTE TEST NO SE POR QUE FALLA
		Assert.assertEquals(1.329,
				nico.getDispositivosInteligentes().stream().mapToDouble(dispositivo -> dispositivo.getKwXHora()).sum(),
				0);
	}

	@Test
	public void testNicoTiene4DispositivosEstandares() {
		Assert.assertEquals(4, nico.getDispositivosEstandar().stream().count(), 0);
	}

	@Test
	public void testDispositivoEstandar1Consume24KWXHora() {
		Assert.assertEquals(0.075, dispositivoEstandar1.getkWXHora(), 0);
	}

	@Test
	public void testNicoConsumeTotalmente135() {
		//TODO testNicoConsumeTotalmente135
		//CORREGIR EL TEST SEGUN LOS MOCKS DE DISPOSITIVO FISICO QUE VAN A CAMBIAR
		fechaActual = DateTime.now();
		Assert.assertEquals(135, nico.consumoTotal(fechaActual.minusHours(1), fechaActual), 0);
	}

	@Test
	public void testNicoEsCategorizadoAR1Inicialmente() {
		Assert.assertEquals(categoria1.getNombre(), nico.getCategoria().getNombre());
	}

	@Test
	public void testNicoEsRecategorizadoAR2() {
		nico.asignarCategoria();
		Assert.assertEquals(categoria2.getNombre(), nico.getCategoria().getNombre());
	}

	@Test
	public void testNicoConvierteDispositivoEstandar1YSeAgregaALosDispositivosInteligentes() {
		nico.convertirDispositivoEstandarAInteligente(dispositivoEstandar1);
		Assert.assertEquals(5, nico.getDispositivosInteligentes().stream().count(), 0);
	}

	@Test
	public void testNicoConvierteDispositivoEstandar1YSeQuitaDeLosDispositivosEstandares() {
		nico.convertirDispositivoEstandarAInteligente(dispositivoEstandar1);
		Assert.assertEquals(3, nico.getDispositivosEstandar().stream().count(), 0);
	}

	@Test
	public void testNicoConvierteDispositivoEstandar1YQuedaCon25Puntos() {
		nico.convertirDispositivoEstandarAInteligente(dispositivoEstandar1);
		Assert.assertEquals(10, nico.getPuntos(), 0);
	}

	@Test
	public void testNicoRegistrarDispositivoEstandar1YQuedaCon15Puntos() {
		nico.registrarDispositivoInteligente(dispositivoInteligente5);
		Assert.assertEquals(15, nico.getPuntos(), 0);
	}

}