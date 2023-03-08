package api;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

public class APIAutomationSample {
    public static void main(String[] args) {
        Response response;

        Faker faker = new Faker();

        response = RestAssured
                .given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer f0041e84fc58b2c43c18fa9a9c15c93a8d5b8a04c5c77349f4e804ed0b634752")
                .body("{\n" +
                        "    \"name\": \""+faker.name().fullName()+"\",\n" +
                        "    \"email\": \""+faker.internet().emailAddress()+"\",\n" +
                        "    \"gender\": \"male\",\n" +
                        "    \"status\": \"active\"\n" +
                        "}").when().post("https://gorest.co.in/public/v2/users")
                .then().log().all().extract().response();
        //System.out.println(response.asString());

        int postId = response.jsonPath().getInt("id");
        String name = response.jsonPath().getString("name");

        response = RestAssured
                .given().log().all()
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Bearer f0041e84fc58b2c43c18fa9a9c15c93a8d5b8a04c5c77349f4e804ed0b634752")
                        .when().get("https://gorest.co.in/public/v2/users/" + postId)
                        .then().log().all().extract().response();

//                RestAssured
//                .given().log().all()
//                .header("Content-Type", "application/json")
//                .header("Authorization", "Bearer f0041e84fc58b2c43c18fa9a9c15c93a8d5b8a04c5c77349f4e804ed0b634752")
//                .when().get("https://gorest.co.in/public/v2/users/" + postId)
//                .then().log().all().extract().response();

        response = RestAssured
                .given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer f0041e84fc58b2c43c18fa9a9c15c93a8d5b8a04c5c77349f4e804ed0b634752")
                .body("{\n" +
                        "    \"name\": \""+faker.name().fullName()+"\",\n" +
                        "    \"email\": \""+faker.internet().emailAddress()+"\",\n" +
                        "    \"gender\": \"male\",\n" +
                        "    \"status\": \"active\"\n" +
                        "}").when().put("https://gorest.co.in/public/v2/users/" + postId)
                .then().log().all().extract().response();

        response = RestAssured
                .given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer f0041e84fc58b2c43c18fa9a9c15c93a8d5b8a04c5c77349f4e804ed0b634752")
                .body("{\"gender\": \"female\"}")
                .when().patch("https://gorest.co.in/public/v2/users/" + postId)
                .then().log().all().extract().response();

        int patchId = response.jsonPath().getInt("id");
        Assert.assertEquals(postId, patchId, "Expected if " + postId + " we found " + patchId);

//        response = RestAssured
//                .given().log().all()
//                .header("Content-Type", "application/json")
//                .header("Authorization", "Bearer f0041e84fc58b2c43c18fa9a9c15c93a8d5b8a04c5c77349f4e804ed0b634752")
//                .when().delete("https://gorest.co.in/public/v2/users/" + postId)
//                .then().log().all().extract().response();
    }
}
