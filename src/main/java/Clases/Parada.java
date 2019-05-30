package Clases;

public class Parada 
{
	private String cod_linea;
	private int cod_parada;
	private String nombre;
	private String calle;
	private double latitud;
	private double longitud;
	
	public Parada()
	{
		
	}
	
	public Parada (String cod_linea, int cod_parada, String nombre, String calle, double latitud, double longitud)
	{
		this.cod_linea = cod_linea;
		this.cod_parada = cod_parada;
		this.nombre = nombre;
		this.calle = calle;
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public String getCod_linea() {
		return this.cod_linea;
	}

	public void setCod_linea(String cod_linea) {
		this.cod_linea = cod_linea;
	}

	public int getCod_parada() {
		return this.cod_parada;
	}

	public void setCod_parada(int cod_parada) {
		this.cod_parada = cod_parada;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCalle() {
		return this.calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public double getLatitud() {
		return this.latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return this.longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	@Override
	public String toString() {
		return "cod_linea=" + cod_linea + ", cod_parada=" + cod_parada + ", nombre=" + nombre + ", calle="
				+ calle + ", latitud=" + latitud + ", longitud=" + longitud;
	}
	
	
}
