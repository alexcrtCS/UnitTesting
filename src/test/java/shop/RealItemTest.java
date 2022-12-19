package shop;

import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;

class RealItemTest {
    RealItem realItem;
    static final double EXPECTED_WEIGHT = 9.99;

    @BeforeTest
    public void setup() {
        realItem = new RealItem();
        realItem.setWeight(EXPECTED_WEIGHT);
    }

    @AfterTest
    public void cleanUp() {
        realItem = null;
    }

    @Test(groups = {"RealItemTest"})
    public void testRealItem() {
        assertTrue(realItem.toString().contains("Weight: " + EXPECTED_WEIGHT));
    }
}