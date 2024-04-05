import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.color.ICC_ColorSpace;
import java.io.File;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
public class AudimasTesting {
    WebDriver _globalDriver;

    private WebDriver globalDriver;
    @BeforeTest
    public void SetupWebDriver (){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        _globalDriver = new ChromeDriver(options);
        _globalDriver.get("https://www.audimas.lt/");
        _globalDriver.manage().window().maximize();
        elementWaiter(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")).click();
        asyncClick(_globalDriver, By.xpath("/html/body/div[8]/div/div/div/button")).thenAccept(result -> {
            System.out.println("Async click performed: " + result);
        });
        //elementWaiterToClick(By.xpath("/html/body/div[8]/div/div/div/button")).click();
        ///html/body/div[7]/div/div/div/button
    }
    @Test
    public void AudimasRegistration(){
        _globalDriver.findElement(By.xpath("/html/body/div[4]/header/div/div[3]/div[2]/a")).click();//click uer icon
        elementWaiterToClick(By.xpath("/html/body/div[11]/div/div/div/div/div/div/div/div/div[2]/a")).click();//click prisiregistruoti
        elementWaiter(By.xpath("/html/body/div[11]/div/div/div/div/div/div/form/div[1]/input")).sendKeys(generateRandomEmail());//type email
        String pasword = generateUserName();//generate pwd
        _globalDriver.findElement(By.id("password_reg")).sendKeys(pasword);
        _globalDriver.findElement(By.id("password2_reg")).sendKeys(pasword);
        _globalDriver.findElement(By.id("i_privacy")).click();//check sutinku
        _globalDriver.findElement(By.xpath("/html/body/div[11]/div/div/div/div/div/div/form/div[6]/button")).click();//click Registruotis
        String confirmText = "×\n" +
                "Sveikiname, sėkmingai užsiregistravus. Patvirtinimo laišką su prisijungimo duomenimis išsiuntėme el. paštu.";
        Assert.assertEquals(elementWaiter(By.xpath("/html/body/div[3]/div[3]/div[2]/div[1]/div")).getText(), confirmText);
    }
    @Test
    public void AudimasCartAndPurchase(){
        //type in search bar and enter
        _globalDriver.findElement(By.xpath("/html/body/div[4]/header/div/div[3]/div[4]/div[1]/form/div/input")).sendKeys("lengva striuke su thermore" + Keys.ENTER);
        elementWaiter(By.xpath("/html/body/div[3]/div[3]/div/div/div/div[2]/div[1]/a/span[1]/span/picture[2]/img")).click();//click item
        elementWaiter(By.xpath("/html/body/div[3]/div[3]/div/div/div/div[1]/div[3]/div/div[3]/div[1]/div[2]/form/div[3]/div[2]/ul/li[1]/span")).click();//click size
        String itemName = _globalDriver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div/div/div[1]/div[3]/div/div[3]/div[1]/div[1]/div/p")).getText();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        elementWaiterToClick(By.id("add2cart_button")).click();//add to cart
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        elementWaiterToClick(By.xpath("/html/body/div[3]/header/div/div[3]/div[1]/div[1]")).click();// click cart
        elementWaiterToClick(By.xpath("/html/body/div[3]/header/div/div[3]/div[1]/div[2]/div/div/div[2]/div[2]/div[1]")).click();//click krepselis
        String itemNameCart = elementWaiter(By.xpath("/html/body/div[3]/div[3]/div/div/div/div/div[2]/div[1]/div/table/tbody/tr/td[2]/div/a/span[1]")).getText();
        Assert.assertEquals(itemNameCart, itemName);
        elementWaiter(By.xpath("/html/body/div[3]/div[3]/div/div/div/div/div[2]/div[2]/div[2]/a")).click();//pirkimas
        elementWaiter(By.xpath("/html/body/div[3]/div[3]/div[2]/div/div/div/div/div/div[1]/div/a")).click();//click teti be registracijos
        elementWaiter(By.xpath("/html/body/div[3]/div[2]/div/div/div/div[1]/form/div[1]/div[1]/div/div/button")).click();//click drop list salis
        elementWaiter(By.xpath("/html/body/div[3]/div[2]/div/div/div/div[1]/form/div[1]/div[1]/div/div/div/ul/li[2]")).click();//click LT
        elementWaiter(By.xpath("/html/body/div[3]/div[2]/div/div/div/div[1]/form/div[1]/div[2]/div[1]/div/label")).click();//click pritatymass i
        elementWaiter(By.xpath("/html/body/div[3]/div[2]/div/div/div/div[1]/form/div[2]/div/div/div[1]/div[1]/div/div[1]/button")).click();//click miestas
        elementWaiter(By.xpath("/html/body/div[3]/div[2]/div/div/div/div[1]/form/div[2]/div/div/div[1]/div[1]/div/div[1]/div/ul/li[93]")).click();//click vilnius
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        elementWaiter(By.xpath("/html/body/div[3]/div[2]/div/div/div/div[1]/form/div[2]/div/div/div[1]/div[1]/div/div[2]/button")).click();//click terminalas
        elementWaiter(By.xpath("/html/body/div[3]/div[2]/div/div/div/div[1]/form/div[2]/div/div/div[1]/div[1]/div/div[2]/div/ul/li[278]")).click();//click pc akropolis
        elementWaiter(By.xpath("/html/body/div[3]/div[2]/div/div/div/div[1]/form/div[2]/div/div/div[11]/div/input")).sendKeys("Belekoks");//name
        elementWaiter(By.xpath("/html/body/div[3]/div[2]/div/div/div/div[1]/form/div[2]/div/div/div[12]/div/input")).sendKeys("Belekaitis");//last name
        elementWaiter(By.xpath("/html/body/div[3]/div[2]/div/div/div/div[1]/form/div[2]/div/div/div[14]/div/input")).sendKeys(generateRandomEmail());//email
        elementWaiter(By.xpath("/html/body/div[3]/div[2]/div/div/div/div[1]/form/div[2]/div/div/div[13]/div/div[1]/input")).sendKeys("65924623");//phone number
        elementWaiter(By.xpath("/html/body/div[3]/div[2]/div/div/div/div[1]/form/div[3]/div/div[3]/div/div/div[1]/div/div/label")).click();//click reikalinga sas. fakt.
        elementWaiter(By.xpath("/html/body/div[3]/div[2]/div/div/div/div[1]/form/div[3]/div/div[3]/div/div/div[2]/div/div/input")).sendKeys(generateRandomEmail());//imones pvd
        elementWaiter(By.xpath("/html/body/div[3]/div[2]/div/div/div/div[1]/form/div[3]/div/div[3]/div/div/div[3]/div/div/input")).sendKeys(generateUserName());//imones kodas
        elementWaiter(By.xpath("/html/body/div[3]/div[2]/div/div/div/div[1]/form/div[3]/div/div[3]/div/div/div[4]/div/div/input")).sendKeys(generateUserName());//pvm kodas
        elementWaiter(By.id("cart_continue_button")).click();//testi
        String text = elementWaiter(By.xpath("/html/body/div[3]/div[2]/div/div/div/div[1]/div/form/div[3]/div/div/button")).getText();
        Assert.assertEquals(text, "PATVIRTINTI IR APMOKĖTI");


    }
    @Test
    public void AudimasSMTH(){}
    public WebElement elementWaiter(By by){
    WebDriverWait wait = new WebDriverWait(_globalDriver, Duration.ofSeconds(15));
    WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
    return element;
    }

    public WebElement elementWaiterToClick(By by){
        WebElement element = elementWaiter(by);
        WebDriverWait wait = new WebDriverWait(_globalDriver, Duration.ofSeconds(15));
        element = wait.until(ExpectedConditions.elementToBeClickable(by));
        return element;
    }
    public static String generateRandomEmail() {
        String[] domains = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "example.com"};
        String[] characters = {"abcdefghijklmnopqrstuvwxyz", "0123456789"};

        Random random = new Random();

        StringBuilder email = new StringBuilder();

        // Generate username part
        int usernameLength = random.nextInt(10) + 5; // Random length between 5 to 14 characters
        for (int i = 0; i < usernameLength; i++) {
            String characterSet = characters[random.nextInt(2)]; // Selecting either alphabets or numbers

            char randomChar = characterSet.charAt(random.nextInt(characterSet.length()));
            email.append(randomChar);
        }
        email.append("@");

        // Selecting random domain
        String randomDomain = domains[random.nextInt(domains.length)];
        email.append(randomDomain);

        return email.toString();
    }
    public static String generateUserName(){
        String[] characters = {"abcdefghijklmnopqrstuvwxyz", "0123456789"};
        Random random = new Random();
        StringBuilder username = new StringBuilder();
        int usernameLength = random.nextInt(10) + 10; // Random length between 5 to 14 cha
        for (int i = 0; i < usernameLength; i++) {
            String characterSet = characters[random.nextInt(2)]; // Selecting either alph
            char randomChar = characterSet.charAt(random.nextInt(characterSet.length()));
            username.append(randomChar);
        }
        return username.toString();
    }
    public static CompletableFuture<Boolean> asyncClick(WebDriver driver, By locator) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                WebElement element = new WebDriverWait(driver, Duration.ofSeconds(30))
                        .until(ExpectedConditions.elementToBeClickable(locator));
                element.click();

                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
    }
}
