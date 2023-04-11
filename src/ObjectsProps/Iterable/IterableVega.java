/**
 * Итерация по объектам коллекции.
 * Все коллекции реализуют интерфейс Iterable.
 * Метод iterator() возвращает объект с помощью которого мы можем итерироваться по коллекции.
 * iterator() хранит в себе указатель на объект в коллекции
 */

package ObjectsProps.Iterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IterableVega {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Iterator<Integer> iterator = list.iterator();

        while(iterator.hasNext()){
            iterator.remove();
            System.out.println(iterator.next());
        }

        for(int x : list){
            System.out.println(x);
        }
    }
}
