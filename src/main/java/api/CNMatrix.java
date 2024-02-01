package api;

import exceptions.MatrixDimensionsException;

import java.util.Arrays;
import java.util.Objects;

public class CNMatrix {
    
    private final int rows;
    private final int cols;
    private final CNMatrixUtils iwant = new CNMatrixUtils();
    private CN[][] matrix;
    
    public CNMatrix(int rows, int cols, double[][][] m) {
        this.rows = rows;
        this.cols = cols;
        matrix = new CN[rows][cols];
        checkDimensions(rows, cols, m);
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                matrix[i][j] = new CN(m[i][j][0], m[i][j][1]);
            }
        }
    }
    
    public CNMatrix(double[][][] m) {
        this.rows = m.length;
        this.cols = m[0].length;
        matrix = new CN[rows][cols];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                matrix[i][j] = new CN(m[i][j][0], m[i][j][1]);
            }
        }
    }
    
    public CNMatrix(int rows, int cols, CN[][] m) {
        this.rows = rows;
        this.cols = cols;
        matrix = new CN[rows][cols];
        checkDimensions(rows, cols, m);
        matrix = Arrays.copyOf(m, m.length);
    }
    
    private void checkDimensions(int rows, int cols, double[][][] m) {
        if(rows != m.length ||
            cols != m[0].length) {
            throw new MatrixDimensionsException("Размеры не совпадают");
        }
    }
    private void checkDimensions(int rows, int cols, CN[][] m) {
        if(rows != m.length ||
            cols != m[0].length) {
            throw new MatrixDimensionsException("Размеры не совпадают");
        }
    }
    
    public void setRow(int index, double[][] row) {
        CN[] mrow = new CN[row.length];
        for (int i = 0; i < row.length; i++) {
            mrow[i] = new CN(row[i][0], row[i][1]);
        }
        matrix[index] = mrow;
    }
    
    public boolean equals(CNMatrix another) {
        if (this == another) { return true; }
        if (another == null || getClass() != another.getClass()) { return false; }
        boolean allRowsEqual = allMatrixRowsEqual(another);
        return rows == another.rows && cols == another.cols && allRowsEqual;
    }
    
    private boolean allMatrixRowsEqual(CNMatrix matrix) {
        boolean result = true;
        for (int i = 0; i < matrix.rows; i++) {
            for (int j = 0; j < matrix.cols; j++) {
                if(!matrix.matrix[i][j].equals(this.matrix[i][j])) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(rows, cols);
    }
    
    public void setElement(int row, int col, CN element) {
        matrix[row][col] = element;
    }
    
    public CN element(int row, int col) {
        return matrix[row][col];
    }
    
    public void setElement(int row, int col, double real, double imaginary) {
        matrix[row][col] = new CN(real, imaginary);
    }
    
    public CNMatrix multBy(CNMatrix anotherMatrix) {
        
        if(this.cols != anotherMatrix.getRows()) {
            throw new MatrixDimensionsException("Размеры не подходят");
        }
        
        int resultRows = this.rows;
        int resultCols = anotherMatrix.cols;
        CNMatrix result = new CNMatrix(iwant.constants3DArray(resultRows, resultCols,  0, 0 ));
        
        for (int i = 0; i < resultRows; i++) {
            for (int j = 0; j < resultCols; j++) {
                CN cn = iwant.thisRowByAnotherColumn(i, j, this, anotherMatrix);
                result.setElement(i, j, cn);
            }
        }
        
        return result;
    }
    
    public int getRows() {
        return rows;
    }
    
    public CN[][] getMatrix() {
        return matrix;
    }
    
    public int getCols() {
        return cols;
    }
}








