package shop;

import org.testng.annotations.*;

import static org.testng.Assert.*;

class CartTest {
    Cart cart;
    RealItem realItem;
    VirtualItem virtualItem;
    final double TAX = 0.2;

    @BeforeMethod
    public void setupSuite() {
        cart = new Cart("test-cart");
    }

    @AfterMethod
    public void cleanUpSuite() {
        cart = null;
    }

    @Test(groups = {"CartTest", "VirtualItemTest"})
    public void testVirtualCart() {
        virtualItem = new VirtualItem();
        virtualItem.setPrice(100);
        cart.addVirtualItem(virtualItem);

        //check that virtual product's price with added tax equals the cart's total price
        assertEquals(virtualItem.getPrice() + virtualItem.getPrice() * TAX, cart.getTotalPrice());
    }

    @Test(groups = {"CartTest", "RealItemTest"})
    public void testRealCart() {
        realItem = new RealItem();
        realItem.setPrice(100);
        cart.addRealItem(realItem);

        //check that real product's price with added tax equals the cart's total price
        assertEquals(realItem.getPrice() + realItem.getPrice() * TAX, cart.getTotalPrice());
    }
}