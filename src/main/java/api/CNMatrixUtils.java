package api;

import api.CN;

public class CNMatrixUtils {
    
    public static CN thisRowByAnotherColumn(int thisRow, int anotherCol, CNMatrix one, CNMatrix another) {
        CN sum = new CN(0, 0);
        for (int i = 0; i < one.getCols(); i++) {
            sum = sum.plus(
                    one.getMatrix()[thisRow][i]
                            .multBy(another.getMatrix()[i][anotherCol])
            );
        }
        return sum;
    }
    
    public CN constantZeros() {
        return new CN(0.0, 0.0);
    }
    public CN constantOnes() {
        return new CN(1.0, 1.0);
    }
    
    public double[][][] constants3DArray(int rows, int cols, double rconst, double imconst) {
        double[][][] result = new double[rows][cols][];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = new double[] { rconst, imconst };
            }
        }
        return result;
    }
    
    public double[][][] constants3DArray(int rows, int cols, CN constant) {
        double[][][] result = new double[rows][cols][];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = new double[] { constant.r(), constant.im() };
            }
        }
        return result;
    }
}
