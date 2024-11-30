package crawler;
    import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
    import utilities.ReusableMethods;
    import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

    public class AmazonCrawler {
        public static void main(String[] args) {
            // 1. Selenium WebDriver'ı başlat
            System.setProperty("webdriver.chrome.driver", "PATH_TO_CHROMEDRIVER");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            WebDriver driver = new ChromeDriver(options);

            try {
                driver.get("https://www.amazon.com");
                WebElement shopByDepartment = driver.findElement(By.id("nav-link-shopall"));
                ReusableMethods.clickWithJS((WebElement) By.id("nav-link-shopall"));

                List<WebElement> departmentLinks = driver.findElements(By.cssSelector("a.nav-hasArrow"));
                BufferedWriter writer = createOutputFile();

                for (WebElement link : departmentLinks) {
                    String url = link.getAttribute("href");
                    String title = link.getText();

                    if (url != null && !url.isEmpty()) {
                        String status = checkLinkStatus(url);
                        saveResult(writer, url, title, status);
                    }
                }
                writer.close();
                System.out.println("Results were saved to file.");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                driver.quit();
            }
        }


        private static String checkLinkStatus(String link) {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(link).openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                int responseCode = connection.getResponseCode();
                return (responseCode == 200) ? "OK" : "Dead link";
            } catch (IOException e) {
                return "Dead link";
            }
        }

        private static BufferedWriter createOutputFile() throws IOException {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String fileName = timestamp + "_results.txt";
            return new BufferedWriter(new FileWriter(fileName));
        }

        private static void saveResult(BufferedWriter writer, String link, String title, String status) throws IOException {
            writer.write(link + "\t" + title + "\t" + status + "\n");
        }
    }


