import java.util.*;

public class Main {
    static class Job {
        int profit, day, id;

        Job(int profit, int day, int id) {
            this.profit = profit;
            this.day = day;
            this.id = id; 
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of jobs: ");
        int n = sc.nextInt();

        List<Job> jobs = new ArrayList<>();
        System.out.println("Enter the profit and deadline for the jobs:");
        for (int i = 0; i < n; i++) {
            int profit = sc.nextInt();
            int day = sc.nextInt();
            jobs.add(new Job(profit, day - 1, i + 1)); 
        }

        System.out.println("Enter the number of days:");
        int days = sc.nextInt();

        int[] res = new int[days];
        Arrays.fill(res, -1);

        jobs.sort((a, b) -> b.profit - a.profit);

        int totalProfit = 0;
        List<Integer> selectedJobs = new ArrayList<>();

        for (Job job : jobs) {
            int deadline = job.day;

            while (deadline >= 0 && res[deadline] != -1) {
                deadline--;
            }

            if (deadline >= 0) {
                res[deadline] = job.id; 
                selectedJobs.add(job.id); 
                totalProfit += job.profit;
            }
        }

        System.out.println("Selected jobs are:");
        for (int id : selectedJobs) {
            System.out.print("Job" + id + " ");
        }
        System.out.println();

        System.out.println("Total profit: " + totalProfit);
    }
}
