public class Stack<E> {
    public static final int CAPACITY = 1000;
    private E[] data;
    private int size = 0;

    public Stack() {
        this.data = (E[]) new Object[this.CAPACITY];
    }

    public boolean isEmpty() {
        return (this.size == 0);
    }

    public int size() {
        return this.size;
    }

    public void push(E value) {
        this.data[this.size] = value;
        this.size++;
    }

    public E pop() throws Exception {
        if (this.isEmpty()) {
            throw new Exception("La Pila está vacía");
        }
        this.size--;
        E result = this.data[this.size];
        this.data[this.size] = null;
        return result;
    }

    public E peek() throws Exception {
        if (this.isEmpty()) {
            throw new Exception("La Pila está vacía");
        }
        return this.data[this.size - 1];
    }

    public void show() {
        if (this.isEmpty()) {
            System.out.println("La Pila está vacía");
            return;
        }

        System.out.print("Tope -> ");
        for (int i = this.size - 1; i >= 0; i--) {
            System.out.print(this.data[i]);
            if (i > 0) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }
}