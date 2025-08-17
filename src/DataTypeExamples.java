// Alejandro Garcia Pleayp Banda
// 16/08/2025

public class DataTypeExamples {
    // Tipos primitivos
    public int suma(int a, int b) {
        return a + b;
    }


    // Tipos abstractos (clases personalizadas)
    public Contacto crearContacto(String nombre, String direccion, String telefono) {
        return new Contacto(nombre, direccion, telefono);
    }


    // Método que combina varios tipos
    public String generarReporteContactos(LinkedList<Contacto> lista) {
        if (lista == null) return "Lista vacía";

        StringBuilder reporte = new StringBuilder();
        int totalContactos = 0;
        int contactosSinTelefono = 0;

        Nodo<Contacto> actual = lista.getCabeza();
        while (actual != null) {
            Contacto contacto = actual.getDato();
            if (contacto != null) {
                totalContactos++;
                if (contacto.getTelefono() == null || contacto.getTelefono().trim().isEmpty()) {
                    contactosSinTelefono++;
                }
                reporte.append("Contacto: ").append(contacto.getNombre())
                        .append(", Tel: ").append(contacto.getTelefono() != null ? contacto.getTelefono() : "N/A")
                        .append("\n");
            }
            actual = actual.getSiguiente();
            if (actual == lista.getCabeza()) break; // Para listas circulares
        }

        reporte.append("\nResumen:\n")
                .append("Total contactos: ").append(totalContactos).append("\n")
                .append("Contactos sin teléfono: ").append(contactosSinTelefono).append("\n")
                .append("Porcentaje completos: ").append(totalContactos > 0 ?
                        (100 * (totalContactos - contactosSinTelefono) / totalContactos) : 0)
                .append("%");

        return reporte.toString();
    }

        // Método para demostrar polimorfismo
        public void procesarLista(LinkedList<?> lista) {
            System.out.println("\nProcesando lista genérica:");
            if (lista != null) {
                // Cambia lista.mostrar() por lista.mostrar(null) para mostrar todos los elementos
                lista.mostrar();
                System.out.println("Tipo de lista: " +
                        (lista.isEsCircular() ? "Circular" : "Lineal") + ", " +
                        (lista.isEsDoble() ? "Doble" : "Simple"));
            }
        }
    }