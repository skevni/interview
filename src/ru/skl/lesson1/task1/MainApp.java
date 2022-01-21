package ru.skl.lesson1.task1;

public class MainApp {
    public static void main(String[] args) {
        Person person = new Person.Builder().firstName("Iam").age(1).address("moscow").country("ru").build();
        System.out.println(person);

    }
}
