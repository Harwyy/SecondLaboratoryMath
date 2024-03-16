package Math.second.math.Service;

import org.springframework.stereotype.Service;

// f(x) = cos(x) + e^x
@Service
public class Two extends AbstractNotSystem{
    @Override
    double resultOfFunction(double x) {
        return Math.cos(x) + Math.pow(Math.E, x);
    }

    @Override
    double firstDerivatives(double x) {
        return Math.pow(Math.E, x) - Math.sin(x);
    }

    @Override
    double secondDerivatives(double x) {
        return Math.pow(Math.E, x) - Math.cos(x);
    }

    @Override
    double resultOfQ(double x, double lambda) {
        return x + lambda * (Math.cos(x) + Math.pow(Math.E, x));
    }

    @Override
    double firstDerivativesOfQ(double x, double lambda) {
        return 1 + lambda * (Math.pow(Math.E, x) - Math.sin(x));
    }

    public static void main(String[] args) {
        Two task = new Two();
        System.out.println(task.methodNewton(-9, -7, 0.0001));
        System.out.println(task.methodChord(-9, -7, 0.0001));
        System.out.println(task.methodOfIteration(-9, -7, 0.0001));

    }
}
