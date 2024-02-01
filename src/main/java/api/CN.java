package api;

import java.util.Objects;

public class CN {
    
    private final double imaginary;
    private final double real;
    
    public CN(double r, double im) {
        this.real = r;
        this.imaginary = im;
    }
    
    public double r() {
        return real;
    }
    
    
    public double im() {
        return imaginary;
    }
    
    public CN plus(CN another) {
        return new CN(this.real + another.real,
                this.imaginary + another.imaginary);
    }
    
    public CN multBy(CN another) {
        return new CN(
                this.real * another.real - this.imaginary * another.imaginary,
                this.imaginary * another.real + this.real * another.imaginary);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        CN cn = (CN) o;
        return Double.compare(imaginary, cn.imaginary) == 0 && Double.compare(real, cn.real) == 0;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(imaginary, real);
    }
}
