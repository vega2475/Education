/**
 * Сортировка объектов по какому-либо принципу.
 * Comparable - класс реализующий интерфейс Comparable становиться "классом способным сравниваться" по каким-то признакам.
 * Тут метод compareTo сравнивает объект в аргументе с каким-либо другим объектов на котором мы этот метод и вызываем.
 * В меиоде compareTo() мы можем задать любой порядкок сортировки объектов как в TreeSet так и в других сортировках.
 */

package ObjectsProps.ComparatorComparable;

import java.util.*;

public class ComparableVega {
    public static void main(String[] args) {
        List<PersonComparable> peopleList = new ArrayList<>();
        Set<PersonComparable> peopleSet = new TreeSet<>();

        addElements(peopleList);
        addElements(peopleSet);

        System.out.println(peopleList);
        System.out.println(peopleSet);

        System.out.println(new PersonComparable(5, "DD").compareTo(new PersonComparable(4, "Popa")));
    }

    private static void addElements(Collection<PersonComparable> collection){
        collection.add(new PersonComparable(1, "Dima"));
        collection.add(new PersonComparable(2, "Nik"));
        collection.add(new PersonComparable(4, "Maxim"));
        collection.add(new PersonComparable(3, "David"));
    }
}
class PersonComparable implements Comparable<PersonComparable>{
    int id;
    String name;

    public int getId() {
        return id;
    }

    public PersonComparable(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonComparable that = (PersonComparable) o;

        if (id != that.id) return false;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PersonComparable{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(PersonComparable o) {
        if(this.id > o.getId()){
            return 1;
        }else if (this.id < o.getId()) return -1;
        else return 0;
    }
}
