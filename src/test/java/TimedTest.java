import api.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

public class TimedTest {
    
    private final static CNMatrixUtils iwant = new CNMatrixUtils();
    private final static CNMatrixMultiplyThreads threadRipper = new CNMatrixMultiplyThreads();
    private final static CNMatrixMultiplier multiplier = new CNMatrixMultiplier(threadRipper);
    
    @AfterAll
    static void afterAll() {
        threadRipper.getPool().shutdown();
    }
    
    @Test void differentSizes() {
        final int HOWMANY = 4;
        for (int i = 1; i <= HOWMANY; i++) {
            int size = (int) Math.pow(10, i);
            var matrixOne = createTestMatrix(size);
            var matrixTwo = createTestMatrix(size);
            long itTook = timeIt(() -> {
               multiplier.multiply(matrixOne, matrixTwo);
            });
            System.out.printf("It took% .4f ms to calculate square complex number matrix of side %d\n",
                    itTook / 1e6, size);
        }
    }
    
    CNMatrix createTestMatrix(int size) {
        var doubles = iwant.constants3DArray(size, size, new CN(1, 1));
        return new CNMatrix(doubles);
    }
    
    long timeIt(Runnable action) {
        long start = System.nanoTime();
        action.run();
        long end = System.nanoTime();
        return end - start;
    }
}
