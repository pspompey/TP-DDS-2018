package tp0.modelo.hogar;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import tp0.modelo.dispositivo.Dispositivo;
import tp0.modelo.dispositivo.DispositivoInteligente;
import tp0.modelo.dispositivo.regla.Accion;
import tp0.modelo.dispositivo.regla.Condicion;
import tp0.modelo.dispositivo.regla.Regla;

public class Hogar {
	
	protected String direccion;
	protected List<Regla> reglas = new ArrayList<>();
	protected boolean accionAutomatica = false;
	protected Optimizador optimizador;
	
	public Hogar(String direccion) {
		this.direccion = direccion;
	}
	
	public void setOptimizador(Optimizador optimizador) {
		this.optimizador = optimizador;
	}
	
	public double[] optimizar() {
		double[] resultado = optimizador.optimizar();
		
		if(this.accionAutomatica) {
			this.reglas.stream().forEach(regla -> 
			accionar(regla.getAccion().getDispositivo(), resultado, regla));
		}
		return resultado;
	}

	private void accionar(DispositivoInteligente dispositivoInteligente, double[] resultado,
			Regla regla) {
		double resultadoConsumo = resultado[dispositivoInteligente.getNombreGenericoPosicion()];
		regla.ejecutar(resultadoConsumo);
	}

	public void configurarAccionesAutomaticas(List<Accion> acciones) {
		acciones.stream().forEach(accion -> reglas.add(crearRegla(accion)));
		this.accionAutomatica = true;
	}

	private Regla crearRegla(Accion accion) {
		
		Condicion condicion = new Condicion(accion.getDispositivo().getSensor()) {
			
			@Override
			public boolean cumplida(double resultado) {
				return this.getSensor().medicion(DateTime.now(), 
						new DateTime().dayOfMonth().withMinimumValue()) > resultado;
			}
		};
		
		return new Regla(condicion, accion);
	}
	
	public void actualizarDispositivos(List<Dispositivo> dispositivos) {
		this.optimizador.setCondiciones(dispositivos);
	}

	public String getDireccion() {
		return this.direccion;
	}
	
}