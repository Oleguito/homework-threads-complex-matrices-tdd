package api;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class CNMatrixMultiplyThreads {
    
    
    private ExecutorService pool;
    
    public CNMatrixMultiplyThreads() {
        this.pool = Executors.newFixedThreadPool(12);
    }
    
    public ExecutorService getPool() {
        return pool;
    }
}
