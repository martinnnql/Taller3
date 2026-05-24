package dominio;

public class HechizoTierra extends Hechizo implements Calculador {
	private int mejoraDefensa;
	
	
	public HechizoTierra(String nombre, String tipoHechizo, int daño, int mejoraDefensa) {
		super(nombre, tipoHechizo, daño);
		this.mejoraDefensa = mejoraDefensa;
	}


	public int getMejoraDefensa() {
		return mejoraDefensa;
	}


	public void setMejoraDefensa(int mejoraDefensa) {
		this.mejoraDefensa = mejoraDefensa;
	}


	@Override
	public int calcularPuntuacion() {
		
		return (getDaño() * mejoraDefensa)/2;
	}

	
	
	
	
	
}
