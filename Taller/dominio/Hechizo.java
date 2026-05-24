package dominio;

public abstract class Hechizo {
	private String nombre;
	private String tipoHechizo;
	private int daño;
	
	public Hechizo(String nombre, String tipoHechizo, int daño) {
		this.nombre = nombre;
		this.tipoHechizo = tipoHechizo;
		this.daño = daño;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoHechizo() {
		return tipoHechizo;
	}

	public void setTipoHechizo(String tipoHechizo) {
		this.tipoHechizo = tipoHechizo;
	}

	public int getDaño() {
		return daño;
	}

	public void setDaño(int daño) {
		this.daño = daño;
	}
	
	
	
	
	
	
	
	
	
	
}
