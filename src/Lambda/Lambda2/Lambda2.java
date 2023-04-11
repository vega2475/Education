/**
 * Применение лямбда выражений в специальных методах.
 * Лямбда не может оперировать на листах и массивах, требуется преобразование в поток.
 */

package Lambda.Lambda2;

import java.util.*;
import java.util.stream.Collectors;

public class Lambda2 {
    public static void main(String[] args) {
        int[] arr1 = new int[10];
        List<Integer> list1 = new ArrayList<>();

        fillArray(arr1);
        fillList(list1);

        System.out.println(Arrays.toString(arr1));
        System.out.println(list1);

        for(int i = 0; i < arr1.length; i++){
            arr1[i] = arr1[i] * 2;
            list1.set(i, list1.get(i) * 2);
        }
        System.out.println(Arrays.toString(arr1));
        System.out.println(list1);

        //те же операции только через лямбду:
        //1. map() method - отображение
        arr1 = Arrays.stream(arr1).map(a -> a * 2).toArray(); //на вход массив на выходе поток(этот поток не имеент отношения к многопоточности), потом мы на на этом потоке вызываем специальный метод map().
        list1 = list1.stream().map(a -> a * 2).toList(); // в аргументах методу map() мы должны указать логику сопоставления элементов старого массива к элементам нового массива. Тут map - перевод отображение.

        System.out.println(Arrays.toString(arr1));
        System.out.println(list1);

        //2. filter() method - фильтрует только нужные нам даннные, из массива выделит подмассив.
        int[] arr2 = new int[10];
        List<Integer> list2 = new ArrayList<>();
        fillArray(arr2);
        fillList(list2);
        //хотим оставить только четные числа
        arr2 = Arrays.stream(arr2).filter(a -> a % 2 == 0).toArray();
        list2 = list2.stream().filter(a -> a % 2 == 0).toList();
        System.out.println(Arrays.toString(arr2));
        System.out.println(list2);

        //3. forEach - пройти по всем элементам массива
        Arrays.stream(arr2).forEach(a -> System.out.print(a + " "));
        System.out.println();
        list2.stream().forEach(a -> System.out.print(a + " "));
        System.out.println();

        //4. reduce - сжать набор данных в один элемент - например посчитать сумму всех элементов - сжатие по сумме
        int[] arr3 = new int[10];
        List<Integer> list3 = new ArrayList<>();

        fillArray(arr3);
        fillList(list3);

        int sum = Arrays.stream(arr3).reduce((accumulator, current) -> accumulator += current).getAsInt();
        int product = list3.stream().reduce((accumulator, current) -> accumulator *= current).get();
        System.out.println(sum);
        System.out.println(product);

        //5. все эти методы можно вызывать один за другим
        int [] arr4 = new int[10];

        fillArray(arr4);
        //все нечетные * 2
        arr4 = Arrays.stream(arr4).filter(a -> a % 2 != 0).map(a -> a * 2).toArray();

        System.out.println(Arrays.toString(arr4));

        //так же эти методы можно вызывать на всех коллекциях

        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);

        set = set.stream().map(a -> a * 3).collect(Collectors.toSet());

        System.out.println(set);
    }

    private static void fillArray(int [] array){
        for(int i = 0; i < array.length; i++){
            array[i] = i + 1;
        }
    }

    private static void fillList(List<Integer> list){
        for(int i = 0; i < 10; i++){
            list.add(i + 1);
        }
    }
}
