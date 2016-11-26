package T123ContactForm;

import TCommon.TCommonMain;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;

public class T123ContactFormMain extends TCommonMain {

    private static String rootDomainUrl = "https://www.123contactform.com";

    public T123ContactFormMain(){

    }

    @Before
    public void runBeforeTestMethod(){
        this.loginTo123CF();
    }

    @After
    public void runAfterTestMethod(){
        this.logoutFrom123CF();
    }

    private void loginTo123CF() {
        this.getWindowHandler().goToPage(T123ContactFormMain.rootDomainUrl);

        this.checkThatWeAreOnLoginPage();

        this.getWebElementsHandler().click(By.id("headerLogin"));

        this.getWebElementsHandler().fillElementOrFail(By.name("username"), "cirtogflorin@gmail.com", "Username doesn't exist!");
        this.getWebElementsHandler().fillElementOrFail(By.name("password"), "tz056889", "Password is not correct!");

        this.getWebElementsHandler().clickElementOrFail(By.id("submit-form-button"), "Submit button is not there!");
    }

    private void logoutFrom123CF(){
        this.getWindowHandler().goToPage(T123ContactFormMain.rootDomainUrl + "/index.php?p=signout");
    }

    private void checkThatWeAreOnLoginPage() {
        this.getWindowHandler().iAmOnPage("Login");
        this.getWindowHandler().iAmOnPage("Login to your 123ContactForm account");
    }

}
