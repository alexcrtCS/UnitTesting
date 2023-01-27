package pageclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage extends BasePage {

    @FindBy(className = "item-info")
    private List<WebElement> productList;

    @FindBy(css = ".price .cart-price .price")
    private List<WebElement> productPrices;

    @FindBy(css = ".input-text.qty")
    private List<WebElement> productQuantities;

    @FindBy(css = ".subtotal .cart-price .price")
    private List<WebElement> productSubtotals;

    @FindBy(css = ".sub .price")
    private WebElement cartSubtotal;

    @FindBy(css = ".grand .price")
    private WebElement grandTotal;

    private static final By DISCOUNT = By.cssSelector("td[data-th='Discount'] .price");
    private static final By TAX = By.cssSelector(".totals-tax .price");

    public CartPage() {
        super();
    }

    public NavBar getNavBar() {
        return new NavBar();
    }

    public int getProductQuantity() {
        int initialQuantity = 0;
        for (WebElement productQuantity : productQuantities) {
            int quantity = Integer.parseInt(productQuantity.getAttribute("value"));
            initialQuantity += quantity;
        }
        return initialQuantity;
    }

    public boolean isProductSubtotalValid() {
        for (int i = 0; i < productList.size(); i++) {
            WebElement productPrice = productPrices.get(i);
            double price = Double.parseDouble(productPrice.getText().replaceAll("[,$]", ""));
            WebElement productQuantity = productQuantities.get(i);
            int quantity = Integer.parseInt(productQuantity.getAttribute("value"));
            WebElement productSubtotal = productSubtotals.get(i);
            double subtotal = Double.parseDouble(productSubtotal.getText().replaceAll("[,$]", ""));
            if (price * quantity != subtotal) {
                return false;
            }
        }
        return true;
    }

    public boolean isCartSubtotalValid() {
        double expectedSubtotal = Double.parseDouble(cartSubtotal.getText().replaceAll("[,$]", ""));
        double actualSubtotal = 0;
        for (WebElement productSubtotal : productSubtotals) {
            double subtotal = Double.parseDouble(productSubtotal.getText().replaceAll("[,$]", ""));
            actualSubtotal += subtotal;
        }
        return actualSubtotal == expectedSubtotal;
    }

    public boolean isProductQuantityValid(int initialQuantity, int addedQuantity) {
        int finalQuantity = getProductQuantity();
        return initialQuantity + addedQuantity == finalQuantity;
    }

    public boolean isTotalValid() {
        double totalValue = Double.parseDouble(grandTotal.getText().replaceAll("[,$]", ""));
        double subtotalValue = Double.parseDouble(cartSubtotal.getText().replaceAll("[,$]", ""));
        double discountValue = 0;
        double taxValue = 0;
        // Since discount & tax are not always present in DOM, isDisplayed() doesn't work for this case
        // Instead, trying to find any matching element, if unsuccessful will continue without errors
        if (!driver.findElements(DISCOUNT).isEmpty()) {
            WebElement discount = driver.findElement(DISCOUNT);
            discountValue = Double.parseDouble(discount.getText().replaceAll("[-,$]", ""));
        }
        if (!driver.findElements(TAX).isEmpty()) {
            WebElement tax = driver.findElement(TAX);
            taxValue = Double.parseDouble(tax.getText().replaceAll("[,$]", ""));
        }
        return subtotalValue - discountValue + taxValue == totalValue;
    }

    // Custom method for alternative solution by just using as condition in if
    private boolean isElementPresent(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }
}
