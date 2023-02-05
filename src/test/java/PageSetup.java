import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageSetup {
    // final - jeśli wiemy, że zmienna nigdy się nie zmieni
    String projectLocation = System.getProperty("user.dir");
    String ultimateQAURL = "https://ultimateqa.com/simple-html-elements-for-automation/";
    WebDriver driver; //WebDriver to interfejs
    @BeforeEach // adnotacja, która oznacza że metoda będzie się wykonywała przed każdym testem
    public void pageSetup(){
        System.setProperty("webdriver.chrome.driver",projectLocation+"/resources/chromedriver.exe");
        // =========== Uruchomienie przeglądarki ===========
        driver = new ChromeDriver();
        // =========== Dostosowanie okna przeglądarki do rozmiaru wyświetlacza ===========
        driver.manage().window().maximize();
        // =========== Zamknięcie przeglądarki ===========
        // driver.close();
        // =========== Przekierowanie na stronę ===========
        driver.get(ultimateQAURL);
    }
    public void pageTearUp(){
        //driver.close();
    }
}
