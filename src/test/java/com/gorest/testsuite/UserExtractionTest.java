package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class UserExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void init() {
        response = RestAssured.given()
                .when()
                .get("https://gorest.co.in/public/v2/users?page=1&per_page=20")
                .then()
                .statusCode(200);
    }

    //Extract the All Ids
    @Test
    public void test001() {
        List<Integer> ids = response.extract().path("id");
        System.out.println("All ids are " + ids);
    }

    //Extract the all Names
    @Test
    public void test002() {
        List<String> names = response.extract().path("name");
        System.out.println("All names are " + names);
    }

    //Extract the name of 5th object
    @Test
    public void test003() {
        String name = response.extract().path("[4].name");
        System.out.println("The name of 5th object is " + name);
    }

    //Extract the names of all object whose status = inactive
    @Test
    public void test004() {
        List<String> names = response.extract().path("findAll{it.status=='inactive'}.name");
        System.out.println("The names of all object whose status = inactive are " + names);
    }

    //Extract the gender of all the object whose status = active
    @Test
    public void test005() {
        List<String> names = response.extract().path("findAll{it.status=='active'}.gender");
        System.out.println("The gender of all the object whose status = active are " + names);
    }

    //Print the names of the object whose gender = female
    @Test
    public void test006() {
        List<String> names = response.extract().path("findAll{it.gender=='female'}.name");
        System.out.println("The names of the object whose gender = female are " + names);
    }

    //Get all the emails of the object where status = inactive
    @Test
    public void test007() {
        List<String> emails = response.extract().path("findAll{it.status=='inactive'}.email");
        System.out.println("The the emails of the object where status = inactive are " + emails);
    }

    //Get the ids of the object where gender = male
    @Test
    public void test008() {
        List<Integer> names = response.extract().path("findAll{it.gender=='male'}.id");
        System.out.println("The ids of the object where gender = male are " + names);
    }

    //Get all the status
    @Test
    public void test009() {
        List<String> status = response.extract().path("status");
        System.out.println("All status are " + status);
    }
}
