package testCases;

import com.Bank.pageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TempTest extends  BaseTestClass{

    @Test
    public void tempTest1(){
        Assert.assertTrue(true);
    }

    @Test
    public void tempTest2(){
        Assert.assertTrue(false);
    }
}
