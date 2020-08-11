//环形双向链表：最后一个节点的next指向第一个节点，而第一个节点的prev指向最后一个节点
public class LinkedListDeque<T> {

    public class TNode {
        public TNode prev;
        public T item;
        public TNode next;
        public TNode(TNode p, T i, TNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private TNode sentinel;
    private int size;

    public LinkedListDeque(){
        sentinel = new TNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /*  Adds an item of type T to the front of the deque. */
    //sentinel.next 是第一个item
    public void addFirst(T item){
        sentinel.next = new TNode(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    /* Adds an item of type T to the back of the deque. */
    //sentinel.pre 是最后一个item
    public void addLast(T item){
        sentinel.prev = new TNode(sentinel.prev, item, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    /* Returns true if deque is empty, false otherwise. */
    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        else return false;
    }

    /* Returns the number of items in the deque. */
    public int size(){
        return size;
    }

    /* Prints the items in the deque from first to last, separated by a space.
       Once all the items have been printed, print out a new line. */
    public void printDeque(){
        TNode p = sentinel;
        for (int i = 0; i < size; i ++){
            System.out.println(p.next.item + " ");
            p = p.next;
        }
        System.out.println(); //结束后输出空行
    }

    /* Removes and returns the item at the front of the deque.
       If no such item exists, returns null. */
    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        T p = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return p;
    }

    /* Removes and returns the item at the back of the deque.
       If no such item exists, returns null. */
    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        T p = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return p;
    }

    /*  Gets the item at the given index, where 0 is the front,
        1 is the next item, and so forth.
        If no such item exists, returns null. Must not alter the deque! */
    public T get(int index){
            TNode p = sentinel;
        if (isEmpty() || index >= size){
            return null;
        }
        else {
            for (int i = 0; i <= index; i++) {
                p = p.next;
            }
            return p.item;
        }
    }

    /*  Creates a deep copy of other.  */
    public LinkedListDeque(LinkedListDeque other){
        sentinel = new TNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;

        for (int i = 0; i < other.size(); i++){
            addLast((T) other.get(i)); // (T) is cast, since type of other is unknown
        }
    }

    /* Same as get, but uses recursion. */
    private TNode Recursive(int index){
        TNode p = sentinel;
        TNode x = new TNode(null, null, null);
        if (index == 0){
            return p.next;
        }
        else {
            x = Recursive(index - 1);
            return x.next;
        }
    }

    public T getRecursive(int index){
        if (isEmpty() || index >= size){
            return null;
        }
        else {
            TNode x = Recursive(index);
            return x.item;
        }
    }
}
