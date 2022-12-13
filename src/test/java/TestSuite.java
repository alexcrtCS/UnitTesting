import com.google.gson.JsonSyntaxException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import parser.JsonParser;
import parser.NoSuchFileException;
import parser.Parser;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@Suite
@SelectClasses({JsonParserTest.class, RealItemTest.class, VirtualItemTest.class, CartTest.class})
@IncludeTags({"JsonParserTest", "RealItemTest", "VirtualItemTest", "CartTest"})
class TestSuite {
}

@Tag("JsonParserTest")
@DisplayName("JsonParser Class Tests")
class JsonParserTest {
    Parser parser;
    File file;
    Cart cart;

    @BeforeEach
    public void setup() {
        parser = new JsonParser();
        cart = new Cart("test-cart");
        file = new File("src/main/resources/" + cart.getCartName() + ".json");
    }

    @AfterEach
    public void cleanUp() {
        file.deleteOnExit();
        cart = null;
    }

    @DisplayName("Should Write To File")
    @Test
    public void shouldWriteToFile() {
        parser.writeToFile(cart);
        // checking that file is not empty
        assertNotEquals(0, file.length());
    }

    @DisplayName("Should Read From File")
    @ParameterizedTest
    @ValueSource(strings = {"andrew-cart", "eugen-cart"})
    public void shouldReadFromFile(String cartName) {
        cart = parser.readFromFile(new File("src/main/resources/" + cartName + ".json"));
        // checking that cart is not null
        assertNotNull(cart);
    }

    // NoSuchFileException Test with 5 datasets
    @DisplayName("Should Throw NoSuchFileException")
    @ParameterizedTest
    @ValueSource(strings = {"", "file", "file.json", "file.txt", " file "})
    public void shouldThrowNoSuchFileException(String fileName) {
        assertThrows(NoSuchFileException.class,
                () -> parser.readFromFile(new File("src/main/resources/" + fileName)));
    }

    // Testing Exception Message using nested group assertion
    // Decided to disable this test
    @Disabled
    @DisplayName("Exception Message Test")
    @Test
    public void shouldMatchExceptionMessage() {
        String fileName = "dummy";
        String expectedErrorMessage = "File " + fileName + ".json not found!";
        assertAll("TestExceptionThrown",
                () -> {
                    Exception exception = assertThrows(NoSuchFileException.class,
                            () -> parser.readFromFile(new File(fileName)));

                    assertAll("TestExceptionMessage",
                            () -> assertEquals(expectedErrorMessage, exception.getMessage())
                    );
                }
        );
    }

    // Testing other types of exceptions
    // Used regular grouped assertion
    @Test
    public void shouldThrowExceptions() {
        File corruptFile = new File("src/main/resources/corrupt.json");
        assertAll(
                // Test for JsonSyntaxException when file is corrupt
                () -> assertThrows(JsonSyntaxException.class, () -> parser.readFromFile(corruptFile)),
                // Test for NullPointerException when file is null
                () -> assertThrows(NullPointerException.class, () -> parser.readFromFile(null)),
                // Test for NullPointerException when cart is null
                () -> assertThrows(NullPointerException.class, () -> parser.writeToFile(null))
        );
    }

}

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

@Tag("CartTest")
@DisplayName("Cart Class Tests")
class CartTest {
    Cart cart;
    RealItem realItem;
    VirtualItem virtualItem;
    final double TAX = 0.2;

    @BeforeEach
    public void setupSuite() {
        cart = new Cart("test-cart");
    }

    @AfterEach
    public void cleanUpSuite() {
        cart = null;
    }

    @DisplayName("Test Cart With Virtual Item")
    @Test
    public void testVirtualCart() {
        virtualItem = new VirtualItem();
        virtualItem.setPrice(100);
        cart.addVirtualItem(virtualItem);

        //check that virtual product's price with added tax equals the cart's total price
        assertEquals(virtualItem.getPrice() + virtualItem.getPrice() * TAX, cart.getTotalPrice());
    }

    @DisplayName("Test Cart With Real Item")
    @Test
    public void testRealCart() {
        realItem = new RealItem();
        realItem.setPrice(100);
        cart.addRealItem(realItem);

        //check that real product's price with added tax equals the cart's total price
        assertEquals(realItem.getPrice() + realItem.getPrice() * TAX, cart.getTotalPrice());
    }
}
