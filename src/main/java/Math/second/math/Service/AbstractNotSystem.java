package Math.second.math.Service;

import java.util.ArrayList;

public abstract class AbstractNotSystem {

    abstract double resultOfFunction(double x);
    abstract double firstDerivatives(double x);
    abstract double secondDerivatives(double x);

    abstract double resultOfQ(double x, double lambda);

    abstract double firstDerivativesOfQ(double x, double lambda);

    private boolean isMonotony(double a, double b) {
        double previous = resultOfFunction(a);
        if (resultOfFunction(a + 0.01) - previous >= 0){
            for (double i = a; i <= b; i+=0.01){
                if (previous > resultOfFunction(i)){
                    return false;
                }
                previous = resultOfFunction(i);
            }
        } else {
            for (double i = a; i <= b; i+=0.001){
                if (previous < resultOfFunction(i)){
                    return false;
                }
                previous = resultOfFunction(i);
            }
        }
        return true;
    }

    private String conditionOfNewton(double a, double b){
        double A = resultOfFunction(a) * secondDerivatives(a);
        double B = resultOfFunction(b) * secondDerivatives(b);
        if (A > 0){
            return "A";
        } else if (B > 0) {
            return "B";
        }
        return "Error";
    }

    // готово
    public ArrayList<Double> methodNewton(double a, double b, double epsilon) {
        ArrayList<Double> list = new ArrayList<>();
        if (!isMonotony(a, b)){
            list.add(Double.MIN_VALUE);
            return list;
        }

        if (resultOfFunction(a) >= 0 && resultOfFunction(a) <= resultOfFunction(b)){
            list.add(0d);
            return list;
        }
        if (resultOfFunction(b) <= 0 && resultOfFunction(b) >= resultOfFunction(a)){
            list.add(0d);
            return list;
        }

        double currentX;
        if (conditionOfNewton(a, b) == "A"){
            currentX = a;
        } else if (conditionOfNewton(a, b) == "B"){
            currentX = b;
        } else {
            list.add(Double.MAX_VALUE);
            return list;
        }
        double previousX = Integer.MAX_VALUE;
        double countOfIteration = 0d;
        while (Math.abs(currentX - previousX) > epsilon){
            previousX = currentX;
            currentX = previousX - (resultOfFunction(previousX)/firstDerivatives(previousX));
            countOfIteration+=1;
        }
        list.add(currentX);
        list.add(resultOfFunction(currentX));
        list.add(countOfIteration);
        return list;
    }

    private boolean conditionOfChord(double a, double b){
        for (double i = a; i <= b; i+=0.001){
            if (firstDerivatives(i) * secondDerivatives(i) < 0){
                return false;
            }
        }
        return true;
    }

    // готово
    public ArrayList<Double> methodChord(double a, double b, double epsilon){
        ArrayList<Double> list = new ArrayList<>();

        if (!isMonotony(a, b)){
            list.add(Double.MIN_VALUE);
            return list;
        }
        if (resultOfFunction(a) >= 0 && resultOfFunction(a) <= resultOfFunction(b)){
            list.add(0d);
            return list;
        }
        if (resultOfFunction(b) <= 0 && resultOfFunction(b) >= resultOfFunction(a)){
            list.add(0d);
            return list;
        }
        double countOfIteration = 0d;
        // b зафиксирована
        if (!conditionOfChord(a, b)){
            double previousX = a + epsilon + 1;
            double currentX = a;
            while (Math.abs(currentX - previousX) >= epsilon){
                previousX = currentX;
                currentX = previousX - ((b - previousX) /
                        (resultOfFunction(b) - resultOfFunction(previousX))) *
                        resultOfFunction(previousX);
                countOfIteration+=1;
            }
            list.add(currentX);
            list.add(resultOfFunction(currentX));
            list.add(countOfIteration);
        }
        // a зафиксирована
        else {
            double previousX = b + epsilon + 1;
            double currentX = b;
            while (Math.abs(currentX - previousX) >= epsilon){
                previousX = currentX;
                currentX = previousX - ((a - previousX) /
                        (resultOfFunction(a) - resultOfFunction(previousX))) *
                        resultOfFunction(previousX);
                countOfIteration+=1;
                if (countOfIteration > 10000){
                    list.add(0d);
                    return list;
                }
            }
            list.add(currentX);
            list.add(resultOfFunction(currentX));
            list.add(countOfIteration);
        }
        return list;
    }

    private double findLambda(double a, double b){
        double max = Double.MIN_VALUE;
        for (double i = a; i <= b; i+=0.01){
            if (Math.abs(firstDerivatives(i)) > max){
                max = Math.abs(firstDerivatives(i));
            }
        }
        if (firstDerivatives(b) > 0 && firstDerivatives(a) > 0){
            return -1 / max;
        }
        return 1 / max;
    }

    private boolean conditionOfIteration(double a, double b){
        double lambda = findLambda(a, b);
        double left = firstDerivativesOfQ(a, lambda);
        double right = firstDerivativesOfQ(b, lambda);
        if (Math.abs(right) >= 0 && Math.abs(right) < 1 && Math.abs(left) >= 0 && Math.abs(left) < 1){
            return true;
        }
        return false;
    }

    // готово
    public ArrayList<Double> methodOfIteration(double a, double b, double epsilon){
        ArrayList<Double> list = new ArrayList<>();

        if (!isMonotony(a, b)){
            list.add(Double.MIN_VALUE);
            return list;
        }
        if (resultOfFunction(a) >= 0 && resultOfFunction(a) <= resultOfFunction(b)){
            list.add(0d);
            return list;
        }
        if (resultOfFunction(b) <= 0 && resultOfFunction(b) >= resultOfFunction(a)){
            list.add(0d);
            return list;
        }
        if (!conditionOfIteration(a, b)){
            list.add(Double.MAX_VALUE);
            return list;
        }
        double countOfIteration = 0d;

        double previous = a+epsilon+1;
        double current = a;
        double lambda = findLambda(a, b);
        while (Math.abs(current - previous) >= epsilon){
            previous = current;
            current = resultOfQ(previous, lambda);
            countOfIteration+=1;
        }
        list.add(current);
        list.add(resultOfFunction(current));
        list.add(countOfIteration);
        return list;
    }

}
