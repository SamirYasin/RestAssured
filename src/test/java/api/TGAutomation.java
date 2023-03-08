package api;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TGAutomation {
    public static void main(String[] args) {
        Response response;

        Faker faker = new Faker();
//      Post Method to create a student
        response = RestAssured
                .given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer f0041e84fc58b2c43c18fa9a9c15c93a8d5b8a04c5c77349f4e804ed0b634752")
                .body("{\n" +
                        "    \"firstName\": \"" + faker.name().firstName() + "\",\n" +
                        "    \"lastName\": \"" + faker.name().lastName() + "\",\n" +
                        "    \"email\": \"" + faker.internet().emailAddress() + "\",\n" +
                        "    \"dob\": \"2000-01-01\"\n" +
                        "}")
                .when().post("https://tech-global-training.com/students")
                .then().log().all().extract().response();

        int postId = response.jsonPath().getInt("id");

//      Get method to fetch a specific student
        response = RestAssured
                .given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer f0041e84fc58b2c43c18fa9a9c15c93a8d5b8a04c5c77349f4e804ed0b634752")
                .when().get("https://tech-global-training.com/students/" + postId)
                .then().log().all().extract().response();

//      Get method to fetch all students
        response = RestAssured
                .given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer f0041e84fc58b2c43c18fa9a9c15c93a8d5b8a04c5c77349f4e804ed0b634752")
                .when().get("https://tech-global-training.com/students/")
                .then().log().all().extract().response();

        //      Put method to update all the information for a specific user
        response = RestAssured
                .given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer f0041e84fc58b2c43c18fa9a9c15c93a8d5b8a04c5c77349f4e804ed0b634752")
                .body("{\n" +
                        "    \"firstName\": \"" + faker.name().firstName() + "\",\n" +
                        "    \"lastName\": \"" + faker.name().lastName() + "\",\n" +
                        "    \"email\": \"" + faker.internet().emailAddress() + "\",\n" +
                        "    \"dob\": \"2000-01-01\"\n" +
                        "}")
                .when().put("https://tech-global-training.com/students/" + postId)
                .then().log().all().extract().response();

        //      Patch method to update certain information for a specific user
        response = RestAssured
                .given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer f0041e84fc58b2c43c18fa9a9c15c93a8d5b8a04c5c77349f4e804ed0b634752")
                .body("{\"dob\": \"2002-02-02\"}")
                .when().patch("https://tech-global-training.com/students/" + postId)
                .then().log().all().extract().response();

        //      Delete method to delete a specific user
        response = RestAssured
                .given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer f0041e84fc58b2c43c18fa9a9c15c93a8d5b8a04c5c77349f4e804ed0b634752")
                .when().delete("https://tech-global-training.com/students/" + postId)
                .then().log().all().extract().response();
    }
}
