package shop;

import org.testng.annotations.*;

import static org.testng.Assert.assertTrue;

class VirtualItemTest {
    VirtualItem virtualItem;
    static final double EXPECTED_SIZE= 9.99;

    @BeforeTest
    public void setup() {
        virtualItem = new VirtualItem();
        virtualItem.setSizeOnDisk(EXPECTED_SIZE);
    }

    @AfterTest
    public void cleanUp() {
        virtualItem = null;
    }

    @Test(groups = {"VirtualItemTest"})
    public void testVirtualItem() {
        assertTrue(virtualItem.toString().contains("Size on disk: " + EXPECTED_SIZE));
    }
}