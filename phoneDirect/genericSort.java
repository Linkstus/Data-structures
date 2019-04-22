import java.util.Comparator;

class genericSort<T> implements Comparator<T> {
    public int compareTo(T item1, T item2) {
        return item1.compareTo(item2);
    }
}