import api.CN;
import api.CNMatrix;
import api.CNMatrixUtils;
import exceptions.MatrixDimensionsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CNMatrixMultiplyTest {
    
    private CNMatrixUtils iwant;
    
    @BeforeEach
    void setUp() {
        iwant = new CNMatrixUtils();
    }
    
    @Test void matrixEquality_1x1() {
        CNMatrix oneMatrix = new CNMatrix(1, 1, iwant.constants3DArray(1, 1, 0, 0));
        CNMatrix anotherMatrix = new CNMatrix(1, 1, iwant.constants3DArray(1, 1, 0, 0));
        
        assertTrue(oneMatrix.equals(anotherMatrix));
    }
    
    @Test void matrixNonEquality_1x1() {
        CNMatrix oneMatrix = new CNMatrix(1, 1, iwant.constants3DArray(1, 1, 0, 0));
        CNMatrix anotherMatrix = new CNMatrix(1, 1, iwant.constants3DArray(1, 1, 1, 0));

        assertFalse(oneMatrix.equals(anotherMatrix));
    }
    
    @Test void inputArrayDimensionsDontMatch() {
        assertThrows(MatrixDimensionsException.class, () -> {
            CNMatrix oneMatrix = new CNMatrix(1, 2, iwant.constants3DArray(1, 1, 0, 0));
        });
    }
    
    @Test void inputArrayDimensionsDoMatch() {
        assertDoesNotThrow(() -> {
            CNMatrix oneMatrix = new CNMatrix(1, 2, iwant.constants3DArray(1, 2, 0, 0));
        });
    }
    
    @Test void equality_1x2() {
        CNMatrix oneMatrix = new CNMatrix(1, 2, iwant.constants3DArray(1, 2, 0, 0));
        CNMatrix anotherMatrix = new CNMatrix(1, 2, iwant.constants3DArray(1, 2, 0, 0));
        assertTrue(oneMatrix.equals(anotherMatrix));
    }
    
    @Test void canSetMatrixElement() {
        CNMatrix oneMatrix = new CNMatrix(1, 2, iwant.constants3DArray(1, 2, 0, 0));
        CN testElement = new CN(5, 5);
        oneMatrix.setElement(0, 0, testElement);
        assertTrue(testElement.equals(oneMatrix.element(0, 0)));
    }

    @Test void canSetMatrixElementAnotherWay() {
        CNMatrix oneMatrix = new CNMatrix(1, 2, iwant.constants3DArray(1, 2, 0, 0));
        oneMatrix.setElement(0, 0, 5, 5);
        assertTrue(oneMatrix.element(0, 0).r() == 5);
        assertTrue(oneMatrix.element(0, 0).im() == 5);
    }
    
    @Test void canMultiplyZeros_1x1_by_1x1() {
        CNMatrix oneMatrix = new CNMatrix(1, 1, iwant.constants3DArray(1, 1, 0, 0));
        CNMatrix anotherMatrix = new CNMatrix(1, 1, iwant.constants3DArray(1, 1, 0, 0));
        
        CNMatrix product = oneMatrix.multBy(anotherMatrix);
        CNMatrix expectedMatrix = new CNMatrix(1, 1, iwant.constants3DArray(1, 1, 0, 0));
        
        assertTrue(product.equals(expectedMatrix));
    }
    
    @Test void canMultiplyOnes_1x1_by_1x1() {
        CNMatrix oneMatrix = new CNMatrix(1, 1, iwant.constants3DArray(1, 1, iwant.constantOnes()));
        CNMatrix anotherMatrix = new CNMatrix(1, 1, iwant.constants3DArray(1, 1, iwant.constantOnes()));

        CNMatrix product = oneMatrix.multBy(anotherMatrix);
        CNMatrix expectedMatrix = new CNMatrix(1, 1, iwant.constants3DArray(1, 1, 0, 2));
        
        assertTrue(product.equals(expectedMatrix));
    }
    
    @Test void canMultiplyZeros_1x2_by_2x1() {
        CNMatrix oneMatrix = new CNMatrix(iwant.constants3DArray(1, 2, iwant.constantZeros()));
        CNMatrix anotherMatrix = new CNMatrix(iwant.constants3DArray(2, 1, iwant.constantZeros()));

        CNMatrix product = oneMatrix.multBy(anotherMatrix);
        CNMatrix expectedMatrix = new CNMatrix(iwant.constants3DArray(1, 1, iwant.constantZeros()));

        assertTrue(product.equals(expectedMatrix));
    }

    @Test void canMultiplyOnes_1x2_by_2x1() {
        CNMatrix oneMatrix = new CNMatrix(iwant.constants3DArray(1, 2, iwant.constantOnes()));
        CNMatrix anotherMatrix = new CNMatrix(iwant.constants3DArray(2, 1, iwant.constantOnes()));

        CNMatrix product = oneMatrix.multBy(anotherMatrix);
        CNMatrix expectedMatrix = new CNMatrix(iwant.constants3DArray(1, 1, 0, 4));

        assertTrue(product.equals(expectedMatrix));
    }

    @Test void canMultiplyOnes_1x3_by_3x2() {
        CNMatrix oneMatrix = new CNMatrix(iwant.constants3DArray(1, 3, iwant.constantOnes()));
        CNMatrix anotherMatrix = new CNMatrix(iwant.constants3DArray(3, 2, iwant.constantOnes()));

        CNMatrix product = oneMatrix.multBy(anotherMatrix);
        CNMatrix expectedMatrix = new CNMatrix(iwant.constants3DArray(1, 2, 0, 6));

        assertTrue(product.equals(expectedMatrix));
    }
    
    @Test void rejectWrongDims() {
        CNMatrix oneMatrix = new CNMatrix(iwant.constants3DArray(1, 2, iwant.constantOnes()));
        CNMatrix anotherMatrix = new CNMatrix(iwant.constants3DArray(3, 2, iwant.constantOnes()));

        assertThrows(MatrixDimensionsException.class, () -> {
            CNMatrix product = oneMatrix.multBy(anotherMatrix);
        });
    }
    
}













