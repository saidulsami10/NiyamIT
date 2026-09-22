package stepdefinitions;
import java.time.Duration;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import hooks.TestSetup;
import io.cucumber.java.en.*;

public class CareerSteps {
    WebDriverWait wait = new WebDriverWait(TestSetup.driver, Duration.ofSeconds(15));
    JavascriptExecutor js = (JavascriptExecutor) TestSetup.driver;

    private void click(By locator) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
        js.executeScript("arguments[0].click();", el);
    }

    @Given("I open the Niyam IT website")
    public void i_open_the_niyam_it_website() {}

    @When("I click on Careers")
    public void i_click_on_careers() {
        click(By.xpath("//ul[@class='linkNames']//button[text()='Careers']"));
    }

    @When("I view the available careers")
    public void i_view_the_available_careers() {
        click(By.xpath("//a[contains(text(), 'View Careers')] | //button[contains(text(), 'View Careers')]"));
    }

    @Then("I should see the open career positions")
    public void i_should_see_the_open_career_positions() {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//*[contains(@class, 'careers')] | //*[contains(text(), 'Openings') or contains(text(), 'Current Opportunities')]"))).isDisplayed());
    }
    
    @When("I select Automation Engineer")
    public void i_select_automation_engineer() {
        String orig = TestSetup.driver.getWindowHandle();
        click(By.xpath("//li[@id='bhrPositionID_432']//a | //a[text()='Automation Engineer']"));
        TestSetup.driver.getWindowHandles().stream().filter(h -> !h.equals(orig)).findFirst()
            .ifPresent(h -> TestSetup.driver.switchTo().window(h));
    }

    @When("I click Apply for this Job")
    public void i_click_apply_for_this_job() {
        click(By.xpath("//a[contains(text(), 'Apply') or contains(text(), 'APPLY')] | //button[contains(text(), 'Apply') or contains(text(), 'APPLY')]"));
    }

    @Then("I should see the job application page")
    public void i_should_see_the_job_application_page() {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//form | //input[@type='email'] | //*[contains(text(), 'Submit') or contains(text(), 'Application')]"))).isDisplayed());
    }
}