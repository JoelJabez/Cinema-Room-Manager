package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {

    static Scanner scanner = new Scanner(System.in);
    static int row, seats;
    static double currentIncome = 0,
            totalIncome = 0, counter = 0;

    public static void main(String[] args) {
        row = get_int("Enter the number of rows:" +
                              " ");

        seats = get_int("Enter the number of" +
                                " seats in " +
                                "each row: ");

        char[][] cinemaRoom = new char[row][seats];
        for (int i = 0; i < row; i++) {
            Arrays.fill(cinemaRoom[i], 'S');
        }

        int status = 4;
        while (status != 0) {
            printStatus();
            status = scanner.nextInt();

            if (status == 1) {
                printCinema(cinemaRoom, row, seats);
            } else if (status == 2) {
                buyTicket(cinemaRoom);
            } else if (status == 3) {
                int sum = row * seats;

                if (sum <= 60) {
                    totalIncome = sum * 10;
                } else {
                    int first_row = row / 2;
                    totalIncome = ((first_row * 10) + ((row - first_row) * 8)) * seats;
                }

                statistics();
            }
        }

    }

    public static void printStatus() {
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    public static void printCinema(char[][] cinemaRoom, int row, int column) {
        System.out.println("\nCinema: ");

        System.out.print("  ");
        for (int i = 1; i <= column; i++) {
            System.out.print(i + " ");
        }

        System.out.println();

        for (int i = 1; i <= row; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < column; j++) {
                System.out.print(cinemaRoom[i - 1][j] + " ");
            }
            System.out.println();
        }
    }

    public static int get_int(String word) {
        while (true) {
            System.out.println(word);
            int number = scanner.nextInt();
            if (number > 0) {
                return number;
            }
        }
    }

    public static void buyTicket(char[][] cinemaRoom) {
        do {
            int bookRow = get_int("\nEnter a row " +
                                          "number: ");

            int bookSeats = get_int("\nEnter a seat " +
                                            "number" +
                                            " in that " +
                                            "row: ");
            if (range(bookRow, row) || range(bookSeats, seats)) {
                System.out.println("Wrong input!");

            } else if (cinemaRoom[bookRow - 1][bookSeats - 1] == 'B') {
                System.out.println("That ticket has" +
                                           " already" +
                                           " been " +
                                           "purchased!");
            } else {
                cinemaRoom[bookRow - 1][bookSeats - 1] = 'B';
                counter++;

                System.out.print("\nTicket price: $");
                if (row * seats <= 60) {
                    System.out.println("10");
                    currentIncome += 10;
                } else {
                    int first_row = row / 2;
                    if (bookRow <= first_row) {
                        System.out.println("10");
                        currentIncome += 10;
                    } else {
                        System.out.println("8");
                        currentIncome += 8;
                    }
                }

                return;
            }
        } while (true);
    }

    public static boolean range(int number,
                                int comparable) {
        return 0 > number || number >= comparable + 1;
    }

    public static void statistics() {
        System.out.printf("Number of purchased tickets: %.0f\n", counter);
        System.out.printf("Percentage: %.2f%%\n",
                (counter / (row * seats)) * 100);
        System.out.printf("Current income: $%.0f\n", currentIncome);
        System.out.printf("Total income: $%.0f\n" , totalIncome);
    }
}