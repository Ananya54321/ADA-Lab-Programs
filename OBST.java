import java.util.Scanner;

public class Main {
    public static int sum(int[] freq, int i, int j) {
        int total = 0;
        for (int k = i; k <= j; k++) {
            total += freq[k];
        }
        return total;
    }

    public static void obst(int n, int[] keys, int[] freq) {
        int[][] cost = new int[n][n];

        // Base case: cost of single keys
        for (int i = 0; i < n; i++) {
            cost[i][i] = freq[i];
        }

        // Fill cost matrix for chains of length 2 to n
        for (int L = 2; L <= n; L++) {
            for (int i = 0; i <= n - L; i++) {
                int j = i + L - 1;
                cost[i][j] = Integer.MAX_VALUE;

                // Try making each key in the range the root
                for (int r = i; r <= j; r++) {
                    int leftCost = (r > i) ? cost[i][r - 1] : 0;
                    int rightCost = (r < j) ? cost[r + 1][j] : 0;
                    int totalCost = leftCost + rightCost + sum(freq, i, j);

                    if (totalCost < cost[i][j]) {
                        cost[i][j] = totalCost;
                    }
                }
            }
        }

        // Print the cost matrix
        System.out.println("DP Table (Cost Matrix):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j < i) {
                    System.out.print("   -  ");
                } else {
                    System.out.printf("%5d ", cost[i][j]);
                }
            }
            System.out.println();
        }

        // The optimal cost is in cost[0][n-1]
        System.out.println("The optimal cost of the binary search tree is: " + cost[0][n - 1]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of nodes:");
        int n = sc.nextInt();

        int[] keys = new int[n];
        int[] freq = new int[n];

        System.out.println("Enter the keys:");
        for (int i = 0; i < n; i++) {
            keys[i] = sc.nextInt();
        }

        System.out.println("Enter the frequencies:");
        for (int i = 0; i < n; i++) {
            freq[i] = sc.nextInt();
        }

        obst(n, keys, freq);
    }
}
