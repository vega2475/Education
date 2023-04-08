/**
    @info
    equals в классе Object сравниваеи учатски в памяти.(поэтому его практически всегда нужно переопределять).
    hashcode аналогично.

    Почему мы не можем сравнивать, объекты только с помощью метода equals? -
    метод hashcode работает быстрее, когда мы проверяем объекты на равенство мы сначала вызываем метод hashcode,
    и если метод hashcode прошел проверку, то объекты равны, а если не прошел (разные хэши), то вызывается метод equals
    для дополнительной првоерки. (equals всегда выдает верный ответ).

    Контракт hashcode equals:
    1. Invoke hashcode method for 2 comparable objects -> if they have different hash -> objects not equals.
    2. If hash's equals, invoke method equals()
    3. Equals guaranteed right answer.
 */

package HashCodeEquals;

import java.util.*;

public class HashCodeEquals {
    public static void main(String[] args) {
        Map<Person, String> map = new HashMap<>();
        Set<Person> set = new HashSet<>();

        Person person1 = new Person(1, "Dima");
        Person person2 = new Person(1, "Dima");

        map.put(person1, "2004");
        map.put(person2, "2004");

        set.add(person1);
        set.add(person2);

        System.out.println(map);
        System.out.println(set);

        System.out.println(person1.equals(person1));

    }
}
class Person {
    private int id;
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != person.id) return false;
        return Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}