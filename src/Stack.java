// Alejandro Garcia Pelayo Banda
// 21/08/2025
public class Stack<E> {
    private LinkedList<E> lista;

    public Stack() {
        this.lista = new LinkedList<>(false, false); // Pila simple no circular
    }

    public boolean isEmpty() {
        return lista.getCabeza() == null;
    }

    public boolean isFull() {
        // Con LinkedList, teóricamente nunca se llena
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
        // Push: agregar al final de la lista (el tope será el último elemento)
        lista.insertar(value);
    }

    public E pop() throws Exception {
        if (isEmpty()) {
            throw new Exception("La Pila está vacía");
        }

        // Encontrar el último nodo (tope)
        Nodo<E> actual = lista.getCabeza();
        Nodo<E> anterior = null;

        while (actual.getSiguiente() != null) {
            anterior = actual;
            actual = actual.getSiguiente();
        }

        E resultado = actual.getDato();

        if (anterior == null) {
            // Solo había un elemento
            lista = new LinkedList<>(false, false);
        } else {
            anterior.setSiguiente(null);
        }

        return resultado;
    }

    public E peek() throws Exception {
        if (isEmpty()) {
            throw new Exception("La Pila está vacía");
        }

        // Encontrar el último nodo (tope)
        Nodo<E> actual = lista.getCabeza();
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }

        return actual.getDato();
    }

    public void clear() {
        lista = new LinkedList<>(false, false);
    }

    public void show() {
        if (isEmpty()) {
            System.out.println("La Pila está vacía");
            return;
        }

        System.out.print("Tope -> ");

        // Para mostrar en orden LIFO, necesitamos recorrer desde el final
        // Esto es ineficiente, pero funciona para mostrar
        Stack<E> temp = new Stack<>();
        try {
            // Primero vaciamos la pila original a una temporal
            while (!isEmpty()) {
                temp.push(this.pop());
            }

            // Luego mostramos y restauramos
            while (!temp.isEmpty()) {
                E elemento = temp.pop();
                System.out.print(elemento);
                this.push(elemento);
                if (!temp.isEmpty()) {
                    System.out.print(" -> ");
                }
            }

        } catch (Exception e) {
            System.out.println("Error al mostrar la pila: " + e.getMessage());
        }
        System.out.println();
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Pila vacía";
        }

        StringBuilder sb = new StringBuilder("Tope -> ");

        // Misma lógica que show() pero para string
        Stack<E> temp = new Stack<>();
        try {
            while (!isEmpty()) {
                temp.push(this.pop());
            }

            while (!temp.isEmpty()) {
                E elemento = temp.pop();
                sb.append(elemento);
                this.push(elemento);
                if (!temp.isEmpty()) {
                    sb.append(" -> ");
                }
            }

        } catch (Exception e) {
            return "Error al convertir pila a string: " + e.getMessage();
        }

        return sb.toString();
    }
}