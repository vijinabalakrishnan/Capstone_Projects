package test.java;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class RestAPITesting {
	 private String userToken;
	 private String loginToken;
	 private String userEmail;
	 private String contactId;
	 
    
	 
    @Test(priority = 0)
    public void testAddNewUser() {
        RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
        String currentTime = LocalTime.now().toString();
        userEmail =  "qa"+currentTime.replaceAll(":","_")+"@vijinamail.com";
        System.out.println(userEmail);
        
        String requestBody = """
                {
                   "firstName": "Test",
					"lastName": "User123",
					"email": "{userEmail}",
					"password": "myPassword"
                }
                """;
        requestBody = requestBody.replace("{userEmail}", userEmail);

        System.out.println(requestBody);
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
        .when()
                .post("/users")
        .then()
                .statusCode(201)
                .statusLine(equalTo("HTTP/1.1 201 Created"))
                .extract().response();
        
        userToken = response.jsonPath().getString("token");
        

        System.out.println(userToken);
    }
    
    
    
    @Test(priority = 1)
    public void testGetUserProfile() {
        given()
                .header("Authorization", "Bearer " + userToken)
        .when()
                .get("/users/me")
        .then()
                .statusCode(200)
                .statusLine(equalTo("HTTP/1.1 200 OK"));
    }
    
    @Test(priority = 2)
    public void testUpdateUser() {
    	String currentTime = LocalTime.now().toString();
        userEmail =  "qa"+currentTime.replaceAll(":","_")+"@vijinamail.com";
        System.out.println(userEmail);
       
        String updateBody = """
                {
                    "firstName": "Updated",
                    "lastName": "Username",
                    "email": "{userEmail}",
					"password": "myPassword"
                }
                """;
        updateBody = updateBody.replace("{userEmail}", userEmail);
        
        given()
                .header("Authorization", "Bearer " + userToken)
                .contentType(ContentType.JSON)
                .body(updateBody)
        .when()
                .patch("/users/me")
        .then()
                .statusCode(200)
                .statusLine(equalTo("HTTP/1.1 200 OK"));
    }
    
    @Test(priority = 3)
    public void Login() {
    RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
    
    // Login to get token (assumes user already exists)
    String loginBody = """
            {
                "email": "{userEmail}",
                "password": "myPassword"
            }
            """;
    loginBody = loginBody.replace("{userEmail}", userEmail);
    
    Response loginResponse = given()
            .contentType(ContentType.JSON)
            .body(loginBody)
    .when()
            .post("/users/login")
    .then()
            .statusCode(200)
            .extract().response();

    loginToken = loginResponse.jsonPath().getString("token");
    System.out.println(loginToken);
}
    
    @Test(priority = 4)
    public void testAddContact() {
        String contactBody = """
                {
                    "firstName": "John",
                    "lastName": "Doe",
                    "birthdate": "1970-01-01",
                    "email": "test_email125@vijinamail.com",
                    "phone": "8005555555",
                    "street1": "1 Main St.",
                    "street2": "Apartment A",
                    "city": "Anytown",
                    "stateProvince": "KS",
                    "postalCode": "12345",
                    "country": "USA"
                }
                """;

        given()
                .header("Authorization", "Bearer " + loginToken)
                .contentType(ContentType.JSON)
                .body(contactBody)
        .when()
                .post("/contacts")
        .then()
                .statusCode(201)
                .statusLine(equalTo("HTTP/1.1 201 Created"));
    }
    
    @Test(priority = 5)
    public void testGetContactList() {
    	Response searchResponse = given()
                .header("Authorization", "Bearer " + loginToken)
                .contentType(ContentType.JSON)
        .when()
                .get("/contacts")
        .then()
                .statusCode(200)
                .statusLine(equalTo("HTTP/1.1 200 OK"))
                .extract().response();

    	contactId = searchResponse.jsonPath().getString("[0]._id");//store the search result
    }
    
    @Test(priority = 6)
    public void testGetContactById() {
        given()
                .header("Authorization", "Bearer " + loginToken)
                .contentType(ContentType.JSON)
        .when()
                .get("/contacts/" + contactId)
        .then()
                .statusCode(200)
                .statusLine(equalTo("HTTP/1.1 200 OK"));
    }
    
    @Test(priority = 7)
    public void testUpdateContact() {
    	System.out.println("First Contact ID: " + contactId);
        String updateBody = """
                {
					"firstName": "Amy",
					"lastName": "Miller",
					"birthdate": "1992-02-02",
					"email": "test_email125@vijinamail.com",
					"phone": "8005554242",
					"street1": "13 School St.",
					"street2": "Apt. 5",
					"city": "Washington",
					"stateProvince": "QC",
					"postalCode": "A1A1A1",
					"country": "Canada"
				}
                """;

        given()
                .header("Authorization", "Bearer " + loginToken)
                .contentType(ContentType.JSON)
                .body(updateBody)
        .when()
                .put("/contacts/" + contactId)
        .then()
                .statusCode(200)
                .body("email", equalTo("test_email125@vijinamail.com"))
                .statusLine(equalTo("HTTP/1.1 200 OK"));
    }
    
    @Test(priority = 8)
    public void testPatchUpdateContact() {
        String patchBody = """
                {
                    "firstName": "Anna"
                }
                """;

        given()
                .header("Authorization", "Bearer " + loginToken)
                .contentType(ContentType.JSON)
                .body(patchBody)
        .when()
                .patch("/contacts/" + contactId)
        .then()
                .statusCode(200)
                .body("firstName", equalTo("Anna"))
                .statusLine(equalTo("HTTP/1.1 200 OK"));
    }
    
    @Test(priority = 9)
    public void testLogoutUser() {
        given()
                .header("Authorization", "Bearer " + loginToken)
                .contentType(ContentType.JSON)
        .when()
                .post("/users/logout")
        .then()
                .statusCode(200)
                .statusLine(equalTo("HTTP/1.1 200 OK"));
    }
}
