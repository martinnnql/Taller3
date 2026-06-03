package logica;

import java.util.ArrayList;

import dominio.*;
	

public class SystemImpl implements ISystem {
	
	private static ArrayList<Hechizo> hechizos = new ArrayList<>();
	private static ArrayList<Mago> magos = new ArrayList<>();

	
	public  Hechizo buscarHechizo(String hechizoBuscar) {
		for (Hechizo hechizo : hechizos) {
			if (hechizoBuscar.equalsIgnoreCase(hechizo.getNombre())) {
				return hechizo;
			
			}
		}
		return null;
	}
	
	public Mago buscarMago(String nombreMago) {
		for (Mago mago : magos) {
			if (nombreMago.equalsIgnoreCase(mago.getNombre())) {
				return mago;
			}
		}
		return null;
	}
	
	
	@Override
	public void añadirMago(String nombre, ArrayList<Hechizo> hechizosMago) {
		magos.add(new Mago(nombre, hechizosMago));
		
	}


	

	

	@Override
	public String mostrarMagos() {
		int cont = 1;
		String linea = "";
		
		for (Mago mago : magos) {
			linea += cont + ") " + mago.getNombre() + "\n";
			cont++;
		}
		return linea;
	}
		
	

	
	@Override
	public void mostrarMagosConPuntuacion() {
		// TODO Auto-generated method stub
		int cont = 1;
		for (Mago mago : magos) {
			System.out.println(cont + ") " + mago.getNombre() + " | Puntuación: " + mago.calcularPuntuacion());
			cont++;
		}
	}
		
	

	@Override
	public void añadirHechizo(Hechizo hechizo) {
		// TODO Auto-generated method stub
		hechizos.add(hechizo);
		
		
		
	}

	@Override
	public int cantHechizos() {
		// TODO Auto-generated method stub
		return hechizos.size();
	}

	@Override
	public String mostrarHechizos() {
		int cont = 1;
		String linea = "";
		
		for (Hechizo hechizo : hechizos) {
			linea += cont + ") " + hechizo.getNombre() + "\n";
			cont++;
		}
		return linea;
	}

	@Override
	public void ordenarHechizos() {
		// TODO Auto-generated method stub
		for (int i = 0; i < hechizos.size() - 1; i++) {
			for (int j = 0; j < hechizos.size() - 1 - i; j++) {

				if (hechizos.get(j).calcularPuntuacion() < hechizos.get(j + 1).calcularPuntuacion()) {

					Hechizo aux = hechizos.get(j);
					hechizos.set(j, hechizos.get(j + 1));
					hechizos.set(j + 1, aux);
				}
			}
		}
	}

	@Override
	public String mostrarTop10Hechizos() {
		// TODO Auto-generated method stub
		boolean condicion = true;
		int cont = 1;
		String linea = "";
		
		while (condicion) {
			for (Hechizo hechizo : hechizos) {
				linea += cont + ") " + hechizo.getNombre() + " | Puntuación: " + hechizo.calcularPuntuacion() + "\n";
				cont++;

				if (cont == 11) {
					condicion = false;
					break;
				}
			}

		}
		

		return linea;
	}

	@Override
	public void ordenarMagos() {
		// TODO Auto-generated method stub
		for (int i = 0; i < magos.size() - 1; i++) {
			for (int j = 0; j < magos.size() - 1 - i; j++) {

				if (magos.get(j).calcularPuntuacion() < magos.get(j + 1).calcularPuntuacion()) {

					Mago aux = magos.get(j);
					magos.set(j, magos.get(j + 1));
					magos.set(j + 1, aux);
				}
			}
		}
	}

	@Override
	public String mostrarTop3Magos() {
		// TODO Auto-generated method stub
		String linea = "";
		boolean condicion = true;
		int cont = 1;
		
		while (condicion) {
			for (Mago mago : magos) {
				linea += cont + ") " + mago.getNombre() + " | Puntuación: " + mago.calcularPuntuacion() + "\n";
				cont++;

				if (cont == 4) {
					condicion = false;
					break;
				}
			}

		}
		
		return linea;
	}

	@Override
	public void mostrarHechizosConPuntacion() {
		int cont = 1;
		for (Hechizo hechizo : hechizos) {
			System.out.println(cont + ") " + hechizo.getNombre() + " | Puntuación: " + hechizo.calcularPuntuacion());
			cont++;
		}
	
	}

	@Override
	public void mostrarHechizosMago(Mago magoSeleccionado) {
		int cont = 1;
		
		for (Hechizo hechizo : magoSeleccionado.getHechizos()) {
			System.out.println(cont + ") " + hechizo.getNombre());
			cont++;
		}
		
		
	}

	@Override
	public void eliminarMago(String nombre) {
		// TODO Auto-generated method stub
		for (int i = 0; i < magos.size(); i++) {
			if (magos.get(i).getNombre().equals(nombre)) {
				magos.remove(i);
				break;
			}
		}
	}
	
	

}
