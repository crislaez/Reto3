package Clases;

public class Billete {

	
	

	private String cod_billete; //Cod_Billete
	private String ntrayecto; //NTreayecto
	private String cod_linea; //Cod_Linea
	private String cod_bus; //Cod_Bus
	private String cod_parada_inicio; //Cod_Parada_Inicio
	private String cod_parada_final; //Cod_Parada_Fin
	private String fecha;    //Fecha
	private String hora;   //Hora
	private String dni;   //DNI
	private String precio; //Precio
	
	public Billete() {
	       //sc = new Scanner(System.in); 
	        
	    }
	
	public Billete(String cod_billete, String ntrayecto, String cod_linea, String cod_bus, String cod_parada_inicio,
					String cod_parada_final, String fecha, String hora, String dni, String precio) 
	{
		// TODO Auto-generated constructor stub
		this.cod_billete=cod_billete;
		this.ntrayecto=ntrayecto;
		this.cod_linea=cod_linea;
		this.cod_bus=cod_bus;
		this.cod_parada_inicio=cod_parada_inicio;
		this.cod_parada_final=cod_parada_final;
		this.fecha=fecha;
		this.hora=hora;
		this.dni=dni;
		this.precio=precio;
		
	}
	
	public Billete(String ntrayecto, String cod_linea, String cod_bus, String cod_parada_inicio,
			String cod_parada_final, String fecha, String hora, String dni, String precio) 
		{
		// TODO Auto-generated constructor stub
		this.ntrayecto=ntrayecto;
		this.cod_linea=cod_linea;
		this.cod_bus=cod_bus;
		this.cod_parada_inicio=cod_parada_inicio;
		this.cod_parada_final=cod_parada_final;
		this.fecha=fecha;
		this.hora=hora;
		this.dni=dni;
		this.precio=precio;
		
		}
	
	//-----------------------------------------------------------------------------------------
	public String getCod_billete() 
	{
        return cod_billete;
	}
       
	public void setcod_billete(String cod_billete) 
	{
            this.cod_billete = cod_billete;
    }
	//------------------------------------------------------------------------------------------
	
	public String getNtrayecto() 
	{
		return ntrayecto;
	}
	
	public void setNtrayecto(String ntrayecto) 
	{
		this.ntrayecto=ntrayecto;
	}
	
	//-----------------------------------------------------------------------------------------
	
	public String getcod_linea() 
	{
		return cod_linea;
	}
	
	public void setcod_linea(String cod_linea) 
	{
		this.cod_linea=cod_linea;
	}
	
	//-------------------------------------------------------------------------------------------
	
	public String getcod_bus() 
	{
		return cod_bus;
	}
	
	public void setcod_bus(String cod_bus) 
	{
		this.cod_bus=cod_bus;
	}
	
	//-----------------------------------------------------------------------------------------
	public String getcod_parada_inicio() 
	{
        return cod_parada_inicio;
	}
       
	public void setcod_parada_inicio(String cod_parada_inicio) 
	{
            this.cod_parada_inicio = cod_parada_inicio;
    }
	
	//-----------------------------------------------------------------------------------------
	public String getcod_parada_final() 
	{
        return cod_parada_final;
	}
       
	public void setcod_parada_final(String cod_parada_final)
	{
            this.cod_parada_final = cod_parada_final;
    }
	
	//-----------------------------------------------------------------------------------------
	public String getFecha() 
	{
	     return fecha;
	}
	       
	public void setFecha(String fecha) 
	{
	        this.fecha = fecha;
	}
	
    //-----------------------------------------------------------------------------------------
	public String getHora() 
	{
	     return hora;
    }
	       
	public void setHora(String hora) 
	{
	        this.hora = hora;
	}
	
	//-----------------------------------------------------------------------------------------
    public String getDni() 
    {
	      return dni;
    }
	       
    public void setDni(String dni) 
    {
	        this.dni = dni;
	}
    
	//-----------------------------------------------------------------------------------------
    public String getPrecio() 
    {
	     return precio;
	}
	       
    public void setPrecio(String precio) 
    {
	         this.precio = precio;
	}

	@Override
	public String toString() {
		return "Billete [cod_billete=" + cod_billete + ", ntrayecto=" + ntrayecto + ", cod_linea=" + cod_linea
				+ ", cod_bus=" + cod_bus + ", cod_parada_inicio=" + cod_parada_inicio + ", cod_parada_final="
				+ cod_parada_final + ", fecha=" + fecha + ", hora=" + hora + ", dni=" + dni + ", precio=" + precio
				+ "]";
	}
	
	
	//-------------------------------------------------------------------------------------------
	
	//metodo para mostrar todas las lineas-------------------------------------------------------------------------------------
	
}



