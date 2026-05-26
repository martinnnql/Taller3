package logica;

import java.util.ArrayList;

import dominio.Hechizo;
import dominio.Mago;

public interface ISystem {
	void añadirMago(String nombre, ArrayList<Hechizo> hechizosMago);
	
	void añadirHechizo(Hechizo hechizo);
	
	String mostrarMagos();
		
	void mostrarMagosConPuntuacion();
	
	Hechizo buscarHechizo(String hechizoBuscar);

	int cantHechizos();

	String mostrarHechizos();
	
	Mago buscarMago(String nombreMago);

	void ordenarHechizos();

	String mostrarTop10Hechizos();

	void ordenarMagos();

	String mostrarTop3Magos();

	void mostrarHechizosConPuntacion();
}
