package shop;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@Tag("VirtualItemTest")
@DisplayName("VirtualItem Class Tests")
class VirtualItemTest {
    VirtualItem virtualItem;
    static final double EXPECTED_SIZE= 9.99;

    @BeforeEach
    public void setup() {
        virtualItem = new VirtualItem();
        virtualItem.setSizeOnDisk(EXPECTED_SIZE);
    }

    @AfterEach
    public void cleanUp() {
        virtualItem = null;
    }

    @DisplayName("Test Virtual Item Size")
    @Test
    public void testVirtualItem() {
        assertTrue(virtualItem.toString().contains("Size on disk: " + EXPECTED_SIZE));
    }
}