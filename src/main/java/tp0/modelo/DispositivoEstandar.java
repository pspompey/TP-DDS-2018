package tp0.modelo;

import org.joda.time.DateTime;
import org.joda.time.Hours;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DispositivoEstandar implements Dispositivo {

	@JsonProperty
	protected String nombreGenerico;
	@JsonProperty
	protected double kWXHora;
	@JsonProperty
	protected double horasDeConsumo;

	@JsonCreator
	public DispositivoEstandar(@JsonProperty("nombre generico") String nombreGenerico,
			@JsonProperty("KW/H") double KwXHora, @JsonProperty("Horas de consumo") double horasDeConsumo) {
		setNombreGenerico(nombreGenerico);
		setkWXHora(KwXHora);
		setHorasDeConsumo(horasDeConsumo);
	}

	private void setNombreGenerico(String nombreGenerico) {
		this.nombreGenerico = nombreGenerico;
	}

	private void setkWXHora(double kWXHora) {
		this.kWXHora = kWXHora;
	}

	private void setHorasDeConsumo(double horasDeConsumo) {
		this.horasDeConsumo = horasDeConsumo;
	}

	public double getHorasDeConsumo() {
		return horasDeConsumo;
	}

	public double getkWXHora() {
		return kWXHora;
	}

	public String getNombreGenerico() {
		return nombreGenerico;
	}

	public double consumo(Hours horas) {
		return this.consumoPorHoraAproximada() * horas.getHours();
	}

	private double consumoPorHoraAproximada() {
		return this.getHorasDeConsumo() * this.getkWXHora() / 24;
	}

	public double consumoTotal(DateTime periodo) {
		return this.consumoPorHoraAproximada() * Hours.hoursBetween(periodo, DateTime.now()).getHours();
	}

	/*
	 * public double consumo() { return this.KwXHora * this.horasDeUso; }
	 * 
	 * public Boolean esInteligente() { return false; }
	 * 
	 * public DispositivoAdaptado adaptarAInteligente(String nombreGenerico, Boolean
	 * estado) { return new DispositivoAdaptado(nombreGenerico, this.KwXHora,
	 * estado); }
	 */

}
