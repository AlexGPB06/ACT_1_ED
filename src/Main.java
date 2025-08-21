// Alejandro Garcia Pleayp Banda
// 16/08/2025

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SimuladorOS simulador = new SimuladorOS();
        Scanner scanner = new Scanner(System.in);
        boolean ejecutando = true;

        while (ejecutando) {
            mostrarMenuPrincipal();
            try {
                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1 -> simulador.ejecutarComandos(scanner);
                    case 2 -> simulador.agregarProcesos(scanner);
                    case 3 -> simulador.operacionesHistorial(scanner);
                    case 4 -> simulador.procesarComandos();
                    case 5 -> simulador.realizarProcesos();
                    case 6 -> {
                        System.out.println("Saliendo del simulador...");
                        ejecutando = false;
                    }
                    default -> System.out.println("Opción inválida. Intente nuevamente.");
                }
            } catch (Exception e) {
                System.out.println("Error: Ingrese un número válido.");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n=== SIMULADOR DE SISTEMA OPERATIVO ===");
        System.out.println("1. EJECUTANDO COMANDOS (PUSH) ---");
        System.out.println("2. AGREGANDO PROCESOS (ENQUEUE) ---");
        System.out.println("3. OPERACIONES DE HISTORIAL (POP/PEEK) ---");
        System.out.println("4. PROCESANDO COMANDOS (STACK OPERATIONS) ---");
        System.out.println("5. REALIZANDO PROCESOS (QUEUE OPERATIONS) ---");
        System.out.println("6. SALIR DEL SIMULADOR ---");
        System.out.print("\nEscriba el número de la acción que desea realizar: ");
    }
}