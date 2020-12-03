package ru.ndg;

import ru.ndg.calculator.Calculate;
import ru.ndg.calculator.CalculateImpl;
import ru.ndg.calculator.common.ParserExpressionImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

import static ru.ndg.calculator.common.Operations.EXIT;

public class Application {
    public static void main(String[] args) {

        Calculate calculate = new CalculateImpl(new ParserExpressionImpl());
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                showDialog();
                String expression = reader.readLine();
                if (Objects.nonNull(expression) && EXIT.getOperation().equals(expression)) {
                    break;
                }
                System.out.println(calculate.getResult(expression));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void showDialog() {
        System.out.println("Введите римские или арабские числа одной строчкой, знак операции между ними. Пример (I + II) или (1 + 2) \n"
        + "Для выхода введите exit");
    }
}
