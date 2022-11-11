package project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

        @FindBy (xpath = "//input[@name='uid']")
        WebElement userID;

        @FindBy (name = "password")
        WebElement password;

        @FindBy (name = "btnLogin")
        WebElement loginBtn;

        @FindBy (xpath = "//table/tbody/tr[3]/td")
        WebElement managerID;

        public void performLogin(HashMap<String,String> data){
            userID.sendKeys(data.get("userID"));
            password.sendKeys(data.get("password"));
            loginBtn.click();
        }

        public String getManagerID(){
            return managerID.getText().split(":")[1];
        }

}
