
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestClientExample {
    public static void main(String[] args) {


   Client client = ClientBuilder.newClient();
   WebTarget target = client.target("https://insights-collector.newrelic.com/v1/accounts/1614879/events");
   String input = "{\"testId\":\"default\",\"eventType\":\"TestResult_2\",\"testsuite\":\"testng\",\"describe\":\"testng desc\",\"result\":\"success\"}";
 
        
    Response response = target.request().header("X-Insert-Key","vuFsXye2t6scXqd-QhVHlXCIsA0twnst").accept( MediaType.APPLICATION_JSON).post(Entity.json(input));


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

