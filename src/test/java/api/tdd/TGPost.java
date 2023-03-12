package api.tdd;

import apache_poi_excel.ReadingFromTGExcelFile;
import api.pojo_classes.go_rest.TGWithLombok;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.ConfigReader;
import java.io.IOException;
import com.jayway.jsonpath.JsonPath;
import static org.hamcrest.MatcherAssert.assertThat;

public class TGPost {
    Response response;

    static Logger logger = LogManager.getLogger(TGPost.class);
    ReadingFromTGExcelFile readingFromTGExcelFile = new ReadingFromTGExcelFile();
    String[][] testData = readingFromTGExcelFile.readTestData();

    public TGPost() throws IOException {
    }

    @BeforeTest
    public void beforeTest() {
        logger.debug("Starting the API test");


        RestAssured.baseURI = ConfigReader.getProperty("TGBaseURI");
    }

    @Test
    public void TGCRUDWithLombok() throws IOException {
        TGWithLombok tgWithLombok = TGWithLombok.builder()
                .firstName(testData[0][0])
                .lastName(testData[0][1])
                .dob(testData[0][2])
                .email(testData[0][3])
                .build();

        logger.debug("Starting the POST test");


        response = RestAssured
                .given().log().all()
//                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .header("Authorization", ConfigReader.getProperty("TGToken"))
                .body(tgWithLombok)
                .when().post("/students")
                .then().log().all().and()
                .assertThat().statusCode(200)
                .time(Matchers.lessThan(5000L))
                .extract().response();


//        assertThat("Validating first name",("firstName"), Matchers.equalTo("John"));
        assertThat("Validating first name", JsonPath.read(response.getBody().asString(), "$.firstName"), Matchers.equalTo("John"));
        assertThat("Validating last name", JsonPath.read(response.getBody().asString(), "$.lastName"), Matchers.equalTo("Doe"));
        assertThat("Validating dob", JsonPath.read(response.getBody().asString(), "$.dob"), Matchers.equalTo("2000-01-01"));
        assertThat("Validating email", JsonPath.read(response.getBody().asString(), "$.email"), Matchers.equalTo("johndough3@gmail.com"));



        int postId = response.jsonPath().getInt("id");
//
        logger.debug("Starting the DELETE test");



        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", ConfigReader.getProperty("TGToken"))
                .when().delete("/students/" + postId)
                .then().log().all().and()
                .assertThat().statusCode(200)
                .time(Matchers.lessThan(5000L))
                .extract().response();



    }
}
