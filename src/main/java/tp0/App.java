package tp0;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import tp0.modelo.Categoria;
import tp0.modelo.Cliente;
import tp0.modelo.dispositivo.DispositivoConcreto;
import tp0.modelo.dispositivo.DispositivoEstandar;
import tp0.modelo.dispositivo.DispositivoInteligente;
import tp0.modelo.dispositivo.estado.Estado;
import tp0.modelo.hogar.zona.Transformador;
import tp0.modelo.repositorios.Repositorio;
import tp0.modelo.repositorios.RepositorioEnMemoria;

public class App {


	public static void main(String[] args) {
		new App().start();
	}

	
	private void start() {

		Cliente nico;
		DispositivoInteligente dispositivoInteligente1;
		DispositivoInteligente dispositivoInteligente2;
		DispositivoEstandar dispositivoEstandar1;
		DispositivoEstandar dispositivoEstandar2;
		Categoria categoria1;
		Categoria categoria2;
		Repositorio<Categoria> categorias;
		RepositorioEnMemoria<DispositivoConcreto> repositorioDeDispositivos = new RepositorioEnMemoria<DispositivoConcreto>();
		List<DispositivoEstandar> dispositivosEstandares = new ArrayList<DispositivoEstandar>();
		List<DispositivoInteligente> dispositivosInteligentes = new ArrayList<DispositivoInteligente>();

		repositorioDeDispositivos.agregar(
					Arrays.asList(new DispositivoConcreto("HELADERA_CONFREEZER", 0.09, 0, 0, false),
							new DispositivoConcreto("LAVARROPAS_AUTO_5KG", 0.175, 6, 30, true),
					new DispositivoConcreto("TELEVISOR_TUBO_21", 0.075, 90, 360, true),
					new DispositivoConcreto("VENTILADOR_PIE", 0.09, 120, 360, true)));
			
			dispositivoInteligente1 = new DispositivoInteligente("HELADERA_CONFREEZER", 150);
			dispositivoInteligente1.setEstado(Estado.ENCENDIDO);
			dispositivoInteligente1.setDispositivoGenerico(repositorioDeDispositivos);
			
			dispositivoInteligente2 = new DispositivoInteligente("LAVARROPAS_AUTO_5KG", 150);
			dispositivoInteligente2.setEstado(Estado.APAGADO);
			dispositivoInteligente2.setDispositivoGenerico(repositorioDeDispositivos);

			dispositivoEstandar1 = new DispositivoEstandar("TELEVISOR_TUBO_21", 24, 1);
			dispositivoEstandar1.setDispositivoGenerico(repositorioDeDispositivos);
			dispositivoEstandar2 = new DispositivoEstandar("VENTILADOR_PIE", 24, 2);
			dispositivoEstandar2.setDispositivoGenerico(repositorioDeDispositivos);
			
			dispositivosEstandares.addAll(Arrays.asList(dispositivoEstandar1, dispositivoEstandar2));
			dispositivosInteligentes.addAll(Arrays.asList(dispositivoInteligente1, dispositivoInteligente2));


			categoria1 = new Categoria("R1", 18.76, 0.644, 0, 100);

			categoria2 = new Categoria("R2", 25.0, 0.85, 100, 200);

			categorias = new RepositorioEnMemoria<Categoria>();
			categorias.agregar(Arrays.asList(categoria1, categoria2));

			nico = new Cliente("Nicolás", "Fonseca", "DNI", 39068888, "1141693939", "Calle Falsa 123", "2018-01-01", "R1",
					dispositivosEstandares, dispositivosInteligentes, 0);
			nico.setRepositorioCategorias(categorias);
			nico.obtenerCategoria();

			Transformador transformador1 = new Transformador();
			Transformador transformador2 = new Transformador();
			transformador1.agregarCliente(nico);
			
			EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();

			transaction.begin();
			
			entityManager.persist(categoria1);
			entityManager.persist(categoria2);
			
			repositorioDeDispositivos.todos().forEach(disp -> entityManager.persist(disp));
			
			entityManager.persist(dispositivoEstandar1);
			entityManager.persist(dispositivoEstandar2);
			entityManager.persist(dispositivoInteligente1);
			entityManager.persist(dispositivoInteligente2);
		
			entityManager.persist(nico);

			entityManager.persist(transformador1);
			entityManager.persist(transformador2);

			transaction.commit();	


	}

}