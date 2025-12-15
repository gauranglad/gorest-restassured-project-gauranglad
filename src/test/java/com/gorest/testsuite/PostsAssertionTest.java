package com.gorest.testsuite;

import com.google.common.base.Verify;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.screenplay.conditions.Check;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class PostsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void init() {
        response = RestAssured.given()
                .when()
                .get("https://gorest.co.in/public/v2/posts?page=1&per_page=25")
                .then()
                .statusCode(200);
    }

    //Verify the if the total record is 25
    @Test
    public void test001(){
        response.body("$.size()",equalTo(25));
    }
    //Verify the if the title of id = 260029 is equal to ”Abduco somniculosus clamo vere aspernatur.”
    @Test
    public void test002(){
        response.body("find{it.id==260029}.title",equalTo("Abduco somniculosus clamo vere aspernatur."));
    }
    //Check the single user_id in the Array list (8263987)
    @Test
    public void test003(){
        response.body("user_id",hasItem(8263987));
    }

    //Check the multiple ids in the ArrayList (260029, 260028, 260026)
    @Test
    public void test004(){
        response.body("id",hasItems(260029, 260028, 260026));
    }
    //Verify the body of userid = 8263985 is equal Ea dolores cibus. Compello reprehenderit astrum. Defleo vir dolorem. Atavus depulso truculenter. Quibusdam autus communis. Spectaculum alias audentia. Caput modi bis. Odio cum bardus. Thesis angulus porro. Cras titulus pauci. Trans excepturi avoco. Conicio clibanus est. Colligo quod utique. Tenus harum nulla. Capio cotidie mollitia. Substantia tantum neque. Amplus despirmatio arcesso. Contigo valens qui.
    @Test
    public void test005(){
        response.body("find{it.user_id==8263985}.body",equalTo("Ea dolores cibus. Compello reprehenderit astrum. Defleo vir dolorem. Atavus depulso truculenter. Quibusdam autus communis. Spectaculum alias audentia. Caput modi bis. Odio cum bardus. Thesis angulus porro. Cras titulus pauci. Trans excepturi avoco. Conicio clibanus est. Colligo quod utique. Tenus harum nulla. Capio cotidie mollitia. Substantia tantum neque. Amplus despirmatio arcesso. Contigo valens qui."));
    }
}
