package test.java;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestAPITesting {
	 private String token;
	 private String contactId;
	 
    //@Test --Uncomment to register a new user. The below user is already registered.
    public void testAddNewUser() {
        RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";

        String requestBody = """
                {
                   "firstName": "Test",
					"lastName": "User123",
					"email": "test_email125@fake.com",
					"password": "myPassword"
                }
                """;

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
        .when()
                .post("/users")
        .then()
                .statusCode(201)
                .statusLine(equalTo("HTTP/1.1 201 Created"))
                .extract().response();
        
        String token = response.jsonPath().getString("token");
        
        token.codePoints();
    }
    
    @BeforeClass
    public void setupAndLogin() {
        RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";

        // Login to get token (assumes user already exists)
        String loginBody = """
                {
                    "email": "test_email125@fake.com",
                    "password": "myPassword"
                }
                """;

        Response loginResponse = given()
                .contentType(ContentType.JSON)
                .body(loginBody)
        .when()
                .post("/users/login")
        .then()
                .statusCode(200)
                .extract().response();

        token = loginResponse.jsonPath().getString("token");
    }
    
    @Test(priority = 1)
    public void testGetUserProfile() {
        given()
                .header("Authorization", "Bearer " + token)
        .when()
                .get("/users/me")
        .then()
                .statusCode(200)
                .statusLine(equalTo("HTTP/1.1 200 OK"));
    }
    
    @Test(priority = 2)
    public void testUpdateUser() {
        String updateBody = """
                {
                    "firstName": "Updated",
                    "lastName": "Username",
                    "email": "test_email125@fake.com",
					"password": "myPassword"
                }
                """;

        given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(updateBody)
        .when()
                .patch("/users/me")
        .then()
                .statusCode(200)
                .statusLine(equalTo("HTTP/1.1 200 OK"));
    }
    
    @Test(priority = 3)
    public void testAddContact() {
        String contactBody = """
                {
                    "firstName": "John",
                    "lastName": "Doe",
                    "birthdate": "1970-01-01",
                    "email": "test_email125@fake.com",
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
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(contactBody)
        .when()
                .post("/contacts")
        .then()
                .statusCode(201)
                .statusLine(equalTo("HTTP/1.1 201 Created"));
    }
    
    @Test(priority = 4)
    public void testGetContactList() {
    	Response searchResponse = given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
        .when()
                .get("/contacts")
        .then()
                .statusCode(200)
                .statusLine(equalTo("HTTP/1.1 200 OK"))
                .extract().response();

    	contactId = searchResponse.jsonPath().getString("[0]._id");//store the search result
    }
    
    @Test(priority = 5)
    public void testGetContactById() {
        given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
        .when()
                .get("/contacts/" + contactId)
        .then()
                .statusCode(200)
                .statusLine(equalTo("HTTP/1.1 200 OK"));
    }
    
    @Test(priority = 6)
    public void testUpdateContact() {
    	System.out.println("First Contact ID: " + contactId);
        String updateBody = """
                {
					"firstName": "Amy",
					"lastName": "Miller",
					"birthdate": "1992-02-02",
					"email": "test_email125@fake.com",
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
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(updateBody)
        .when()
                .put("/contacts/" + contactId)
        .then()
                .statusCode(200)
                .body("email", equalTo("test_email125@fake.com"))
                .statusLine(equalTo("HTTP/1.1 200 OK"));
    }
    
    @Test(priority = 7)
    public void testPatchUpdateContact() {
        String patchBody = """
                {
                    "firstName": "Anna"
                }
                """;

        given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(patchBody)
        .when()
                .patch("/contacts/" + contactId)
        .then()
                .statusCode(200)
                .body("firstName", equalTo("Anna"))
                .statusLine(equalTo("HTTP/1.1 200 OK"));
    }
    
    @Test(priority = 8)
    public void testLogoutUser() {
        given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
        .when()
                .post("/users/logout")
        .then()
                .statusCode(200)
                .statusLine(equalTo("HTTP/1.1 200 OK"));
    }
}
