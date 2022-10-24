import java.util.ArrayList;
import java.util.List;

public class Main {
    //Create a list of 1,000,000 random integers and put in variable
    public static List<Integer> randomList = new ArrayList<>();

    public static void main(String[] args) {
        //Create a list of 1,000,000 random positive integers and put in variable

        for (int i = 0; i < 1000000; i++) {
            randomList.add((int) (Math.random() * (3000 - 1))+ 1);
        }


        //divide the list into two sublist
        List<Integer> firstHalf = randomList.subList(0, 500000);
        List<Integer> secondHalf = randomList.subList(500000, 1000000);


        //Combine the results of Calculate threads to get the average and sum of the complete  list
        Calculate firstHalfCalculate = new Calculate(firstHalf);
        Calculate secondHalfCalculate = new Calculate(secondHalf);

        firstHalfCalculate.start();
        secondHalfCalculate.start();
        // .start() starts the thread

        try {
            firstHalfCalculate.join();
            secondHalfCalculate.join();
            // .join() waits for the thread to finish before continuing
            // .join() is used to make sure the threads are finished before the main thread continues
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("THREAD: The sum of the list is: " + (firstHalfCalculate.getSum() + secondHalfCalculate.getSum()));
        System.out.println("THREAD: The average of the list is: " + (firstHalfCalculate.getAverage() + secondHalfCalculate.getAverage()) / 2);





    }



    //Create two threads to calculate the average and sum of the sublist
    public static class Calculate extends Thread {
        private List<Integer> list;
        private int sum;
        private double average;

        public Calculate(List<Integer> list) {
            this.list = list;
        }

        public void run() {
            for (Integer integer : list) {
                sum += integer;
            }
            average = sum / list.size();
        }

        public int getSum() {
            return sum;
        }

        public double getAverage() {
            return average;
        }
    }



}