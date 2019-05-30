package Principal;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Clases.Parada;

public class Metodos {
	
	public static void DeshabilitarMonedas(JButton Continuar_Pago, JButton billete200, JButton billete100, JButton billete50, JButton billete20, JButton billete10,
											JButton billete5, JButton moneda2e, JButton moneda1e, JButton moneda50cent, JButton moneda20cent, JButton moneda10cent, 
											JButton moneda5cent, JButton moneda2cent,  JButton moneda1cent, JTextField DineroFaltante)
	{
		Continuar_Pago.setEnabled(true);
		billete200.setEnabled(false);
		billete100.setEnabled(false);
		billete50.setEnabled(false);
		billete20.setEnabled(false);
		billete10.setEnabled(false);
		billete5.setEnabled(false);
		moneda2e.setEnabled(false);
		moneda1e.setEnabled(false);
		moneda50cent.setEnabled(false);
		moneda20cent.setEnabled(false);
		moneda10cent.setEnabled(false);
		moneda5cent.setEnabled(false);
		moneda2cent.setEnabled(false);
		moneda1cent.setEnabled(false);
		DineroFaltante.setText("0.00");
	}
	
	public static double distanciaCoord(double lat1, double lng1, double lat2, double lng2) {  
        double radiomedio_Tierra = 6371; //en kilómetros
        double lati1 = Math.toRadians(lat1);
        double lati2 = Math.toRadians(lat2);
        double long1 = Math.toRadians(lng1);
        double long2 = Math.toRadians(lng2);
        double distancia = radiomedio_Tierra * Math.acos(Math.cos(lati1)*Math.cos(lati2)*Math.cos(long2-long1)+Math.sin(lati1)*Math.sin(lati2));
        return distancia;  
    }  
	
	public static ArrayList<Parada> ordenarParadas(ArrayList<Parada> misParadas)
	{
		double lat1, lat2, long1, long2, distancia, distancia2=10000;
		ArrayList<Parada> paradas_aux = new ArrayList<Parada>();
		paradas_aux.add(new Parada());
		
		for (int cont_parada1 = 0; cont_parada1 < misParadas.size()-1; cont_parada1++)
		{
			lat1 = misParadas.get(cont_parada1).getLatitud();
			long1 = misParadas.get(cont_parada1).getLongitud();
			
			for (int cont_parada2 = cont_parada1+1; cont_parada2<misParadas.size(); cont_parada2++)
			{
				lat2 = misParadas.get(cont_parada2).getLatitud();
				long2 = misParadas.get(cont_parada2).getLongitud();
				
				distancia = distanciaCoord(lat1, long1, lat2, long2);
				
				if (cont_parada2 == cont_parada1+1)
				{
					distancia2 = distancia;
				}
				
				else
				{
					if (distancia<distancia2)
					{
						distancia2 = distancia;
						
						paradas_aux.set(0, misParadas.get(cont_parada1+1));
						misParadas.set(cont_parada1+1, misParadas.get(cont_parada2));
						misParadas.set(cont_parada2, paradas_aux.get(0));
					}
				}			
			}
		}
		return misParadas;
	}
	
	public static void colocacionComboBox(JRadioButton rdbtnIda, ArrayList<Parada> misParadas, JComboBox <String> Origen, JComboBox <String> Destino, int paradaOrigenSeleccionda)
	{
		if (paradaOrigenSeleccionda!=-1) 
			Destino.removeAllItems();		

		if (rdbtnIda.isSelected())
		{
			
			if (paradaOrigenSeleccionda==-1) 
				for (int cont = 0; cont<misParadas.size(); cont++)
					Origen.addItem(misParadas.get(cont).getNombre());
						
			for (int cont = paradaOrigenSeleccionda+1; cont<misParadas.size(); cont++)
				Destino.addItem(misParadas.get(cont).getNombre());
			
		}
		else
		{			
		
			if (paradaOrigenSeleccionda==-1) 	
				for (int cont = misParadas.size()-1; cont > -1; cont--)
					Origen.addItem(misParadas.get(cont).getNombre());
			for (int cont = misParadas.size()-1-paradaOrigenSeleccionda-1; cont >= 0; cont--)
				Destino.addItem(misParadas.get(cont).getNombre());
		}
	}
	
	public static double CalcularPrecio(int index_inicio,int index_final,double consumo,int plazastotales,int plazasocupadas, ArrayList<Parada>misParadas){
		
		double lat1, lat2, long1, long2, distancia, distancia_total = 0, preciobillete = 0; 
		double porcentaje=(plazasocupadas*0.2)/plazastotales;
		double preciobillete_total = 0;
		
	

		
		for (int cont = index_inicio; cont < index_final; cont++)
		{
			lat1 = misParadas.get(cont).getLatitud();
			long1 = misParadas.get(cont).getLongitud();
			lat2 = misParadas.get(cont+1).getLatitud();
			long2 = misParadas.get(cont+1).getLongitud();
				
				distancia = distanciaCoord(lat1,long1,lat2,long2);
				distancia_total = distancia + distancia_total;
		}
		
		preciobillete=distancia_total*consumo;
		preciobillete_total = (preciobillete*porcentaje) + preciobillete;
		
			
		return preciobillete_total;
	}
}










