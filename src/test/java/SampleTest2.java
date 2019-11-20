import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleTest2 {
   @Test(description="SampleTest2.myMethod1() assertTrue true")
   public void myMethod1() {
      Assert.assertTrue(true);
   }
	  
   @Test(description="SampleTest2.myMethod2() assertTrue false")
   public void myMethod2() {
      Assert.assertTrue(false);
   }
	  
   @Test(dependsOnMethods = {"myMethod2"}, description="SampleTest2.myMethod2() assertTrue true")
   public void myMethod3() {
      Assert.assertTrue(true);
   }
}

