package Math.second.math.Service;

import org.springframework.stereotype.Service;

import static java.lang.Math.*;

// x + 0.01 * cos(x+y) = 0
// 0.01 e ^ (x+3) - y = 0
@Service
public class Six extends AbstractSystem{
    @Override
    double resultOfFunctionForFirst(double x, double y) {
        return x + 0.01 * cos(x+y);
    }

    @Override
    double resultOfFunctionForSecond(double x, double y) {
        return 0.01 * Math.pow(Math.E, x+3) - y;
    }

    @Override
    double resultOfQForFirst(double x, double y) {
        return -0.01 * cos(x+y);
    }

    @Override
    double resultOfQForSecond(double x, double y) {
        return 0.01 * pow(E, x+3);
    }

    @Override
    double derivativesQForFirstX(double x, double y) {
        return 0.01 * sin(x+y);
    }

    @Override
    double derivativesQForFirstY(double x, double y) {
        return 0.01 * sin(x+y);
    }

    @Override
    double derivativesQForSecondX(double x, double y) {
        return 0.01 * pow(E, x+3);
    }

    @Override
    double derivativesQForSecondY(double x, double y) {
        return 0;
    }

    public static void main(String[] args) {
        Six task = new Six();
        System.out.println(task.methodIteration(-0.1, 0.1, 0, 0.3, 0.001));
    }
}
