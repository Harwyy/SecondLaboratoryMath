package Math.second.math.Service;

import org.springframework.stereotype.Service;

// f(x) = 4x^2 + 12x + 8
@Service
public class Three extends AbstractNotSystem{
    @Override
    double resultOfFunction(double x) {
        return 4 * Math.pow(x, 2) + 12 * x + 8;
    }

    @Override
    double firstDerivatives(double x) {
        return 8 * x + 12;
    }

    @Override
    double secondDerivatives(double x) {
        return 8;
    }

    @Override
    double resultOfQ(double x, double lambda) {
        return x + lambda * (4 * Math.pow(x, 2) + 12 * x + 8);
    }

    @Override
    double firstDerivativesOfQ(double x, double lambda) {
        return 1 + lambda * (8 * x + 12);
    }

    public static void main(String[] args) {
        Three task = new Three();
        System.out.println(task.methodNewton(-1.01 , -0.9, 0.001));
        System.out.println(task.methodChord(-1.01 , -0.9, 0.001));
        System.out.println(task.methodOfIteration(-1.01 , -0.9, 0.001));
    }
}
