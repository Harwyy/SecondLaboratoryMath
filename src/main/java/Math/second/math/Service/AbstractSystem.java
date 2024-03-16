package Math.second.math.Service;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;

public abstract class AbstractSystem {

    abstract double resultOfFunctionForFirst(double x, double y);

    abstract double resultOfFunctionForSecond(double x, double y);

    abstract double resultOfQForFirst(double x, double y);

    abstract double resultOfQForSecond(double x, double y);

    abstract double derivativesQForFirstX(double x, double y);

    abstract double derivativesQForFirstY(double x, double y);

    abstract double derivativesQForSecondX(double x, double y);

    abstract double derivativesQForSecondY(double x, double y);

    public boolean conditionOfIteration(double leftX, double rightX, double leftY, double rightY){
        double max = Double.MIN_VALUE;
        for (double i = leftX; i <= rightX; i+=0.1){
            for (double j = leftY; j <= rightY; j+=0.1){
                if (Math.abs(derivativesQForFirstX(i, j)) + Math.abs(derivativesQForFirstY(i, j)) > max){
                    max = Math.abs(derivativesQForFirstX(i, j)) + Math.abs(derivativesQForFirstY(i, j));
                }
                if (Math.abs(derivativesQForSecondX(i, j)) + Math.abs(derivativesQForSecondY(i, j)) > max){
                    max = Math.abs(derivativesQForSecondX(i, j)) + Math.abs(derivativesQForSecondY(i, j));
                }
            }
        }
        if (max < 1){
            return true;
        }
        return false;
    }

    public ArrayList<Double> methodIteration(double leftX, double rightX, double leftY, double rightY, double epsilon){
        double countOfIteration = 0d;
        double currentX = leftX;
        double currentY = leftY;
        double previousX = leftX + epsilon + 1;
        double previousY = leftY + epsilon + 1;
        ArrayList<Double> list = new ArrayList<>();
        if (!conditionOfIteration(leftX, rightX, leftY, rightY)){
            list.add(Double.MAX_VALUE);
            return list;
        }
        while (Math.abs(currentX - previousX) >= epsilon && Math.abs(currentY - previousY) >= epsilon){
            previousX = currentX;
            previousY = currentY;
            currentX = resultOfQForFirst(previousX, previousY);
            currentY = resultOfQForSecond(previousX, previousY);
            countOfIteration+=1;
        }
        if (currentX < leftX || currentX > rightX || currentY < leftY || currentY > rightY){
            list.add(Double.MIN_VALUE);
            return list;
        }
        list.add(currentX);
        list.add(currentY);
        list.add(resultOfFunctionForFirst(currentX, currentY));
        list.add(resultOfFunctionForSecond(currentX, currentY));
        list.add(countOfIteration);
        return list;
    }

}
