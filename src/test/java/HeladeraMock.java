import org.joda.time.DateTime;
import org.joda.time.Hours;

import tp0.modelo.dispositivo.*;


public class HeladeraMock implements DispositivoFisicoAdapter {

	@Override
	public double consumo(Hours horas) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double consumoTotal(DateTime periodo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void apagar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void encender() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ahorrarEnergia() {
		// TODO Auto-generated method stub
		
	}
	
}