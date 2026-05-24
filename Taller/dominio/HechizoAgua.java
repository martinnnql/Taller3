package dominio;

public class HechizoAgua extends Hechizo implements Calculador{
	private int cantHeal;
	private int presionDelAgua;
	
	public HechizoAgua(String nombre, String tipoHechizo, int daño, int cantHeal, int presionDelAgua) {
		super(nombre, tipoHechizo, daño);
		this.cantHeal = cantHeal;
		this.presionDelAgua = presionDelAgua;
	}

	public int getCantHeal() {
		return cantHeal;
	}

	public void setCantHeal(int cantHeal) {
		this.cantHeal = cantHeal;
	}

	public int getPresionDelAgua() {
		return presionDelAgua;
	}

	public void setPresionDelAgua(int presionDelAgua) {
		this.presionDelAgua = presionDelAgua;
	}

	@Override
	public int calcularPuntuacion() {
		return (getDaño() + cantHeal + presionDelAgua) * 2;
	}
	
	
	
	
	
}
