import java.util.function.*;

public class Calculator {
    static Supplier<Calculator> instance = Calculator::new;

    final static String ERROR_DIVIDE_ZERO = "You cannot divide by zero!";

    UnaryOperator<Integer> pow = x -> x * x;
    UnaryOperator<Integer> abs = x -> x > 0 ? x : x * -1;

    BinaryOperator<Integer> plus = (x, y) -> x + y;
    BinaryOperator<Integer> minus = (x, y) -> x - y;
    BinaryOperator<Integer> multiply = (x, y) -> x * y;
    BinaryOperator<Integer> divide = (x, y) -> this.abs.apply(y) > 0.00001 ? x / y :
            Integer.MAX_VALUE * Integer.signum(x);

    Predicate<Integer> isPositive = x -> x > 0;

    Integer divideZero(Integer x, Integer y) {
        if (this.abs.apply(y) > 0.00001) return this.divide.apply(x,y);
        else throw new RuntimeException(ERROR_DIVIDE_ZERO);
    }

    Consumer<Integer> println = System.out::println;
}