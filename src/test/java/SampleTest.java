import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleTest {
   @Test(description="SampleTest.testMethodOne() assertTrue true")
   public void testMethodOne() {
      Assert.assertTrue(true);
   }
	  
   @Test(description="SampleTest.testMethodTwo() assertTrue false")
   public void testMethodTwo() {
      Assert.assertTrue(false);
   }
	  
   @Test(dependsOnMethods = {"testMethodTwo"}, description="SampleTest.testMethodThree() assertTrue true")
   public void testMethodThree() {
      Assert.assertTrue(true);
   }
}

