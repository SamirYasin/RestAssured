package api.tdd;

import api.pojo_classes.go_rest.CreateGoRestUserWithLombok;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.ConfigReader;

import static org.hamcrest.core.IsEqual.equalTo;

public class GoRestWithLombok {
    ObjectMapper objectMapper = new ObjectMapper(); // converts java to json and vice versa
    Response response;


    Faker faker = new Faker();

    @BeforeTest
    public void beforeTest(){
        System.out.println("*****\nStarting the API test\n*****");

        RestAssured.baseURI = ConfigReader.getProperty("GoRestBaseURI");

    }

    @Test
    public void goRestCRUDWithLombok() throws JsonProcessingException {
        CreateGoRestUserWithLombok createUser = CreateGoRestUserWithLombok.builder()
                .name("Tech Global")
                .email(faker.internet().emailAddress())
                .gender("female")
                .status("active")
                .build();

        response = RestAssured
                .given().log().all()
//                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .header("Authorization", ConfigReader.getProperty("GoRestToken"))
                .when().post("/public/v2/users")
                .then().log().all().and()
                .assertThat().statusCode(201)
                .time(Matchers.lessThan(5000L))
                .body("name", equalTo("Samir Yasin"))
                .contentType(ContentType.JSON)
                .extract().response();
    }
}
