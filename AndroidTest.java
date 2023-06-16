package com.test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

public class AndroidTest {
    private By btnMenu = AppiumBy.accessibilityId("open menu");
    private By menuBtnLogin = AppiumBy.accessibilityId("menu item log in");
    private By txtUsername = AppiumBy.accessibilityId("Username input field");
    private By txtPassword = AppiumBy.accessibilityId("Password input field");
    private By btnLogin = AppiumBy.accessibilityId("Login button");

    @Test
    public void launchApp() throws MalformedURLException, InterruptedException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Chathumal Device");
        options.setApp(System.getProperty("user.dir") + "/apps/Android-MyDemoAppRN.1.3.0.build-244.apk");
        AndroidDriver androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        Thread.sleep(4000);
    }

    @Test
    public void Navigation() throws MalformedURLException, InterruptedException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Chathumal Device");
        options.setApp(System.getProperty("user.dir") + "/apps/Android-MyDemoAppRN.1.3.0.build-244.apk");

        AndroidDriver androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        Thread.sleep(4000);

        androidDriver.findElement(btnMenu).click();
        Thread.sleep(4000);

        androidDriver.findElement(menuBtnLogin).click();
        Thread.sleep(4000);

        androidDriver.findElement(txtUsername).sendKeys("bob@example.com");
        Thread.sleep(4000);

        androidDriver.findElement(txtPassword).sendKeys("10203040");
        Thread.sleep(4000);

        androidDriver.findElement(btnLogin).click();
        Thread.sleep(4000);
    }

    @Test
    public void NavigationWithExplicitWaits() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Chathumal Device");
        options.setApp(System.getProperty("user.dir") + "/apps/Android-MyDemoAppRN.1.3.0.build-244.apk");

        AndroidDriver androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

        new WebDriverWait(androidDriver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(btnMenu)).click();
        new WebDriverWait(androidDriver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(menuBtnLogin)).click();
        new WebDriverWait(androidDriver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(txtUsername)).sendKeys("bob@example.com");
        new WebDriverWait(androidDriver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(txtPassword)).sendKeys("10203040");
        new WebDriverWait(androidDriver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(btnLogin)).click();
    }

    @Test
    public void NavigationWithElementWaitPlugin() throws MalformedURLException {

        // here I used  `appium --use-plugins=element-wait@1.5.0` when appium sever start

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Chathumal Device");
        options.setApp(System.getProperty("user.dir") + "/apps/Android-MyDemoAppRN.1.3.0.build-244.apk");

        AndroidDriver androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

        androidDriver.findElement(btnMenu).click();
        androidDriver.findElement(menuBtnLogin).click();
        androidDriver.findElement(txtUsername).sendKeys("bob@example.com");
        androidDriver.findElement(txtPassword).sendKeys("10203040");
        androidDriver.findElement(btnLogin).click();
    }

    @Test
    public void tap() throws MalformedURLException {


        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Chathumal Device");
        options.setApp(System.getProperty("user.dir") + "/apps/Android-MyDemoAppRN.1.3.0.build-244.apk");

        AndroidDriver androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        Point location = androidDriver.findElement(menuBtnLogin).getLocation();
        Dimension size = androidDriver.findElement(menuBtnLogin).getSize();


    }

    @Test
    public void swipeOrScroll() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Chathumal Device");
        options.setApp(System.getProperty("user.dir") + "/apps/ApiDemos-debug.apk");

        AndroidDriver androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

        androidDriver.findElement(By.xpath(".//*[@text='Views']")).click();

        Dimension size = androidDriver.manage().window().getSize();

        int startX = size.getWidth() / 2;

        int startY = size.getHeight() / 2;

        int endX = startY;

        int endY = (int) (size.getHeight() * 0.25);

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");

        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))

                .addAction(new Pause(finger1, Duration.ofMillis(200)))
                .addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));


        androidDriver.perform(Collections.singletonList(sequence));

    }
}
