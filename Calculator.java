import java.util.Scanner;

public class Calculator {

    public static int performOperation(int a, int b, char operation) {
        switch (operation) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Cannot divide by zero");
                }
                return a / b;
            default:
                throw new IllegalArgumentException("Invalid operation");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter the arithmetic expression: ");
            String expression = scanner.nextLine();

            String[] parts = expression.split("\\s+");
            if (parts.length == 3) {
                int a = Integer.parseInt(parts[0]);
                char operation = parts[1].charAt(0);
                int b = Integer.parseInt(parts[2]);

                validateNumbers(a, b);
                int result = performOperation(a, b, operation);
                System.out.println("Result: " + result);
            } else if (parts.length == 5) {
                int a = Integer.parseInt(parts[0]);
                char operation1 = parts[1].charAt(0);
                int b = Integer.parseInt(parts[2]);
                char operation2 = parts[3].charAt(0);
                int c = Integer.parseInt(parts[4]);
                
                validateNumbers(a, b, c);
                int result = 0;
                if(operation2=='*' || operation2=='/'){
                    result = performOperation(b, c, operation2);
                    result = performOperation(a, result, operation1);
                }else{
                    result = performOperation(a, b, operation1);
                    result = performOperation(result, c, operation2);
                }

                System.out.println("Result: " + result);
            } else {
                throw new IllegalArgumentException("Invalid input format");
            }

        } catch (IllegalArgumentException | ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void validateNumbers(int... numbers) {
        for (int num : numbers) {
            if (num < 1 || num > 10) {
                throw new IllegalArgumentException("Numbers must be between 1 and 10 inclusive");
            }
        }
    }
}
