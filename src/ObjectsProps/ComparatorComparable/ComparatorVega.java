/**
 * Сортировка объектов по какому-либо принципу.
 * Comparator - тот кто сравнивает объекты между собой (метод compare() сравнивает 2 объекта и в int выводить результат)
 * В меиоде compareTo() мы можем задать любой порядкок сортировки объектов как в TreeSet так и в других сортировках.
 */

package ObjectsProps.ComparatorComparable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorVega {
    public static void main(String[] args) {
        List<String> animals = new ArrayList<>();

        animals.add("cat");
        animals.add("dog");
        animals.add("Z");
        animals.add("frog");
        animals.add("panda");

        Collections.sort(animals, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.length() > o2.length())return 1;
                else if(o1.length() < o2.length())return -1;
                else return 0;
            }
        }); //первый аргумент - коллекция, второй аргумент рбъект компаратор.

        System.out.println(animals);


        List<Person> people = new ArrayList<>();
        people.add(new Person(1, "Dima"));
        people.add(new Person(3, "Nik"));
        people.add(new Person(2, "Maxim"));
        people.add(new Person(4, "David"));

        Collections.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                if(o1.id > o2.id)return 1;
                else if(o1.id < o2.id)return -1;
                else return 0;
            }
        });
        System.out.println(people);
    }


    public int compare(String o1, String o2) {
        /*
        o1 > o2 -> return 1
        o1 < o2 -> return -1
        o1 == o2 -> return 0
         */

        if(o1.length() > o2.length())return 1;
        else if(o1.length() < o2.length())return -1;
        else return 0;
    }
}

class StringLengthSort implements Comparator<String>{

    @Override
    public int compare(String o1, String o2) {
        /*
        o1 > o2 -> return 1
        o1 < o2 -> return -1
        o1 == o2 -> return 0
         */

        if(o1.length() > o2.length())return 1;
        else if(o1.length() < o2.length())return -1;
        else return 0;
    }
}

class Person{
    int id;
    String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
