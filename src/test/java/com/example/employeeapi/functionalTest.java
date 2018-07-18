package com.example.employeeapi;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertEquals;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class functionalTest {
    private static ChromeDriver chromeDriver;

    @Value("${local.server.port}")
    private int port;

    @Before
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver_mac");
        chromeDriver = new ChromeDriver(new ChromeOptions());
        chromeDriver.manage().timeouts()
                .implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void showEmployee() {
        String baseUrl = "http://localhost:" + port + "/employees";
        chromeDriver.get(baseUrl);
        chromeDriver.findElementById("username").sendKeys("admin");
        chromeDriver.findElementById("password").sendKeys("123");
        chromeDriver.findElementById("submit").click();
        assertEquals("小明", chromeDriver.findElementsByTagName("tr").get(1).findElements(By.tagName("td")).get(1).getText());
    }

    @After
    public void closeBrowser() {
        chromeDriver.close();
    }
}
