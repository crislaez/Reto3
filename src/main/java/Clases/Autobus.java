package Clases;

public class Autobus
{
	private String cod_linea;
	private int Cod_bus;
	private int N_plazas;
	private double Consumo_km;
	private String Color;
	
	public Autobus ()
	{
		
	}
	
	public Autobus (String cod_linea, int Cod_bus, int N_plazas, double Consumo_km, String Color)
	{
		this.cod_linea = cod_linea;
		this.Cod_bus = Cod_bus;
		this.N_plazas = N_plazas;
		this.Consumo_km = Consumo_km;
		this.Color = Color;
	}

	public String getCod_linea() {
		return this.cod_linea;
	}

	public void setCod_linea(String cod_linea) {
		this.cod_linea = cod_linea;
	}

	public int getCod_bus() {
		return this.Cod_bus;
	}

	public void setCod_bus(int cod_bus) {
		this.Cod_bus = cod_bus;
	}

	public int getN_plazas() {
		return this.N_plazas;
	}

	public void setN_plazas(int n_plazas) {
		this.N_plazas = n_plazas;
	}

	public double getConsumo_km() {
		return this.Consumo_km;
	}

	public void setConsumo_km(double consumo_km) {
		this.Consumo_km = consumo_km;
	}

	public String getColor() {
		return this.Color;
	}

	public void setColor(String color) {
		this.Color = color;
	}

	@Override
	public String toString() {
		return cod_linea + "\t" + Cod_bus + "\t" + N_plazas + "\t" + Consumo_km + "\t" + Color ;
	}
	
	
}
