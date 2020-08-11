public class ArrayDeque<T> {
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    /* Creates an empty array deque. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    public boolean isFull() {
        return size == items.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isSparse() {
        return items.length >= 16 && size < 0.25 * items.length;
    }

    public int size(){
        return size;
    }

    //越界取余
    private int plusOne(int index) {
        return (index + 1) % items.length;
    }

    private int minusOne(int index) {
        return (index - 1 + items.length) % items.length;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int p = plusOne(nextFirst);
        for(int i = 0; i < size; i++){
            a[i] = items[p];
            p = plusOne(p);
        }
        items = a;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    /* Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        if (isFull()) {
            resize(size * 2);
        } else {
            items[nextFirst] = item;
            nextFirst = minusOne(nextFirst);
            size += 1;
        }
    }

    /* Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        if (isFull()) {
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size += 1;

    }

    /* Prints the items in the deque from first to last, separated by a space.
        Once all the items have been printed, print out a new line. */
    public void printDeque() {
        int p = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            System.out.println(items[p] + " ");
            p = plusOne(p);
        }
        System.out.println();
    }

    /* Removes and returns the item at the front of the deque.
       If no such item exists, returns null. */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            if(isSparse()){
            resize(items.length/2);
        }
            nextFirst = plusOne(nextFirst);
            T toRemove = items[nextFirst];
            items[nextFirst] = null;
            size -= 1;
            return toRemove;
        }
    }

    /* Removes and returns the item at the back of the deque.
           If no such item exists, returns null. */
    public T removeLast(){
        if (isEmpty()) {
            return null;
        } else {
            if(isSparse()){
                resize(items.length/2);
            }
            nextLast = minusOne(nextFirst);
            T toRemove = items[nextLast];
            items[nextLast] = null;
            size -= 1;
            return toRemove;
        }
    }

    /*  Gets the item at the given index, where 0 is the front,
        1 is the next item, and so forth.
        If no such item exists, returns null. Must not alter the deque! */
    public T get(int index){
        if (isEmpty() || index >= size){
            return null;
        }
        int p = plusOne(nextFirst);
        return items[(p + index) % items.length];
    }

    /* Creates a deep copy of other.  */
    public ArrayDeque(ArrayDeque other){
        items = (T[]) new Object[other.size];
        nextLast = other.nextLast;
        nextFirst = other.nextFirst;
        size = other.size;
        System.arraycopy(other.items, 0, items, 0, other.size);
    }

}
