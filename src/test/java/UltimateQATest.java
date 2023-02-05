import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

// hermetyzacja (enkapsulacja) - private, public, protect
// zagnieżdżanie klas
// przeciążanie klas (override, overload)
// Testy równoległe - Selenium Grid
// jbehave.org
// obsługa wyjątków
// try, catch, finally

// https://ultimateqa.com/simple-html-elements-for-automation/
public class UltimateQATest extends PageSetup {

    @Test // Cel: weryfikacja działania przycisku
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
    @Test // Cel: weryfikacja formularza z imieniem, emailem
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

        Thread.sleep(3000);

        // Sposoby na szukanie elementów na stronie:
        // - atrybuty (id, className, name)
        // - CSS selector
        // - xpath

        // Ogólna postać xpath: //*[]
        // Przykład: //div[@class=' ']/p
        // * - może zastąpić np. div lup p z końca

        WebElement thanksForContactingUsText = driver.findElement(By.xpath("//div[@class='et-pb-contact-message']/p"));
        Assertions.assertEquals("Thanks for contacting us", thanksForContactingUsText.getText());
    }

    @Test // Cel: czy są cztery wiersze w tabeli
    public void TestThree(){
        List<WebElement> listOfATitles = driver.findElements(By.xpath("//table[@id='htmlTableId']//tr"));
        // Dwa ukośniki bo trzeba zjechać dwa poziomy niżej (table/tbody/tr)
        // Po napisaniu xpath należy skopiować go do szukajki w przeglądarce i sprawdzić czy nie istnieją elementy o
        // tym samym xpath

        // Można też skakać po tabeli w następujący sposób:
        // //table[@id='htmlTableId']//tr[1] - pierwszy wiersz
        // //table[@id='htmlTableId']//tr[2] - drugi wiersz
        // //table[@id='htmlTableId']//tr[2]/td[1] - drugi wiersz, pierwsza komórka
        // itd

        Assertions.assertEquals(4, listOfATitles.size());
    }

    // Nadawanie grup testom np. TestNG
    // @org.testing.annotations.Test({"smoke test});

    @Test() // Cel: weryfikacja działania listy (dropdown)
    public void TestFour(){
        List<String> listOfCars = new ArrayList<>();

        listOfCars.add("volvo");
        listOfCars.add("saab");
        listOfCars.add("opel");
        listOfCars.add("audi");

        // Szukanie listy na stronie:
        WebElement dropdownOfCars = driver.findElement(By.xpath("//div[@class='et_pb_blurb_description']//select"));
        // //div[text()='Select an option and validate that it is selected']

        // Listy są indeksowane od 0
        for(int i=0; i < listOfCars.size(); i++){

            // Klikanie na listę:
            dropdownOfCars.click();

            System.out.println("//option[@value='"+ listOfCars.get(i) + "']");

            // Klikanie na element w liście:
            WebElement dropdownOfCarsOption = driver.findElement(By.xpath("//option[@value='"+ listOfCars.get(i) + "']"));

            dropdownOfCarsOption.click();

            // Potwierdź prawdę czy opcja np. 'opel' jest wybrana
            Assertions.assertTrue(dropdownOfCarsOption.isSelected());

            System.out.println(dropdownOfCarsOption.getText().toLowerCase());

            Assertions.assertEquals(listOfCars.get(i), dropdownOfCarsOption.getText().toLowerCase());

            // //input[@type='checkbox' and @value='Bike']
            // //div[contains(text(), 'Select an option')]
            // //div[contains(text(), 'and validate that')]
            // //div[contains(text(), 'and validate that')]/ancestor::div
            // ancestor, following-sibling,
        }
    }
}