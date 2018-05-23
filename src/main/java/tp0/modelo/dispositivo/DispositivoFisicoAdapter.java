package tp0.modelo.dispositivo;

import org.joda.time.DateTime;

import tp0.modelo.dispositivo.regla.Accion;

public interface DispositivoFisicoAdapter {
	public double consumoUltimas(int horas);
	
	public double consumoTotal(DateTime fechaInicial, DateTime fechaFinal);
	
	public void apagar();
	
	public void encender();
	
	public void ahorrarEnergia();

	public void ejecutar(Accion accionInmediata);
}
