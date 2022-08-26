package dux_java;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Dux_java {

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in).useDelimiter("\n");

        System.out.println("***PARTIDO DE TENIS***");
        System.out.println("");
        String rta = "";

        do {
            System.out.println("INGRESE EL NOMBRE DEL TORNEO:");
            String nombreTorneo = read.next();

            System.out.println("INGRESE EL NOMBRE DEL JUGADOR 1:");
            String jug1 = read.next();
            Jugador jugador1 = new Jugador(jug1);
            System.out.println("INGRESE LA PROBABILIDAD DE GANAR (1-100)");
            int prob1 = read.nextInt();
            System.out.println("");
            System.out.println("INGRESE EL NOMBRE DEL JUGADOR 2:");
            String jug2 = read.next();
            Jugador jugador2 = new Jugador(jug2);
            System.out.println("INGRESE LA PROBABILIDAD DE GANAR (1-100)");
            int prob2 = read.nextInt();
            System.out.println("");
            System.out.println("INGRESE AHORA LA CANTIDAD DE SETS (3 O 5)");
            float cantidadSet = read.nextFloat();
            System.out.println("___________________________________________________________");

            desarrolloSet(cantidadSet, jugador1, jugador2, nombreTorneo, prob1, prob2);

            System.out.println("¿Desea continuar por la revancha?");
            rta = read.next();
        } while (rta.equalsIgnoreCase("si"));
    }
//-----------------------------------------------------------------------------------------------------------------------

    static boolean desarrolloJuego(Jugador j1, Jugador j2, int prob1, int prob2) {
        Random aleatorioA = new Random();
        Random aleatorioB = new Random();
        j1.setMarcador(0);
        j2.setMarcador(0);
        j1.setPuntos(0);
        j2.setPuntos(0);
        boolean ganador = true;
        boolean finJuego = false;
        ArrayList<Integer> marcadorJug1 = new ArrayList();
        ArrayList<Integer> marcadorJug2 = new ArrayList();
        int jugadorAleatorio;

        while (finJuego != true) {
            int probJug1 = aleatorioA.nextInt(prob1); 
            int probJug2 = aleatorioB.nextInt(prob2); 
            
            if (probJug1 > probJug2) {
                jugadorAleatorio = 0;
            } else {
                jugadorAleatorio = 1;
            }

            //  int jugadorAleatorio = saque.nextInt(2);
            String marcadorPuntos = "";

            if (jugadorAleatorio == 0) {
                System.out.println("");

                //if (!finJuego && !ganador) {
                System.out.println("Saque: " + j1.getNombre());
                // }

                j1.setMarcador(j1.getMarcador() + 1);
                marcadorJug1.add(j1.getMarcador());

                if ((j1.getMarcador() > 3) && ((j1.getMarcador() - j2.getMarcador()) > 1)) {
                    System.out.println("----- GANADOR/A DEL PUNTO: " + j1.getNombre() + " -----");

                    j1.setMarcador(0);
                    j2.setMarcador(0);
                    ganador = true;
                    finJuego = true;
                }
            }

            if (jugadorAleatorio == 1) {
                System.out.println("");

                //if (!finJuego && !ganador) {
                System.out.println("Saque: " + j2.getNombre());
                //}

                j2.setMarcador(j2.getMarcador() + 1);
                marcadorJug2.add(j2.getMarcador());

                if ((j2.getMarcador() > 3) && ((j2.getMarcador() - j1.getMarcador()) > 1)) {
                    System.out.println("----- GANADOR/A DEL PUNTO: " + j2.getNombre() + " -----");

                    j1.setMarcador(0);
                    j2.setMarcador(0);
                    ganador = false;
                    finJuego = true;
                }
            }

            if (j1.getMarcador() == 1) {
                j1.setPuntos(15);
                marcadorPuntos = j1.getPuntos() + " - " + j2.getPuntos();
            }

            if (j1.getMarcador() == 2) {
                j1.setPuntos(30);
                marcadorPuntos = j1.getPuntos() + " - " + j2.getPuntos();
            }

            if (j1.getMarcador() == 3) {
                j1.setPuntos(40);
                marcadorPuntos = j1.getPuntos() + " - " + j2.getPuntos();
            }

            if (j2.getMarcador() == 1) {
                j2.setPuntos(15);
                marcadorPuntos = j1.getPuntos() + " - " + j2.getPuntos();
            }

            if (j2.getMarcador() == 2) {
                j2.setPuntos(30);
                marcadorPuntos = j1.getPuntos() + " - " + j2.getPuntos();
            }

            if (j2.getMarcador() == 3) {
                j2.setPuntos(40);
                marcadorPuntos = j1.getPuntos() + " - " + j2.getPuntos();
            }

            if (j1.getMarcador() > 3 && j2.getMarcador() > 3 && j1.getMarcador() == j2.getMarcador()) {
                marcadorPuntos = "Deuce";
            }

            if (j1.getMarcador() > 3 && j1.getMarcador() > j2.getMarcador()) {
                j1.setPuntos(0);
                j2.setPuntos(0);
                marcadorPuntos = "Vantaja para: " + j1.getNombre();
            }

            if (j2.getMarcador() > 3 && j2.getMarcador() > j1.getMarcador()) {
                j1.setPuntos(0);
                j2.setPuntos(0);
                marcadorPuntos = "Vantaja para: " + j2.getNombre();
            }

            System.out.println(marcadorPuntos);
        }
        return ganador;
    }

//---------------------------------------------------------------------------------------------------------------------------
    static void desarrolloSet(float sets, Jugador j1, Jugador j2, String nombreTorneo, int prob1, int prob2) {

        boolean finPartido = false;
        float set1 = (sets / 2);
        double set = (set1 + 0.5);

        while (!finPartido) {

            boolean resultadoJuego = desarrolloJuego(j1, j2, prob1, prob2);
            if (resultadoJuego) {
                j1.setJuegos(j1.getJuegos() + 1);
            } else {
                j2.setJuegos(j2.getJuegos() + 1);
            }

            if (j1.getJuegos() == 6 && j2.getJuegos() == 6) {
                System.out.println("Tie braak");
            }

            if (j1.getJuegos() >= 6 && (j1.getJuegos() - j2.getJuegos()) > 1 || j1.getJuegos() == 7 && j2.getJuegos() == 6) {
                j1.setSet(j1.getSet() + 1);
                System.out.println("--------------- " + j1.getNombre() + " GANA EL SET POR " + j1.getJuegos() + " A " + j2.getJuegos() + " ---------------");
                j1.setJuegos(0);
                j2.setJuegos(0);

                if (j1.getSet() == set && j1.getSet() > j2.getSet()) {
                    System.out.println("_______________________________________________________________________");
                    System.out.println("*** TORNEO " + nombreTorneo + " FINALIZADO ***");
                    System.out.println("////////// " + j1.getNombre() + " GANA EL PARTIDO POR " + j1.getSet() + " SETS A " + j2.getSet() + " //////////");
                    System.out.println("_______________________________________________________________________");
                    finPartido = true;

                }
            }

            if (j2.getJuegos() >= 6 && (j2.getJuegos() - j1.getJuegos()) > 1 || j2.getJuegos() == 7 && j1.getJuegos() == 6) {
                j2.setSet(j2.getSet() + 1);
                System.out.println("--------------- " + j2.getNombre() + " GANA EL SET POR " + j2.getJuegos() + " A " + j1.getJuegos() + " ---------------");
                j1.setJuegos(0);
                j2.setJuegos(0);

                if (j2.getSet() == set && j2.getSet() > j1.getSet()) {
                    System.out.println("_______________________________________________________________________");
                    System.out.println("*** TORNEO " + nombreTorneo + " FINALIZADO ***");
                    System.out.println("////////// " + j2.getNombre() + " GANA EL PARTIDO POR " + j2.getSet() + " SETS A " + j1.getSet() + " //////////");
                    System.out.println("_______________________________________________________________________");
                    finPartido = true;
                }
            }

        }

    }

}
