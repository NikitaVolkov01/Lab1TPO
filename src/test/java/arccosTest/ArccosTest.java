package arccosTest;

import arccos.Arccos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static java.lang.Float.NaN;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ArccosTest {

    private Arccos arccos;

    private Stream<Arguments> testArccos() {
        return Stream.of(
                Arguments.of(-1, 0.0),
                Arguments.of(-0.9, 2.69),
                Arguments.of(-0.7, 2.35),
                Arguments.of(-0.5, 2.1),
                Arguments.of(-0.3, 1.88),
                Arguments.of(-0.1, 1.68),
                Arguments.of(0, 1.58),
                Arguments.of(0.2, 1.37),
                Arguments.of(0.4, 1.16),
                Arguments.of(0.6, 0.93),
                Arguments.of(0.8, 0.65),
                Arguments.of(1, 0),
                Arguments.of(-2, NaN),
                Arguments.of(2, NaN)
        );
    }

    @BeforeEach
    public void setUp() {
        this.arccos = new Arccos();
    }


    @ParameterizedTest(name = "{index}: arccos({0}) = {1}")
    @MethodSource
    @DisplayName("testArcos")
    public void testArccos(double in, double expected) {
        double actual;
        actual = arccos.arccos(in);
        assertEquals(expected, actual, arccos.getAccurate());
    }
}
