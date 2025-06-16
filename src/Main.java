import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.LongBinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/*
Testing [[69, 130], [87, 1310], [30, 40]]
Actual (262,34060)(26,34060)(8515,34060)
(18078,34060)(2262,34060)(25545,34060)
Expect (18078,34060)(2262,34060)(25545,34060)
 */

public class Main {
    public static void main(String[] args) {
        var list = Permutations.singlePermutations("abc");
        System.out.println(String.join(",", list));
    }
}


//task: Mean Square Error
class Solution {
    public static double solution(int[] arr1, int[] arr2) {
        return IntStream.range(0, arr1.length)
                .mapToDouble(i -> Math.pow((arr1[i] > arr2[i] ? arr1[i] - arr2[i] : arr2[i] - arr1[i]), 2))
                .average()
                .orElse(0.0);
    }
}

//task: Common Denominators
class Fracts {
    public static String convertFrac(long[][] lst) {
        return switch (lst.length) {
            case 0 -> "";
            case 1 -> lst[0].length == 0 ? "" : "(" + lst[0][0] + "," + lst[0][1] + ")";
            default -> getConvertetListAsString(lst);
        };
    }

    static String getConvertetListAsString(long[][] lst) {
        lst = Arrays.stream(lst).map(arr -> {
            var gcd = gcd(arr[0], arr[1]);
            return new long[]{arr[0] / gcd, arr[1] / gcd};
        }).toArray(long[][]::new);

        long tempLCM = lcm.applyAsLong(lst[0][1], lst[1][1]);

        for (int i = 1; i < lst.length; ++i)
            tempLCM = lcm.applyAsLong(tempLCM, lst[i][1]);

        long listLCM = tempLCM;
        return Arrays.stream(lst)
                .map(x -> "(" + listLCM / x[1] * x[0] + "," + listLCM + ")")
                .collect(Collectors.joining(""));
    }

    static LongBinaryOperator lcm = (a, b) -> a * b / gcd(a , b);

    static long gcd(long a, long b) {
        long greatest = Math.max(a, b);
        long lowest = Math.min(a, b);
        long diff = greatest - lowest;
        return diff == 0 ? greatest : gcd(lowest, diff);
    }
}

//task: So Many Permutations!
class Permutations {
    enum ShiftDirection {
        LEFT, RIGHT
    }
    static ShiftDirection currDirection = ShiftDirection.LEFT;

    public static List<String> singlePermutations(String s) {

        var stepCount = 0;
        List<String> permutations = new ArrayList<>();
        while (stepCount < s.length() * 2) {
            if (stepCount == s.length())
                currDirection = ShiftDirection.RIGHT;
            s = shift(s);
            permutations.add(s);
            stepCount++;
        }
        return permutations;
    }

    static String shift(String s){
        return (currDirection == ShiftDirection.LEFT) ?
                s.substring(1) + s.charAt(0) :
                s.substring(s.length() - 1) + s.substring(0, s.length() - 1);
    }
}