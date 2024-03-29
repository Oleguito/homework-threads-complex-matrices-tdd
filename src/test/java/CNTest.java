import api.CN;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class CNTest {
    
    @Test void
    createComplexNumber() {
        CN cn = new CN(0.0, 0.0);
        
        assertEquals(0.0, cn.r());
        assertEquals(0.0, cn.im());
    }
    
    @Test void
    checkEquality() {
        CN one = new CN(1, 1);
        CN another = new CN(1, 1);
        CN yetAnother = new CN(1, 2);
        
        assertEquals(one, one);
        assertEquals(one, another);
        assertNotEquals(one,yetAnother);
        assertNotEquals(another, yetAnother);
    }
    
    @Test void
    add_1_1_and_2_1() {
        CN one = new CN(1, 1);
        CN another = new CN(2, 1);
        
        CN res = one.plus(another);
        
        assertEquals(3, res.r());
        assertEquals(2, res.im());
    }
    
    @Test void
    multiply_2_3_and_4_5() {
        CN one = new CN(2, 3);
        CN another = new CN(4, 5);
        
        CN res = one.multBy(another);
        
        assertEquals(-7, res.r());
        assertEquals(22, res.im());
    }
    
    @ParameterizedTest
    @CsvSource({
        "-1.0 , -1.0 , -1.0 , -1.0 , 0.0 , 2.0",
        "-1.0 , -1.0 , -1.0 , 0.0 , 1.0 , 1.0",
        "-1.0 , -1.0 , -1.0 , 1.0 , 2.0 , 0.0",
        "-1.0 , -1.0 , 0.0 , -1.0 , -1.0 , 1.0",
        "-1.0 , -1.0 , 0.0 , 0.0 , 0.0 , -0.0",
        "-1.0 , -1.0 , 0.0 , 1.0 , 1.0 , -1.0",
        "-1.0 , -1.0 , 1.0 , -1.0 , -2.0 , 0.0",
        "-1.0 , -1.0 , 1.0 , 0.0 , -1.0 , -1.0",
        "-1.0 , -1.0 , 1.0 , 1.0 , 0.0 , -2.0",
        "-1.0 , 0.0 , -1.0 , -1.0 , 1.0 , 1.0",
        "-1.0 , 0.0 , -1.0 , 0.0 , 1.0 , -0.0",
        "-1.0 , 0.0 , -1.0 , 1.0 , 1.0 , -1.0",
        "-1.0 , 0.0 , 0.0 , -1.0 , 0.0 , 1.0",
        "-1.0 , 0.0 , 0.0 , 0.0 , -0.0 , 0.0",
        "-1.0 , 0.0 , 0.0 , 1.0 , -0.0 , -1.0",
        "-1.0 , 0.0 , 1.0 , -1.0 , -1.0 , 1.0",
        "-1.0 , 0.0 , 1.0 , 0.0 , -1.0 , 0.0",
        "-1.0 , 0.0 , 1.0 , 1.0 , -1.0 , -1.0",
        "-1.0 , 1.0 , -1.0 , -1.0 , 2.0 , 0.0",
        "-1.0 , 1.0 , -1.0 , 0.0 , 1.0 , -1.0",
        "-1.0 , 1.0 , -1.0 , 1.0 , 0.0 , -2.0",
        "-1.0 , 1.0 , 0.0 , -1.0 , 1.0 , 1.0",
        "-1.0 , 1.0 , 0.0 , 0.0 , -0.0 , 0.0",
        "-1.0 , 1.0 , 0.0 , 1.0 , -1.0 , -1.0",
        "-1.0 , 1.0 , 1.0 , -1.0 , 0.0 , 2.0",
        "-1.0 , 1.0 , 1.0 , 0.0 , -1.0 , 1.0",
        "-1.0 , 1.0 , 1.0 , 1.0 , -2.0 , 0.0",
        "0.0 , -1.0 , -1.0 , -1.0 , -1.0 , 1.0",
        "0.0 , -1.0 , -1.0 , 0.0 , 0.0 , 1.0",
        "0.0 , -1.0 , -1.0 , 1.0 , 1.0 , 1.0",
        "0.0 , -1.0 , 0.0 , -1.0 , -1.0 , -0.0",
        "0.0 , -1.0 , 0.0 , 0.0 , 0.0 , 0.0",
        "0.0 , -1.0 , 0.0 , 1.0 , 1.0 , 0.0",
        "0.0 , -1.0 , 1.0 , -1.0 , -1.0 , -1.0",
        "0.0 , -1.0 , 1.0 , 0.0 , 0.0 , -1.0",
        "0.0 , -1.0 , 1.0 , 1.0 , 1.0 , -1.0",
        "0.0 , 0.0 , -1.0 , -1.0 , 0.0 , -0.0",
        "0.0 , 0.0 , -1.0 , 0.0 , -0.0 , 0.0",
        "0.0 , 0.0 , -1.0 , 1.0 , -0.0 , 0.0",
        "0.0 , 0.0 , 0.0 , -1.0 , 0.0 , 0.0",
        "0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0",
        "0.0 , 0.0 , 0.0 , 1.0 , 0.0 , 0.0",
        "0.0 , 0.0 , 1.0 , -1.0 , 0.0 , 0.0",
        "0.0 , 0.0 , 1.0 , 0.0 , 0.0 , 0.0",
        "0.0 , 0.0 , 1.0 , 1.0 , 0.0 , 0.0",
        "0.0 , 1.0 , -1.0 , -1.0 , 1.0 , -1.0",
        "0.0 , 1.0 , -1.0 , 0.0 , -0.0 , -1.0",
        "0.0 , 1.0 , -1.0 , 1.0 , -1.0 , -1.0",
        "0.0 , 1.0 , 0.0 , -1.0 , 1.0 , 0.0",
        "0.0 , 1.0 , 0.0 , 0.0 , 0.0 , 0.0",
        "0.0 , 1.0 , 0.0 , 1.0 , -1.0 , 0.0",
        "0.0 , 1.0 , 1.0 , -1.0 , 1.0 , 1.0",
        "0.0 , 1.0 , 1.0 , 0.0 , 0.0 , 1.0",
        "0.0 , 1.0 , 1.0 , 1.0 , -1.0 , 1.0",
        "1.0 , -1.0 , -1.0 , -1.0 , -2.0 , 0.0",
        "1.0 , -1.0 , -1.0 , 0.0 , -1.0 , 1.0",
        "1.0 , -1.0 , -1.0 , 1.0 , 0.0 , 2.0",
        "1.0 , -1.0 , 0.0 , -1.0 , -1.0 , -1.0",
        "1.0 , -1.0 , 0.0 , 0.0 , 0.0 , 0.0",
        "1.0 , -1.0 , 0.0 , 1.0 , 1.0 , 1.0",
        "1.0 , -1.0 , 1.0 , -1.0 , 0.0 , -2.0",
        "1.0 , -1.0 , 1.0 , 0.0 , 1.0 , -1.0",
        "1.0 , -1.0 , 1.0 , 1.0 , 2.0 , 0.0",
        "1.0 , 0.0 , -1.0 , -1.0 , -1.0 , -1.0",
        "1.0 , 0.0 , -1.0 , 0.0 , -1.0 , 0.0",
        "1.0 , 0.0 , -1.0 , 1.0 , -1.0 , 1.0",
        "1.0 , 0.0 , 0.0 , -1.0 , 0.0 , -1.0",
        "1.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0",
        "1.0 , 0.0 , 0.0 , 1.0 , 0.0 , 1.0",
        "1.0 , 0.0 , 1.0 , -1.0 , 1.0 , -1.0",
        "1.0 , 0.0 , 1.0 , 0.0 , 1.0 , 0.0",
        "1.0 , 0.0 , 1.0 , 1.0 , 1.0 , 1.0",
        "1.0 , 1.0 , -1.0 , -1.0 , 0.0 , -2.0",
        "1.0 , 1.0 , -1.0 , 0.0 , -1.0 , -1.0",
        "1.0 , 1.0 , -1.0 , 1.0 , -2.0 , 0.0",
        "1.0 , 1.0 , 0.0 , -1.0 , 1.0 , -1.0",
        "1.0 , 1.0 , 0.0 , 0.0 , 0.0 , 0.0",
        "1.0 , 1.0 , 0.0 , 1.0 , -1.0 , 1.0",
        "1.0 , 1.0 , 1.0 , -1.0 , 2.0 , 0.0",
        "1.0 , 1.0 , 1.0 , 0.0 , 1.0 , 1.0",
        "1.0 , 1.0 , 1.0 , 1.0 , 0.0 , 2.0"
    })
    void test_all_combos(double oneReal, double oneImag,
                         double anotherReal, double anotherImag,
                         double resultReal, double resultImag) {
        CN one = new CN(oneReal, oneImag);
        CN another = new CN(anotherReal, anotherImag);
        
        CN res = one.multBy(another);
        
        assertEquals(resultReal, res.r());
        assertEquals(resultImag, res.im());
    }
    
    // class ComplexNumbersArgumentsProvider implements ArgumentsProvider {
    //
    //     @Override
    //     public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
    //         return Stream.of(
    //           Arguments.of((String) null),
    //           Arguments.of(""),
    //           Arguments.of("   ")
    //         );
    //     }
    // }
}
