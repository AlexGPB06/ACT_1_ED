public class Queue<E> {
    // Capacidad del arreglo
    public static final int CAPACITY = 1000;

    // Lugar para guardar los valores
    private E[] data;

    // La cantidad de elementos en la cola
    private int size = 0;

    // Crea un nuevo objeto tipo Cola
    public Queue() {
        this.data = (E[]) new Object[this.CAPACITY];
    }

    // Regresa TRUE si la cola está vacía
    public boolean isEmpty() {
        return (this.size == 0);
    }

    // Regresa el tamaño de la cola
    public int size() {
        return (this.size);
    }

    // Agrega un elemento al final de la cola
    public void push(E value) {
        this.data[this.size] = value;
        this.size++;
    }

    // Saca el primer elemento de la cola
    public E pop() throws Exception {
        E result = null;
        if (this.isEmpty()) {
            throw new Exception("La cola está vacía");
        }

        result = this.data[0];

        // Movemos los elementos hacia adelante
        for (int i = 0; i < this.size - 1; i++) {
            data[i] = data[i + 1];
        }

        this.data[this.size] = null; // ayuda al Garbage Collector
        this.size--;

        return result;
    }

    // Muestra el primer elemento de la cola sin eliminarlo
    public E peek() throws Exception {
        if (this.isEmpty()) {
            throw new Exception("La Cola está vacía");
        }
        return this.data[0];
    }

    // Método show para imprimir la cola
    public void show() {
        if (this.isEmpty()) {
            System.out.println("La cola está vacía");
        } else {
            System.out.print("Cola: ");
            for (int i = 0; i < this.size; i++) {
                System.out.print(this.data[i]);
                if (i < this.size - 1) {
                    System.out.print(" <- "); // muestra el orden
                }
            }
            System.out.println();
        }
    }

    // Método main para probar la clase Queue
    public static void main(String[] args) {
        try {
            Queue<Integer> cola = new Queue<>();

            cola.push(10);
            cola.push(20);
            cola.push(30);

            cola.show(); // Cola: 10 <- 20 <- 30

            System.out.println("Peek: " + cola.peek()); // Peek: 10

            System.out.println("Pop: " + cola.pop());   // Pop: 10

            cola.show(); // Cola: 20 <- 30

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
