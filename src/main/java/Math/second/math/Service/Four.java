package Math.second.math.Service;

import org.springframework.stereotype.Service;

import static java.lang.Math.pow;

// 0.2 * x^2 + 0.3 * y^2 + x - 1= 0
// 0.1 * x^2 + 1 - y = 0
@Service
public class Four extends AbstractSystem{

    @Override
    double resultOfFunctionForFirst(double x, double y) {
        return 0.2 * pow(x, 2) + 0.3 * pow(y, 2) + x - 1;
    }

    @Override
    double resultOfFunctionForSecond(double x, double y) {
        return 0.1 * pow(x, 2) + 1 - y;
    }

    @Override
    double resultOfQForFirst(double x, double y) {
        return 1 - 0.2 * pow(x, 2) - 0.3 * pow(y, 2);
    }

    @Override
    double resultOfQForSecond(double x, double y) {
        return 0.1 * pow(x, 2) + 1;
    }

    @Override
    double derivativesQForFirstX(double x, double y) {
        return -0.4 * x;
    }

    @Override
    double derivativesQForFirstY(double x, double y) {
        return -0.6 * y;
    }

    @Override
    double derivativesQForSecondX(double x, double y) {
        return 0.2 * x;
    }

    @Override
    double derivativesQForSecondY(double x, double y) {
        return 0;
    }

    public static void main(String[] args) {
        Four task = new Four();
        System.out.println(task.methodIteration(0.5, 0.8, 1, 1.1, 0.0001));
    }
}
