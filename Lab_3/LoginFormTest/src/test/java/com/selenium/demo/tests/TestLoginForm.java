package com.selenium.demo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestLoginForm {

    private WebDriver driver;

    @BeforeMethod
    public void setup() { 
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // 👉 Adjust this path if necessary
        driver.get("http://localhost:8000/login.html");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // 🔹 1. Successful login
    @Test
    public void testLoginSuccess() throws InterruptedException {
        By usernameField = By.id("username");
        By passwordField = By.id("password");
        By loginBtn = By.id("btnLogin");

        driver.findElement(usernameField).sendKeys("sv1@ptit.edu.vn");
        driver.findElement(passwordField).sendKeys("P@ssw0rd");
        driver.findElement(loginBtn).click();

        Thread.sleep(1000); // wait for success message to appear

        WebElement msgSuccess = driver.findElement(By.id("msg-success"));
        Assert.assertTrue(msgSuccess.isDisplayed(), "Expected success message not found.");
        System.out.println("Test 1 Passed: Successful login message displayed correctly.");
    }

    // 🔹 2. Invalid login credentials
    @Test
    public void testLoginWrongPassword() throws InterruptedException {
        driver.findElement(By.id("username")).sendKeys("sv1@ptit.edu.vn");
        driver.findElement(By.id("password")).sendKeys("wrong_pass");
        driver.findElement(By.id("btnLogin")).click();

        Thread.sleep(1000);

        WebElement msgError = driver.findElement(By.id("msg-error"));
        Assert.assertTrue(msgError.getText().contains("Invalid credentials."),
                "Expected 'Invalid credentials.' message was not displayed.");
        System.out.println("Test 2 Passed: Error message displayed correctly for invalid credentials.");
    }

    // 🔹 3. Empty fields validation
    @Test
    public void testEmptyFields() throws InterruptedException {
        driver.findElement(By.id("btnLogin")).click();
        Thread.sleep(500);

        WebElement msgError = driver.findElement(By.id("msg-error"));
        Assert.assertTrue(msgError.getText().contains("Please fill all required fields."),
                "Expected warning message for empty fields not found.");
        System.out.println("Test 3 Passed: Warning message displayed for empty username/password fields.");
    }

    // 🔹 4. Forgot password link
    @Test
    public void testForgotPasswordLink() {
        WebElement linkForgot = driver.findElement(By.id("linkForgot"));
        Assert.assertTrue(linkForgot.isDisplayed(), "'Forgot password?' link is not visible.");
        linkForgot.click();

        System.out.println("Test 4 Passed: 'Forgot password?' link is visible and clickable (currently points to '#').");
    }

    // 🔹 5. Sign-up link
    @Test
    public void testSignUpLink() {
        WebElement linkSignup = driver.findElement(By.id("linkSignup"));
        Assert.assertTrue(linkSignup.isDisplayed(), "'SIGN UP' link is not visible.");
        linkSignup.click();

        System.out.println("Test 5 Passed: 'SIGN UP' link is visible and clickable (currently points to '#').");
    }

    // 🔹 6. Social login buttons visibility and interaction
    @Test
    public void testSocialLoginButtons() {
        WebElement fb = driver.findElement(By.id("btnFacebook"));
        WebElement tw = driver.findElement(By.id("btnTwitter"));
        WebElement gg = driver.findElement(By.id("btnGoogle"));

        Assert.assertTrue(fb.isDisplayed() && tw.isDisplayed() && gg.isDisplayed(),
                "One or more social login buttons are missing.");
        System.out.println("Test 6 Passed: All three social login buttons (Facebook, Twitter, Google) are visible.");

        // Simulate button clicks (they currently don't trigger navigation)
        fb.click();
        tw.click();
        gg.click();
    }
}
