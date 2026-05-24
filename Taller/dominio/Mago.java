package dominio;

import java.util.ArrayList;

public class Mago implements Calculador{
	private String nombre;
	private ArrayList<Hechizo> hechizos;
	
	public Mago(String nombre, ArrayList<Hechizo> hechizos) {
		super();
		this.nombre = nombre;
		this.hechizos = hechizos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Hechizo> getHechizos() {
		return hechizos;
	}

	public void setHechizos(ArrayList<Hechizo> hechizos) {
		this.hechizos = hechizos;
	}
	
	public void añadirHechizo(Hechizo hechizo) {
		hechizos.add(hechizo);
	}

	@Override
	public int calcularPuntuacion() {
		int puntuacion = 0;
		for (Hechizo hechizo : hechizos) {
			if (hechizo instanceof HechizoFuego) {
				puntuacion += ((HechizoFuego) hechizo).calcularPuntuacion();
				
			}else if (hechizo instanceof HechizoTierra) {
					puntuacion += ((HechizoTierra) hechizo).calcularPuntuacion();
			
			} else if (hechizo instanceof HechizoPlanta) {
					puntuacion += ((HechizoPlanta) hechizo).calcularPuntuacion();
					
			} else if (hechizo instanceof HechizoAgua) {
					puntuacion += ((HechizoAgua) hechizo).calcularPuntuacion();
			}
		}
		return puntuacion;
		
	}
}
