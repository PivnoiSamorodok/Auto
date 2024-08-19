package UmobixMainFlowTests1;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class retryTests implements IRetryAnalyzer {
    int actualRetry = 0;
    static final int maxRetry = 3;

    public boolean retry(ITestResult result){
        if(actualRetry < maxRetry){
            actualRetry++;
            return true;
        } else {
            return false;
        }
    }
}
