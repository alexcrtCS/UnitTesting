package parser;

import com.google.gson.JsonSyntaxException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import shop.Cart;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@Tag("JsonParserTest")
@DisplayName("JsonParser Class Tests")
class JsonParserTest {
    Parser parser;

    @BeforeEach
    public void setup() {
        parser = new JsonParser();
    }

    @AfterEach
    public void cleanUp() {
        File testFile = new File("src/main/resources/test-cart.json");
        if (testFile.exists()) {
            testFile.deleteOnExit();
        }
    }

    @DisplayName("Should Write To File")
    @Test
    public void shouldWriteToFile() {
        Cart cart = new Cart("test-cart");
        parser.writeToFile(cart);
        // have to use main resources folder as writeToFile method creates files there, not in test resources
        File file = new File("src/main/resources/" + cart.getCartName() + ".json");
        // checking that file is not empty
        assertNotEquals(0, file.length());
    }

    @DisplayName("Should Read From File")
    @ParameterizedTest
    @ValueSource(strings = {"andrew-cart", "eugen-cart"})
    public void shouldReadFromFile(String cartName) {
        Cart cart = parser.readFromFile(new File("src/test/resources/" + cartName + ".json"));
        // checking that cart name is the same
        assertEquals(cartName, cart.getCartName());
    }

    // NoSuchFileException Test with 5 datasets
    @DisplayName("Should Throw NoSuchFileException")
    @ParameterizedTest
    @ValueSource(strings = {"", "file", "file.json", "file.txt", " file "})
    public void shouldThrowNoSuchFileException(String fileName) {
        assertThrows(NoSuchFileException.class,
                () -> parser.readFromFile(new File("src/test/resources/" + fileName)));
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
        File corruptFile = new File("src/test/resources/corrupt.json");
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