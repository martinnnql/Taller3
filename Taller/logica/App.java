package logica;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import dominio.*;

public class App {
	static ArrayList<Hechizo> hechizos = new ArrayList<>();
	static ArrayList<Mago> magos = new ArrayList<>();

	public static void main(String[] args) throws FileNotFoundException {

		cargarMagos();
		cargarHechizos();

		int opcionMenu1 = cargarMenu1();

		if (opcionMenu1 == 1) {
			int opcionMenuAdmin = cargarMenuAdmin();

			if (opcionMenuAdmin == 1) {
				agregarMago();

			} else if (opcionMenuAdmin == 2) {
				modificarMago();

			} else if (opcionMenuAdmin == 3) {
				eliminarMago();

			} else if (opcionMenuAdmin == 4) {
				agregarHechizo();

			} else if (opcionMenuAdmin == 5) {
				modificarHechizo();

			} else if (opcionMenuAdmin == 6) {
				eliminarHechizo();
			}
		}

		if (opcionMenu1 == 2) {
			int opcionMenuAnalista = cargarMenuAnalista();

			if (opcionMenuAnalista == 1) {
				mostrarTop10Hechizos();

			} else if (opcionMenuAnalista == 2) {
				mostrarTop3Magos();

			} else if (opcionMenuAnalista == 3) {
				mostrarHechizos();

			} else if (opcionMenuAnalista == 4) {
				mostrarMagos();

			} else if (opcionMenuAnalista == 5) {
				mostrarHechizoConPuntuacion();

			} else if (opcionMenuAnalista == 6) {
				mostrarMagosConPuntuacion();
			}
		}

	}

	private static void mostrarMagosConPuntuacion() {
		// TODO Auto-generated method stub

	}

	private static void mostrarHechizoConPuntuacion() {
		// TODO Auto-generated method stub

	}

	private static void mostrarMagos() {
		// TODO Auto-generated method stub

	}

	private static void mostrarHechizos() {
		// TODO Auto-generated method stub

	}

	private static void mostrarTop3Magos() {
		// TODO Auto-generated method stub

	}

	private static void mostrarTop10Hechizos() {
		// TODO Auto-generated method stub

	}

	private static void eliminarHechizo() {
		// TODO Auto-generated method stub

	}

	private static void modificarHechizo() {
		// TODO Auto-generated method stub

	}

	private static void agregarHechizo() {
		// TODO Auto-generated method stub

	}

	private static void eliminarMago() {
		// TODO Auto-generated method stub

	}

	private static void modificarMago() {
		// TODO Auto-generated method stub

	}

	private static void agregarMago() {
		// TODO Auto-generated method stub

	}

	private static int cargarMenuAnalista() {
		Scanner s = new Scanner(System.in);
		int opcion = 0;
		boolean condicion = false;

		while (!condicion) {
			try {
				System.out.println(" - - - Menu de Analisis - - - ");
				System.out.println("\n1. Top 10 Mejores Hechizos\r\n" + "2. Top 3 Mejores Magos\r\n"
						+ "3. Mostrar todos los Hechizos\r\n" + "4. Mostrar todos los magos\r\n"
						+ "5. Mostrar todos los Hechizos junto a su puntuacion\r\n"
						+ "6. Mostrar todos los magos junto a su puntuacion\r\n" + "");
				System.out.print("> ");
				opcion = s.nextInt();

				if (opcion <= 6 && opcion >= 1) {
					condicion = true;
				} else {
					System.err.println("\nIngrese un valor valido.");
				}

			} catch (Exception e) {
				System.err.println("\nIngrese un valor valido.");
				s.nextLine();
			}

		}

		s.nextLine();
		return opcion;
	}

	private static int cargarMenuAdmin() {
		Scanner s = new Scanner(System.in);
		int opcion = 0;
		boolean condicion = false;

		while (!condicion) {
			try {
				System.out.println(" - - - Menu de Administrador - - - ");
				System.out.println("\n1. Agregar Mago\r\n" + "2. Modificar Mago\r\n" + "3. Eliminar Mago\r\n"
						+ "4. Agregar Hechizo\r\n" + "5. Modificar Hechizo\r\n" + "6. Eliminar Hechizo");
				System.out.print("> ");
				opcion = s.nextInt();

				if (opcion <= 6 && opcion >= 1) {
					condicion = true;
				} else {
					System.err.println("\nIngrese un valor valido.");
				}

			} catch (Exception e) {
				System.err.println("\nIngrese un valor valido.");
				s.nextLine();
			}

		}

		s.nextLine();
		return opcion;
	}

	private static int cargarMenu1() {
		Scanner s = new Scanner(System.in);
		int opcion1 = 0;
		boolean condicion = false;

		while (!condicion) {
			try {
				System.out.println(" - - - Bienvenido Usuario - - - ");
				System.out.println("¿A que panel desea ingresar?");
				System.out.println("\n1) Panel de Administrador");
				System.out.println("2) Panel de Analista");
				System.out.print("> ");
				opcion1 = s.nextInt();

				if (opcion1 <= 2 && opcion1 >= 1) {
					condicion = true;
				} else {
					System.err.println("\nIngrese un valor valido.");
				}

			} catch (Exception e) {
				System.err.println("\nIngrese un valor valido.");
				s.nextLine();
			}

		}

		s.nextLine();
		return opcion1;

	}

	private static void cargarHechizos() throws FileNotFoundException {
		Scanner sarch = new Scanner(new File("Hechizos.txt"));

		while (sarch.hasNextLine()) {
			String linea = sarch.nextLine();
			String[] partes = linea.split(";");

			String nombre = partes[0];
			String tipo = partes[1];
			int daño = Integer.parseInt(partes[2]);

			switch (tipo) {
			case "Fuego":
				int duracionQuemadura = Integer.parseInt(partes[3]);
				Hechizo nuevoHechizoFuego = new HechizoFuego(nombre, tipo, daño, duracionQuemadura);
				hechizos.add(nuevoHechizoFuego);
				break;
			case "Tierra":
				int mejoraDefensa = Integer.parseInt(partes[3]);
				Hechizo nuevoHechizoTierra = new HechizoTierra(nombre, tipo, daño, mejoraDefensa);
				hechizos.add(nuevoHechizoTierra);
				break;
			case "Planta":
				String[] partesPlanta = partes[3].split(",");
				int duracionStunt = Integer.parseInt(partesPlanta[0]);
				int cantPlantas = Integer.parseInt(partesPlanta[1]);
				Hechizo nuevoHechizoPlanta = new HechizoPlanta(nombre, tipo, daño, duracionStunt, cantPlantas);
				hechizos.add(nuevoHechizoPlanta);
				break;
			case "Agua":
				String[] partesAgua = partes[3].split(",");
				int cantidadHeal = Integer.parseInt(partesAgua[0]);
				int presionDelAgua = Integer.parseInt(partesAgua[1]);
				Hechizo nuevoHechizoAgua = new HechizoAgua(nombre, tipo, daño, cantidadHeal, presionDelAgua);
				hechizos.add(nuevoHechizoAgua);
				break;
			}

		}
	}

	private static void cargarMagos() throws FileNotFoundException {
		Scanner sarch = new Scanner(new File("Magos.txt"));

		while (sarch.hasNextLine()) {
			String linea = sarch.nextLine();
			String[] partes = linea.split(";");

			String nombre = partes[0];

			String[] partesHechizos = partes[1].split("|");
			ArrayList<Hechizo> hechizosMago = new ArrayList<>();

			for (String hechizo : partesHechizos) {
				Hechizo hechizoM = buscarHechizo(hechizo);
				hechizosMago.add(hechizoM);
			}
			Mago nuevoMago = new Mago(nombre, hechizosMago);
			magos.add(nuevoMago);

		}
	}

	private static Hechizo buscarHechizo(String hechizoBuscar) {
		// TODO Auto-generated method stub
		for (Hechizo hechizo : hechizos) {
			if (hechizoBuscar.equalsIgnoreCase(hechizo.getNombre())) {
				return hechizo;
			}
		}
		return null;
	}

}
