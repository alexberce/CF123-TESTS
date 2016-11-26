package T123ContactForm.Dashboard;

import T123ContactForm.T123ContactFormMain;
import org.junit.Test;
import org.openqa.selenium.By;

public class DashboardMain extends T123ContactFormMain {

    @Test
    public void checkThatWeAreOnDashboard() {
        this.getWindowHandler().iAmOnPage("Dashboard");
        this.getWindowHandler().iAmOnPage("Dashboard - 123ContactForm");
    }

    @Test
    public void checkThatNewFormsButtonExists(){
        this.getWebElementsHandler().clickElementOrFail(By.id("qa-forms"), "Forms are not there!");
    }

}
