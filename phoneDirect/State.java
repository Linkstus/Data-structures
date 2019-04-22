public class State {
    String name;
    Person n = new Person(" ", 0);
    genericrbt<Person> grbt = new genericrbt<>(n);

    public State(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}