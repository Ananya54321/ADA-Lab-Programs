import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SubsetSum {

    static void subsetSum(int[] arr, int n, int idx, List<List<Integer>> res, int k, List<Integer> temp) {
        if (idx == n) {
            if (k == 0) {
                res.add(new ArrayList<>(temp));
            }
            return;
        }

        subsetSum(arr, n, idx + 1, res, k, temp);

        if (k >= arr[idx]) {
            temp.add(arr[idx]);
            subsetSum(arr, n, idx + 1, res, k - arr[idx], temp);
            temp.remove(temp.size() - 1); 
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter n: ");
        int n = sc.nextInt();

        int[] arr = new int[n];
        System.out.println("Enter the array elements: ");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print("Enter the sum: ");
        int k = sc.nextInt();

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();

        subsetSum(arr, n, 0, res, k, temp);

        System.out.println("The subsets having sum " + k + " are:");
        for (List<Integer> subset : res) {
            System.out.println(subset);
        }

        sc.close();
    }
}
