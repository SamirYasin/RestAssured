package api.tdd;

import api.pojo_classes.go_rest.CreateGoRestUser;
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

public class GoRest {
    ObjectMapper objectMapper = new ObjectMapper(); // converts java to json and vice versa
    Response response;


    Faker faker = new Faker();

    @BeforeTest
    public void beforeTest(){
        System.out.println("*****\nStarting the API test\n*****");

        RestAssured.baseURI = ConfigReader.getProperty("GoRestBaseURI");

    }

    @Test
    public void goRestCRUD() throws JsonProcessingException {
        CreateGoRestUser createGoRestUser = new CreateGoRestUser();
        createGoRestUser.setName("Samir Yasin");
        createGoRestUser.setGender("male");
        createGoRestUser.setEmail(faker.internet().emailAddress());
        createGoRestUser.setStatus("active");

        System.out.println("*****\nStarting Post Request Test\n*****");

        response = RestAssured
                .given().log().all()
//                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .header("Authorization", ConfigReader.getProperty("GoRestToken"))
                .body(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(createGoRestUser))
                .when().post("/public/v2/users")
                .then().log().all().and()
                .assertThat().statusCode(201)
                .time(Matchers.lessThan(5000L))
                .body("name", equalTo("Samir Yasin"))
                .contentType(ContentType.JSON)
                .extract().response();

        int postId = response.jsonPath().getInt("id");

        System.out.println("*****\nStarting Get Request Test\n*****");

        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", ConfigReader.getProperty("GoRestToken"))
                .when().get("/public/v2/users/" + postId)
                .then().log().all()
                .and().assertThat().statusCode(200)
                .time(Matchers.lessThan(5000L))
                .body("name", equalTo("Samir Yasin"))
                .contentType(ContentType.JSON)
                .extract().response();

        System.out.println("*****\nStarting Put Request Test\n*****");

        createGoRestUser.setEmail(faker.internet().emailAddress());
        createGoRestUser.setName("Blah Blah");
        createGoRestUser.setGender("female");

        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", ConfigReader.getProperty("GoRestToken"))
                .body(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(createGoRestUser) )
                .when().put("/public/v2/users/" + postId)
                .then().log().all()
                .and().assertThat().statusCode(200)
                .time(Matchers.lessThan(5000L))
                .body("name", equalTo("Blah Blah"))
                .contentType(ContentType.JSON)
                .extract().response();

        System.out.println("*****\nStarting Delete Request Test\n*****");

        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", ConfigReader.getProperty("GoRestToken"))
                .when().delete("/public/v2/users/" + postId)
                .then().log().all()
                .and().assertThat().statusCode(204)
                .time(Matchers.lessThan(5000L))
                .body("name", equalTo("Samir Yasin"))
                .contentType(ContentType.JSON)
                .extract().response();

    }
}
