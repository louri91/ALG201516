package mochila_voraz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
/**
 * 
 * @author louri
 * @date 1 Mayo 2016
 */
public class MochilaVoraz {

	public static void main(String[] args) {
		System.out.println("1. Creación de los elementos que podremos cargar en la mochila: ");
		Objeto[] objetos = {
			new Objeto("Manzana", 2, 5),
			new Objeto("Pera", 2, 2),
			new Objeto("Libreta de Algoritmica", 3, 15),
			new Objeto("Estuche", 4, 2),
			new Objeto("Libro de Algoritmica",5, 20),
			new Objeto("Libro de AC", 5, 15),
			new Objeto("Portatil", 10, 40),
			new Objeto("Zumo", 1, 2),
			new Objeto("Bocadillo", 1, 2),
			new Objeto("Tablet", 2, 15),
			new Objeto("Botella de agua", 2, 5),
			new Objeto("Libreta de AC", 2, 15)
		};

		for(Objeto objeto : objetos){
			System.out.println("	"+objeto.toString());
		}
		
		System.out.println("2. Ordenamos el array de objetos para que el primero sea el que mayor beneficio nos aporta. ");
		
		Arrays.sort(objetos, new Comparator<Objeto>() {
	        @Override
	        public int compare(Objeto o1, Objeto o2) {
	        	return o2.compareTo(o1);
	        }
	    });

		for(Objeto objeto : objetos){
			System.out.println("	"+objeto.toString());
		}
		
		System.out.println("3. Creamos la mochila");
		
		Mochila mochila = new Mochila(33);
		
		System.out.println("4. Llenamos la mochila");
		
		mochila.llenarMochila(objetos);
		
		System.out.println("5. Resultado: ");
		System.out.println("5.1 - Peso actual de la mochila: "+mochila.getPesoActual());
		System.out.println("5.2 - Peso máximo de la mochila: "+mochila.getPesoMaximo());
		ArrayList<Objeto> objetosMochila = mochila.getElementosMochila();
		System.out.println("5.3 - Objetos que llevamos en la mochila = "+objetosMochila.size());
		
		double beneficioTotal = 0;

		for(Objeto objeto : objetosMochila){
			beneficioTotal += objeto.getBeneficio();
			System.out.println("	"+objeto.toString());
		}
		
		System.out.println("5.4 - Beneficio total obtenido: "+beneficioTotal);
		
	}

}
