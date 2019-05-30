package Clases;

import java.util.Scanner;

public class Cliente {

	

	private String dni;
	private String nombre;
	private String apellido;
	private String fecha_nacimiento;
	private String sexo;
	private String contrasena;
	
	public Cliente() 
	{
	        
	}
	
	public Cliente(String dni, String nombre,String apellido,String fechadenacimiento,String sexo,String contrasena) 
	{
		// TODO Auto-generated constructor stub
		this.dni=dni;
		this.nombre=nombre;
		this.apellido=apellido;
		this.fecha_nacimiento=fechadenacimiento;
		this.sexo=sexo;
		this.contrasena=contrasena;
		
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
	
	//------------------------------------------------------------------------------------------
	
	public String getNombre() 
	{
		return nombre;
	}
	
	public void setNombre(String nombre) 
	{
		this.nombre=nombre;
	}
	//-----------------------------------------------------------------------------------------
	
	public String getApellido() 
	{
		return apellido;
	}
	
	public void setApellido(String apellido) 
	{
		this.apellido=apellido;
	}
	
	//-------------------------------------------------------------------------------------------
	
	public String getFechadenacimiento() 
	{
		return fecha_nacimiento;
	}
	
	public void setFechadenacimiento(String fechadenacimiento) 
	{
		this.fecha_nacimiento=fechadenacimiento;
	}
	
	//-----------------------------------------------------------------------------------------
	public String getSexo() 
	{
        return sexo;
	}
	
       
	public void setSexo(String sexo)
	{
            this.sexo = sexo;
    }
	
	//-----------------------------------------------------------------------------------------
	public String getContrasena() 
	{
        return contrasena;
	}
       
	public void setContrasena(String contrasena) 
	{
            this.contrasena = contrasena;
    }
	
	
	//-------------------------------------------------------------------------------------------
	
	//metodo para mostrar todas las lineas-------------------------------------------------------------------------------------
	public String mostrar() 
	{
        return "Lineas \n" + "----------------\n" + "DNI: " + dni + "\n" + "NOMBRE: " + nombre + "\n"
                + "FECHA DE NACIMIENTO: " + fecha_nacimiento + "\n" + "SEXO: " + sexo + "\n" + "CONTRASE�A: " + contrasena;
    }
	
	
	
	
    
}



