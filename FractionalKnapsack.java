import java.util.*;

public class Main {
    static class Item {
        double value, weight, ratio;

        Item(double value, double weight) {
            this.value = value;
            this.weight = weight;
            this.ratio = value / weight; // Value-to-weight ratio
        }
    }

    public static double fractionalKnapSack(double capacity, List<Item> items) {
        // Sort items by value-to-weight ratio in descending order
        items.sort((a, b) -> Double.compare(b.ratio, a.ratio));

        double profit = 0.0;

        for (Item item : items) {
            if (capacity == 0) {
                break;
            }

            // Take the minimum of the item's weight or remaining capacity
            double stake = Math.min(capacity, item.weight);
            profit += stake * item.ratio; // Add the profit for the stake
            capacity -= stake; // Reduce the capacity by the stake
        }

        return profit;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of items:");
        int n = sc.nextInt();

        List<Item> items = new ArrayList<>();
        System.out.println("Enter the value and weight for each item:");

        for (int i = 0; i < n; i++) {
            System.out.print("Value[" + (i + 1) + "]: ");
            double value = sc.nextDouble();

            System.out.print("Weight[" + (i + 1) + "]: ");
            double weight = sc.nextDouble();

            items.add(new Item(value, weight));
        }

        System.out.println("Enter the capacity of the knapsack:");
        double capacity = sc.nextDouble();

        double profit = fractionalKnapSack(capacity, items);
        System.out.printf("Maximum profit of the knapsack is: %.2f%n", profit);
    }
}
