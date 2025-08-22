// Alejandro Garcia Pelayo Banda
// 21/08/2025

public class LinkedList<T> {
    private Nodo<T> cabeza;
    private Nodo<T> cola;
    private boolean esCircular;
    private boolean esDoble;

    public LinkedList(boolean esCircular, boolean esDoble) {
        this.cabeza = null;
        this.cola = null;
        this.esCircular = esCircular;
        this.esDoble = esDoble;
    }

    public void insertar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
            if (esCircular) {
                cola.setSiguiente(cabeza);
                if (esDoble) cabeza.setAnterior(cola);
            }
        } else {
            cola.setSiguiente(nuevo);
            if (esDoble) nuevo.setAnterior(cola);
            cola = nuevo;
            if (esCircular) {
                cola.setSiguiente(cabeza);
                if (esDoble) cabeza.setAnterior(cola);
            }
        }
    }

    public void eliminar(T dato) {
        if (cabeza == null || dato == null) return;

        Nodo<T> actual = cabeza;
        Nodo<T> anterior = null;
        boolean encontrado = false;

        do {
            if (dato.equals(actual.getDato())) {
                encontrado = true;
                break;
            }
            anterior = actual;
            actual = actual.getSiguiente();
        } while (actual != null && actual != cabeza);

        if (!encontrado) return;

        // Caso 1: Eliminar el único nodo
        if (cabeza == cola) {
            cabeza = null;
            cola = null;
            return;
        }

        // Caso 2: Eliminar la cabeza
        if (actual == cabeza) {
            cabeza = cabeza.getSiguiente();
            if (esDoble) {
                cabeza.setAnterior(cola);
                cola.setSiguiente(cabeza);
            }
            if (esCircular) {
                cola.setSiguiente(cabeza);
            }
        }
        // Caso 3: Eliminar la cola
        else if (actual == cola) {
            cola = anterior;
            if (esDoble) {
                cola.setAnterior(anterior.getAnterior());
            }
            if (esCircular) {
                cola.setSiguiente(cabeza);
            }
        }
        // Caso 4: Eliminar nodo intermedio
        else {
            anterior.setSiguiente(actual.getSiguiente());
            if (esDoble && actual.getSiguiente() != null) {
                actual.getSiguiente().setAnterior(anterior);
            }
        }
    }

    public boolean buscar(T dato) {
        if (cabeza == null || dato == null) return false;

        Nodo<T> actual = cabeza;
        do {
            if (dato.equals(actual.getDato())) return true;
            actual = actual.getSiguiente();
        } while (actual != null && actual != cabeza);

        return false;
    }

    public void setCabeza(Nodo<T> cabeza) {
        this.cabeza = cabeza;
    }

    public void mostrar() {
        if (cabeza == null) {
            System.out.println("La lista está vacía.");
            return;
        }

        System.out.println("\n--- Representación de la lista ---");

        if (!esDoble && !esCircular) {
            Nodo<T> actual = cabeza;
            while (actual != null) {
                System.out.print("[" + actual.getDato() + "] -> ");
                actual = actual.getSiguiente();
            }
            System.out.println("NULL");
        }
        else if (esDoble && !esCircular) {
            System.out.println("De izquierda a derecha:");
            Nodo<T> actual = cabeza;
            while (actual != null) {
                System.out.print("[" + actual.getDato() + "] <-> ");
                actual = actual.getSiguiente();
            }
            System.out.println("NULL");

            System.out.println("De derecha a izquierda:");
            actual = cola;
            while (actual != null) {
                System.out.print("[" + actual.getDato() + "] <-> ");
                actual = actual.getAnterior();
            }
            System.out.println("NULL");
        }
        else if (esCircular && !esDoble) {
            Nodo<T> actual = cabeza;
            do {
                System.out.print("[" + actual.getDato() + "] -> ");
                actual = actual.getSiguiente();
            } while (actual != cabeza);
            System.out.println("(vuelve al inicio)");
        }
        else if (esCircular && esDoble) {
            System.out.println("De izquierda a derecha:");
            Nodo<T> actual = cabeza;
            do {
                System.out.print("[" + actual.getDato() + "] <-> ");
                actual = actual.getSiguiente();
            } while (actual != cabeza);
            System.out.println("(vuelve al inicio)");

            System.out.println("De derecha a izquierda:");
            actual = cola;
            do {
                System.out.print("[" + actual.getDato() + "] <-> ");
                actual = actual.getAnterior();
            } while (actual != cola);
            System.out.println("(vuelve al final)");
        }
    }

    public Nodo<T> getCabeza() {
        return cabeza;
    }

    public boolean isEsCircular() {
        return esCircular;
    }

    public boolean isEsDoble() {
        return esDoble;
    }
}