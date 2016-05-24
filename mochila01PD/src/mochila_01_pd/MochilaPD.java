package mochila_01_pd;

import mochila_01_pd.Objeto;

import java.util.Arrays;
import java.util.Comparator;

import mochila_01_pd.Mochila;

public class MochilaPD {
	private static double[][] tablaResultados;
	
	public static void main(String[] args){
		System.out.println("1. Creación de los elementos que podremos cargar en la mochila: ");
		Objeto[] objetos = {
			new Objeto("Manzana", 2, 5),
			new Objeto("Pera", 2, 2),
			new Objeto("Libreta de Algoritmica", 3, 15),
			new Objeto("Estuche", 4, 2),
			new Objeto("Libro de Algoritmica",5, 20),
			new Objeto("Libro de AC", 5, 15),
			new Objeto("Portatil", 10, 40),
			new Objeto("Bocadillo", 1, 2),
			new Objeto("Tablet", 2, 15),
			new Objeto("Botella de agua", 2, 5),
			new Objeto("Libreta de AC", 2, 15),
			new Objeto("Zumo", 1, 2)
		};

		for(Objeto objeto : objetos){
			System.out.println("	"+objeto.toString());
		}
		
		/*
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
		*/
		
		System.out.println("3. Creamos la mochila");
		
		Mochila mochila = new Mochila(27);
		
		System.out.println("4. Creamos la tabla V que contendrá los subproblemas");
		
		tablaResultados = new double[objetos.length][mochila.getPesoMaximo()];
		
		System.out.println("5. Rellenamos la tabla de subproblemas");
		System.out.println("5.1. Inicializamos con los casos base");
		
		for(int i = 0; i< objetos.length; i++){
			for(int j = 0; j<mochila.getPesoMaximo(); j++){
				tablaResultados[i][0] = 0;
				tablaResultados[0][j] = 0;
				System.out.print(tablaResultados[i][j] + " ");
			}
			System.out.print("\n");
		}
		
		System.out.println("5.2. Rellenamos con los resultados de los subproblemas");
		
		for(int i = 1; i< objetos.length; i++){
			for(int j = 1; j<mochila.getPesoMaximo(); j++){
				if((j - (objetos[i].getPeso())) < 0){
					tablaResultados[i][j] = tablaResultados[i-1][j];
				}else{
					tablaResultados[i][j] = Math.max(tablaResultados[i-1][j], objetos[i].getBeneficio() + tablaResultados[i-1][(int)(j - (objetos[i].getPeso()))]);
				}
				//System.out.print(tablaResultados[i][j] + " ");
			}
			//System.out.print("\n");
		}
		
		for(int i = 0; i< objetos.length; i++){
			for(int j = 0; j<mochila.getPesoMaximo(); j++){
				System.out.print(tablaResultados[i][j] + " ");
			}
			System.out.print("\n");
		}
		
		System.out.println("6. Beneficio óptimo que obtenemos:"+ tablaResultados[objetos.length-1][mochila.getPesoMaximo()-1]);

		
		System.out.println("7. Recomponemos la solución final");
		
		int[] tuplaSolucion = new int[objetos.length];
		
		int j = mochila.getPesoMaximo()-1;
		
		for(int i = objetos.length-1;i>0;i--){
			if(tablaResultados[i][j]==tablaResultados[i-1][j]){
				tuplaSolucion[i] = 0;
			}else{
				tuplaSolucion[i] = 1;
				j = (int) (j - objetos[i].getPeso());
			}
		}
		
		System.out.println("8. Solución:");
		double pesoTotal = 0.0;
		for(int z = 0; z<tuplaSolucion.length;z++){
			if(tuplaSolucion[z]==1){
				pesoTotal = pesoTotal + objetos[z].getPeso();
				System.out.println("Nos llevamos el objeto "+ objetos[z].getNombre()+ " con peso "+objetos[z].getPeso());
			}
			System.out.print("x"+z +" = " +tuplaSolucion[z]+", ");
		}
		System.out.println("Peso total :" +pesoTotal);

	}
}
