package shop;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

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