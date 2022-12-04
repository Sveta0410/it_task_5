import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        System.out.println("Задание 1");
        int[] array1 = new int[]{72, 33, -73, 84, -12, -3, 13, -13, -68};
        System.out.println(Arrays.toString(encrypt("Hello")));
        System.out.println(Arrays.toString(encrypt("Sunshine")));
        System.out.println(decrypt(array1));

    }
    // принимаем строку и возвращаем закодированное сообщение
    // первая буква -> её символьный код, следующие элементы - различия между символам
    public static int[] encrypt(String str){
        char[] chars = str.toCharArray(); // массив с символами из строки
        int[] result = new int[chars.length];
        result[0] = chars[0]; // первая буква -> её символьный код
        for (int i = 1; i < chars.length; i++){
            result [i] = chars [i] - chars [i - 1];
        }
        return result;

    }
    // принимаем массив и возвращаем декодированное сообщение
    public static String decrypt(int[] arr){
        StringBuilder result = new StringBuilder();
        int prev = 0; // значение ascii прошлого символа
        for (int i = 0; i < arr.length; i++){
            result.append((char) (prev + arr[i]));
            prev = result.charAt(i);
        }
        return result.toString();
    }
}