package shop;

import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;

class RealItemTest {
    RealItem realItem;

    @BeforeTest
    public void setup() {
        realItem = new RealItem();
    }

    @AfterTest
    public void cleanUp() {
        realItem = null;
    }

    @Test(groups = {"RealItemTest"})
    public void testRealItem() {
        double expectedWeight = 9.99;
        realItem.setWeight(expectedWeight);
        // checking that the expected weight equals the actual weight that is returned by weight getter
        assertEquals(expectedWeight, realItem.getWeight());
    }
}