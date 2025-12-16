package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class UserAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void init() {
        response = RestAssured.given()
                .when()
                .get("https://gorest.co.in/public/v2/users?page=1&per_page=20")
                .then()
                .statusCode(200);
    }

    //Verify the if the total record is 20
    @Test
    public void test001() {
        response.body("$.size()", equalTo(20));
    }

    //Verify the if the name of id = 8228718 is equal to ”Amb. Gurdev Varrier”
    @Test
    public void test002() {
        response.body("find{it.id==8228718}.name", equalTo("Amb. Gurdev Varrier"));
    }

    //Check the single ‘Name’ in the Array list (Chandan Verma)
    @Test
    public void test003() {
        response.body("name", hasItem("Chandan Verma"));
    }
//Check the multiple ‘Names’ in the ArrayList ("Chandan Verma","Gobinda Gill","Mrs. Sanya Gupta")
@Test
public void test004() {
    response.body("name", hasItems("Chandan Verma","Gobinda Gill","Mrs. Sanya Gupta"));
}
//Verify the email of userid = 8228716 is equal “gill_gobinda@orn-collier.example”
@Test
public void test005() {
    response.body("find{it.id==8228716}.email", equalTo("gill_gobinda@orn-collier.example"));
}
//Verify the status is “Active” of user name is “Gobinda Gill”
@Test
public void test006() {
    response.body("find{it.id==8228716}.status", equalTo("active"));
}

//Verify the Gender = male of user name is “Gobinda Gill”
@Test
public void test007() {
    response.body("find{it.name=='Gobinda Gill'}.gender", equalTo("male"));
}

}
