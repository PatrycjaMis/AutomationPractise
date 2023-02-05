import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

// hermetyzacja (enkapsulacja) - private, public, protect
// zagnieżdżanie klas
// przeciążanie klas (override, overload)
// Testy równoległe - Selenium Grid
// jbehave.org
// obsługa wyjątków
// try, catch, finally
public class UltimateQATest extends PageSetup {

    @Test
    public void testOne(){
        // Szukamy bezpośrednio elementów na stronie:
        // WebElement - interfejs
        WebElement buttonUsingID = driver.findElement(By.id("idExample"));
        // Lepiej nie szukać po id, bo może być kilka tych samych elementów na stronie

        buttonUsingID.click();

        WebElement buttonSuccessText = driver.findElement(By.className("entry-title"));

        // Weryfikacja czy tekst się znajduje na stronie:
        // Assertions.assertEquals(oczekiwany rezultat, aktualny rezultat);
        Assertions.assertEquals("Button success", buttonSuccessText.getText());

        // Ten test nie przejdzie (case sensitive):
        //Assertions.assertEquals("Button Success", buttonSuccessText.getText());
        // W konsoli wypisane jest gdzie wystąpił błąd
    }
    @Test
    public void testTwo() throws InterruptedException {
        // Testowanie pola tekstowego z imieniem:
        WebElement nameTextField = driver.findElement(By.id("et_pb_contact_name_0"));

        // Przesłanie tekstu do pola tekstowego:
        nameTextField.sendKeys("Tester");

        // Testowanie pola tekstowego z emailem:
        WebElement emailTextField = driver.findElement(By.id("et_pb_contact_email_0"));

        // Przesłanie tekstu do pola tekstowego:
        emailTextField.sendKeys("tester@tester.com");

        // implicit wait / explicit wait / Thread.sleep

        Thread.sleep(2000);

        WebElement emailMeButton = driver.findElement(By.name("et_builder_submit_button"));

        // Sposoby naciśnięcia w element:
        // - .click()
        // - .Keys.ENTER()
        // - Action Perform
        // - custom javascript methods

        emailMeButton.click();
        //emailMeButton.sendKeys(Keys.ENTER);

        // Sposoby na szukanie elementów na stronie:
        // - atrybuty (id, className, name)
        // - CSS selector
        // - xpath

        // Ogólna postać xpath: //*[]
        // Przykład: //div[@class=' ']/p
        // * - może zastąpić np. div lup p z końca

        Thread.sleep(2000);

        WebElement thanksForContactingUsText = driver.findElement(By.xpath("//div[@class='et-pb-contact-message']/p"));
        Assertions.assertEquals("Thanks for contacting us", thanksForContactingUsText.getText());
    }
}