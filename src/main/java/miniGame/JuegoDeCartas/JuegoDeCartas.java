package miniGame.JuegoDeCartas;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// Enumeración para representar los palos de las cartas
enum Palo {
    ESPADAS, BASTOS, OROS, COPAS
}

// Clase para representar una carta
class Carta {
    private int numero;
    private Palo palo;

    public Carta(int numero, Palo palo) {
        this.numero = numero;
        this.palo = palo;
    }

    @Override
    public String toString() {
        return numero + " de " + palo;
    }
}

// Clase para representar una baraja de cartas españolas
class Baraja {
    private List<Carta> cartas;

    public Baraja() {
        cartas = new ArrayList<>();
        for (Palo palo : Palo.values()) {
            for (int numero = 1; numero <= 12; numero++) {
                if (numero != 8 && numero != 9) {
                    cartas.add(new Carta(numero, palo));
                }
            }
        }
    }

    public void barajar() {
        Collections.shuffle(cartas);
    }

    public Carta siguienteCarta() {
        if (cartas.isEmpty()) {
            System.out.println("No hay más cartas en la baraja.");
            return null;
        }
        return cartas.remove(0);
    }

    public int cartasDisponibles() {
        return cartas.size();
    }

    public List<Carta> darCartas(int cantidad) {
        if (cantidad > cartas.size()) {
            System.out.println("No hay suficientes cartas para dar.");
            return new ArrayList<>();
        }

        List<Carta> cartasADar = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            cartasADar.add(cartas.remove(0));
        }
        return cartasADar;
    }

    public List<Carta> cartasMonton() {
        if (cartas.isEmpty()) {
            System.out.println("No se han sacado cartas todavía.");
            return new ArrayList<>();
        }
        return cartas;
    }

    public void mostrarBaraja() {
        for (Carta carta : cartas) {
            System.out.println(carta);
        }
    }
}

public class JuegoDeCartas {
    public static void main(String[] args) {
        Baraja baraja = new Baraja();
        baraja.barajar();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenú de opciones:");
            System.out.println("1. Barajar cartas");
            System.out.println("2. Mostrar siguiente carta");
            System.out.println("3. Cartas disponibles");
            System.out.println("4. Dar cartas");
            System.out.println("5. Cartas en el montón");
            System.out.println("6. Mostrar todas las cartas en la baraja");
            System.out.println("7. Salir");

            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    baraja.barajar();
                    System.out.println("Cartas barajadas.");
                    break;
                case 2:
                    Carta siguienteCarta = baraja.siguienteCarta();
                    if (siguienteCarta != null) {
                        System.out.println("Siguiente carta: " + siguienteCarta);
                    }
                    break;
                case 3:
                    int cartasDisponibles = baraja.cartasDisponibles();
                    System.out.println("Cartas disponibles: " + cartasDisponibles);
                    break;
                case 4:
                    System.out.print("Ingrese el número de cartas a dar: ");
                    int cantidad = scanner.nextInt();
                    List<Carta> cartasDadas = baraja.darCartas(cantidad);
                    if (!cartasDadas.isEmpty()) {
                        System.out.println("Cartas dadas: " + cartasDadas);
                    }
                    break;
                case 5:
                    List<Carta> cartasMonton = baraja.cartasMonton();
                    System.out.println("Cartas en el montón: " + cartasMonton);
                    break;
                case 6:
                    System.out.println("Mostrar todas las cartas en la baraja:");
                    baraja.mostrarBaraja();
                    break;
                case 7:
                    scanner.close();
                    System.out.println("¡Hasta luego!");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
            // Agregamos una pausa para que el usuario pueda ver la salida antes de mostrar el menú nuevamente
            System.out.println("\nPulse Enter para continuar...");
            scanner.nextLine(); // Limpiar el búfer de entrada
            scanner.nextLine(); // Esperar hasta que el usuario presione Enter
        }
    }
}
