package insertSort;

import javafx.util.Pair;
import java.util.Arrays;

public class InsertSort {

    public int[] sort(int[] array) {
        array = go_forward(array);
        return array;

    }

    public int[] go_forward(int[] array) {
        int len = array.length;
        for (int left = 0; left < len; left++) {
            int value = array[left];
            int i = left - 1;
            Pair<Integer, int[]> p = go_back(array, i, value);
            array = p.getValue();
            i = p.getKey();
            array[i + 1] = value;
        }
        return array;
    }

    public Pair<Integer, int[]> go_back(int[] array, int i, int value) {
        for (; i >= 0; i--)
            if (compare_values(value, array[i])) array[i + 1] = array[i];
            else break;
        return new Pair<>(i, array);
    }

    public boolean compare_values(int a, int b) {
        return a < b;
    }

}


/*
    public static void main(String[] params) {
        int[] array = new int[]{64, 53, 32, 71, 69, 4, 56, 31, 55};
        insertSort(array);
        System.out.println(Arrays.toString(array));

    }

    public static void insertSort(int[] array) {
        for (int i = 0; i < array.length; i++) { //внешний цикл , начинаем с 1 индекса , так как 0 эллемента автоматически
            int current = array[i]; //запоминаем эллемент под индексом i                          //попадает в отсортированную псоледовательсноть
            int j = i;//для внутреннего цикла создаем переменную j , которая изначально = i
            while (j > 0 && array[j - 1] > current) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = current;
        }

    }

*/


