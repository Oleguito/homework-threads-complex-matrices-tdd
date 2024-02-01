import api.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;

public class CNMatrixMultiplyThreadsTest {
    
    private CNMatrixMultiplyThreads tmm;
    private CNMatrixUtils iwant;
    
    @BeforeEach
    void setUp() {
        tmm = new CNMatrixMultiplyThreads();
        iwant = new CNMatrixUtils();
    }
    
    @Test void createAPool() {
        assertDoesNotThrow(() -> {
            tmm.getPool().execute(() -> {});
        });
    }
    
    @Test void doingFutures() throws ExecutionException, InterruptedException {
        Callable task = () -> 2;
        Future <Integer> result = tmm.getPool().submit(task);
        assertTrue(result.get() == 2);
    }
    
    @Test void howMultiplicationWorks() {
        CNMatrixMultiplyThreads threadRipper = new CNMatrixMultiplyThreads();
        
        CNMatrix oneMatrix = new CNMatrix(iwant.constants3DArray(10, 10, iwant.constantOnes()));
        CNMatrix anotherMatrix = new CNMatrix(iwant.constants3DArray(10, 10, iwant.constantOnes()));
        
        var multiplier = new CNMatrixMultiplier(threadRipper);
        var resultMatrix = multiplier.multiply(oneMatrix, anotherMatrix);
        
        var constant = new CN(0, 20);
        var expectedDoubles = iwant.constants3DArray(10, 10, constant);
        var expectedMatrix = new CNMatrix(expectedDoubles);
        assertTrue(resultMatrix.equals(expectedMatrix));
        
        threadRipper.getPool().shutdown();
    }
    
}
