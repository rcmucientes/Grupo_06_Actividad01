package Main;

import java.util.Scanner;

public class Menu {
	
	public static int pintarMenu() {

		int opcion = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("");
		System.out.println("--GESTIÓN DE ALMACEN--");
		System.out.println("1.- Añadir nuevo artículo");
		System.out.println("2.- Borrar artículo");
		System.out.println("3.- Consultar artículo por id");
		System.out.println("4.- Listar todos los artículos");
		System.out.println("5.- Exportar a archivo CSV");
		System.out.println("6.- Grabar y salir del programa");
		System.out.println("Teclea una opción: ");
		opcion = sc.nextInt();
		while (opcion <1 || opcion>6){
			System.out.println("del 1 al 6");
			opcion = sc.nextInt();
			}
		
		return opcion;
		
		}
	
	
}



