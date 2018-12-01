package tp0.modelo.hogar;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.joda.time.Hours;
import org.joda.time.Seconds;

import tp0.modelo.dispositivo.Dispositivo;

public class CommandOptimizarHogar {
	
    public CommandOptimizarHogar(Hogar hogar, List<Dispositivo> dispositivos, Seconds seg) {
    	Timer timer = new Timer(false);
    	TimerTask timerTask = new TimerTask() {
    		
    		@Override
    		public void run() {
    			hogar.optimizar(dispositivos);
    		}

    	};
        timer.scheduleAtFixedRate(timerTask, 0, seg.getSeconds());	
    }

}
