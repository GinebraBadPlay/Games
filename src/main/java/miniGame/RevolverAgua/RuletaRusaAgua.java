package miniGame.RevolverAgua;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class RevolverAgua {
    private int posicionActual;
    private int posicionAgua;

    public void llenarRevolver() {
        Random random = new Random();
        posicionActual = random.nextInt(6); // 6 posiciones en el tambor
        posicionAgua = random.nextInt(6);
    }

    public boolean mojar() {
        return posicionActual == posicionAgua;
    }

    public void siguienteChorro() {
        posicionActual = (posicionActual + 1) % 6; // Avanza al siguiente tambor
    }

    @Override
    public String toString() {
        return "Posición actual: " + posicionActual + ", Posición agua: " + posicionAgua;
    }
}

class Jugador {
    private int id; // getter
    private String nombre;
    private boolean mojado;

    public Jugador(int id) {
        this.setId(id);
        this.nombre = "Jugador " + id;
        this.mojado = false;
    }

    public boolean disparo(RevolverAgua revolver) {
        System.out.println(nombre + " se apunta y aprieta el gatillo.");
        if (revolver.mojar()) {
            System.out.println(nombre + " se moja.");
            mojado = true;
            return true;
        } else {
            System.out.println("¡Click! " + nombre + " se salvó.");
            revolver.siguienteChorro();
            return false;
        }
    }

    public boolean estaMojado() {
        return mojado;
    }

    @Override
    public String toString() {
        return nombre;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

class Juego {
    private ArrayList<Jugador> jugadores;
    private RevolverAgua revolver;

    public void llenarJuego(ArrayList<Jugador> jugadores, RevolverAgua revolver) {
        this.jugadores = jugadores;
        this.revolver = revolver;
    }

    public void ronda() {
        int jugadorActual = 0;
        boolean alguienSeMojo = false;

        while (!alguienSeMojo) {
            Jugador jugador = jugadores.get(jugadorActual);
            alguienSeMojo = jugador.disparo(revolver);

            jugadorActual = (jugadorActual + 1) % jugadores.size();
        }

        for (Jugador jugador : jugadores) {
            if (jugador.estaMojado()) {
                System.out.println("¡" + jugador + " se mojó y perdió!");
            }
        }
    }
}

public class RuletaRusaAgua {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Pedir al usuario el número de jugadores
        System.out.print("Ingrese el número de jugadores (entre 1 y 6): ");
        int numJugadores = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        if (numJugadores < 1 || numJugadores > 6) {
            System.out.println("Número de jugadores no válido. Se establecerán 6 jugadores por defecto.");
            numJugadores = 6;
        }

        // Crear un revólver de agua y llenarlo
        RevolverAgua revolver = new RevolverAgua();
        revolver.llenarRevolver();

        // Crear un conjunto de jugadores
        ArrayList<Jugador> jugadores = new ArrayList<>();

        for (int i = 1; i <= numJugadores; i++) {
            Jugador jugador = new Jugador(i);
            jugadores.add(jugador);
        }

        // Crear el juego y llenarlo con los jugadores y el revólver
        Juego juego = new Juego();
        juego.llenarJuego(jugadores, revolver);

        // Comenzar la ronda
        juego.ronda();

        // Cerrar el scanner
        scanner.close();
    }
}
