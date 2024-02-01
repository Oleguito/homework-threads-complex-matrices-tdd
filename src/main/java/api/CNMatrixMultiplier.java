package api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CNMatrixMultiplier {

    private final CNMatrixMultiplyThreads threadRipper;
    private final CNMatrixUtils iwant = new CNMatrixUtils();
    
    public CNMatrixMultiplier(CNMatrixMultiplyThreads threadRipper) {
        this.threadRipper = threadRipper;
    }
    
    public CNMatrix multiply(CNMatrix oneMatrix, CNMatrix anotherMatrix) {
        
        var matrixOne = oneMatrix;
        var matrixTwo = anotherMatrix;
        var resultRows = oneMatrix.getRows();
        var resultCols = anotherMatrix.getCols();
        var resultDoubles = iwant.constants3DArray(
                resultRows, resultCols, iwant.constantZeros());
        var resultMatrix = new CNMatrix(resultDoubles);
        
        List <Future <CN>> results = new ArrayList <>();
        for (int i = 0; i < matrixOne.getCols(); i++) {
            for (int j = 0; j < matrixTwo.getRows(); j++) {
                int rowOne = i;
                int colTwo = j;
                var multiplier = new MatrixRowByColumnCalculator(
                        matrixOne, matrixTwo, rowOne, colTwo);
                results.add(threadRipper.getPool().submit(multiplier));
            }
        }

        for (int i = 0; i < matrixOne.getCols(); i++) {
            for (int j = 0; j < matrixTwo.getRows(); j++) {
                int rowOne = i;
                int colTwo = j;
                var element = results.remove(0);
                try {
                    resultMatrix.setElement(rowOne, colTwo, element.get());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        
        return resultMatrix;
    }
}
