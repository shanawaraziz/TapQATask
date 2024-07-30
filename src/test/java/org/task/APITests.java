package org.task;

import com.aventstack.extentreports.ExtentTest;
import io.restassured.response.Response;
import org.task.setup.BaseTest;
import org.task.utils.RequestPayload;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import static io.restassured.RestAssured.given;

public class APITests extends BaseTest {

    ExtentTest test;

    @Test
    public void testLeadEntity() throws IOException {
        test = extent.createTest("testLeadEntity", "Verify POST Entity Lead API and Validate Response");

        String requestBody = RequestPayload.getRequestPayload();

        test.info("Request Payload: " + requestBody);

        Response response = given()
                .spec(getRequestSpecification())
                .body(requestBody)
                .when()
                .post()
                .then()
                .statusCode(200) // or the expected status code
                .extract()
                .response();

        test.info("Response: " + response.asPrettyString());

        if (response.statusCode() == 200) {
            test.pass("POST Entity Lead API test passed");
        } else {
            test.fail("POST Entity Lead API test failed");
        }

        // Additional field validation
        Object entityField = response.jsonPath().getString("entity");
        Assert.assertNotNull(entityField, "The entity field is not present in the response");
        test.pass("Entity is present in response");

        Boolean carriesALicense = response.jsonPath().getBoolean("entity.carries_a_license");
        Assert.assertTrue(carriesALicense == true || carriesALicense == false, "Carries a License is not a boolien");
        test.pass("carries_a_license is a boolean value in response");

        System.out.println(response.asString());
    }

    @Test
    public void testLeadWallet() throws IOException {
        test = extent.createTest("testLeadWallet", "Verify POST Wallet Lead API and Validate Wallet fields");

        String requestBody = RequestPayload.getRequestPayload();
        test.info("Request Payload: " + requestBody);

        Response response = given()
                .spec(getRequestSpecification())
                .body(requestBody)
                .when()
                .post()
                .then()
                .statusCode(200) // or the expected status code
                .extract()
                .response();

        test.info("Response: " + response.asString());

        if (response.statusCode() == 200) {
            test.pass("POST Wallet Lead API test passed");
        } else {
            test.fail("POST Wallet Lead API test failed");
        }

        // Additional field validation
        Object walletField = response.jsonPath().getString("wallet");
        Assert.assertNotNull(walletField, "The entity field is not present in the response");
        test.pass("Wallet is present in response");

        String walletName = response.jsonPath().getString("wallet.name");
        Assert.assertTrue(walletName.length() >= 3, "Name should be less than 3 characters");
        test.pass("Wallet Name is greater than 3 characters");

        System.out.println(response.asString());
    }
}
