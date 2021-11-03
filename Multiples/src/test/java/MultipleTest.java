import static org.junit.jupiter.api.Assertions.*;

class MultipleTest {
    @org.junit.jupiter.api.Test
    void triple_0_returns_0() {
        assertEquals(0.0, Multiple.triple(0.0));
    }

    @org.junit.jupiter.api.Test
    void triple_pos_returns_pos() {
//        DecimalFormat df = new DecimalFormat("0.000");

        double actual = Math.round(Multiple.triple(4.111)*1000)/1000.0;

        assertEquals(12.333, actual);
    }

    @org.junit.jupiter.api.Test
    void triple_neg_returns_neg() {
        assertEquals(-13.5, Multiple.triple(-4.5));
    }
}