package insertSortTest;

import insertSort.InsertSort;
import javafx.util.Pair;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class InsertSortTests {

    private InsertSort insertSort = new InsertSort();


    @DisplayName("Проверка корректости функции сравнения") //Используется для предоставления любого настраиваемого отображаемого имени для тестового класса или тестового метода
    @ParameterizedTest // Используйте аннотацию @ParameterizedTest, чтобы выполнить тест несколько раз, но с разными аргументами. Нам не нужно использовать аннотацию @Test, вместо этого в таких тестах используется только аннотация @ParameterizedTest.
    @CsvSource({"-1,-3", "1,-1", "6,1"}) //Эта аннотация позволяет нам задавать списки аргументов как значения, разделенные запятыми. Каждый CSV токен представляет собой строку CSV и приводит к одному вызову параметризованного теста.
    public void if_first_biggest_than_second(int a, int b) {
        boolean res = insertSort.compare_values(a, b);
        assertFalse(res);
    }

    @DisplayName("Проверка корректости функции сравнения")
    @ParameterizedTest
    @CsvSource({"-1,-1", "0,0", "4,4", "1,-0"})
    public void if_first_equal_second(int a, int b) {
        boolean res = insertSort.compare_values(a, b);
        assertFalse(res);
    }

    /*
    assertTrue() и assertFalse()
    Метод assertTrue()утверждает, что предоставленное условие истинно или предоставленное логическое условие BooleanSupplier истинно.
    Точно так же assertFalse() утверждает, что предоставленное условие ложно.
     */

    @DisplayName("Проверка корректости функции сравнения")
    @ParameterizedTest
    @CsvSource({"-4,-1", "-1,1", "2,5"})
    public void if_first_smallest_than_second(int a, int b) {
        boolean res = insertSort.compare_values(a, b);
        assertTrue(res);
    }
    /*
    @MethodSource
    Она используется для ссылки на один или несколько фабричных методов тестового класса или внешних классов. Фабричный метод должен генерировать поток аргументов, где каждый аргумент в потоке будет использоваться методом, аннотированным @ParameterizedTest.
    Фабричный метод должен быть static, если тестовый класс не аннотирован с помощью @TestInstance(Lifecycle.PER_CLASS).
    Кроме того, фабричный метод не должен принимать аргументы.
     */

    @DisplayName("Проверка корректости функции обратного обхода")
    @ParameterizedTest
    @MethodSource("array_int_and_int_provider")
    public void if_current_value_not_in_left(int[] array, int i, int value) {
        int[] tr_array = {2, 2, 2, 2};
        Pair<Integer, int[]> p = insertSort.go_back(array, i - 1, value);
        int[] arr = p.getValue();
        System.out.println(Arrays.toString(arr));
        assertArrayEquals(tr_array, arr); // сравнивает два массива и передает только в том случае, если они содержат одни и те же элементы в одних и тех же позициях, в противном случае это не удается. Если оба массива равны null , они считаются равными.
    }

    static Stream<Arguments> array_int_and_int_provider() {

        return Stream.of(
                arguments(new int[]{2, 1, 2, 2}, 1, 1),
                arguments(new int[]{2, 2, 1, 2}, 2, 1),
                arguments(new int[]{2, 2, 2, 1}, 3, 1));
    }

    @DisplayName("Проверка корректости функции обратного обхода")
    @ParameterizedTest
    @MethodSource("array_int_and_int_provider_")
    public void if_current_value_in_left(int[] array, int i, int value) {
        int[] tr_array = {1, 2, 2, 2};
        Pair<Integer, int[]> p = insertSort.go_back(array, i - 1, value);
        int[] arr = p.getValue();
        assertArrayEquals(tr_array, arr);
    }
    /*
     assertArrayEquals
     Метод assertArrayEquals() утверждает, что ожидаемый и фактический массивы равны
     Он также имеет перегруженные методы для различных типов данных, например, boolean[], char[], int[] и т. д. Он также выдает сообщения об ошибках, которые будут напечатаны в случае сбоя теста. например
     */

    static Stream<Arguments> array_int_and_int_provider_() {

        return Stream.of(
                arguments(new int[]{1, 2, 2, 2}, 0, 1));
    }


    @DisplayName("Проверка корректости функции сортировки")
    @ParameterizedTest
    @MethodSource("arrays_example_provider")
    public void if_array_sort_method_is_correct(int[] array) {
        int[] array_tr = {0, 1, 2, 2, 2, 3, 4, 9, 9, 9};
        assertArrayEquals(insertSort.sort(array), array_tr);
    }

    static Stream<Arguments> arrays_example_provider() {
        return Stream.of(
                arguments(new int[]{9, 9, 2, 3, 0, 1, 2, 4, 9, 2}),
                arguments(new int[]{2, 1, 4, 9, 0, 2, 3, 2, 9, 9}),
                arguments(new int[]{9, 9, 9, 4, 3, 2, 2, 2, 1, 0}),
                arguments(new int[]{9, 9, 9, 4, 3, 2, 2, 2, 0, 1}));
    }

    @DisplayName("Проверка корректости функции сортировки")
    @ParameterizedTest
    @MethodSource("all_elements_arrays_is_equal_example_provider")
    public void if_all_elements_in_array_is_equal(int[] array) {
        int[] array_tr = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
        assertArrayEquals(insertSort.sort(array), array_tr);
    }

    static Stream<Arguments> all_elements_arrays_is_equal_example_provider() {
        return Stream.of(
                arguments(new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2}));
    }

    @DisplayName("Проверка корректости функции сортировки при пустом массиве")
    @ParameterizedTest
    @MethodSource("arrays_empty_provider")
    public void if_array_is_empty(int[] array) {
        int[] array_tr = {};
        assertArrayEquals(insertSort.sort(array), array_tr);
    }

    static Stream<Arguments> arrays_empty_provider() {
        return Stream.of(
                arguments(new int[]{ }));
    }

    @DisplayName("Проверка корректости функции сортировки при массиве с 1 элементом")
    @ParameterizedTest
    @MethodSource("arrays_one_element_provider")
    public void if_array_is_i_empty(int[] array) {
        int[] array_tr = {1};
        assertArrayEquals(insertSort.sort(array), array_tr);
    }

    static Stream<Arguments> arrays_one_element_provider() {
        return Stream.of(
                arguments(new int[]{1}));
    }


}
