package logica;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import dominio.*;

public class App {
	private static Scanner s = new Scanner(System.in);
	private static ISystem sys = new SystemImpl();

	public static void main(String[] args) throws IOException {
		cargarHechizos();
		cargarMagos();

		boolean condicion = false;
		int opcionMenu1 = cargarMenu1();

		while (!condicion) {
			if (opcionMenu1 == 1) {
				int opcionMenuAdmin = cargarMenuAdmin();

				if (opcionMenuAdmin == 7) {
					System.out.println("Saliendo . . .");
					condicion = true;
				}

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

				if (opcionMenuAnalista == 7) {
					System.out.println("Saliendo . . .");
					condicion = true;
				}

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
		s.close();
	}

	private static void mostrarMagosConPuntuacion() {
		// TODO Auto-generated method stub
		System.out.println(" - - - MAGOS - - - ");
		System.out.println();

		sys.mostrarMagosConPuntuacion();
	}

	private static void mostrarHechizoConPuntuacion() {
		// TODO Auto-generated method stub
		System.out.println(" - - - HECHIZOS - - - ");
		System.out.println();

		sys.mostrarHechizosConPuntacion();

	}

	private static void mostrarMagos() {
		// TODO Auto-generated method stub
		int cont = 1;
		System.out.println(" - - - MAGOS - - - ");
		System.out.println();

		System.out.println(sys.mostrarMagos());

	}

	private static void mostrarHechizos() {
		// TODO Auto-generated method stub
		int cont = 1;
		System.out.println(" - - - HECHIZOS - - - ");
		System.out.println();

		System.out.println(sys.mostrarHechizos());

	}

	private static void mostrarTop3Magos() {
		// TODO Auto-generated method stub
		sys.ordenarMagos();

		System.out.println(" - - - TOP 3 MEJORES MAGOS - - -");
		System.out.println("");

		System.out.println(sys.mostrarTop3Magos());

	}

	private static void mostrarTop10Hechizos() {
		sys.ordenarHechizos();
		System.out.println(" - - - TOP 10 MEJORES HECHIZOS - - -");
		System.out.println("");

		System.out.println(sys.mostrarTop10Hechizos());

	}

	private static void eliminarHechizo() {
		// TODO Auto-generated method stub
		System.out.println("- - - ELIMINADOR DE HECHIZOS - - -");
		System.out.println("Bienvenido al eliminador de hechizos!");
		System.out.println();

		System.out.println(sys.mostrarHechizos());

		System.out.println("\nIngrese el nombre del hechizo que desea eliminar");
		System.out.print("> ");
		String nombreHechizoEliminar = s.nextLine();

		Hechizo hechizoSeleccionado = sys.buscarHechizo(nombreHechizoEliminar);

		if (hechizoSeleccionado == null) {
			System.err.println("Ese hechizo no existe!");
			return;
		}

		sys.eliminarHechizo(hechizoSeleccionado.getNombre());

		// Eliminar hechizo del txt
		try {
			ArrayList<String> lineas = new ArrayList<>();
			BufferedReader br = new BufferedReader(new FileReader("Hechizos.txt"));
			String linea;

			while ((linea = br.readLine()) != null) {

				if (linea.trim().isEmpty()) {
					continue;
				}

				String[] partes = linea.split(";");
				String nombreHechizoTxt = partes[0];

				if (nombreHechizoTxt.equalsIgnoreCase(hechizoSeleccionado.getNombre())) {
					continue;
				}

				lineas.add(linea);
			}
			br.close();

			BufferedWriter bw = new BufferedWriter(new FileWriter("Hechizos.txt"));

			for (int i = 0; i < lineas.size(); i++) {
				bw.write(lineas.get(i));

				if (i < lineas.size() - 1) {
					bw.newLine();
				}
			}
			bw.close();

			System.out.println("¡Hechizo eliminado con éxito!");

		} catch (IOException e) {

		}

	}

	private static void modificarHechizo() throws FileNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("- - - MODIFICAR HECHIZO - - -");
		System.out.println("Bienvenido al modificador de hechizos! \nQue hechizo desea modificar?");

		System.out.println(sys.mostrarHechizos());

		System.out.println("Ingrese el nombre del hechizo que desea modificar");
		System.out.print("> ");
		String nombreHechizo = s.nextLine();

		Hechizo hechizoSeleccionado = sys.buscarHechizo(nombreHechizo);

		if (hechizoSeleccionado == null) {
			System.err.println("Ese hechizo no existe!");
			return;
		}

		int opcionMod;

		do {
			System.out.println("Que cambios deseas aplicar al hechizo " + hechizoSeleccionado.getNombre() + "?");
			System.out.println("1) Cambiar nombre. \n2) Cambiar tipo y estadisticas. \n3) Salir.");
			opcionMod = s.nextInt();
			s.nextLine();

			if (opcionMod == 3) {
				System.out.println("Saliendo . . .");
				return;
			}
			if (opcionMod == 1) {
				cambiarNombreHechizo(hechizoSeleccionado);
			}
			if (opcionMod == 2) {
				cambiarTipoYEstadisticas(hechizoSeleccionado);
			}

		} while (opcionMod < 1 || opcionMod > 3);

	}

	private static void cambiarTipoYEstadisticas(Hechizo hechizoSeleccionado) {
		// TODO Auto-generated method stub
		String nuevoTipo = "";
		String tipoFormateado = "";
		boolean tipoValido = false;

		while (!tipoValido) {
			System.out.println("Elija el nuevo tipo para el hechizo " + hechizoSeleccionado.getNombre() + " (Actual: "
					+ hechizoSeleccionado.getTipoHechizo() + "):");
			System.out.print("> ");
			nuevoTipo = s.nextLine().toLowerCase();

			if (nuevoTipo.equals("fuego")) {
				tipoFormateado = "Fuego";
				tipoValido = true;
			} else if (nuevoTipo.equals("agua")) {
				tipoFormateado = "Agua";
				tipoValido = true;
			} else if (nuevoTipo.equals("tierra")) {
				tipoFormateado = "Tierra";
				tipoValido = true;
			} else if (nuevoTipo.equals("planta")) {
				tipoFormateado = "Planta";
				tipoValido = true;
			} else {
				System.err.println("Tipo no válido. Debe ser Fuego, Agua, Tierra o Planta!!");
			}

			if (tipoValido && tipoFormateado.equals(hechizoSeleccionado.getTipoHechizo())) {
				System.err.println("El hechizo ya es de tipo " + tipoFormateado + ". Elige uno distinto!");
				tipoValido = false;
			}
		}

		System.out.println("Ingrese el nuevo daño del hechizo:");
		System.out.print("> ");
		int nuevoDaño = s.nextInt();
		s.nextLine();

		String lineaNueva = "";

		switch (tipoFormateado) {
		case "Fuego":
			System.out.println("Ingrese el daño de quemadura: ");
			int quemadura = s.nextInt();
			s.nextLine();
			lineaNueva = hechizoSeleccionado.getNombre() + ";Fuego;" + nuevoDaño + ";" + quemadura;
			break;

		case "Tierra":
			System.out.println("Ingrese el valor de la mejorad de defensa: ");
			int defensa = s.nextInt();
			s.nextLine();
			lineaNueva = hechizoSeleccionado.getNombre() + ";Tierra;" + nuevoDaño + ";" + defensa;
			break;

		case "Agua":
			System.out.println("Ingrese la cantidad de heal: ");
			int heal = s.nextInt();
			s.nextLine();
			System.out.println("Ingrese la presión del agua: ");
			int presion = s.nextInt();
			s.nextLine();
			lineaNueva = hechizoSeleccionado.getNombre() + ";Agua;" + nuevoDaño + ";" + heal + "," + presion;
			break;

		case "Planta":
			System.out.println("Ingrese la duración del Stunt: ");
			int stunt = s.nextInt();
			s.nextLine();
			System.out.println("Ingrese la cantidad de plantas que se generan: ");
			int plantas = s.nextInt();
			s.nextLine();
			lineaNueva = hechizoSeleccionado.getNombre() + ";Planta;" + nuevoDaño + ";" + stunt + "," + plantas;
			break;
		}

		try {
			ArrayList<String> lineas = new ArrayList<>();
			BufferedReader br = new BufferedReader(new FileReader("Hechizos.txt"));
			String linea;
			while ((linea = br.readLine()) != null) {
				if (linea.trim().isEmpty())
					continue;

				String[] partes = linea.split(";");
				if (partes[0].equalsIgnoreCase(hechizoSeleccionado.getNombre())) {
					lineas.add(lineaNueva);
				} else {
					lineas.add(linea);
				}
			}
			br.close();

			BufferedWriter bw = new BufferedWriter(new FileWriter("Hechizos.txt"));
			for (int i = 0; i < lineas.size(); i++) {
				bw.write(lineas.get(i));
				if (i < lineas.size() - 1)
					bw.newLine();
			}
			bw.close();

			System.out.println("Tipo y estadisticas modificadas con exito!");
		} catch (IOException e) {

		}

	}

	private static void cambiarNombreHechizo(Hechizo hechizoSeleccionado) throws FileNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("Ingrese el nuevo nombre del hechizo " + hechizoSeleccionado.getNombre());
		System.out.print("> ");
		String nuevoNombre = s.nextLine();
		String antiguoNombre = hechizoSeleccionado.getNombre();

		hechizoSeleccionado.setNombre(nuevoNombre);

		try {
			ArrayList<String> lineas = new ArrayList<>();
			BufferedReader br = new BufferedReader(new FileReader("Hechizos.txt"));
			String linea;

			while ((linea = br.readLine()) != null) {
				if (linea.trim().isEmpty()) {
					continue;
				}

				String[] partes = linea.split(";");
				String nombreHechizoTxt = partes[0];

				if (nombreHechizoTxt.equalsIgnoreCase(antiguoNombre)) {

					partes[0] = nuevoNombre;

					linea = partes[0];

					for (int j = 1; j < partes.length; j++) {
						linea = linea + ";" + partes[j];
					}
				}

				lineas.add(linea);
			}
			br.close();

			BufferedWriter bw = new BufferedWriter(new FileWriter("Hechizos.txt"));
			for (int i = 0; i < lineas.size(); i++) {
				bw.write(lineas.get(i));

				if (i < lineas.size() - 1) {
					bw.newLine();
				}
			}
			bw.close();

			System.out.println("\nEl nombre del hechizo ha sido actualizado con éxito!");

		} catch (Exception e) {

		}

	}

	private static void agregarHechizo() {
	    System.out.println("- - - CREADOR DE HECHIZOS - - -");
	    System.out.println("Bienvenido al creador de hechizos!");
	    System.out.println("\nIngrese el nombre del nuevo hechizo");
	    System.out.print("> ");
	    String nombreNuevoHechizo = s.nextLine();

	    String tipoHechizoNuevo = "";
	    boolean tipoValido = false;

	    while (!tipoValido) {
	        System.out.println("Ingrese el tipo del hechizo (Fuego, Agua, Tierra o Planta)");
	        System.out.print("> ");
	        tipoHechizoNuevo = s.nextLine().toLowerCase();

	        if (tipoHechizoNuevo.equals("fuego") || tipoHechizoNuevo.equals("tierra")
	                || tipoHechizoNuevo.equals("agua") || tipoHechizoNuevo.equals("planta")) {
	            tipoValido = true;
	        } else {
	            System.err.println("\nIngrese un tipo entre Fuego, Agua, Tierra o Planta !!");
	        }
	    }

	    int cantDañoNuevo = -1;
	    while (cantDañoNuevo < 0) {
	        try {
	            System.out.println("Ingrese la cantidad de daño que hace el hechizo:");
	            System.out.print("> ");
	            cantDañoNuevo = Integer.parseInt(s.nextLine());
	            
	            if (cantDañoNuevo < 0) {
	                System.err.println("El daño no puede ser menor a 0!");
	            }
	        } catch (NumberFormatException e) {
	            System.err.println("Error: Debes ingresar un número válido.");
	            cantDañoNuevo = -1; 
	        }
	    }

			String lineaArchivo = "";

			switch (tipoHechizoNuevo) {
			case "fuego":
				int cantDañoQuemaduraNuevo = -1;
			    
			    while (cantDañoQuemaduraNuevo < 0) {
			        try {
			            System.out.println("Ingrese la cantidad de daño por quemadura:");
			            System.out.print("> ");
			            cantDañoQuemaduraNuevo = Integer.parseInt(s.nextLine());
			            
			            if (cantDañoQuemaduraNuevo < 0) {
			                System.err.println("El daño no puede ser menor a 0!");
			            }
			        } catch (NumberFormatException e) {
			            System.err.println("Error: Debes ingresar un número válido.");
			            cantDañoQuemaduraNuevo = -1; 
			        }
			    }

			    Hechizo nuevoHechizoFuego = new HechizoFuego(nombreNuevoHechizo, tipoHechizoNuevo, cantDañoNuevo, cantDañoQuemaduraNuevo);
			    sys.añadirHechizo(nuevoHechizoFuego);
			    
			    lineaArchivo = nombreNuevoHechizo + ";Fuego;" + cantDañoNuevo + ";" + cantDañoQuemaduraNuevo;
			    System.out.println("Hechizo de fuego creado correctamente!");
				break;
			case "agua":
                int cantidadHealNuevo = -1;
                int presionDelAguaNuevo = -1;

                while (cantidadHealNuevo < 0) {
                    try {
                        System.out.println("Ingrese la cantidad de heal:");
                        System.out.print("> ");
                        cantidadHealNuevo = Integer.parseInt(s.nextLine());
                        if (cantidadHealNuevo < 0) System.err.println("¡No puede ser negativo!");
                    } catch (NumberFormatException e) {
                        System.err.println("Error: Ingrese un número válido.");
                    }
                }

                while (presionDelAguaNuevo < 0) {
                    try {
                        System.out.println("Ingrese la presión del agua:");
                        System.out.print("> ");
                        presionDelAguaNuevo = Integer.parseInt(s.nextLine());
                        if (presionDelAguaNuevo < 0) System.err.println("¡No puede ser negativo!");
                    } catch (NumberFormatException e) {
                        System.err.println("Error: Ingrese un número válido.");
                    }
                }

                Hechizo nuevoHechizoAgua = new HechizoAgua(nombreNuevoHechizo, tipoHechizoNuevo, cantDañoNuevo, cantidadHealNuevo, presionDelAguaNuevo);
                sys.añadirHechizo(nuevoHechizoAgua);
                lineaArchivo = nombreNuevoHechizo + ";Agua;" + cantDañoNuevo + ";" + cantidadHealNuevo + "," + presionDelAguaNuevo;
                System.out.println("Hechizo de agua creado correctamente!");
                break;

            case "tierra":
                int cantDefensaNuevo = -1;
                while (cantDefensaNuevo < 0) {
                    try {
                        System.out.println("Ingrese la cantidad de defensa:");
                        System.out.print("> ");
                        cantDefensaNuevo = Integer.parseInt(s.nextLine());
                        if (cantDefensaNuevo < 0) System.err.println("¡No puede ser menor a 0!");
                    } catch (NumberFormatException e) {
                        System.err.println("Error: Ingrese un número válido.");
                    }
                }

                Hechizo nuevoHechizoTierra = new HechizoTierra(nombreNuevoHechizo, tipoHechizoNuevo, cantDañoNuevo, cantDefensaNuevo);
                sys.añadirHechizo(nuevoHechizoTierra);
                lineaArchivo = nombreNuevoHechizo + ";Tierra;" + cantDañoNuevo + ";" + cantDefensaNuevo;
                System.out.println("Hechizo de tierra creado correctamente!");
                break;

            case "planta":
                int duracionStunNuevo = -1;
                int cantPlantasNuevo = -1;

                while (duracionStunNuevo < 0) {
                    try {
                        System.out.println("Ingrese la duración del Stun:");
                        System.out.print("> ");
                        duracionStunNuevo = Integer.parseInt(s.nextLine());
                        if (duracionStunNuevo < 0) System.err.println("¡No puede ser negativo!");
                    } catch (NumberFormatException e) {
                        System.err.println("Error: Ingrese un número válido.");
                    }
                }

                while (cantPlantasNuevo < 0) {
                    try {
                        System.out.println("Ingrese la cantidad de plantas:");
                        System.out.print("> ");
                        cantPlantasNuevo = Integer.parseInt(s.nextLine());
                        if (cantPlantasNuevo < 0) System.err.println("¡No puede ser negativo!");
                    } catch (NumberFormatException e) {
                        System.err.println("Error: Ingrese un número válido.");
                    }
                }

                Hechizo nuevoHechizoPlanta = new HechizoPlanta(nombreNuevoHechizo, tipoHechizoNuevo, cantDañoNuevo, duracionStunNuevo, cantPlantasNuevo);
                sys.añadirHechizo(nuevoHechizoPlanta);
                lineaArchivo = nombreNuevoHechizo + ";Planta;" + cantDañoNuevo + ";" + duracionStunNuevo + "," + cantPlantasNuevo;
                System.out.println("Hechizo de planta creado correctamente!");
                break;

			default:
				System.err.println("\nIngrese un tipo entre Fuego, Agua, Tierra o Planta !!");

			}

			// Agregar hechizos al txt
			if (!lineaArchivo.isEmpty()) {
				try {

					FileWriter fw = new FileWriter("Hechizos.txt", true);
					BufferedWriter bw = new BufferedWriter(fw);

					bw.newLine();
					bw.write(lineaArchivo);
					bw.close();

				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}

	

	private static void eliminarMago() {
		System.out.println("- - - ELIMINAR MAGO - - - ");
		System.out.println(sys.mostrarMagos());

		System.out.println("Ingrese el nombre del mago para eliminar: ");
		System.out.print("> ");
		String nombreMago = s.nextLine();

		Mago magoSeleccionado = sys.buscarMago(nombreMago);
		if (magoSeleccionado == null) {
			System.err.println("El mago no existe!");
			return;
		}

		sys.eliminarMago(magoSeleccionado.getNombre());

		try {
			ArrayList<String> lineas = new ArrayList<>();
			BufferedReader br = new BufferedReader(new FileReader("Magos.txt"));
			String linea;

			while ((linea = br.readLine()) != null) {
				if (linea.trim().isEmpty())
					continue;
				String[] partes = linea.split(";");
				if (partes[0].equalsIgnoreCase(magoSeleccionado.getNombre())) {
					continue;
				}
				lineas.add(linea);
			}
			br.close();

			BufferedWriter bw = new BufferedWriter(new FileWriter("Magos.txt"));
			for (int i = 0; i < lineas.size(); i++) {
				bw.write(lineas.get(i));
				if (i < lineas.size() - 1) {
					bw.newLine();
				}
			}
			bw.close();

			System.out.println("Mago eliminado con éxito!");
		} catch (IOException e) {
		}
	}

	private static void modificarMago() throws IOException {
		// TODO Auto-generated method stub
		boolean condicion = false;
		int opcion = 0;
		System.out.println("- - - MODIFICAR MAGO - - - ");

		System.out.println(sys.mostrarMagos());

		System.out.println("Ingrese el nombre de uno de los magos para modificar: ");
		System.out.print("> ");
		String nombreMago = s.nextLine();

		Mago magoSeleccionado = sys.buscarMago(nombreMago);

		if (magoSeleccionado == null) {
			System.err.println("El mago no existe!");
			return;
		}

		while (!condicion) {
			try {
				System.out.println("\n¿Qué desea modficar? \nSeleccione una opción: ");
				System.out.println("1) Modificar nombre");
				System.out.println("2) Modificar hechizos");
				System.out.println("3) Salir");
				System.out.print("> ");
				opcion = s.nextInt();
				s.nextLine();

				if (opcion >= 1 && opcion <= 3) {
					condicion = true;
				} else {
					System.err.println("Ingrese un valor valido.");
				}
			} catch (InputMismatchException e) {
				// TODO: handle exception
				System.err.println("Ingrese un valor valido.");
				s.nextLine();
			}

		}
		if (opcion == 1) {
			modificarNombre(magoSeleccionado);
		} else if (opcion == 2) {
			modificarHechizos(magoSeleccionado);
		} else {
			System.out.println("Saliendo . . .");
		}

	}

	private static void modificarHechizos(Mago magoSeleccionado ) throws FileNotFoundException {
	    System.out.println("- - - MODIFICAR HECHIZO - - -");
	    System.out.println("Bienvenido al modificador de hechizos! \nQue hechizo desea modificar?");
	    System.out.println(sys.mostrarHechizos());

	    System.out.println("Ingrese el nombre del hechizo que desea modificar");
	    System.out.print("> ");
	    String nombreHechizo = s.nextLine();

	    Hechizo hechizoSeleccionado = sys.buscarHechizo(nombreHechizo);

	    if (hechizoSeleccionado == null) {
	        System.err.println("Ese hechizo no existe!");
	        return;
	    }

	    int opcionMod = -1;
	    while (opcionMod < 1 || opcionMod > 3) {
	        System.out.println("Que cambios deseas aplicar al hechizo " + hechizoSeleccionado.getNombre() + "?");
	        System.out.println("1) Cambiar nombre. \n2) Cambiar tipo y estadisticas. \n3) Salir.");
	        System.out.print("> ");
	        
	        try {
	            opcionMod = Integer.parseInt(s.nextLine());
	            
	            if (opcionMod == 3) {
	                System.out.println("Saliendo . . .");
	                return;
	            }
	            if (opcionMod == 1) {
	            	cambiarNombreHechizo(hechizoSeleccionado);
	                break; 
	            }
	            if (opcionMod == 2) {
	                cambiarTipoYEstadisticas(hechizoSeleccionado);
	                break;
	            }
	            if (opcionMod < 1 || opcionMod > 3) {
	                System.err.println("Opción no válida.");
	            }
	            } catch (NumberFormatException e) {
	            System.err.println("Error: Debes ingresar un número (1, 2 o 3).");
	        }
	    }
	    
	}

	private static void agregarHechizoAMago(Mago magoSeleccionado) {
		sys.mostrarHechizos();

		int cantAgregar = 0;
		boolean condicion = false;

		while (!condicion) {
			try {
				System.out.println("\nIndique la cantidad de hechizos a agregar: ");
				System.out.print("> ");
				cantAgregar = s.nextInt();
				s.nextLine();

				if (cantAgregar <= sys.cantHechizos() && cantAgregar >= 1) {
					condicion = true;
				} else if (cantAgregar < 1) {
					System.err.println("\nDebes agregar al menos 1 hechizo!");

				} else if (cantAgregar > sys.cantHechizos()) {
					System.err.println("\nSolo existen " + sys.cantHechizos() + " hechizos!");
				}

			} catch (Exception e) {
				System.err.println("\nIngrese un valor valido.");
				s.nextLine();
			}
		}

		System.out.println(sys.mostrarHechizos());

		for (int i = 0; i < cantAgregar; i++) {
			System.out.println("\nIngrese el nombre del hechizo número " + (i + 1) + ": ");
			System.out.print("> ");
			String nombreHechizo = s.nextLine();

			Hechizo hechizoSeleccionado = sys.buscarHechizo(nombreHechizo);

			if (hechizoSeleccionado == null) {
				System.err.println("¡El hechizo no existe!");
				i--;
			} else if (magoSeleccionado.getHechizos().contains(hechizoSeleccionado)) {
				System.err.println("¡Error! El mago ya posee el hechizo: " + hechizoSeleccionado.getNombre());
				i--;
			} else {
				magoSeleccionado.añadirHechizo(hechizoSeleccionado);
				System.out.println("Hechizo agregado correctamente.");
			}
		}

		try {
			ArrayList<String> lineas = new ArrayList<>();
			BufferedReader br = new BufferedReader(new FileReader("Magos.txt"));
			String linea;

			while ((linea = br.readLine()) != null) {
				String[] partes = linea.split(";");
				String nombreMago = partes[0];

				if (nombreMago.equalsIgnoreCase(magoSeleccionado.getNombre())) {
					String nuevaLinea = magoSeleccionado.getNombre() + ";";

					for (int j = 0; j < magoSeleccionado.getHechizos().size(); j++) {
						nuevaLinea += magoSeleccionado.getHechizos().get(j).getNombre();

						if (j < magoSeleccionado.getHechizos().size() - 1) {
							nuevaLinea += "|";
						}
					}
					lineas.add(nuevaLinea);
				} else {
					// Si es otro mago se guarda la linea tal cual
					lineas.add(linea);
				}
			}
			br.close();

			BufferedWriter bw = new BufferedWriter(new FileWriter("Magos.txt"));
			for (String l : lineas) {
				bw.write(l);
				bw.newLine();
			}
			bw.close();

			System.out.println("¡Cambio realizado con éxito!");

		} catch (IOException e) {
			System.err.println("Error al actualizar el archivo: " + e.getMessage());
		}
	}

	private static void eliminarHechizo(Mago magoSeleccionado) {
		if (magoSeleccionado.getHechizos().size() <= 1) {
			System.err.println("\nEl mago " + magoSeleccionado.getNombre()
					+ " solo tiene un hechizo. No puede quedarse sin ninguno.");
			return;
		}

		System.out.println("Escriba el nombre del hechizo que desea eliminar: ");
		System.out.print("> ");
		String hechizoAEliminar = s.nextLine();

		boolean eliminado = false;
		for (int i = 0; i < magoSeleccionado.getHechizos().size(); i++) {
			if (magoSeleccionado.getHechizos().get(i).getNombre().equalsIgnoreCase(hechizoAEliminar)) {
				magoSeleccionado.getHechizos().remove(i);
				eliminado = true;
				break;
			}
		}

		if (!eliminado) {
			System.err.println("El mago no posee ese hechizo.");
			return; // Salimos del método si no se elimina nada
		}

		// Eliminar del txt y actualizar
		try {
			ArrayList<String> lineas = new ArrayList<>();
			BufferedReader br = new BufferedReader(new FileReader("Magos.txt"));
			String linea;

			while ((linea = br.readLine()) != null) {
				String[] partes = linea.split(";");
				String nombreMago = partes[0];

				if (nombreMago.equalsIgnoreCase(magoSeleccionado.getNombre())) {
					String nuevaLinea = magoSeleccionado.getNombre() + ";";

					for (int j = 0; j < magoSeleccionado.getHechizos().size(); j++) {
						nuevaLinea += magoSeleccionado.getHechizos().get(j).getNombre();

						if (j < magoSeleccionado.getHechizos().size() - 1) {
							nuevaLinea += "|";
						}
					}
					lineas.add(nuevaLinea);
				} else {
					lineas.add(linea);
				}
			}
			br.close();

			BufferedWriter bw = new BufferedWriter(new FileWriter("Magos.txt"));
			for (String l : lineas) {
				bw.write(l);
				bw.newLine();
			}
			bw.close();

			System.out.println("¡Hechizo eliminado con éxito!");

		} catch (IOException e) {
			System.err.println("Error al actualizar el archivo: " + e.getMessage());
		}
	}

	private static void modificarNombre(Mago magoSeleccionado) throws IOException {

		System.out.println("Indique el nuevo nombre del mago: " + magoSeleccionado.getNombre());
		System.out.print("> ");
		String nuevoNombre = s.nextLine();

		String antiguoNombre = magoSeleccionado.getNombre();

		magoSeleccionado.setNombre(nuevoNombre);

		ArrayList<String> lineas = new ArrayList<>();

		BufferedReader br = new BufferedReader(new FileReader("Magos.txt"));
		String linea;

		while ((linea = br.readLine()) != null) {
			String[] partesc = linea.split(";");

			String nombreMago = partesc[0];

			if (nombreMago.equalsIgnoreCase(antiguoNombre)) {
				String nuevaLinea = nuevoNombre + ";";

				for (int i = 0; i < magoSeleccionado.getHechizos().size(); i++) {
					nuevaLinea += magoSeleccionado.getHechizos().get(i).getNombre();

					if (i < magoSeleccionado.getHechizos().size() - 1) {
						nuevaLinea += "|";
					}
				}
				lineas.add(nuevaLinea);
			} else {
				lineas.add(linea);
			}

		}
		br.close();

		// cambiar nombre en txt
		BufferedWriter bw = new BufferedWriter(new FileWriter("Magos.txt"));

		for (String line : lineas) {
			bw.write(line);
			bw.newLine();
		}

		bw.close();

		System.out.println("Nombre cambiado con exito!");

	}

	private static void agregarMago() throws IOException {
		// TODO Auto-generated method stub

		System.out.println("- - - NUEVO MAGO - - - ");
		System.out.println("\nIngrese el nombre del mago: ");
		System.out.print("> ");
		String nombreNuevoMago = s.nextLine();

		int cantHechizos = cantHechizos();

		// mostrar hechizos numerados para escoger
		System.out.println(" - - - HECHIZOS - - - ");
		System.out.println();

		System.out.println(sys.mostrarHechizos());

		System.out.println("\nIngrese el nombre de " + cantHechizos + " hechizos para el mago: ");

		ArrayList<Hechizo> hechizosMago = new ArrayList<>();

		for (int i = 0; i < cantHechizos; i++) {
			Hechizo hechizoN = null;

			while (hechizoN == null) {
				System.out.println("Ingrese el nombre del hechizo número " + (i + 1) + ": ");
				System.out.print("> ");
				String nombreHechizo = s.nextLine();

				hechizoN = sys.buscarHechizo(nombreHechizo);

				if (hechizoN == null) {
					System.err.println("El hechizo " + nombreHechizo + " no existe!");
				}
			}

			hechizosMago.add(hechizoN);
		}

		sys.añadirMago(nombreNuevoMago, hechizosMago);

		// guardar mago
		BufferedWriter bf = new BufferedWriter(new FileWriter("Magos.txt", true));
		String linea = nombreNuevoMago + ";";

		for (int i = 0; i < hechizosMago.size(); i++) {

			linea += hechizosMago.get(i).getNombre();

			if (i < hechizosMago.size() - 1) {
				linea += "|";
			}
		}
		bf.newLine();
		bf.write(linea);
		bf.close();

		System.out.println("Mago creado con exito !!");

	}

	private static int cantHechizos() {
		boolean condicion = false;
		int cantHechizos = 0;
		while (!condicion) {
			try {
				System.out.println("Ingrese la cantidad de hechizos que dominará el mago: ");
				System.out.print("> ");
				cantHechizos = s.nextInt();
				s.nextLine();

				if (cantHechizos <= sys.cantHechizos() && cantHechizos >= 1) {
					condicion = true;
				} else if (cantHechizos < 1) {
					System.err.println("\nUn mago debe conocer al menos 1 hechizo!!");

				} else if (cantHechizos > sys.cantHechizos()) {
					System.err.println("\nSolo existen " + sys.cantHechizos() + " hechizos!");
				}

			} catch (Exception e) {
				System.err.println("\nIngrese un valor valido.");
				s.nextLine();
			}
		}

		return cantHechizos;
	}

	private static int cargarMenuAnalista() {
		int opcion = 0;
		boolean condicion = false;

		while (!condicion) {
			try {
				System.out.println("\n - - - Menu de Analisis - - - ");
				System.out.println("\n1. Top 10 Mejores Hechizos\r\n" + "2. Top 3 Mejores Magos\r\n"
						+ "3. Mostrar todos los Hechizos\r\n" + "4. Mostrar todos los magos\r\n"
						+ "5. Mostrar todos los Hechizos junto a su puntuacion\r\n"
						+ "6. Mostrar todos los magos junto a su puntuacion" + "\n7. Salir");
				System.out.print("> ");
				opcion = s.nextInt();

				if (opcion <= 7 && opcion >= 1) {
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
		int opcion = 0;
		boolean condicion = false;

		while (!condicion) {
			try {
				System.out.println("\n - - - Menu de Administrador - - - ");
				System.out.println("\n1. Agregar Mago\r\n" + "2. Modificar Mago\r\n" + "3. Eliminar Mago\r\n"
						+ "4. Agregar Hechizo\r\n" + "5. Modificar Hechizo\r\n" + "6. Eliminar Hechizo" + "\n7. Salir");
				System.out.print("> ");
				opcion = s.nextInt();

				if (opcion <= 7 && opcion >= 1) {
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
				sys.añadirHechizo(nuevoHechizoFuego);
				break;
			case "Tierra":
				int mejoraDefensa = Integer.parseInt(partes[3]);
				Hechizo nuevoHechizoTierra = new HechizoTierra(nombre, tipo, daño, mejoraDefensa);
				sys.añadirHechizo(nuevoHechizoTierra);
				break;
			case "Planta":
				String[] partesPlanta = partes[3].split(",");
				int duracionStunt = Integer.parseInt(partesPlanta[0]);
				int cantPlantas = Integer.parseInt(partesPlanta[1]);
				Hechizo nuevoHechizoPlanta = new HechizoPlanta(nombre, tipo, daño, duracionStunt, cantPlantas);
				sys.añadirHechizo(nuevoHechizoPlanta);
				break;
			case "Agua":
				String[] partesAgua = partes[3].split(",");
				int cantidadHeal = Integer.parseInt(partesAgua[0]);
				int presionDelAgua = Integer.parseInt(partesAgua[1]);
				Hechizo nuevoHechizoAgua = new HechizoAgua(nombre, tipo, daño, cantidadHeal, presionDelAgua);
				sys.añadirHechizo(nuevoHechizoAgua);
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

			String[] partesHechizos = partes[1].split("\\|");
			ArrayList<Hechizo> hechizosMago = new ArrayList<>();

			for (String hechizo : partesHechizos) {
				Hechizo hechizoM = sys.buscarHechizo(hechizo);
				hechizosMago.add(hechizoM);
			}
			sys.añadirMago(nombre, hechizosMago);

		}
	}

}
