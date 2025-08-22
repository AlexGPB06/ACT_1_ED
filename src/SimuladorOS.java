import java.util.Scanner;

public class SimuladorOS {
    private Stack<String> pilaComandos;
    private Queue<String> colaProcesos;

    public SimuladorOS() {
        this.pilaComandos = new Stack<>();
        this.colaProcesos = new Queue<>();
    }

    public void ejecutarComandos(Scanner scanner) {
        System.out.println("\n--- EJECUTANDO COMANDOS (PUSH) ---");
        boolean continuar = true;

        while (continuar) {
            System.out.print("Ingrese el comando a ejecutar: ");
            String comando = scanner.nextLine();

            try {
                pilaComandos.push(comando);
                System.out.println("✓ Comando '" + comando + "' PUSH ejecutado exitosamente.");
                System.out.println("Tamaño actual de la pila: " + pilaComandos.size());

                // Preguntar y validar respuesta
                boolean respuestaValida = false;
                while (!respuestaValida) {
                    System.out.print("¿Desea agregar otro comando? (si/no): ");
                    String respuesta = scanner.nextLine().trim().toLowerCase();

                    if (respuesta.equals("si") || respuesta.equals("sí") || respuesta.equals("s")) {
                        respuestaValida = true;
                        // Continuará en el siguiente ciclo
                    } else if (respuesta.equals("no") || respuesta.equals("n")) {
                        respuestaValida = true;
                        continuar = false;
                        System.out.println("Regresando al menú principal...");
                    } else {
                        System.out.println("Respuesta no válida. Por favor ingrese 'si' o 'no'.");
                    }
                }

            } catch (Exception e) {
                System.out.println("✗ Error al ejecutar PUSH: " + e.getMessage());
                continuar = false;
            }
        }
    }

    public void agregarProcesos(Scanner scanner) {
        System.out.println("\n--- AGREGANDO PROCESOS (ENQUEUE) ---");
        boolean continuar = true;

        while (continuar) {
            System.out.print("Ingrese el nombre del proceso: ");
            String proceso = scanner.nextLine();

            try {
                colaProcesos.push(proceso);
                System.out.println("✓ Proceso '" + proceso + "' ENQUEUE agregado exitosamente.");
                System.out.println("Tamaño actual de la cola: " + colaProcesos.size());

                // Preguntar y validar respuesta
                boolean respuestaValida = false;
                while (!respuestaValida) {
                    System.out.print("¿Desea agregar otro proceso? (si/no): ");
                    String respuesta = scanner.nextLine().trim().toLowerCase();

                    if (respuesta.equals("si") || respuesta.equals("sí") || respuesta.equals("s")) {
                        respuestaValida = true;
                        // Continuará en el siguiente ciclo
                    } else if (respuesta.equals("no") || respuesta.equals("n")) {
                        respuestaValida = true;
                        continuar = false;
                        System.out.println("Regresando al menú principal...");
                    } else {
                        System.out.println("Respuesta no válida. Por favor ingrese 'si' o 'no'.");
                    }
                }

            } catch (Exception e) {
                System.out.println("Error al realizar ENQUEUE: " + e.getMessage());
                continuar = false;
            }
        }
    }

    // Método modificado para solo mostrar las listas
    public void operacionesHistorial(Scanner scanner) throws Exception {
        System.out.println("\n--- HISTORIAL DE OPERACIONES ---");
        System.out.println("Mostrando estado actual de la Pila de Comandos y la Cola de Procesos.");

        MostarEstadoPila();
        mostrarEstadoCola();
    }

    private void peekComando() {
        try {
            String ultimoComando = pilaComandos.peek();
            System.out.println("✓ PEEK exitoso - Último comando: " + ultimoComando);
        } catch (Exception e) {
            System.out.println("✗ PEEK fallido - No hay comandos en el historial.");
        }
    }

    private void popComando() {
        try {
            String comandoDeshecho = pilaComandos.pop();
            System.out.println("✓ POP exitoso - Comando deshecho: " + comandoDeshecho);
            System.out.println("Nuevo tamaño de pila: " + pilaComandos.size());
        } catch (Exception e) {
            System.out.println("✗ POP fallido - No hay comandos para deshacer.");
        }
    }

    private void MostarEstadoPila() {
        if (pilaComandos.isEmpty()) {
            System.out.println("El historial de comandos (PILA) está vacío.");
            return;
        }

        System.out.println("\n--- HISTORIAL DE COMANDOS (STACK) ---");
        System.out.println("+---------------------------------+");
        System.out.println("|              PILA               |");
        System.out.println("+---------------------------------+");

        Stack<String> tempStack = new Stack<>();

        try {
            boolean esTop = true;

            // Mostrar elementos desde el TOP hacia la base
            while (!pilaComandos.isEmpty()) {
                String comando = pilaComandos.pop();
                String topIndicator = esTop ? "TOP -> " : "      ";
                System.out.printf("│ %-40s │\n", topIndicator + comando);
                tempStack.push(comando);
                esTop = false;
            }

            // Restaurar pila original
            while (!tempStack.isEmpty()) {
                pilaComandos.push(tempStack.pop());
            }

        } catch (Exception e) {
            System.out.println("Error al mostrar el estado de la pila: " + e.getMessage());
        }

        System.out.println("--------------------------------------------------");

        // Mostrar porcentaje de llenado
        int capacidad = 1000;
        int usados = pilaComandos.size();
        int porcentaje = (usados * 100) / capacidad;

        mostrarBarraProgreso(porcentaje, usados, capacidad, "PILA");
    }

    public void procesarComandos() {
        System.out.println("\n--- PROCESANDO COMANDOS (STACK OPERATIONS) ---");

        if (pilaComandos.isEmpty()) {
            System.out.println("No hay comandos para procesar.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        int contador = 1;

        try {
            System.out.println("Tamaño inicial de la pila: " + pilaComandos.size());
            System.out.println("TOP inicial de la pila: " + pilaComandos.peek());

            while (!pilaComandos.isEmpty() && continuar) {
                // Mostrar el estado actual
                System.out.println("\n--- ESTADO ACTUAL ---");
                System.out.println("Comandos pendientes: " + pilaComandos.size());
                System.out.println("TOP actual: " + pilaComandos.peek());

                // Preguntar si desea procesar el siguiente comando
                boolean respuestaValida = false;
                while (!respuestaValida) {
                    System.out.print("¿Desea procesar el siguiente comando? (si/no): ");
                    String respuesta = scanner.nextLine().trim().toLowerCase();

                    if (respuesta.equals("si") || respuesta.equals("sí") || respuesta.equals("s")) {
                        respuestaValida = true;

                        // Procesar el comando
                        String comando = pilaComandos.pop();
                        System.out.println(contador + ". Procesando POP: " + comando);
                        System.out.println("✓ Comando ejecutado exitosamente");
                        contador++;

                        // Si aún quedan comandos, mostrar el nuevo top
                        if (!pilaComandos.isEmpty()) {
                            System.out.println("Nuevo TOP: " + pilaComandos.peek());
                        } else {
                            System.out.println("¡Todos los comandos han sido procesados!");
                            continuar = false;
                        }

                    } else if (respuesta.equals("no") || respuesta.equals("n")) {
                        respuestaValida = true;
                        continuar = false;
                        System.out.println("Procesamiento interrumpido por el usuario.");
                        System.out.println("Comandos pendientes: " + pilaComandos.size());
                    } else {
                        System.out.println("Respuesta no válida. Por favor ingrese 'si' o 'no'.");
                    }
                }

                Thread.sleep(300); // Pequeña pausa para mejor visualización
            }

        } catch (Exception e) {
            System.out.println("Error durante el procesamiento: " + e.getMessage());
        }
    }

    public void realizarProcesos() {
        System.out.println("\n--- REALIZANDO PROCESOS (QUEUE OPERATIONS) ---");

        if (colaProcesos.isEmpty()) {
            System.out.println("No hay procesos en la cola.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        int contador = 1;

        try {
            System.out.println("Tamaño inicial de la cola: " + colaProcesos.size());
            System.out.println("FRONT inicial de la cola: " + colaProcesos.peek());

            while (!colaProcesos.isEmpty() && continuar) {
                // Mostrar el estado actual
                System.out.println("\n--- ESTADO ACTUAL ---");
                System.out.println("Procesos pendientes: " + colaProcesos.size());
                System.out.println("FRONT actual: " + colaProcesos.peek());

                // Preguntar si desea procesar el siguiente proceso
                boolean respuestaValida = false;
                while (!respuestaValida) {
                    System.out.print("¿Desea procesar el siguiente proceso? (si/no): ");
                    String respuesta = scanner.nextLine().trim().toLowerCase();

                    if (respuesta.equals("si") || respuesta.equals("sí") || respuesta.equals("s")) {
                        respuestaValida = true;

                        // Procesar el proceso
                        String proceso = colaProcesos.pop();
                        System.out.println(contador + ". Ejecutando DEQUEUE: " + proceso);
                        System.out.println("✓ Proceso ejecutado exitosamente");
                        contador++;

                        // Si aún quedan procesos, mostrar el nuevo front
                        if (!colaProcesos.isEmpty()) {
                            System.out.println("Nuevo FRONT: " + colaProcesos.peek());
                        } else {
                            System.out.println("¡Todos los procesos han sido completados!");
                            continuar = false;
                        }

                    } else if (respuesta.equals("no") || respuesta.equals("n")) {
                        respuestaValida = true;
                        continuar = false;
                        System.out.println("Ejecución interrumpida por el usuario.");
                        System.out.println("Procesos pendientes: " + colaProcesos.size());
                    } else {
                        System.out.println("Respuesta no válida. Por favor ingrese 'si' o 'no'.");
                    }
                }

                Thread.sleep(400); // Pequeña pausa para mejor visualización
            }

        } catch (Exception e) {
            System.out.println("Error durante la ejecución de procesos: " + e.getMessage());
        }
    }

    private void mostrarEstadoCola() {
        if (colaProcesos.isEmpty()) {
            System.out.println("La cola de procesos está vacía.");
            return;
        }

        System.out.println("\n--- ESTADO COMPLETO DE LA COLA ---");
        System.out.println("+---------------------------------+");
        System.out.println("|              Fila               |");
        System.out.println("+---------------------------------+");

        Queue<String> tempQueue = new Queue<>();
        int originalSize = colaProcesos.size();

        try {
            // Transferir y mostrar
            for (int i = 0; i < originalSize; i++) {
                String proceso = colaProcesos.pop();
                System.out.printf("│ %-40s │\n", proceso);
                tempQueue.push(proceso);
            }

            // Restaurar
            while (!tempQueue.isEmpty()) {
                colaProcesos.push(tempQueue.pop());
            }

        } catch (Exception e) {
            System.out.println("Error al mostrar el estado de la Fila: " + e.getMessage());
        }

        System.out.println("------------------------------------------------------");

        // Mostrar porcentaje de llenado
        int capacidad = 1000;
        int usados = colaProcesos.size();
        int porcentaje = (usados * 100) / capacidad;

        mostrarBarraProgreso(porcentaje, usados, capacidad, "Fila");
    }

    private void mostrarBarraProgreso(int porcentaje, int usados, int capacidad, String nombre) {
        int anchoBarra = 30; // longitud visual de la barra
        int llenos = (porcentaje * anchoBarra) / 100;
        int vacios = anchoBarra - llenos;

        String barra = "█".repeat(llenos) + " ".repeat(vacios);

        System.out.printf("%s: [%s] %d%% (%d/%d)\n", nombre, barra, porcentaje, usados, capacidad);
    }
}