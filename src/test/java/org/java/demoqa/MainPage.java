package org.java.demoqa;

import org.java.core.BaseSeleniumPage;
import org.java.utils.Student;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.util.List;

public class MainPage extends BaseSeleniumPage {
    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    @FindBy(id = "userEmail")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@id='userNumber']")
    private WebElement mobileInput;

    @FindBy(id = "dateOfBirthInput")
    private WebElement dateOfBirthInput;

    @FindBy(css = "#subjectsInput")
    private WebElement subjectsInput;

    @FindBy(id = "uploadPicture")
    private WebElement uploadPictureInput;

    @FindBy(id = "currentAddress")
    private WebElement currentAddressInput;

    @FindBy(id = "state")
    private WebElement stateSelect;

    @FindBy(id = "city")
    private WebElement citySelect;

    @FindBy(id = "submit")
    private WebElement submitButton;

    public MainPage() {
        driver.get("https://demoqa.com/automation-practice-form");
        PageFactory.initElements(driver, this);
    }

    private void setFirstName(String firstName) {
        firstNameInput.sendKeys(firstName);
    }

    private void setLastName(String lastName) {
        lastNameInput.sendKeys(lastName);
    }

    private void setEmail(String email) {
        emailInput.sendKeys(email);
    }

    private void setGender(String gender) {
        String genderId = switch (gender) {
            case "Male" -> "1";
            case "Female" -> "2";
            default -> "3";
        };

        WebElement genderInput = driver.findElement(By.id("gender-radio-%s".formatted(genderId)));
        new Actions(driver).moveToElement(genderInput).click().perform();
    }

    private void setMobile(String mobile) {
        mobileInput.sendKeys(mobile);
    }

    private void setDateOfBirth(String month, String year, String day) {
        dateOfBirthInput.click();

        //month
        WebElement monthInput = driver.findElement(By.className("react-datepicker__month-select"));
        monthInput.sendKeys(month);
        monthInput.click();

        //year
        WebElement yearInput = driver.findElement(By.className("react-datepicker__year-select"));
        yearInput.sendKeys(year);
        yearInput.click();

        //day
        driver.findElement(By.className("react-datepicker__day--0%s".formatted(day))).click();
    }

    private void setSubjects(List<String> subjects) {
        subjects.forEach(subject -> {
            subjectsInput.sendKeys(subject);
            subjectsInput.sendKeys(Keys.ENTER);
        });
    }

    private void setHobbies(List<String> hobbies) {
        hobbies.forEach(hobby -> {
            String hobbyId = switch (hobby) {
                case "Sports" -> "1";
                case "Reading" -> "2";
                default -> "3";
            };

            WebElement hobbyInput = driver.findElement(By.id("hobbies-checkbox-%s".formatted(hobbyId)));
            new Actions(driver).moveToElement(hobbyInput).click().perform();
        });
    }

    private void setPicture(String imagePath) {
        uploadPictureInput.sendKeys(new File(imagePath).getAbsolutePath());
    }

    private void setCurrentAddress(String address) {
        currentAddressInput.sendKeys(address);
    }

    private void setState(String state) {
        stateSelect.click();
        driver.findElement(By.xpath("//*[text()='%s']".formatted(state))).click();
    }

    private void setCity(String city) {
        citySelect.click();
        driver.findElement(By.xpath("//*[text()='%s']".formatted(city))).click();
    }

    private void submitForm() {
        submitButton.click();
    }

    public void fillForm(Student student) {
        setFirstName(student.firstName());
        setLastName(student.lastName());
        setEmail(student.email());
        setGender(student.gender());
        setMobile(student.mobile());
        setDateOfBirth(student.month(), student.year(), student.day());
        setSubjects(student.subjects());
        setHobbies(student.hobbies());
        setPicture(student.imagePath());
        setCurrentAddress(student.address());
        setState(student.state());
        setCity(student.city());
        submitForm();
    }
}