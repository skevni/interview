package ru.skl.lesson2;

public class MainApp {
    public static void main(String[] args) {
        MyLinkedList<String> ll = new MyLinkedList<>();

        ll.add("1");
        ll.addFirst("2");
        ll.addFirst("99");
        ll.addFirst("100");
        ll.add("3");

        System.out.println(ll);
        System.out.println("removed: " + ll.remove(2));
        System.out.println(ll);

    }
}
