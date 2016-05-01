package mochila_voraz;

import java.util.ArrayList;
/**
 * 
 * @author louri
 * @date 1 Mayo 2016
 */

public class Mochila {
	
private int pesoMaximo;
private int pesoActual;
private ArrayList<Objeto> elementosMochila;

public Mochila(int pesoMaximo) {
	this.pesoMaximo = pesoMaximo;
	this.pesoActual = 0;
	this.elementosMochila = new ArrayList<Objeto>();
}

public boolean add(Objeto objeto){
	if(elementosMochila.add(objeto)){
		return true;
	}else{
		return false;
	}
}

public int getPesoMaximo() {
	return pesoMaximo;
}

public void setPesoMaximo(int pesoMaximo) {
	this.pesoMaximo = pesoMaximo;
}

public int getPesoActual() {
	return pesoActual;
}

public void setPesoActual(int pesoActual) {
	this.pesoActual = pesoActual;
}

public ArrayList<Objeto> getElementosMochila() {
	return elementosMochila;
}

public void setElementosMochila(ArrayList<Objeto> elementosMochila) {
	this.elementosMochila = elementosMochila;
}

@Override
public String toString() {
	return "PesoMaximo de la mochila: " + pesoMaximo + ", \n PesoActual de la mochila: " + pesoActual + ", \n Elementos que contiene la mochila: " + elementosMochila
			+ "]";
}

public void llenarMochila(Objeto[] objetos){	
	for(Objeto objeto : objetos){
		if(pesoActual < pesoMaximo){
			if(pesoActual + objeto.getPeso() <= pesoMaximo){
				add(objeto);
				pesoActual += objeto.getPeso();
			}else{
				add(new Objeto(objeto.getNombre()+" partido", 
						((pesoMaximo-pesoActual)/objeto.getPeso())*objeto.getPeso(), 
						((pesoMaximo-pesoActual)/objeto.getPeso())*objeto.getBeneficio()));
				pesoActual = pesoMaximo;
			}
		}
	}
}

}
