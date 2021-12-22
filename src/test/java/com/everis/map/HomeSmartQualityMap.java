package com.everis.map;

import com.everis.core.Hook;
import com.everis.pages.BasePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class HomeSmartQualityMap extends BasePage {

    public HomeSmartQualityMap(){
        PageFactory.initElements(new AppiumFieldDecorator(Hook.getDriver()), this);
    }

    protected MobileElement campoDeTexto(String valorCampo) {
        By campoTexto = By.xpath("//input[@name='"+valorCampo+"']");
        MobileElement elementoText = this.driver.findElement(campoTexto);
        return elementoText;

    }


}
