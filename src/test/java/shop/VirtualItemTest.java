package shop;

import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;

class VirtualItemTest {
    VirtualItem virtualItem;

    @BeforeTest
    public void setup() {
        virtualItem = new VirtualItem();
    }

    @AfterTest
    public void cleanUp() {
        virtualItem = null;
    }

    @Test(groups = {"VirtualItemTest"})
    public void testVirtualItem() {
        virtualItem = new VirtualItem();
        double expectedSize = 64.32;
        virtualItem.setSizeOnDisk(expectedSize);
        // checking that the expected size equals the actual size that is returned by size getter
        assertEquals(expectedSize, virtualItem.getSizeOnDisk());
    }
}