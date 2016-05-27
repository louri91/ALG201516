package mochila_01_pd;

/**
 * 
 * @author louri
 * @date 24 Mayo 2016
 */
public class Objeto implements Comparable<Objeto>{
	private String nombre;
	private double peso;
	private double beneficio;
	
	public Objeto(String nombre, double peso, double beneficio) {
		super();
		this.nombre = nombre;
		this.peso = peso;
		this.beneficio = beneficio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(double beneficio) {
		this.beneficio = beneficio;
	}

	@Override
	public String toString() {
		return "Nombre: " + nombre + ", Peso: " + peso + ", Beneficio: " + beneficio;
	}

	@Override
	/*public int compareTo(Objeto o) {
		if(((o.getBeneficio())/(o.getPeso()))>((this.getBeneficio())/(this.getPeso()))){
			return -1;
		}else if(((o.getBeneficio())/(o.getPeso()))<((this.getBeneficio())/(this.getPeso()))){
			return 1;
		}else{
			return 0;
		}
	}*/
	
	public int compareTo(Objeto o) {
		if(((o.getPeso()))>((this.getPeso()))){
			return 1;
		}else if(((o.getPeso()))<((this.getPeso()))){
			return -1;
		}else{
			return 0;
		}
	}
		

}
