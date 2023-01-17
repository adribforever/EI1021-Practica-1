package viajes;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Scanner;


public class ViajesLocal {


    /**
     * Muestra el menu de opciones y lee repetidamente de teclado hasta obtener una opcion valida
     *
     * @param teclado stream para leer la opción elegida de teclado
     * @return opción elegida
     */
    public static int menu(Scanner teclado) {
        int opcion;
        System.out.println("\n\n");
        System.out.println("=====================================================");
        System.out.println("============            MENU        =================");
        System.out.println("=====================================================");
        System.out.println("0. Salir");
        System.out.println("1. Consultar viajes con un origen dado");
        System.out.println("2. Reservar un viaje");
        System.out.println("3. Anular una reserva");
        System.out.println("4. Ofertar un viaje");
        System.out.println("5. Borrar un viaje");
        do {
            System.out.print("\nElige una opcion (0..5): ");
            opcion = teclado.nextInt();
        } while ((opcion < 0) || (opcion > 5));
        teclado.nextLine(); // Elimina retorno de carro del buffer de entrada
        return opcion;
    }


    /**
     * Programa principal. Muestra el menú repetidamente y atiende las peticiones del cliente.
     *
     * @param args no se usan argumentos de entrada al programa principal
     */
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        // Crea un gestor de viajes
        GestorViajes gestor = new GestorViajes();

        System.out.print("Introduce tu codigo de cliente: ");
        String codcli = teclado.nextLine();

        int opcion;

        Scanner scanner = new Scanner(System.in); // Creo Scanner

        do {
            opcion = menu(teclado);
            switch (opcion) {
                case 0: // Guardar los datos en el fichero y salir del programa
                    gestor.guardaDatos();
                    break;

                case 1: { // Consultar viajes con un origen dado
                    System.out.println("¿Cual es el origen?"); // Preguntamos el origen
                    String codigoDado = scanner.nextLine(); // Lo guardamos en una variable
                    if (gestor.consultaViajes(codigoDado).isEmpty()) { // si no existe
                        System.out.println("No existe ese origen");
                    } else { // si  existe en el JSON
                        System.out.println("La siguiente informacion corresponde con los viajes con origen " + codigoDado +" :");
                        System.out.println(gestor.consultaViajes(codigoDado));
                    }
                    break;
                }

                case 2: { // Reservar un viaje
                    System.out.println("Introduce el codigo del viaje:");
                    String codviaje = scanner.nextLine();
                    if (gestor.reservaViaje(codviaje, codcli).isEmpty()) { // Si el valor de retorno es vacio es que ha habido algun error
                        System.out.println("EL viaje no ha podido reservarse");
                    } else {
                        System.out.println("Viaje reservado con exito");
                    }

                    break;
                }

                case 3: { // Anular una reserva

                    System.out.println("Introduce el código del viaje que quieres anular"); // Preguntamos el que viaje que quiere anular
                    String codviaje = scanner.nextLine(); // Lo guardamos en una variable

                    if (gestor.anulaReserva(codviaje, codcli).isEmpty()) { // Si el valor de retorno es vacio es que ha habido algun error
                        System.out.println("El viaje no puede anularse");
                    } else {
                        System.out.println("Viaje anulado con exito");

                    }
                    break;
                }
                case 4: { // Ofertar un viaje
                    System.out.println("Introduce el origen:"); //guarda origen
                    String origen = scanner.nextLine();
                    System.out.println("Introduce el destino:"); //guarda destino
                    String destino = scanner.nextLine();
                    System.out.println("Introduce la fecha en formato: dd-MM-yyyy:"); //guarda fecha
                    String fecha = scanner.nextLine();
                    System.out.println("Introduce el precio del viaje:"); //guarda precio
                    long precio = Long.parseLong(scanner.nextLine());
                    System.out.println("Introduce el número de plazas:"); //guarda numplazas
                    long numplazas = Long.parseLong(scanner.nextLine());
                    if (gestor.ofertaViaje(codcli, origen, destino, fecha, precio, numplazas).isEmpty()) { // Si el valor de retorno es vacio es que ha habido algun error
                        System.out.println("El viaje no ha podido crearse");
                    } else {
                        System.out.println("El siguiente viaje se ha ofertado con exito:");
                        System.out.println(gestor.ofertaViaje(codcli,origen,destino,fecha,precio,numplazas));
                    }
                    break;
                }

                case 5: { // Borrar un viaje ofertado
                    System.out.println("Introduce el código del viaje que quieres anular:"); // Preguntamos el que viaje que quiere anular
                    String codviaje = scanner.nextLine(); // Lo guardamos en una variable
                    if (gestor.borraViaje(codviaje, codcli).isEmpty()) {// Si el valor de retorno es vacio es que ha habido algun error
                        System.out.println("No se ha podido borrar el viaje ofertado");
                    } else {
                        System.out.println("Viaje borrado con éxito");
                    }
                }
                break;

            } // fin switch

        } while (opcion != 0);

    } // fin de main

} // fin class
