package listeners;
import org.testng.ITestContext;
import org.testng.TestListenerAdapter;

import javax.json.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class CustomListener extends TestListenerAdapter {


    @Override
    public void onFinish(ITestContext testContext) {


        JsonArrayBuilder results = Json.createArrayBuilder();


        testContext.getPassedTests().getAllResults()
                .forEach(result -> {

                            String it = result.getName() + " " + result.getMethod().getDescription();
                            ITestContext ctx = result.getTestContext();
                            String desc = ctx.getName();
                            String testsuite = ctx.getSuite().getName();
                            String testId = System.getenv("TESTID");
                            testId = testId != null ? testId : "default";

                            JsonObject json = Json.createObjectBuilder()
                                    .add("eventType", "TestResult_2")
                                    .add("testId", testId)
                                    .add("testsuite", testsuite)
                                    .add("describe", desc)
                                    .add("result", "success")
                                    .add("it", it).build();


                            results.add(json);
                        }
                );


        testContext.getFailedTests().getAllResults()
                .forEach(result -> {

                            String it = result.getName() + " " + result.getMethod().getDescription();
                            ITestContext ctx = result.getTestContext();
                            String desc = ctx.getName();
                            String testsuite = ctx.getSuite().getName();
                            String testId = System.getenv("TESTID");
                            testId = testId != null ? testId : "default";

                            JsonObject json = Json.createObjectBuilder()
                                    .add("eventType", "TestResult_2")
                                    .add("testId", testId)
                                    .add("testsuite", testsuite)
                                    .add("describe", desc)
                                    .add("result", "failed")
                                    .add("it", it).build();


                            results.add(json);
                        }

                );


        testContext.getSkippedTests().getAllResults()
                .forEach(result -> {

                            String it = result.getName() + " " + result.getMethod().getDescription();
                            ITestContext ctx = result.getTestContext();
                            String desc = ctx.getName();
                            String testsuite = ctx.getSuite().getName();
                            String testId = System.getenv("TESTID");
                            testId = testId != null ? testId : "default";

                            JsonObject json = Json.createObjectBuilder()
                                    .add("eventType", "TestResult_2")
                                    .add("testId", testId)
                                    .add("testsuite", testsuite)
                                    .add("describe", desc)
                                    .add("result", "disabled")
                                    .add("it", it).build();


                            results.add(json);
                        }

                );

        String input = results.build().toString();
        System.out.println(input);
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://insights-collector.newrelic.com/v1/accounts/1614879/events");


        Response response = target.request().header("X-Insert-Key", "vuFsXye2t6scXqd-QhVHlXCIsA0twnst").accept(MediaType.APPLICATION_JSON).post(Entity.json(input));


        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }

        // display response
        String output = response.readEntity(String.class);
        System.out.println("Output from Server .... ");
        System.out.println(output + "\n");


    }



}
