### Customizing Configuration

####Override Security

compile('org.springframework.boot:spring-boot-starter-security')

```
@Configuration
@EnableWebSecurity
```

#### Externalizing Override Settings

```
1. command-line
2. JNDI attribute
3. JVM system properties
4. environment variable
5. application.yml / application.properties
```

#### 配置文件对应不同环境

```
1. @Profile("production)  文件中注解
  spring.profile.active = production
2. application-{profile}.properties 
   application-{profile}.yml
3. application.yml 文件中
---
spring:
   profile: development
logging:
   level:
     root: DEBUG
---     
     
```

#### error 页面映射

```
1. view interface with bean ID of "error"
2. Thymeleaf: error.html
3. FreeMarker: error.ftl
4. Velocity: error.vm
5. JSP: error.jspw
```

-----



### Testing with Spring Boot

#### Intergration Test

1. @RunWith(SpringJUnit4ClassRunner.class)

2. 2 method

   ```
   @ContextConfiguration(classes = EmployeeAPIApplication.class)
   
   //@SpringApplicationConfiguration() spring boot 2 cancel
   @SpringBootTest(classes = EmployeeAPIApplication.class)
   ```

#### MVC Request Test

1. Spring Mock MVC

   - standaloneSetup()   — Manualy created controllers

     ```
     @WebMvcTest(EmployeeController.class)
     ```

   - webAppContextSetup() — Spring Application context

     ```
     @WebAppConfiguration
     ```

     

2. Web Intergration Test (real server request)

   ```
   //@WebIntegrationTest  cancle
   @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
   TestRestTemplete
   ```

3. Test Pages with Selenium

   引入testCompile('org.seleniumhq.selenium:selenium-java:2.45.0')

   ```
   System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver_mac");
           chromeDriver = new ChromeDriver(new ChromeOptions());
   chromeDriver.get(baseUrl);
           assertEquals("小明", chromeDriver.findElementsByTagName("tr").get(1).findElements(By.tagName("td")).get(1).getText());
   ```

   