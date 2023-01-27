package helpers;

import org.apache.commons.lang3.StringUtils;

public class Constants {
    public static final String BROWSER = StringUtils.defaultString(System.getProperty("browser"), "chrome");
    public static final String ENVIRONMENT = StringUtils.defaultString(System.getProperty("environment"), "local");
    public static final String REMOTE = StringUtils.defaultString(System.getProperty("remote"), "saucelabs");
    public static final String GRID_URL = "http://localhost:4444/wd/hub";
    private static final String USERNAME = "alexcrtCS";
    private static final String USER_KEY = "2fd5dab0-128a-45af-bd39-04a6d2de11b0";
    public static final String SAUCE_URL = "https://" + USERNAME + ":" + USER_KEY + "@ondemand.eu-central-1.saucelabs.com:443/wd/hub";
    public static final String WEBPAGE = "https://magento.softwaretestingboard.com/";
    public static final String CART_URL = "https://magento.softwaretestingboard.com/checkout/cart/";
    public static final String EMAIL = "testseleniumcs@cstc.com";
    public static final String PASSWORD = "TestSelenium!";
    public static final String RANDOM = "Random";
    public static final String NEW_CATEGORY = "What's New";
    public static final String WOMEN_CATEGORY = "Women";
    public static final String MEN_CATEGORY = "Men";
    public static final String TOPS_SUB_CATEGORY = "Tops";
    public static final String BOTTOMS_SUB_CATEGORY = "Bottoms";
    public static final String TEES_SUB_CATEGORY = "Tees";
    public static final String PANTS_SUB_CATEGORY = "Pants";
    public static final String MEN_TEE = "Zoltan Gym Tee";
}
