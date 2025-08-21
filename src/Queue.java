public class Queue<E> {
    private LinkedList<E> lista;

    public Queue() {
        this.lista = new LinkedList<>(false, false); // Cola simple no circular
    }

    public boolean isEmpty() {
        return lista.getCabeza() == null;
    }

    public boolean isFull() {
        // Con LinkedList, teóricamente nunca se llena (hasta que la memoria se agote)
        return false;
    }

    public int size() {
        int count = 0;
        Nodo<E> actual = lista.getCabeza();
        while (actual != null) {
            count++;
            actual = actual.getSiguiente();
        }
        return count;
    }

    public void push(E value) {
        // Enqueue: agregar al final de la lista
        lista.insertar(value);
    }

    public E pop() throws Exception {
        if (isEmpty()) {
            throw new Exception("La cola está vacía");
        }

        // Dequeue: remover el primer elemento (FIFO)
        E resultado = lista.getCabeza().getDato();

        // Eliminar el primer nodo
        if (lista.getCabeza().getSiguiente() == null) {
            // Solo había un elemento
            lista = new LinkedList<>(false, false);
        } else {
            lista.setCabeza(lista.getCabeza().getSiguiente());
        }

        return resultado;
    }

    public E peek() throws Exception {
        if (isEmpty()) {
            throw new Exception("La Cola está vacía");
        }
        return lista.getCabeza().getDato();
    }

    public void clear() {
        lista = new LinkedList<>(false, false);
    }

    public void show() {
        if (isEmpty()) {
            System.out.println("La cola está vacía");
        } else {
            System.out.print("Cola: ");
            Nodo<E> actual = lista.getCabeza();
            while (actual != null) {
                System.out.print(actual.getDato());
                if (actual.getSiguiente() != null) {
                    System.out.print(" <- ");
                }
                actual = actual.getSiguiente();
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Cola vacía";
        }

        StringBuilder sb = new StringBuilder("Cola: ");
        Nodo<E> actual = lista.getCabeza();
        while (actual != null) {
            sb.append(actual.getDato());
            if (actual.getSiguiente() != null) {
                sb.append(" <- ");
            }
            actual = actual.getSiguiente();
        }
        return sb.toString();
    }

    // Método auxiliar para obtener la cabeza (útil para testing)
    public Nodo<E> getFront() {
        return lista.getCabeza();
    }
}