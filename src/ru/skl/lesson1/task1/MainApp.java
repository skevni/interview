package ru.skl.lesson1.task1;

public class MainApp {
    public static void main(String[] args) {
        PersonBuilder personBuilder = new PersonBuilder(new Person());

        System.out.println(personBuilder.age(1).address("moscow").country("ru").build());
    }
}
