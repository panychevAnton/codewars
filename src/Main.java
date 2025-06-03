import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(Solution.solution(new int[]{1,2,3}, new int[]{4,5,6}));
    }
}

//task: Mean Square Error
class Solution {
    public static double solution(int[] arr1, int[] arr2) {
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] > arr2[i])
                arr1[i] = (int) Math.pow(arr1[i] - arr2[i], 2);
            else
                arr1[i] = (int) Math.pow(arr2[i] - arr1[i], 2);
        }

        return Arrays.stream(arr1).average().orElse(0);
    }
}