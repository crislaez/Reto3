package Principal;

import java.util.ArrayList;

import Clases.Autobus;
import Clases.Parada;

public class pruebas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		double precio = 0, consumo; 
		int index_inicio = 1, index_final = 3, plazas_ocupadas = 20;
		
		ArrayList<Parada> paradas = new ArrayList<Parada>();
		paradas.add(new Parada("L1", 3, "Metro Leioa", "Otsobarrena", 43.3191, -2.99149));
		paradas.add(new Parada("L1", 4, "Metro Berango", "Sabino Arana", 43.367, -2.99921));
		paradas.add(new Parada("L1",5, "Metro Larrabasterra", "BI-634", 43.3759, -2.99183));
		paradas.add(new Parada("L1",6, "Ayuntamiento Sopelana", "Sabino Arana", 43.3814, -2.98109));
		
		ArrayList<Autobus> buses = new ArrayList<Autobus>();
		buses.add(new Autobus("L1",1001, 40, 0.32, "Rojo"));
		buses.add(new Autobus("L1",1002, 45, 0.36, "Negro"));
		buses.add(new Autobus("L1",1003, 30, 0.29, "Azul"));
		
		for (int cont=0; cont<buses.size(); cont++)
		{
			System.out.println("Numero de plazas: "+buses.get(cont).getN_plazas());
			precio = CalcularPrecio(index_inicio, index_final, buses.get(cont).getConsumo_km(), buses.get(cont).getN_plazas(), plazas_ocupadas, paradas);
			System.out.println(precio);
			
		}
		
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
	
	public static double CalcularPrecio(int index_inicio,int index_final,double consumo,int plazastotales,int plazasocupadas, ArrayList<Parada>misParadas)
	{
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