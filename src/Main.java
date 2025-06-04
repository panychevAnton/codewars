import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        System.out.println(Solution.solution(new int[]{1,2,3}, new int[]{4,5,6}));
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