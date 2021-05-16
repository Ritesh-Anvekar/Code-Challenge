package restAssured;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSender;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RestExecutor {

    private static Logger logger = LogManager.getLogger(RestExecutor.class);

    private Response mResponse = null;
    protected RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
    private static final String BASE_URI = "https://jsonplaceholder.typicode.com";


    public RestExecutor() {
        requestSpecBuilder.setBaseUri(BASE_URI);
    }

    protected Response executeRequest(RequestSpecification pRequestSpec, String pMethod) {

        try {
            RequestSender requestSender = RestAssured.given(pRequestSpec).when();
            switch (pMethod) {

                case "GET":
                    mResponse = requestSender.get();
                    break;

                case "POST":
                    mResponse = requestSender.post();
                    break;

                case "PUT":
                    mResponse = requestSender.put();
                    break;

                case "DELETE":
                    mResponse = requestSender.delete();
                    break;

                default:
                    logger.info("Incorrect Argument !!");
            }
        } catch(Exception e){
            logger.info("Encounter Exception : " + e.getMessage());
        }


        return mResponse;
    }

}
