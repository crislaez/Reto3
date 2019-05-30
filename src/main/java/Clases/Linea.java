package Clases;

public class Linea 
{
	private String codigo;
	private String nombre;
	
	public Linea ()
	{
		
	}
	
	public Linea (String codigo, String nombre)
	{
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return codigo+ "      " + nombre;
	}
	
	
	
}
