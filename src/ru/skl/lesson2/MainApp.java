package ru.skl.lesson2;

public class MainApp {
    public static void main(String[] args) {
//        MyLinkedListImpl<String> ll = new MyLinkedListImpl<>();
//
//        ll.add("1");
//        ll.addFirst("2");
//        ll.addFirst("99");
//        ll.addFirst("100");
//        ll.add("3");
//        ll.add("4");
//        ll.add("5");
//        ll.addLast("last");
//
//        ll.add(3, "into 1");
//
//        System.out.println(ll.toString());
//        System.out.println("removed: " + ll.remove(7));
//        System.out.println(ll);
        MyArrayList<String> list = new MyArrayList<>();
        list.add("1");

        list.add("3");
        list.add("4");
        list.add("5");
        // 1, 3, 4, 5
        list.add(1,"2");
        list.add(0, "0");
        list.add(list.getSize(),"6");
        list.add(list.getSize(),"7");
        list.add("8");
        list.add(2,"99");
        System.out.println(list.remove(9));
        System.out.println(list.remove(2));
        System.out.println(list.remove(3));
        System.out.println(list);


    }
}
