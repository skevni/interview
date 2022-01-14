package ru.skl.lesson1.task1;

public class PersonBuilder {
    private final Person person;

    public PersonBuilder(Person person) {
        this.person = person;
    }

    public PersonBuilder firstName(String firstName) {
        this.person.setFirstName(firstName);
        return this;
    }

    public PersonBuilder lastName(String lastName) {
        this.person.setLastName(lastName);
        return this;
    }

    public PersonBuilder middleName(String middleName) {
        this.person.setMiddleName(middleName);
        return this;
    }

    public PersonBuilder country(String country) {
        this.person.setCountry(country);
        return this;
    }

    public PersonBuilder address(String address) {
        this.person.setAddress(address);
        return this;
    }

    public PersonBuilder phone(String phone) {
        this.person.setPhone(phone);
        return this;
    }

    public PersonBuilder age(int age) {
        this.person.setAge(age);
        return this;
    }

    public PersonBuilder gender(String gender) {
        this.person.setGender(gender);
        return this;
    }

    public Person build() {
        return person;
    }
}
