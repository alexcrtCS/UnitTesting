package shop;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@Tag("RealItemTest")
@DisplayName("RealItem Class Tests")
class RealItemTest {
    RealItem realItem;

    @BeforeEach
    public void setup() {
        realItem = new RealItem();
    }

    @AfterEach
    public void cleanUp() {
        realItem = null;
    }

    @DisplayName("Test Real Item Weight")
    @Test
    public void testRealItem() {
        double expectedWeight = 9.99;
        realItem.setWeight(expectedWeight);
        // checking that the expected weight equals the actual weight that is returned by weight getter
        assertEquals(expectedWeight, realItem.getWeight());
    }
}