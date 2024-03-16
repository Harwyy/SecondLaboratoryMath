package Math.second.math.Service;

import org.springframework.stereotype.Service;

import static java.lang.Math.pow;

// 0.1 x^2 + x + 0.2 y^2 - 0.3
// 0,2 x^2 + y + 0.1 xy - 0.7
@Service
public class Five extends AbstractSystem{
    @Override
    double resultOfFunctionForFirst(double x, double y) {
        return 0.1 * pow(x, 2) + x + 0.2 * pow(y, 2) - 0.3;
    }

    @Override
    double resultOfFunctionForSecond(double x, double y) {
        return 0.2 * pow(x, 2) + y + 0.1 * x * y - 0.7;
    }

    @Override
    double resultOfQForFirst(double x, double y) {
        return 0.3 - 0.1 * pow(x, 2) - 0.2 * pow(y,2);
    }

    @Override
    double resultOfQForSecond(double x, double y) {
        return 0.7 - 0.2 * pow(x, 2) - 0.1 * x * y;
    }

    @Override
    double derivativesQForFirstX(double x, double y) {
        return -0.2 * x;
    }

    @Override
    double derivativesQForFirstY(double x, double y) {
        return -0.4 * y;
    }

    @Override
    double derivativesQForSecondX(double x, double y) {
        return -0.4 * x - 0.1 * y;
    }

    @Override
    double derivativesQForSecondY(double x, double y) {
        return -0.1 * x;
    }

    public static void main(String[] args) {
        Five task = new Five();
        System.out.println(task.methodIteration(-1, 1, -1, 1, 0.0001));
        System.out.println(task.conditionOfIteration(-1, 1, -1, 1));

    }
}
