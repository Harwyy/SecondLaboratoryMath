package Math.second.math.Service;

import org.springframework.stereotype.Service;

// f(x) = x^3 - 3.125 x^2 -3.5 x + 2.458
@Service
public class One extends AbstractNotSystem{

    @Override
    double resultOfFunction(double x) {
        return Math.pow(x, 3) - 3.125 * Math.pow(x, 2) - 3.5 * x + 2.458;
    }

    @Override
    double firstDerivatives(double x) {
        return 3 * Math.pow(x, 2) - 6.25 * x - 3.5;
    }

    @Override
    double secondDerivatives(double x) {
        return 6 * x - 6.25;
    }

    @Override
    double resultOfQ(double x, double lambda) {
        return x + lambda * (Math.pow(x, 3) - 3.125 * Math.pow(x, 2) - 3.5 * x + 2.458);
    }

    @Override
    double firstDerivativesOfQ(double x, double lambda) {
        return 1 + lambda * (3 * Math.pow(x, 2) - 6.25 * x - 3.5);
    }

    public static void main(String[] args) {
        One task = new One();
        System.out.println(task.methodNewton(2.6, 4, 0.0001));
        System.out.println(task.methodChord(2.7, 4, 0.01));
        System.out.println(task.methodOfIteration(2.6, 4, 0.0001));
    }
}
