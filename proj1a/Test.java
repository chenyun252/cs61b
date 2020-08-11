import java.awt.*;

public class Test {
    public static void main(String[] args) {
        ArrayDeque<Integer> L = new ArrayDeque<>();
        L.addLast(1);
        L.addLast(2);
        L.addLast(3);
        L.addLast(4);
        L.addLast(5);
        L.addLast(6);
        L.addLast(7);
        L.addLast(8);
        L.addLast(9);


        L.printDeque();
        System.out.println(L.get(5));
        System.out.println(L.isFull());
    }
}
