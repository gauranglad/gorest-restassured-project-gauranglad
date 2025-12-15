package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ProductsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void init() {
        response = RestAssured.given()
                .when()
                .get("https://gorest.co.in/public/v2/posts?page=1&per_page=25")
                .then()
                .statusCode(200);
    }

    //Extract the title
    @Test
    public void test001() {
        List<String> titles = response.extract().path("title");
        System.out.println("The titles are " + titles);
    }

    //Extract the total number of record
    @Test
    public void test002() {
        int record = response.extract().path("$.size()");
        System.out.println("The titles are " + record);
    }

    //Extract the body of 15th record
    @Test
    public void test003() {
        Map<String, Objects> record = response.extract().path("[14]");
        System.out.println("The body of 15th record is " + record);
    }

    //Extract the user_id of all the records
    @Test
    public void test004() {
        List<String> userIDs = response.extract().path("user_id");
        System.out.println("The user_id of all the records is " + userIDs);
    }

    //Extract the title of all the records
    @Test
    public void test005() {
        List<String> title = response.extract().path("title");
        System.out.println("The title of all the records is " + title);
    }
}
