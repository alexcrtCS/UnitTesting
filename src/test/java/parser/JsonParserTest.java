package parser;

import com.google.gson.JsonSyntaxException;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import shop.Cart;

import java.io.File;

import static org.testng.Assert.*;

class JsonParserTest {
    Parser parser;

    @BeforeMethod
    public void setup() {
        parser = new JsonParser();
    }

    @AfterMethod
    public void cleanUp() {
        File testFile = new File("src/main/resources/test-cart.json");
        if (testFile.exists()) {
            testFile.deleteOnExit();
        }
    }

    @DataProvider(name = "dummyFilePath")
    public Object[][] dummyFileProvider() {
        return new Object[][]{{""}, {"file"}, {"file.json"}, {"file.txt"}, {" file "}};
    }

    @Test(groups = {"JsonParserTest"})
    public void shouldWriteToFile() {
        Cart cart = new Cart("test-cart");
        parser.writeToFile(cart);
        // have to use main resources folder as writeToFile method creates files there, not in test resources
        File file = new File("src/main/resources/" + cart.getCartName() + ".json");
        // checking that file is not empty
        assertNotEquals(0, file.length());
    }

    @Parameters("cartName")
    @Test(groups = {"JsonParserTest"})
    public void shouldReadFromFile(String cartName) {
        Cart cart = parser.readFromFile(new File("src/test/resources/" + cartName + ".json"));
        // checking that cart name is the same
        assertEquals(cartName, cart.getCartName());
    }

    // NoSuchFileException Test with 5 datasets
    @Test(dataProvider = "dummyFilePath", groups = {"JsonParserTest", "ExceptionsTest"})
    public void shouldThrowNoSuchFileException(String fileName) {
        assertThrows(NoSuchFileException.class, () -> parser.readFromFile(new File("src/test/resources/" + fileName)));
    }

    // Testing Exception Message
    // Decided to disable this test
    @Ignore
    @Test(groups = {"JsonParserTest", "ExceptionsTest"})
    public void shouldMatchExceptionMessage() {
        SoftAssert softAssert = new SoftAssert();
        String fileName = "dummy";
        String expectedErrorMessage = "File " + fileName + ".json not found!";

        try {
            parser.readFromFile(new File(fileName));
            // make the test fail if exception is thrown
            softAssert.fail("NoSuchFileException was not thrown");
        } catch (NoSuchFileException e) {
            // NoSuchFileException occurred and got caught
            // checking if the exception message matches the expected message
            softAssert.assertEquals(e.getMessage(), expectedErrorMessage);
        }
        softAssert.assertAll();
    }

    // Testing other types of exceptions
    // Used regular grouped assertion
    @Test(groups = {"JsonParserTest", "ExceptionsTest"})
    public void shouldThrowExceptions() {
        SoftAssert softAssert = new SoftAssert();
        File corruptFile = new File("src/test/resources/corrupt.json");

        // Test for JsonSyntaxException when file is corrupt
        try {
            parser.readFromFile(corruptFile);
            softAssert.fail("Expected JsonSyntaxException to be thrown");
        } catch (JsonSyntaxException e) {
            // Exception was thrown, check if it's of expected type
            softAssert.assertEquals(e.getClass(), JsonSyntaxException.class);
        }

        // Test for NullPointerException when file is null
        try {
            parser.readFromFile(null);
            softAssert.fail("Expected NullPointerException to be thrown");
        } catch (NullPointerException e) {
            // Exception was thrown, check if it's of expected type
            softAssert.assertEquals(e.getClass(), NullPointerException.class);
        }

        // Test for NullPointerException when cart is null
        try {
            parser.writeToFile(null);
            softAssert.fail("Expected NullPointerException to be thrown");
        } catch (NullPointerException e) {
            // Exception was thrown, check if it's of expected type
            softAssert.assertEquals(e.getClass(), NullPointerException.class);
        }
        softAssert.assertAll();
    }
}