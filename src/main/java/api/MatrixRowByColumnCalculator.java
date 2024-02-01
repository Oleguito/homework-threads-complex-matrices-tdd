package api;

import java.util.concurrent.Callable;

class MatrixRowByColumnCalculator implements Callable <CN> {
    
    private CNMatrix matrixOne;
    private CNMatrix matrixTwo;
    private final int rowOne;
    private final int colTwo;
    
    public MatrixRowByColumnCalculator(CNMatrix matrixOne,
                                       CNMatrix matrixTwo,
                                       int rowOne,
                                       int colTwo) {
        this.matrixOne = matrixOne;
        this.matrixTwo = matrixTwo;
        this.rowOne = rowOne;
        this.colTwo = colTwo;
    }
    
    @Override
    public CN call() throws Exception {
        return CNMatrixUtils.thisRowByAnotherColumn(rowOne, colTwo, matrixOne, matrixTwo);
    }
}
