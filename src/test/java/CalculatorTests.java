import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTests {
    Calculator calculator;

    @BeforeEach
    public void init() {
        calculator = new Calculator();
    }
    // Метод divideZero по сути как divide, только при делении на 0 дает исключение

    @ParameterizedTest
    @CsvSource({"1,1,1",
            "2,2,1",
            "12,3,4",
            "20,2,10",
            "45,2,22",
            "100,3,33",
            "105,100,1",
            "645,789,0"})
    public void divideTestSuccess(int left, int right, int expected) {
        assertEquals(expected, calculator.divide.apply(left, right));
        assertEquals(expected, calculator.divideZero(left, right));
    }

    @Test
    public void divideTestWithZeroArgument() {
        assertTrue(calculator.divide.apply(6, 0) > 0);
    }

    @Test
    public void divideZeroTestWithZeroArgumentThrowException() throws RuntimeException {
        Throwable thrown = assertThrows(RuntimeException.class, () -> calculator.divideZero(6, 0));
        assertEquals(Calculator.ERROR_DIVIDE_ZERO, thrown.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 5, 100})
    public void isPositiveTestSuccess(int argument) {
        assertTrue(calculator.isPositive.test(argument));
    }

    @ParameterizedTest
    @ValueSource(ints = {-3, 0, -100})
    public void isPositiveTestUnsuccessful(int argument) {
        assertFalse(calculator.isPositive.test(argument));
    }

    @ParameterizedTest
    @CsvSource({"1,1,1",
            "2,2,4",
            "12,3,36",
            "1,0,0",
            "-1,0,0",
            "-1,-5,5",
            "-1,-0,0",
            "-5,1,-5"})
    public void multiplyTestSuccess(int left, int right, int expected) {
        assertEquals(expected, calculator.multiply.apply(left, right));
    }

    @ParameterizedTest
    @CsvSource({"1,1",
            "2,4",
            "3,9",
            "1,1",
            "-1,1",
            "-5,25",
            "0,0"})
    public void powTestSuccess(int arg, int expected) {
        assertEquals(expected, calculator.pow.apply(arg));
    }

    @ParameterizedTest
    @CsvSource({"-1,1",
            "-2,2",
            "0,0",
            "-0,0",
            "1,1",
            "5,5"})
    public void absTestSuccess(int arg, int expected) {
        assertEquals(expected, calculator.abs.apply(arg));
    }

    @ParameterizedTest
    @CsvSource({"1,1,2",
            "2,2,4",
            "12,3,15",
            "1,0,1",
            "-1,0,-1",
            "-1,-5,-6",
            "-1,-0,-1",
            "-5,1,-4",
            "5,-1,4"})
    public void plusTestSuccess(int left, int right, int expected) {
        assertEquals(expected, calculator.plus.apply(left, right));
    }

    @ParameterizedTest
    @CsvSource({"1,1,0",
            "12,3,9",
            "1,0,1",
            "-1,0,-1",
            "-1,-5,4",
            "-1,-0,-1",
            "-5,1,-6",
            "1,-5,6",
            "5,-1,6"})
    public void minusTestSuccess(int left, int right, int expected) {
        assertEquals(expected, calculator.minus.apply(left, right));
    }

}