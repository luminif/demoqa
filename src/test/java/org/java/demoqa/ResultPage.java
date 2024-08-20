package org.java.demoqa;

import org.java.core.BaseSeleniumPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ResultPage extends BaseSeleniumPage {
    @FindBy(xpath = "//div[@class='modal-header']")
    private WebElement header;

    @FindBy(xpath = "//div[@class='table-responsive']//tbody/tr/td[2]")
    private List<WebElement> data;

    public ResultPage() {
        PageFactory.initElements(driver, this);
    }

    public String getHeader() {
        return header.getText();
    }

    public List<String> getFormData() {
        return data.stream()
            .map(WebElement::getText)
            .toList();
    }
}
