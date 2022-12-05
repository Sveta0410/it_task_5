import javax.management.StringValueExp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


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

        System.out.println("Задание 3");
        System.out.println(canComplete("butl", "beautiful"));
        System.out.println(canComplete("butlz", "beautiful"));
        System.out.println(canComplete("tulb", "beautiful"));
        System.out.println(canComplete("bbutl", "beautiful"));

        System.out.println("Задание 4");
        System.out.println(sumDigProd(16, 28));
        System.out.println(sumDigProd(0));
        System.out.println(sumDigProd(1, 2, 3, 4, 5, 6));

        System.out.println("Задание 5");
        String[] array2 = new String[]{"toe", "ocelot", "maniac"};
        String[] array3 = new String[]{"many", "carriage", "emit", "apricot", "animal"};
        String[] array4 = new String[]{"hoops", "chuff", "bot", "bottom"};
        System.out.println(Arrays.toString(sameVowelGroup(array2)));
        System.out.println(Arrays.toString(sameVowelGroup(array3)));
        System.out.println(Arrays.toString(sameVowelGroup(array4)));

        System.out.println("Задание 6");
        System.out.println(validateCard(1234567890123456L));
        System.out.println(validateCard(1234567890123452L));

        System.out.println("Задание 7");
        System.out.println(numToEng(0));
        System.out.println(numToEng(18));
        System.out.println(numToEng(126));
        System.out.println(numToEng(909));
        System.out.println(numToRu(0));
        System.out.println(numToRu(18));
        System.out.println(numToRu(126));
        System.out.println(numToRu(909));

//        System.out.println("Задание 8");
//        System.out.println(getSha256Hash("password123"));
//        System.out.println(getSha256Hash("Fluffy@home"));
//        System.out.println(getSha256Hash("Hey dude!"));

        System.out.println("Задание 9");
        System.out.println(correctTitle("jOn-SnoW, kINg IN thE noRth."));
        System.out.println(correctTitle("sansa stark, lady of winterfell."));
        System.out.println(correctTitle("TYRION LANNISTER, HAND OF THE QUEEN."));


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

    // проверяем может ли слово быть завершено (буквы удалять и переставлять нельзя)
    public static boolean canComplete(String start, String result){
        // индекс (номер) который мы будем в последующих итерациях
        // = нашли в итоговом слове первую букву -> ищем следующую(вторую) и т.д.
        int index = 0;
        // проходимся по всем элементам
        for (int i = 0; i < result.length(); i++){
            // если букву нашли - ищем следующую (index + 1)
            if (start.charAt(index) == result.charAt(i)){
                index++;
            }
        }
        // если все буквы нашли, будет true
        // иначе значение index будет меньше длины первой строки
        return index == start.length();
    }

    // принимаем числа в качестве аргументов, складываем их вместе и возвращаем
    // произведение цифр до тех пор, пока ответ не станет длиной всего в 1 цифру.
    public static int sumDigProd(int... x){
        int result = 0;
        int product = 0;
        // сумма чисел
        for (int i: x){
            result += i;
        }
        // перемножаем пока результат не станет длиной в 1 цифру
        while (result >= 10) {
            product = 1;
            // перемножаем все цифры числа
            while (result != 0) {
                product *= result % 10;
                result /= 10;
            }
            result = product;
        }
        return result;
    }

    // выбираем все слова, имеющие все те же гласные (в любом порядке и / или количестве),
    // что и первое слово, включая первое слово
    public static String[] sameVowelGroup(String[] words){
        // hashset для хранения гласных первого слова в единственном экземпляре
        Set<Character> firstVovels = findVovels(words[0]);

        // добавляем первое слово в итоговый массив
        ArrayList<String> result = new ArrayList<>();
        result.add(words[0]);

        // перебираем все слова
        for (int i = 1; i < words.length; i++){
            Set<Character> temp = findVovels(words[i]);
            if (temp.equals(firstVovels)){
                result.add(words[i]);
            }
        }
        return result.toArray(new String[]{});

    }
    // вспомогательная функция, помогающая найти уникальные гласные в слове
    public static Set<Character> findVovels(String word){
        // убираем согласные
        String wordVovels = word.replaceAll("[bcdfghjklmnpqrstvwxyz]", "");
        // hashset для хранения гласных в единственном экземпляре
        Set<Character> vovels = new HashSet<>();
        // добавляем все гласные слова в hashset
        for (char i : wordVovels.toCharArray()){
            vovels.add(i);
        }
        return vovels;
    }

    // проверяем, является ли число действительным номером кредитной карты
    public static boolean validateCard(long x) {

        // длина числа
        int xLen = String.valueOf(x).length();
        // проверяем, подходит ли нам такая длина числа
        if (xLen < 14 || xLen > 16) {
            return false;
        }
        // контрольная цифра
        int checkDigit = (int) x % 10;
        // последнюю цифру убираем
        x = x / 10;
        // переворачиваем число
        StringBuilder newX = new StringBuilder(String.valueOf(x)).reverse();
        // переменная, необходимая для последующих вычислений (для удваивания значений в нечётных позициях)
        int number = 0;
        // переменная для суммы
        int sum = 0;
        // проходим по всем цифрам. Удваиваем на нечётных позициях + находим сумму
        for (int i = 0; i < newX.length(); i++) {
            number = newX.charAt(i);
            // если позиция нечётная
            if ((i + 1) % 2 != 0) {
                number *= 2;
                // если удвоенное значение имеет более 1 цифры, складываем цифры вместе
                if (number >= 10) {
                    // слагаемых 2 т.к. максимальное значение number 9*2=18
                    number = number % 10 + (number / 10) % 10;
                }
            }
            sum += number;
        }
        return (10 - (sum % 10)) == checkDigit;

    }

    // возвращаем строковое представление целого числа, написанное на английском языке
    public static String numToEng(int x) {
        // первые 20 цифр
        String[] first20 = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
                "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        // десятки
        String[] tens = {"", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
        if (x == 0){
            return "zero";
        }
        if (x < 20){
            return first20[x];
        } else if (x < 100){
            String p = " ";
            // если просто 20, пробел не нужен после twenty
            if (x % 10 == 0) {
                p = "";
            }
            return tens[x / 10] + p + first20[x % 10];
        } else {
            String p1 = " ";
            String p2 = " ";
            // если у нас десаток нет, то пробел не ставим между сотнями и единицами
            if ((x / 10) % 10 == 0) {
                p2 = "";
                // если единиц нет, после hundred пробел не ставим
                if (x % 10 == 0) {
                    p1 = "";
                }
            }
            return first20[x / 100] + " hundred" + p1 + tens[(x / 10) % 10] + p2 + first20[x % 10];
        }
    }

    // возвращаем строковое представление целого числа, написанное на русском языке
    public static String numToRu(int x) {
        // первые 20 цифр
        String[] first20 = {"", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять", "десять",
                "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать",
                "восемнадцать", "девятнадцать"};
        // десятки
        String[] tens = {"", "ten", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят",
                "восемьдесят", "девяносто"};
        String[] hund = {"", "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот",
                "восемьсот", "девятьсот"};
        if (x == 0){
            return "ноль";
        }
        if (x < 20){
            return first20[x];
        } else if (x < 100){
            String p = " ";
            if (x % 10 == 0) {
                p = "";
            }
            return tens[x / 10] + p + first20[x % 10];
        } else {
            String p1 = " ";
            String p2 = " ";
            // если у нас десаток нет, то пробел не ставим между сотнями и единицами
            if ((x / 10) % 10 == 0) {
                p2 = "";
                // если единиц нет, после hundred пробел не ставим
                if (x % 10 == 0) {
                    p1 = "";
                }
            }
            return hund[x / 100] + p1 + tens[(x / 10) % 10] + p2 + first20[x % 10];
        }
    }
//
//    // возвращаем безопасный хеш SHA-256 для данной строки
//    public static String getSha256Hash(String str) throws NoSuchAlgorithmException {
//        MessageDigest digest = MessageDigest.getInstance("SHA-256");
//        byte[] hash = digest.digest(str.getBytes(StandardCharsets.UTF_8));
//
//        return hash.toString();
//    }

    // возвращает строку с правильным регистром для заголовков символов
    public static StringBuilder correctTitle(String str) {
        String[] words = str.split(" ");
        String alwaysLover = "and the of in";
        StringBuilder result = new StringBuilder();
        String word;
        // перебираем слова
        for (int i = 0; i < words.length; i++) {
            // если это не and the of in
            word = words[i];
            if (word.contains("-")){
            // Дефисные слова считаются отдельными словами.
            String[] parts = word.split("-");
            result.append(String.valueOf(parts[0].charAt(0)).toUpperCase() +
                    parts[0].substring(1).toLowerCase() + "-" +
                    String.valueOf(parts[1].charAt(0)).toUpperCase() +
                    parts[1].substring(1).toLowerCase());
            } else if (!alwaysLover.contains(word)) {
                result.append(String.valueOf(word.charAt(0)).toUpperCase() +
                        word.substring(1).toLowerCase());
            } else {
                result.append(word.toLowerCase());
            }
            // если это не последнее слово - прибавляем пробел
            if (i != words.length - 1) {
                result.append(" ");
            }
        }
        return result;
    }
}