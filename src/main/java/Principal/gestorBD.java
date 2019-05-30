package Principal;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Clases.Autobus;
import Clases.Billete;
import Clases.Linea;
import Clases.Parada;

public class gestorBD {
	
	private Connection connect;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private static String ruta = "txt\\direccion.txt";
	
	public void gestorDB() {
		connect=null;
	}
	//InsertarHost(),InsertarUsuario(),InsertarContrasena()
	public void conectar() throws Exception {
	    try {
	        // This will load the MySQL driver, each DB has its own driver
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        
	        // Setup the connection with the DB
	        connect = DriverManager
	                .getConnection(InsertarHost(),InsertarUsuario(),InsertarContrasena());
	         
	        
	    } catch (Exception e) {
	        
	    	e.printStackTrace();
	    	int decision=JOptionPane.showConfirmDialog(null,"Error al encontrar el archivo .txt. si pulsas Si el programa se cerrara, si pulsas no, continuara sin conexion a la BBDD","Advertencia",JOptionPane.YES_OPTION,JOptionPane.NO_OPTION);
	    	if(decision==JOptionPane.YES_OPTION) {
	    		JOptionPane.showMessageDialog(null, "Hasta la Proxima");
	    		System.exit(1);
	    	}
	    } 
	
	}

	public void borrar_billete(String DNI, int Cod_Billete, String Fecha) throws Exception
	{
		try
		{
			statement = connect.createStatement();
			statement.executeUpdate("delete from billete where DNI = '"+DNI+"' and Cod_Billete = '"+Cod_Billete+"' and Fecha ='"+Fecha+"'");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void modificar_billete(String DNI, String fecha_actual, String fecha_cambio, int Cod_Billete)
	{
		try
		{
			statement = connect.createStatement();
			statement.executeUpdate("update billete set Fecha ='"+fecha_cambio+"' "	+ "where Fecha = (Select Fecha from (select * from billete) as billetes where DNI = '"+DNI+"' and Cod_Billete = '"+Cod_Billete+"')");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public ArrayList<Billete> seleccionarBillete() throws Exception
	{
		ArrayList<Billete> misBilletes = new ArrayList<Billete>();
		try
		{
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from billete");
			
			while (resultSet.next())
			{
				String cod_billete = resultSet.getString("Cod_Billete");
				String ntrayecto = resultSet.getString("NTrayecto");
				String cod_linea = resultSet.getString("Cod_Linea");
				String cod_bus = resultSet.getString("Cod_Bus");
				String cod_parada_inicio = resultSet.getString("Cod_Parada_Inicio");
				String cod_parada_final = resultSet.getString("Cod_Parada_Fin");
				String fecha = resultSet.getString("Fecha");
				String hora = resultSet.getString("Hora");
				String dni = resultSet.getString("DNI");
				String precio = resultSet.getString("Precio");
				misBilletes.add(new Billete(cod_billete, ntrayecto, cod_linea, cod_bus, cod_parada_inicio, cod_parada_final, fecha, hora, dni, precio));
			}
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return misBilletes;
	}
	
	
	public int billete_borrado_modificado (String DNI, String Cod_Billete, String Fecha) throws Exception {
		int numerofilas=0;
		
		try 
		{
	    	
	    	statement = connect.createStatement();
	   
	        resultSet = statement.executeQuery("select * from billete where DNI ='"+DNI+"' and Cod_Billete = '"+Integer.parseInt(Cod_Billete)+"' and Fecha ='"+Fecha+"'");
	                
	        if(resultSet.next()) 
		    {
		    	  numerofilas=1;
		    }
		      
		} 
	    catch (Exception e) 
	    {
		    e.printStackTrace();
		} 
		
	    return numerofilas;
	 
	}
	
	
	public ArrayList<Linea> seleccionarLinea() throws Exception 
	{
        ArrayList<Linea> misLineas=new ArrayList<Linea>();

	    try {
	        // Statements allow to issue SQL queries to the database
	        statement = connect.createStatement();
	        // Result set get the result of the SQL query
	        resultSet = statement
	                .executeQuery("select * from linea");
	        
	        
		    while (resultSet.next()) {
		    	String cod_linea = resultSet.getString("Cod_Linea");
		        String nombre_linea = resultSet.getString("Nombre");
		    	misLineas.add(new Linea(cod_linea, nombre_linea));
		        
		    }
	
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return misLineas;
	}
	
	public ArrayList<Parada> seleccionarParada(String codi_linea) throws Exception
	{
		ArrayList<Parada> misParadas = new ArrayList<Parada>();
		
		try
		{
			statement = connect.createStatement();
			resultSet = statement
					.executeQuery("select Cod_Linea, parada.Cod_Parada, Nombre, Calle, Latitud, Longitud from `linea-parada` inner join `parada` on"
							+ " `linea-parada`.`Cod_Parada` = `parada`.`Cod_Parada` where `linea-parada`.`Cod_Linea`='"+codi_linea+"'");
			
			while (resultSet.next()) 
			{	
				String cod_linea = resultSet.getString("Cod_Linea");
				int cod_parada = resultSet.getInt("parada.Cod_Parada");
				String nombre_parada = resultSet.getString("Nombre");
				String calle_parada = resultSet.getString("Calle");
				double latitud = resultSet.getDouble("Latitud");
				double longitud = resultSet.getDouble("Longitud");
				misParadas.add(new Parada(cod_linea, cod_parada, nombre_parada, calle_parada, latitud, longitud));
			}
				
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return misParadas;
	}
	
	public ArrayList<Autobus> seleccionarAutobus (String codi_linea) throws Exception
	{
		ArrayList<Autobus> misBuses = new ArrayList<Autobus>();
		try
		{
			statement = connect.createStatement();
			resultSet = statement
					.executeQuery("select Cod_Linea, autobus.Cod_bus, N_plazas, Consumo_km, Color from linea_autobus "
							+ "inner join autobus on autobus.Cod_bus = linea_autobus.Cod_bus and cod_linea='"+codi_linea+"'");
			
			while (resultSet.next())
			{
				String Cod_Linea = resultSet.getString("Cod_Linea");
				int Cod_bus = resultSet.getInt("Cod_bus");
				int N_Plazas = resultSet.getInt("N_Plazas");
				double Consumo_km = resultSet.getDouble("Consumo_km");
				String Color = resultSet.getString("Color");
				misBuses.add(new Autobus(Cod_Linea, Cod_bus, N_Plazas, Consumo_km, Color));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return misBuses;
	}

	
	public int numero_plazas_elegidas (int Cod_bus, String fecha)
	{
		int numero_plazas = 0;

		try
		{
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select count(Cod_Billete) as numero_plazas from billete where Cod_Bus ="+Cod_bus+" and Fecha = '"+fecha+"'");
			while (resultSet.next())
			{
				numero_plazas = resultSet.getInt("numero_plazas");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return numero_plazas;
	}
	//------------------------------------------------------------------------------------
	//Comprobar que el DNI y la contrasena que se pasa por parametros coinciden con el de la BBDD, numero de filas es 1
		public int Seleccionar(String dni, String contrasena) throws Exception {
			int numerofilas=0;
			
			
			conectar();
			String sql = "SELECT * FROM cliente WHERE DNI='"+dni+"' AND Contraseña='"+contrasena+"'";
			try 
			{
		    	
		    	statement = connect.createStatement();
		   
		        resultSet = statement.executeQuery(sql);
		                
		        if(resultSet.next()) 
			    {
			    	  numerofilas=1;
			    }
			      
			} 
		    catch (Exception e) 
		    {
			    e.printStackTrace();
		
			    JOptionPane.showMessageDialog(null, "Error al comprobar los datos");
			} 
			
		    return numerofilas;
		 
		}
		
	//----------------------------------------------------------------------------------------------------
	//Insertar todos los usuarios en la base de datos
		
		public void insertarCliente(String dni,String nombre,String apellido,String fechanacimiento, String sexo, String contrasena) throws Exception 
		{
		    try 
		    {
		    	conectar();
		        // Statements allow to issue SQL queries to the database
		        statement = connect.createStatement();
		
		        // PreparedStatements can use variables and are more efficient
		        preparedStatement = connect
		                .prepareStatement("insert into  cliente(DNI,Nombre,Apellidos,Fecha_nac,Sexo,Contraseña) "
		                		+ "values (\""+dni+"\",\""+nombre+"\",\""+apellido+"\",\""+fechanacimiento+"\",\""+sexo+"\",\""+contrasena+"\")");       
		        
		        preparedStatement.executeUpdate();
		
		        JOptionPane.showMessageDialog(null, "Datos correctamente ingresados");
		    } 
		    catch (Exception e) 
		    {
		    	e.printStackTrace();
		    	JOptionPane.showMessageDialog(null, "Error al enviar a informacion a la Base de Datos");		    	
		    } 
		    
		close();
		}
		

	//--------------------------------------------------------------------------------------------------------------------------------------------------
	//Insertar todos los usuarios en la base de datos
		
		public void insertarBillete(String ntrayecto,String cod_linea,String cod_bus,String cod_parada_inicio,String cod_parada_final,
				String fecha,String hora,String dni,String precio) throws Exception 
		{
		    try 
		    {
		    	conectar();
		        // Statements allow to issue SQL queries to the database
		        statement = connect.createStatement();
		
		        // PreparedStatements can use variables and are more efficient
		        preparedStatement = connect
		                .prepareStatement("insert into  billete(NTrayecto,Cod_Linea,Cod_Bus,Cod_Parada_Inicio,Cod_Parada_Fin,Fecha,Hora,DNI,Precio) "
		                		+ "values ('"+Integer.parseInt(ntrayecto)+"','"+cod_linea+"','"+cod_bus+"','"+cod_parada_inicio+"','"+cod_parada_final+"','"+fecha+"','"+hora+"','"+dni+"','"+precio+"')");       
		        
		        preparedStatement.executeUpdate();
		
		       // JOptionPane.showMessageDialog(null, "Billete ingresado correctamente");
		    } 
		    catch (Exception e) 
		    {
		    	e.printStackTrace();
		    //	JOptionPane.showMessageDialog(null, "Error al ingresar el billete en la Base de Datos");		    	
		    } 
		    
		close();
		}
		

	//--------------------------------------------------------------------------------------------------------------------------------------------------
	
		public static String InsertarHost() 
		{
			String texto="";
			 String host="";
			
			try 
			{
				BufferedReader bf = new BufferedReader(new FileReader("txt\\direccion.txt"));
				String temp = "";
				String bfRead;
				while((bfRead = bf.readLine()) !=null) //hace el ciclo mientras bfRead tiene datos
				{
					temp = temp + bfRead;// guardado el texto del archivo
				}
				texto = temp;
			}
			catch(Exception e) {
				System.err.println("no se encontro el archivo");
				
			}
					
			host = texto.split(";")[0];
				
			return host;
		}
		
	//Insertar la direccion de la base de datos del USUARIO--------------------------------------------------------------
		
			public static String InsertarUsuario() 
			{
				String texto="";
				String usuario="";
				
				try 
				{
					BufferedReader bf=new BufferedReader(new FileReader("txt\\direccion.txt"));
					String temp="";
					String bfRead;
					while((bfRead = bf.readLine()) !=null) 
					{//hace el ciclo mientras bfRead tiene datos
						temp=temp + bfRead;// guardado el texto del archivo
					}
					texto=temp;
				}
				catch(Exception e)
				{
					System.err.println("no se encontro el archivo");		
				}
							
				usuario=texto.split(";")[1];
					
				return usuario;
			}
			
			
	//Insertar la direccion de la base de datos de la CONTRASENA--------------------------------------------------------------
			
			public static String InsertarContrasena() 
			{
				String texto="";
				String contrasena="";
				
				try 
				{
					BufferedReader bf=new BufferedReader(new FileReader("txt\\direccion.txt"));
					String temp="";
					String bfRead;
					while((bfRead = bf.readLine()) !=null) 
					{
						temp=temp + bfRead;// guardado el texto del archivo
					}
					
					texto=temp;
				}
				catch(Exception e) 
				{
					System.err.println("no se encontro el archivo");
					
				}
							
				contrasena=texto.split(";")[2];
							
				return contrasena;
			}
	//------------------------------------------------------------------------------------------------------------------------------------------	
	//Leer fichero
			
		public String Leertxt (String direccion) 
		{
			String texto="";
			try 
			{
				BufferedReader bf=new BufferedReader(new FileReader("txt\\direccion.txt"));
				String temp="";
				String bfRead;
				
				while((bfRead = bf.readLine()) !=null) 
				{
					temp=temp + bfRead;// guardado el texto del archivo
				}
				
				texto=temp;
			}
			catch(Exception e) 
			{
				System.err.println("no se encontro el archivo");
			}
			
			return texto;
		}
		
//  esto de abajo se pone en el main	
//		LeerFichero leerarchivo = new LeerFichero();		
//		System.out.println(leerarchivo.Leertxt("C:\\Users\\eclip\\Desktop\\txt\\holapapu.txt"));


	//-------------------------------------------------------------------------------------------------------------------------------------
		
		public String EscribiroCrearFichearo(String Fecha,String DNI,String Cod_Linea,int Cod_Parada_Inicio,int Cod_Parada_Fin) {
			 
			String frase= ("Billete"+"\n"+"-----------"+"\n"+"Fecha: "+Fecha+"\n"+"DNI: " 
					+DNI+"\n"+"Codigo de la linea: "+Cod_Linea+"\n"+"Codigo de la parada de inicio: "+Cod_Parada_Inicio+"\n"+"Codigo de la parada final: " 
	                + Cod_Parada_Fin +"\n");
			
			char c=0;
			
			try {
				//*** crear un objeto para crear un flujo de salida asociado a un fichero datos.txt
				
			//	FileOutputStream f= new FileOutputStream("datos.txt");  //---> lo crea cada vez
			//	FileOutputStream f= new FileOutputStream("datos.txt", true);  //---> aï¿½ade al final del fichero
				FileOutputStream f= new FileOutputStream("txt\\escribir.txt");  //---> En un directorio concreto
				
				//*** pasar los caracteres al flujo
				for(int i =0; i<frase.length(); i++) {
					c=frase.charAt(i);
					f.write(c);
				}
				//*** cerrra el flujo de salida
				f.close();
				
			}catch (IOException e) {
				System.out.println("error  "+e.getMessage());
			}
			return frase;
			
			}
	
	public void close() {
	    try {
	        if (resultSet != null) {
	            resultSet.close();
	        }
	
	        if (statement != null) {
	            statement.close();
	        }
	
	        if (connect != null) {
	            connect.close();
	        }
	    } catch (Exception e) {
	
	    }
}


}
