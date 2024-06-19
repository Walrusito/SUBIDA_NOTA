package proyeto_comida_rapida;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase principal de la aplicación de comida rápida.
 * Proporciona un menú para iniciar sesión, crear una cuenta y realizar pedidos.
 */
public class App {
	static boolean salir = false;
	private static ClientesDAO clientesDAO = new ClientesDAO();

	/**
	 * Método principal que ejecuta la aplicación de comida rápida.
	 *
	 * @param args Argumentos de línea de comandos (no utilizados).
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Bienvenido a la aplicación de comida rápida");

		while (!salir) {
			System.out.println("\n1. Iniciar sesión");
			System.out.println("2. Crear cuenta");
			System.out.println("3. Salir");
			System.out.print("Seleccione una opción: ");

			String opcion = scanner.nextLine();

			switch (opcion) {
			case "1":
				Clientes cliente = iniciarSesion(scanner);
				if (cliente != null) {
					System.out.println("Inicio de sesión exitoso. Bienvenido, " + cliente.getNombre());
					hacerPedido(scanner, cliente);
				} else {
					System.out.println("Nombre de usuario o contraseña incorrectos.");
				}
				break;
			case "2":
				crearCuenta(scanner);
				break;
			case "3":
				salir = true;
				System.out.println("Gracias por utilizar nuestra aplicación. ¡Hasta luego!");
				break;
			default:
				System.out.println("Opción no válida. Intente nuevamente.");
			}
		}

		scanner.close();
	}

	/**
	 * Solicita al usuario que ingrese su información para crear una nueva cuenta.
	 * 
	 * @param scanner Objeto Scanner para leer la entrada del usuario.
	 */
	private static void crearCuenta(Scanner scanner) {
	    System.out.print("Ingrese su nombre: ");
	    String nombre = scanner.nextLine();
	    System.out.print("Ingrese su email: ");
	    String email = scanner.nextLine();
	    System.out.print("Introduzca la contraseña: ");
	    String password = scanner.nextLine();
	    
	    // Crear el objeto Clientes con los datos recopilados
	    Clientes nuevoCliente = new Clientes(nombre, email, password);

	    // Llamar al método insertarCliente de ClientesDAO
	    clientesDAO.insertarCliente(nuevoCliente);
	}

	/**
	 * Solicita al usuario que ingrese su email y contraseña para iniciar sesión.
	 *
	 * @param scanner Objeto Scanner para leer la entrada del usuario.
	 * @return Objeto Clientes si las credenciales son correctas, null en caso contrario.
	 */
	private static Clientes iniciarSesion(Scanner scanner) {
		System.out.print("Ingrese su email: ");
		String email = scanner.nextLine();
		System.out.print("Ingrese su contraseña: ");
		String password = scanner.nextLine();

		return clientesDAO.buscarPorUsuarioYContraseña(email, password);
	}

	/**
	 * Permite al cliente realizar un pedido, seleccionando productos y calculando el total.
	 *
	 * @param scanner Objeto Scanner para leer la entrada del usuario.
	 * @param cliente Objeto Clientes que representa al cliente que está realizando el pedido.
	 * @return Objeto Pedidos con los datos del pedido realizado.
	 */
	private static Pedidos hacerPedido(Scanner scanner, Clientes cliente) {
		ArrayList<Producto> productos = new ArrayList<>();
		double total = 0.0;
		int cantidadHamburguesa = 0;
		int cantidadPatatasFritas = 0;
		int cantidadRefresco = 0;

		boolean seguirAgregando = true;
		while (seguirAgregando) {
			System.out.println("Por favor, elija su comida:");
			System.out.println("1. Hamburguesa");
			System.out.println("2. Patatas Fritas");
			System.out.println("3. Refresco");
			System.out.print("Opción: ");

			int opcion;
			try {
				opcion = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida. Intente nuevamente.");
				continue;
			}

			Producto producto = null;
			switch (opcion) {
			case 1:
				producto = new Hamburguesa();
				((Hamburguesa) producto).elegirTipo();
				HamburguesaDAO.insertarHamburguesa((Hamburguesa) producto);
				cantidadHamburguesa++;
				break;
			case 2:
				producto = new Patatas();
				((Patatas) producto).elegirTipo();
				PatatasDAO.insertarPatatas((Patatas) producto);
				cantidadPatatasFritas++;
				break;
			case 3:
				producto = new Refrescos();
				((Refrescos) producto).elegirTipo();
				RefrescosDAO.insertarRefresco((Refrescos) producto);
				cantidadRefresco++;
				break;
			default:
				System.out.println("Opción no válida.");
			}

			if (producto != null) {
				productos.add(producto);
				total += producto.getPrecio();
			}

			System.out.print("¿Desea agregar otro producto? (si/no): ");
			String respuesta = scanner.nextLine().trim().toLowerCase();
			seguirAgregando = respuesta.equals("si");
		}

		// Crear el objeto Pedidos con los datos recopilados
		Pedidos pedido = new Pedidos(cliente.getIdCliente(), total, productos, cantidadHamburguesa,
				cantidadPatatasFritas, cantidadRefresco);
		System.out.println(pedido.toString());

		return pedido;
	}

}