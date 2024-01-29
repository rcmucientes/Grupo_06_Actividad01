package Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
		
	public static final String fichero = "articulos.dat";
	private static ArrayList<Articulo> listaArticulos;
	
		public static void main(String[] args) {
			
			File file = new File(fichero);
			
			listaArticulos = new ArrayList<Articulo>();
			
			System.out.println("Bienvenido a programa de gestión de stock");
			System.out.println("");
			if (file.exists()) {
					System.out.println("Existe un fichero con información");
				
				try(FileInputStream fis = new FileInputStream("articulos.dat");
						ObjectInputStream ois = new ObjectInputStream(fis);) {
					
					listaArticulos = (ArrayList<Articulo>)ois.readObject();
					
					System.out.println(""
							+ "Fichero cargado");
					
				} catch (FileNotFoundException e) {
					System.out.println("no hay archivo");
					System.out.println("");
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}	
			else
				System.out.println("NO existen ficheros");
			
			int opcion = 0;
			do {
				opcion= Menu.pintarMenu();
				switch (opcion) {
					case 1:
						anadirArticulo();
					break;
					case 2:
						borrarPorId();
					break;
					case 3:
						consultaPorId();
					break;
					case 4:
						listar();
					break;	
					case 5:
						exportarCsv();	
					break;
					case 6:
						escribirFichero();
					break;
				}
			}while (opcion!=6);
			
			System.out.println("Fin del programa");
		}

		public static void anadirArticulo() {
			Articulo articulo = new Articulo();
			Scanner sc = new Scanner(System.in);
			boolean e = false;

				do {
					System.out.println("Indique el id del nuevo artículo:");
					int id = sc.nextInt();
					Articulo art = new Articulo();
					art.setId(id);
					int posicion = listaArticulos.indexOf(art);
					if (posicion == -1) {
						articulo.setId(id);
						sc.nextLine();
						e=true;
					}
					else
						System.out.println("El id ya existe, por favor, asigne uno nuevo");
						System.out.println("");
				}while(!e);
				
				System.out.println("Indique el nombre del nuevo artículo:");
				String nombre = sc.nextLine();
				articulo.setNombre(nombre);
				System.out.println("Indique la descripción del nuevo artículo:");
				String descripcion = sc.nextLine();
				articulo.setDescripcion(descripcion);
				System.out.println("Indique el stock del nuevo artículo:");
				int stock = sc.nextInt();
				articulo.setStock(stock);
				System.out.println("Indique el precio del nuevo artículo:");
				float precio = sc.nextFloat();
				articulo.setPrecio(precio);
			
				listaArticulos.add(articulo);
				System.out.println("El artículo se ha añadido");
			
		}	
		
		public static void borrarPorId() {
			Scanner sc = new Scanner(System.in);
			System.out.println("Indique el id del artículo: ");
			
			int id = sc.nextInt();
		
			Articulo art = new Articulo();
			art.setId(id);
			int posicion = listaArticulos.indexOf(art);
			
			if (posicion == -1)
				System.out.println("No se ha encontrado ningún artículo con ese id");
			else {
				listaArticulos.remove(posicion);
				System.out.println("El artículo ha sido eleminado");
			}
		}
		
		public static void listar(){
			
			boolean contenido = listaArticulos.isEmpty();
			
			if (contenido == true)
				System.out.println("El almacén está vacío");
			else
				for(Articulo p : listaArticulos){
					System.out.println(p);
			}		
		}
		
		public static void consultaPorId(){
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Indique el id del artículo: ");
			
			int id = sc.nextInt();
		
			Articulo art = new Articulo();
			art.setId(id);
			int posicion = listaArticulos.indexOf(art);
			
			if (posicion == -1)
				System.out.println("No se ha encontrado ningún artículo con ese id");
			else
				System.out.println(listaArticulos.get(posicion));
		}
		
		public static void exportarCsv() {
			
			File f = new File("articulos.csv");
			System.out.println("El fichero CSV ha sido creado");
			try (FileWriter fw = new FileWriter(f);){
				for(Articulo a : listaArticulos) {
					fw.write(a.toCSV()+"\n");
				}
			} catch (Exception e) {
			}
			System.out.println("");
			System.out.println("Se ha grabado la información en el fichero CSV");
		}
		
		public static  void escribirFichero(){
			
			try (FileOutputStream fos = new FileOutputStream("articulos.dat");
				 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
				
				oos.writeObject(listaArticulos);
				System.out.println("Archivo grabado");
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}

}
