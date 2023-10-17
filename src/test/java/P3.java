
// Generated by Selenium IDE

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class P3 {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    @BeforeEach
    public void setUp() {
//        System.setProperty("webdriver.gecko.driver","C:\\Users\\Mati-HP\\Downloads\\geckodriver.exe");
//        driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Mati-HP\\Downloads\\chromedriver.exe");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;

        vars = new HashMap<String, Object>();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void p1() {
        driver.get("http://www.plan.uz.zgora.pl/");
        driver.findElement(By.linkText("Plan nauczycieli")).click();
        driver.findElement(By.linkText("B")).click();
        js.executeScript("window.scrollTo(0,14000)");
        driver.findElement(By.linkText("dr inż. Jacek Bieganowski")).click();
        System.out.println(""+driver.findElement(By.cssSelector(".main")).getText());
        assertThat(driver.findElement(By.cssSelector(".main")).getText(), containsString("Seminarium specjalistyczne II"));
    }

    @Test
    public void WolneTerminy() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://rezerwacja.zielona-gora.pl/");
        boolean test = true;
        String month = null;
        List<WebElement> elements = null;
        while (test) {
            Thread.sleep(5000);
            String s =driver.getPageSource();
            boolean t=s.contains("is-valid");
            if(t) {
                elements = driver.findElements(By.className("is-valid"));
                month = driver.findElement(By.cssSelector(".calendar-nav-month")).getText();
                test = false;
                }
            else{
                driver.findElement(By.linkText("›")).click();
            }
        }
        System.out.println(month);
        assertThat(month, containsString("Sierpień 2021"));
        assertThat(elements.size() , not(is(0)) );
    }
    @Test
    public void BraktWolnychTerminowWTymMiesiacu() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://rezerwacja.zielona-gora.pl/");
        Thread.sleep(5000);
        List<WebElement> elements = driver.findElements(By.className("is-valid"));
        String month = driver.findElement(By.cssSelector(".calendar-nav-month")).getText();
        int index = month.indexOf(" ");
        month = month.substring(0,index);
        System.out.println(month);
        int monthFromDate = new Date().getMonth();
        String tabMonth[] = new String[]{"Styczeń", "Luty","Marzec", "Kwiecień", "Maj", "Czerwiec", "Lipiec", "Sierpień", "Wrzesień", "Październik", "Listopad", "Grudzień"};
        assertThat(month, containsString(tabMonth[monthFromDate]));
        assertThat(elements.size(), is(0) );
    }
    @Test
    public void KupSamochod() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//maximize window
        driver.manage().window().maximize();

//open browser with desried URL
        driver.get("https://www.otomoto.pl/oferta/fiat-bravo-fiat-bravo-2-ID6DJZqS.html#001e82aabe");
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        String s =driver.getPageSource();

        int index = s.indexOf("<span class=\"offer-price__number\">");
        String value =s.substring(index+34,index+45).replaceAll(" ", "").replaceAll("\n","");
        int valueToInt = (Integer.parseInt(value));
        System.out.println(value+" "+valueToInt);
        boolean goodPrice;
        if(valueToInt<=9000){
            goodPrice=true;
        }
        else {
            goodPrice=false;
        }
        assertThat(goodPrice , is(true) );
    }
    @Test
    public void KupBTC() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//maximize window
        driver.manage().window().maximize();

//open browser with desried URL
        driver.get("https://bitbay.net/pl/kurs-walut/kurs-bitcoin-pln");
        String s = (driver.findElement(By.cssSelector(".last__value")).getText());
        String value =s.replaceAll(",","");
        double valueToDouble = Double.parseDouble(value);
        boolean buy;
        if(valueToDouble<150000){
            buy = true;
        }else{
            buy=false;
        }
        assertThat(buy, is(true));
    }
    @Test
    public void SprzedajBTC() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//maximize window
        driver.manage().window().maximize();

//open browser with desried URL
        driver.get("https://bitbay.net/pl/kurs-walut/kurs-bitcoin-pln");
        String s = (driver.findElement(By.cssSelector(".last__value")).getText());
        String value =s.replaceAll(",","");
        double valueToDouble = Double.parseDouble(value);
        boolean sell;
        if(valueToDouble>200000){
            sell = true;
        }else{
            sell=false;
        }
        assertThat(sell, is(true));
    }

    @Test
    public void randomOrgTest(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.random.org/");
        driver.switchTo().frame(0);
        driver.findElement(By.id("true-random-integer-generator-button")).click();
        {
            WebElement element = driver.findElement(By.id("true-random-integer-generator-button"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        int i = Integer.valueOf(driver.findElement(By.xpath("/html/body/div/span[5]/center/span[1]")).getText());
        System.out.println(i);
        assertTrue(i >= 0 && i<= 100);
    }
    @Test
    public void Szyfrowanie() throws InterruptedException {
        String szyfrowany = "TestSzyfrowy";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.base64encode.org/");
        Thread.sleep(4000);
        driver.findElement(By.cssSelector(".css-47sehv")).click();
        driver.findElement(By.id("input")).click();
        driver.findElement(By.id("input")).sendKeys(szyfrowany);
        driver.findElement(By.id("submit_text")).click();
        String szyfr = driver.findElement(By.id("output")).getText();
        System.out.println(szyfr);
        driver.get("https://www.base64decode.net/");
        js.executeScript("window.scrollTo(0,48)");
        driver.findElement(By.name("request")).click();
        driver.findElement(By.name("request")).sendKeys(szyfr);
        driver.findElement(By.cssSelector("input")).click();
        {
            WebElement element = driver.findElement(By.cssSelector("input"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        String deszyfrowany = driver.findElement(By.xpath("/html/body/div[2]/div[1]/form/div[4]/textarea")).getText();
        System.out.println(deszyfrowany);
        assertEquals(szyfrowany, deszyfrowany);
    }
}
