package testing;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class DespegarFeatureStepdefs {

    private WebDriver driver;
    private final static String FINAL_TEST="¡Falta poco! Completa tus datos y finaliza tu compra";
    private boolean check;

    @Given("^The website is ready and loaded$")
    public void theWebsiteIsReadyAndLoaded() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\usuario\\Desktop\\CARPETA1\\serenityBDDCucumber\\Chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.despegar.com.co/");

    }

    @When("^Departure is \"([^\"]*)\" and Destination is \"([^\"]*)\" and dates Between the 15 and 25")
    public void departureIsAndDestinationIsAndDatesBetweenTheAnd( String dep, String arr) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        int segundosAEsperar = 10;
        driver.findElement( By. xpath( "//*[contains(text(),'Ida y vuelta')]//following::input[@placeholder=\"Ingresa desde dónde viajas\"][1]")).click();
        driver.findElement( By. xpath( "//*[contains(text(),'Ida y vuelta')]//following::input[@placeholder=\"Ingresa desde dónde viajas\"][1]")).sendKeys(dep);
        driver.manage().timeouts().implicitlyWait(segundosAEsperar, TimeUnit.SECONDS);
        driver.findElement( By. xpath( "//ul[@class=\"ac-group-items\"]//li[@class=\"item -active\"]//span[@class=\"item-text\"]")).click();

        driver.findElement( By. xpath( "//*[contains(text(),'Ida y vuelta')]//following::input[@placeholder=\"Ingresa hacia dónde viajas\"][1]")).click();
        driver.findElement( By. xpath( "//*[contains(text(),'Ida y vuelta')]//following::input[@placeholder=\"Ingresa hacia dónde viajas\"][1]")).sendKeys(arr);
        driver.manage().timeouts().implicitlyWait(segundosAEsperar, TimeUnit.SECONDS);
        driver.findElement( By. xpath( "//ul[@class=\"ac-group-items\"]//li[@class=\"item -active\"]//span[@class=\"item-text\"][1]")).click();

        driver.findElement( By. xpath( "//*[contains(text(),'Ida y vuelta')]//following::input[@placeholder=\"Ida\"][1]")).click();
        driver.findElement( By. xpath( "//following::div[@class=\"sbox5-monthgrid-datenumber-number\"][15]")).click();
        driver.findElement( By. xpath( "//following::div[@class=\"sbox5-monthgrid-datenumber-number\"][25]")).click();
        driver.findElement( By. xpath( "//button/em[contains(text(),'Buscar')]")).click();
    }

    @Then("^Despegar should select the first flight available and goes to the pay page$")
    public void despegarShouldSelectFirstFlightAvailable() {
        //look for element tag name items having dev with id "clusters" if available it means server returned flights
        driver.findElement( By. xpath( "//div[@id=\"clusters\"]/span[1]//em[contains(text(),'Comprar')]")).click();
        driver.findElement( By. xpath( "//em[contains(text(),'Continuar')]")).click();
        WebElement element = driver.findElement(By.xpath("//h2[contains(text(),'¡Falta poco! Completa tus datos y finaliza tu compra')]"));
        System.out.println(element.getText());
        this.check = element.getText().equals(FINAL_TEST);
        assertTrue(check);
    }
}
