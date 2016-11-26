package TCaptainForm;

import TCommon.TCommonMain;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TCaptainformMain extends TCommonMain{

    protected static String rootDomainUrl = "http://app.123contactform.com/index.php?p=login";

    @Before
    public void runBeforeTestMethod() {
        this.windowHandler.goToPage(TCaptainformMain.rootDomainUrl);
    }

    @After
    public void runAfterTestMethod() {

    }

    @Test
    public void testLogin(){
        //nothing to test yet
    }

}
