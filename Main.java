// Alejandro Garcia Pleayp Banda
// 16/08/2025

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DataTypeExamples ejemplos = new DataTypeExamples();
        LinkedList<Contacto> listaActual = null;

        while (true) {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Crear lista (Contactos)");
            System.out.println("2. Ver ejemplos de listas");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");

            int op;
            try {
                op = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Error: Debes ingresar un número entero válido.");
                sc.nextLine();
                continue;
            }

            switch (op) {
                case 1 -> {
                    System.out.println("\n--- Elige el tipo de lista ---");
                    System.out.println("1. Lista Simple");
                    System.out.println("2. Lista Doble");
                    System.out.println("3. Lista Circular");
                    System.out.print("Opción: ");

                    int tipo;
                    try {
                        tipo = sc.nextInt();
                        sc.nextLine();
                    } catch (Exception e) {
                        System.out.println("Error: Debes ingresar un número entero válido.");
                        sc.nextLine();
                        continue;
                    }

                    listaActual = null;
                    if (tipo == 1) listaActual = new LinkedList<>(false, false);
                    else if (tipo == 2) listaActual = new LinkedList<>(false, true);
                    else if (tipo == 3) listaActual = new LinkedList<>(true, false);
                    else {
                        System.out.println("Tipo inválido");
                        continue;
                    }

                    manejarSubmenuContactos(sc, ejemplos, listaActual);
                }

                case 2 -> {
                    System.out.println("\n--- EJEMPLOS DE LISTAS ---");
                    System.out.println("1. Lista Simple");
                    System.out.println("2. Lista Doble");
                    System.out.println("3. Lista Circular");
                    System.out.print("Opción: ");

                    int tipo;
                    try {
                        tipo = sc.nextInt();
                        sc.nextLine();
                    } catch (Exception e) {
                        System.out.println("Error: Debes ingresar un número entero válido.");
                        sc.nextLine();
                        continue;
                    }

                    switch (tipo) {
                        case 1 -> {
                            LinkedList<String> listaSimple = new LinkedList<>(false, false);
                            listaSimple.insertar("A");
                            listaSimple.insertar("B");
                            listaSimple.insertar("C");
                            System.out.println("\nEjemplo Lista Simple:");
                            listaSimple.mostrar(); // Usar mostrar() sin parámetros
                        }
                        case 2 -> {
                            LinkedList<String> listaDoble = new LinkedList<>(false, true);
                            listaDoble.insertar("A");
                            listaDoble.insertar("B");
                            listaDoble.insertar("C");
                            System.out.println("\nEjemplo Lista Doble:");
                            listaDoble.mostrar();
                        }
                        case 3 -> {
                            LinkedList<String> listaCircular = new LinkedList<>(true, false);
                            listaCircular.insertar("A");
                            listaCircular.insertar("B");
                            listaCircular.insertar("C");
                            System.out.println("\nEjemplo Lista Circular:");
                            listaCircular.mostrar();
                        }
                        default -> System.out.println("Opción inválida");
                    }
                }

                case 3 -> {
                    System.out.println("Saliendo del programa...");
                    return;
                }

                default -> System.out.println("Opción inválida");
            }
        }
    }

    private static void manejarSubmenuContactos(Scanner sc, DataTypeExamples ejemplos, LinkedList<Contacto> lista) {
        while (true) {
            System.out.println("\n--- SUBMENÚ CONTACTOS ---");
            System.out.println("1. Insertar contacto");
            System.out.println("2. Eliminar contacto");
            System.out.println("3. Buscar contacto");
            System.out.println("4. Mostrar lista");
            System.out.println("5. Volver al menú principal");
            System.out.print("Elige opción: ");

            int sub;
            try {
                sub = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Error: Debes ingresar un número entero válido.");
                sc.nextLine();
                continue;
            }

            switch (sub) {
                case 1 -> {
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    if (lista.buscar(new Contacto(nombre, "", ""))) {
                        System.out.println("Error: Ya existe un contacto con ese nombre.");
                        continue;
                    }

                    System.out.print("Dirección: ");
                    String dir = sc.nextLine();

                    System.out.print("Teléfono: ");
                    String tel;
                    while (true) {
                        try {
                            tel = sc.nextLine();
                            if (!tel.matches("\\d+")) {
                                throw new Exception("El teléfono debe contener solo números");
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                            System.out.print("Teléfono: ");
                        }
                    }

                    lista.insertar(ejemplos.crearContacto(nombre, dir, tel));
                    System.out.println("Contacto agregado exitosamente.");
                }
                case 2 -> {
                    System.out.print("Nombre del contacto a eliminar: ");
                    String nombre = sc.nextLine();
                    Contacto contactoAEliminar = new Contacto(nombre, "", "");
                    if (lista.buscar(contactoAEliminar)) {
                        lista.eliminar(contactoAEliminar);
                        System.out.println("Contacto eliminado exitosamente.");
                    } else {
                        System.out.println("No se encontró un contacto con ese nombre.");
                    }
                }
                case 3 -> {
                    System.out.print("Nombre a buscar: ");
                    String nombre = sc.nextLine();
                    Contacto contactoABuscar = new Contacto(nombre, "", "");
                    lista.mostrar();
                }
                case 4 -> lista.mostrar();
                case 5 -> {
                    return;
                }
                default -> System.out.println("Opción inválida");
            }
        }
    }
}