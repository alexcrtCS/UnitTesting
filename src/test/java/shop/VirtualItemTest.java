package shop;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@Tag("VirtualItemTest")
@DisplayName("VirtualItem Class Tests")
class VirtualItemTest {
    VirtualItem virtualItem;

    @BeforeEach
    public void setup() {
        virtualItem = new VirtualItem();
    }

    @AfterEach
    public void cleanUp() {
        virtualItem = null;
    }

    @DisplayName("Test Virtual Item Size")
    @Test
    public void testVirtualItem() {
        virtualItem = new VirtualItem();
        double expectedSize = 64.32;
        virtualItem.setSizeOnDisk(expectedSize);
        // checking that the expected size equals the actual size that is returned by size getter
        assertEquals(expectedSize, virtualItem.getSizeOnDisk());
    }
}