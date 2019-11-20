package listeners;

import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;

import org.testng.ITestResult;
import java.util.Set;
import org.testng.ITestNGMethod;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

public class CustomReporter implements IReporter{
   @Override
   public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
      String outputDirectory) {
      
      //Iterating over each suite included in the test
      for (ISuite suite : suites) {
            
         //Following code gets the suite name
         String suiteName = suite.getName();

         Map<String, ISuiteResult> suiteResults = suite.getResults();
         for (ISuiteResult sr : suiteResults.values()) {
            
            ITestContext tc = sr.getTestContext();
             for (ITestResult tresult : tc.getPassedTests().getAllResults()) {
               String result=null;
               switch (tresult.getStatus()) {
                  case ITestResult.SUCCESS:  result = "passed";
                     break;
                  case  ITestResult.FAILURE: result = "failed";
                     break;
                  case  ITestResult.SKIP: result = "disabled";
                     break;
               }

               JsonObject json =Json.createObjectBuilder()
               .add("testsuite",suiteName)
               .add("describe", tc.getName())
               .add("it",tresult.getMethod().getDescription())
               .add("result",result).build();
               System.out.println(json.toString());
                
             }

         }
      }
   }
}

