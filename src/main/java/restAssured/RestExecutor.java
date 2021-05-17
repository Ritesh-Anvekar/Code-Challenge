package restAssured;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSender;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import util.util_Properties;

public class RestExecutor {

    private static Logger logger = LogManager.getLogger(RestExecutor.class);
    private Response mResponse = null;
    protected RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();


    public RestExecutor() {
        String sAUT = System.getProperty("Application");
        requestSpecBuilder.setBaseUri(util_Properties.loadProperties(util_Properties.APPLICATIONS).getProperty(sAUT+"_URI"));
    }

    @Step("RestExecutor: Perform {1} on Request {0}")
    protected Response executeRequest(RequestSpecification pRequestSpec, String pMethod) {

        try {
            RequestSender requestSender = RestAssured.given(pRequestSpec).filter(new AllureRestAssured()).when();
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
