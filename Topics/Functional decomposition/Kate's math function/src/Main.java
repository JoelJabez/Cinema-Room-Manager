import java.util.Scanner;

class MultipleFunction {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double x = scanner.nextDouble();
        System.out.println(f(x));
    }

    public static double f(double x) {
        double square = x * x;

        if (x <= 0) {
            return f1(square);
        } else if (0 < x && x < 1) {
            return f2(square);
        }
        return f3(square);
    }

    //implement your methods here
    public static double f1(double x) {
        return x + 1;
    }

    public static double f2(double x) {
        return 1 / x;
    }

    public static double f3(double x) {
        return x - 1;
    }
}