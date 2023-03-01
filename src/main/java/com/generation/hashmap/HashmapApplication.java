package com.generation.hashmap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class HashmapApplication {
// Ejercicio
// vamos a pedir al usuario que ingrese la cantidad alumnos a los cuales les asignará una nota, 
//luego de que ingrese la catidad de notas de cada alumnos, se va a desplegar un menú.
// las opciones del menú serán la siguentes:
// 1.- mostrar el promedio de notas
// 2.- mostrar si la nota es aprobatoria o reprobatoria
// 3.- mostrar si la nota está por sobre o por debajo del promedio del curso
/* - Funcion para ingreso de cantidad alumnos que sera el tamaño del array y sus notas
 * - Funcion error de ingreso que resiva un string que se le sumara a "Error de ingreso"
 * - Funcion para ingresar notas a un array de 3
 * - Funcion calcular promedio donde ingresan las notas en parametros en forma de array
 * y se calculara promedio y se retorna
 */
	//____________________________________________________________________________________________________________________________________________________________________//
	//          [FUNCION MOSTRAR ERROR]

	public static void mostrarError(String texto){
		System.out.println("[Error de ingreso]-" + texto);
	}
	//____________________________________________________________________________________________________________________________________________________________________//

	//____________________________________________________________________________________________________________________________________________________________________//
	//          [FUNCION INGRESAR CANTIDAD ALUMNOS]

	public static int ingresarCantidadAlumnos(){
		Scanner input = new Scanner(System.in);
		int cantidadAlumnos;
		do {
			System.out.println("Ingrese cantidad de alumnos: ");
			cantidadAlumnos = input.nextInt();
			if (cantidadAlumnos <= 0) {
				mostrarError("Cantidad de alumnos debe ser mayor a 0");
			}
		} while (cantidadAlumnos <= 0);
		return cantidadAlumnos;
	}
	//____________________________________________________________________________________________________________________________________________________________________//

	//____________________________________________________________________________________________________________________________________________________________________//
	//          [FUNCION INGRESAR CANTIDAD NOTAS]

	public static int ingresarCantidadNotas(){
		Scanner input = new Scanner(System.in);
		int cantidadNotas;
		do {
			System.out.println("Ingrese cantidad de notas: ");
			cantidadNotas = input.nextInt();
			if (cantidadNotas <= 0) {
				mostrarError("Cantidad de notas debe ser mayor a 0");
			}
		} while (cantidadNotas <= 0);
		return cantidadNotas;
	}
	//____________________________________________________________________________________________________________________________________________________________________//

	//____________________________________________________________________________________________________________________________________________________________________//
	//          [FUNCION INGRESAR DATOS ALUMNOS]

	public static HashMap <String, ArrayList <Double>> ingresarDatosAlumnos(int cantidadAlumnos, int cantidadNotas){
		HashMap <String, ArrayList <Double>> datosAlumno = new HashMap<String, ArrayList<Double>>();
		ArrayList <Double> notasAlumno = new ArrayList<Double>();
		for (int i = 0; i < cantidadAlumnos; i++) {
			//ArrayList <Double> notasAlumno = new ArrayList<Double>();
			System.out.println("Ingrese el nombre del alumno " + (i + 1));
			Scanner input = new Scanner(System.in);
			String nombreAlumno = input.nextLine();
			//datosAlumno.put(nombreAlumno, notasAlumno);//nuevo
			//notasAlumno.clone();//nuevo
			System.out.println("Ingrese las notas de " + nombreAlumno);
			for (int e = 0; e < cantidadNotas; e++) {
				Double nota;
				do {
					System.out.println("Ingrese la nota N°" + (e + 1));
					nota = input.nextDouble();
					if (nota < 1 || nota > 7) {
						mostrarError("La nota debe ser entre 1 y 7");
					}
				} while (nota < 1 || nota > 7);
				notasAlumno.add(nota);
				//datosAlumno.get(nombreAlumno).add(nota);//nuevo
			}
			//agregar las notas que contiene el array global actualmente a su copia
			ArrayList<Double> copiaNotasAlumno = (ArrayList<Double>) notasAlumno.clone();
			datosAlumno.put(nombreAlumno, copiaNotasAlumno);//nuevo
			notasAlumno.clear();
		}
		return datosAlumno;
	}
	//____________________________________________________________________________________________________________________________________________________________________//

	//____________________________________________________________________________________________________________________________________________________________________//
	//          [FUNCION CALCULAR PROMEDIO]

	public static Double calcularPromedio(ArrayList <Double> notasAlumno){
		Double sumaNotas = 0.0;
		Double promedioAlumno = 0.0;
		for (int e = 0; e < notasAlumno.size(); e++) {
			sumaNotas += notasAlumno.get(e);
		}
		promedioAlumno = sumaNotas / notasAlumno.size();
		return promedioAlumno;
	}
	//____________________________________________________________________________________________________________________________________________________________________//

	//____________________________________________________________________________________________________________________________________________________________________//
	//          [FUNCION MOSTRAR APROBAR O REPROBAR]

	public static void mostrarAprobarOReprobar(ArrayList <Double> notasAlumno, Double notaAprobatoria, String alumno){
		Double promedioAlumno = calcularPromedio(notasAlumno);
		if (promedioAlumno < notaAprobatoria) {
			System.out.println(alumno + " [REPROBÓ] con un promedio de " + promedioAlumno);
		} else {
			System.out.println(alumno + " [APROBÓ] con un promedio de " + promedioAlumno);
		}
	}
	//____________________________________________________________________________________________________________________________________________________________________//

	//____________________________________________________________________________________________________________________________________________________________________//
	//          [FUNCION MOSTRAR COMPARACION PROMEDIOS]

	public static void mostrarComparacionPromedios(ArrayList <Double> notasAlumno, Double promedioGeneral, String alumno){
		Double promedioAlumno = calcularPromedio(notasAlumno);
		System.out.println("| Promedio " + alumno + " [" + promedioAlumno + "] | Promedio General [" + promedioGeneral + "]");
		if (promedioAlumno > promedioGeneral) {
			System.out.println("El promedio de " + alumno + " es mayor al promedio general");
		} else if (promedioAlumno < promedioGeneral) {
			System.out.println("El promedio de " + alumno + " es menor al promedio general");
		} else {
			System.out.println("El promedio de " + alumno + " es igual al promedio general");
		}
	}
	//____________________________________________________________________________________________________________________________________________________________________//

	//____________________________________________________________________________________________________________________________________________________________________//
	//          [FUNCION CONFIRMAR SALIDA]

	public static Boolean confirmarSalida(){
		System.out.println("\n----------------------------------");
		System.out.println("        [Confirme salida]\n");
		System.out.println("      Salir[1]    Volver[2]");
		System.out.println("----------------------------------");
		Scanner input = new Scanner(System.in);
		int confirmacionSalida = input.nextInt();
		if (confirmacionSalida == 1) {
			System.out.println("Hasta luego");
			return true;
		} else {
			return false;
		}
	}
	//____________________________________________________________________________________________________________________________________________________________________//
	
	//____________________________________________________________________________________________________________________________________________________________________//
	//          [MAIN]
	public static void main(String[] args) {
		int cantidadAlumnos = ingresarCantidadAlumnos();
		int cantidadNotas = ingresarCantidadNotas();
		HashMap <String, ArrayList <Double>> datosAlumno = ingresarDatosAlumnos(cantidadAlumnos, cantidadNotas);
		int opcionSeleccionada;
		boolean puedeSalir = false; 
		do {
			System.out.println("\n---------------------[MENU PRINCIPAL]---------------------");
			System.out.println("[Opcion 1] : Promedio alumnos");
			System.out.println("[Opcion 2] : Alumnos aprobados y reprobados");
			System.out.println("[Opcion 3] : Alumnos sobre, igual y menor al promedio");
			System.out.println("[Opcion 4] : Salir");
			System.out.println("----------------------------------------------------------\n");
			Scanner input = new Scanner(System.in);
			System.out.println("Ingrese el numero de su opción: ");
			opcionSeleccionada = input.nextInt();
			System.out.println("\n[Eligió Opcion " + opcionSeleccionada + "]\n");
			if (opcionSeleccionada == 1) {
				for(String i : datosAlumno.keySet()){
					Double promedioAlumno = calcularPromedio(datosAlumno.get(i));
					System.out.println(i + " | Notas: " + datosAlumno.get(i) + " | Promedio: " + promedioAlumno);
					//System.out.println("Promedio " + i + " = " + promedioAlumno);
				}
			} else if (opcionSeleccionada == 2) {
				System.out.println("Eligio opcion 2");
				Double notaAprobatoria = 4.0;
				for(String i : datosAlumno.keySet()){
					mostrarAprobarOReprobar(datosAlumno.get(i), notaAprobatoria, i);
				}
			} else if (opcionSeleccionada == 3) {
				System.out.println("Eligio opcion 3");
				Double sumaPromedios = 0.0;
				Double promedioGeneral = 0.0;
				for(String i : datosAlumno.keySet()){
					sumaPromedios += calcularPromedio(datosAlumno.get(i));
				}
				promedioGeneral = sumaPromedios / datosAlumno.size();
				for(String i : datosAlumno.keySet()){
					mostrarComparacionPromedios(datosAlumno.get(i), promedioGeneral, i);
				}
			} else if (opcionSeleccionada == 4) {
				puedeSalir = confirmarSalida();
				break;
			} else {
				mostrarError("Debe ingresar la opcion [1],[2],[3] o [4]");
			}
			puedeSalir = confirmarSalida();
		} while (puedeSalir == false);
	}
	//____________________________________________________________________________________________________________________________________________________________________//
}
