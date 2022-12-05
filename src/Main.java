import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        System.out.println("Задание 1");
        int[] array1 = new int[]{72, 33, -73, 84, -12, -3, 13, -13, -68};
        System.out.println(Arrays.toString(encrypt("Hello")));
        System.out.println(Arrays.toString(encrypt("Sunshine")));
        System.out.println(decrypt(array1));

        System.out.println("Задание 2");
        System.out.println(canMove("Rook", "A8", "H8"));
        System.out.println(canMove("Bishop", "A7", "G1"));
        System.out.println(canMove("Queen", "C4", "D6"));

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

    // принимаем имя шахматной фигуры, ее положение и целевую позицию.
    // возвращаем true, если фигура может двигаться к цели, и false, если она не может этого сделать.
    public static boolean canMove(String figure, String start, String finish){
        if ("King".equals(figure)){
            // король ходит только на одну клеточку в любую сторону
            // разницы между значениями буковок и цифорок должны быть меньше или равны 1
            // берём модуль т.к. не важно, в какую сторону идём
            return (Math.abs((int) start.charAt(0) - (int) finish.charAt((0))) <= 1)
                    && (Math.abs((int) start.charAt(1) - (int) finish.charAt((1))) <=1);
        } else if ("Queen".equals(figure)){
            // ферзь ходит в любую сторону на любое расстояние
            // сделать ход можно, если оно на одной прямой
            // или наискосок (разницы между буквами и цифрами должны быть равны)
            return (start.charAt(0) == finish.charAt((0))) || (start.charAt(1) == finish.charAt((1))) ||
                    (Math.abs((int) start.charAt(0) - (int) finish.charAt((0))) ==
                            Math.abs((int) start.charAt(1) - (int) finish.charAt((1))));
        } else if ("Bishop".equals(figure)){
            // слон ходит только по диагонали
            // изменение букв == изменение цифр
            return (Math.abs((int) start.charAt(0) - (int) finish.charAt((0))) ==
                    Math.abs((int) start.charAt(1) - (int) finish.charAt((1))));
        } else if ("Knight".equals(figure)) {
            // конь ходит буквой г
            // или на 1 шаг по буквам и 2 по цифрам, или 2 шага по буквам и 1 по цифрам
            return (Math.abs((int) start.charAt(0) - (int) finish.charAt((0))) == 1)
                    && (Math.abs((int) start.charAt(1) - (int) finish.charAt((1))) ==2)
                    || (Math.abs((int) start.charAt(0) - (int) finish.charAt((0))) == 2)
                    && (Math.abs((int) start.charAt(1) - (int) finish.charAt((1))) ==1);
        } else if ("Rook".equals(figure)) {
            // ладья ходит по прямым
            return (start.charAt(0) == finish.charAt((0))) || (start.charAt(1) == finish.charAt((1)));
        } else if ("Pawn".equals(figure)){
            // пешка ходит на 1 клетку только вперёд
            // если первый ход - на 2 вперёд (2 варианта. зависит от того, с какой стороны доски идём)
            return ((start.charAt(0) == finish.charAt(0)) && (finish.charAt(1) - start.charAt(1) == 1))
                    || (start.charAt(1) == 7 && finish.charAt(1) == 5) ||
                    (start.charAt(1) == 2 && finish.charAt(1) == 4);
        }
        return false;
    }
}