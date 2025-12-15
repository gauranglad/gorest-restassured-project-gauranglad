package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

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
    public void test001(){
        response.body("$.size()",equalTo(20));
    }
//Verify the if the name of id = 5914197 is equal to ”Bhilangana Dhawan”
@Test
public void test002(){
    response.body("$.find{it.id==5914197}.name",hasItems("Bhilangana Dhawan"));
}
//Check the single ‘Name’ in the Array list (Dev Bhattacharya)
//Check the multiple ‘Names’ in the ArrayList (Usha Kaul Esq., Akshita Mishra, Chetanaanand Reddy )
//Verify the emai of userid = 5914185 is equal “tandon_iv_aanandinii@prosacco.example”
//Verify the status is “Active” of user name is “Amaresh Rana”
//Verify the Gender = male of user name is “Dhanalakshmi Pothuvaal”

}
