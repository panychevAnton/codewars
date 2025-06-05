import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        System.out.println(Fracts.convertFrac(new long[][] { {1, 2}, {1, 3}, {10, 40} }));
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
        lst = Arrays.stream(lst).map(arr -> {
            var gcd = gcd(arr[0], arr[1]);
            return new long[]{arr[0] / gcd, arr[1] / gcd};
        }).toArray(long[][]::new);
        var generalGcd = gcd(lst[0][1], lst[1][1]);
        for (int i = 1; i < lst.length; ++i)
            generalGcd = gcd(generalGcd, lst[i][1]);
        long multi = Arrays.stream(lst).mapToLong(v -> v[1]).reduce(1, (i, j) -> i * j);
        var lcm = multi / generalGcd;
        return Arrays.stream(lst).map(x -> "(" + lcm / x[1] + "," + lcm + ")").collect(Collectors.joining("")); //(6,12)(4,12)(3,12)
    }

    public static long gcd(long a, long b) {
        long greatest = Math.max(a, b);
        long lowest = Math.min(a, b);
        long diff = greatest - lowest;
        return diff == 0 ? greatest : gcd(lowest, diff);
    }
}