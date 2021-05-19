package restAssured;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSender;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class RestExecutor extends RestDataHandler {

    private static Logger logger = LogManager.getLogger(RestExecutor.class);
    public Response mResponse;

    public RestExecutor() {
        super();
    }

    @Step("RestExecutor] Perform {1} on Request {0}")
    protected Response executeRequest(RequestSpecification pRequestSpec, String pMethod) {
        logger.debug("-- [RestExecutor] executeRequest() --");
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
            logger.info("Encountered Exception : " + e.getMessage());
            e.printStackTrace();
        }

        return mResponse;
    }

}
