package shop;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@Tag("RealItemTest")
@DisplayName("RealItem Class Tests")
class RealItemTest {
    RealItem realItem;
    static final double EXPECTED_WEIGHT = 9.99;

    @BeforeEach
    public void setup() {
        realItem = new RealItem();
        realItem.setWeight(EXPECTED_WEIGHT);
    }

    @AfterEach
    public void cleanUp() {
        realItem = null;
    }

    @DisplayName("Test Real Item Weight")
    @Test
    public void testRealItem() {
        assertTrue(realItem.toString().contains("Weight: " + EXPECTED_WEIGHT));
    }
}