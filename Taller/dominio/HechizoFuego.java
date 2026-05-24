package dominio;

public class HechizoFuego extends Hechizo implements Calculador {
	private int duracionQuemadura;
	
	
	public HechizoFuego(String nombre, String tipoHechizo, int daño, int duracionQuemadura) {
		super(nombre, tipoHechizo, daño);
		this.duracionQuemadura = duracionQuemadura;
	}


	public int getDuracionQuemadura() {
		return duracionQuemadura;
	}


	public void setDuracionQuemadura(int duracionQuemadura) {
		this.duracionQuemadura = duracionQuemadura;
	}


	@Override
	public int calcularPuntuacion() {
		return getDaño() * duracionQuemadura;
		
	}




	

	
	
	
	
	
	
	
}
