package dominio;

public class HechizoPlanta extends Hechizo implements Calculador {
	private int duracionStunt;
	private int cantPlantas;
	
	public HechizoPlanta(String nombre, String tipoHechizo, int daño, int duracionStunt, int cantPlantas) {
		super(nombre, tipoHechizo, daño);
		this.duracionStunt = duracionStunt;
		this.cantPlantas = cantPlantas;
		
	}

	public int getDuracionStunt() {
		return duracionStunt;
	}

	public void setDuracionStunt(int duracionStunt) {
		this.duracionStunt = duracionStunt;
	}

	public int getCantPlantas() {
		return cantPlantas;
	}

	public void setCantPlantas(int cantPlantas) {
		this.cantPlantas = cantPlantas;
	}

	@Override
	public int calcularPuntuacion() {
		return getDaño() + (duracionStunt * cantPlantas);
	}
	
	
	
	
}
