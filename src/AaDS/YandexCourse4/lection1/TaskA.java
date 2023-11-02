package AaDS.YandexCourse4.lection1;

/**
 * Базовым алгоритмом для быстрой сортировки является алгоритм partition, который разбивает набор элементов на две части относительно заданного предиката.
 * По сути элементы массива просто меняются местами так, что левее некоторой точки в нем после этой операции лежат элементы,
 * удовлетворяющие заданному предикату, а справа — не удовлетворяющие ему.
 * Например, при сортировке можно использовать предикат «меньше опорного»,
 * что при оптимальном выборе опорного элемента может разбить массив на две примерно равные части.
 * Напишите алгоритм partition в качестве первого шага для написания быстрой сортировки.
 */

import java.util.Arrays;
import java.util.Scanner;

/**
 * В данном коде представлен алгоритм разбиения через 3 указателя E - equal (указывает на первый элемент последовательности чисел равных опорному),
 * G - greater (указывает на первый элемент последовательности чисел больше опорного), N - now (указывает на необработанную часть массива)
 *
 * Пример массива из лекции:
 *        12
 *        1 2 3 4 4 5 6 7 0 4 8 3
 *        4
 *        System.out.println(partition(true, 3, 5, 8, x, array));
 *
 *        В данном случае разбиение происходит правильно
 */

class TaskA {
    public static void main(String[] args) {
        // Если predicate = true - то это меньше x, иначе все остальные

        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().split("")[0]);
        if(n == 0){
            System.out.println("0\n0");
            return;
        }
        int [] array = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] arrayCopy = Arrays.copyOf(array, array.length);
        int x = Integer.parseInt(scanner.nextLine().split(" ")[0]);

        System.out.println(partition(true, -1, -1, 0, x, array));
        System.out.println(partition(false, -1, -1, 0, x, arrayCopy));
    }

    private static int partition(boolean predicate, int e, int g, int n, int pivot, int[] array){
        int predicateCount = 0;
        // Элементы меньше x
        if(predicate){
            for (; n < array.length; n++) {
                if(pivot < array[n]){
                    if(g == -1) g = n;
                }else if(pivot > array[n]){
                    predicateCount++;
                    if(g == -1){
                        if(e != -1){
                            swap(array, n, e);
                            e++;
                        }
                        continue;
                    }
                    if(e == -1){
                        swap(array, n, g);
                        g++;
                    }else{
                        int tempG = array[g];
                        int tempE = array[e];
                        array[e] = array[n];
                        e++;
                        array[g] = tempE;
                        g++;
                        array[n] = tempG;
                    }
                }else if(pivot == array[n]){
                    if(g == -1){
                        e = n;
                        continue;
                    }
                    int tempG = array[g];
                    if(e == -1){
                        swap(array, n, g);
                        e = g;
                        g++;
                    }else{
                        array[g] = array[e];
                        g++;
                        array[n] = tempG;
                    }

                }

            }

        }else{
            for (; n < array.length; n++) {

                if(pivot < array[n]){
                    predicateCount++;
                }else if(pivot > array[n]){
                    if(g == -1){
                        if(e != -1){
                            swap(array, n, e);
                            e++;
                        }
                        continue;
                    }
                    if(e == -1){
                        swap(array, n, g);
                        g++;
                    }else{
                        int tempG = array[g];
                        int tempE = array[e];
                        array[e] = array[n];
                        e++;
                        array[g] = tempE;
                        g++;
                        array[n] = tempG;
                    }
                }else if(pivot == array[n]){
                    predicateCount++;
                    if(g == -1){
                        e = n;
                        continue;
                    }
                    int tempG = array[g];
                    if(e == -1){
                        swap(array, n, g);
                        e = g;
                        g++;
                    }else{
                        array[g] = array[e];
                        g++;
                        array[n] = tempG;
                    }
                }

            }

        }
        return predicateCount;

    }

    private static void swap(int[] array, int index1, int index2){
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
