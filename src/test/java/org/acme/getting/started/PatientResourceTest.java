package org.acme.getting.started;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;

/**
 * @author Vanja Bisanovic
 */
@QuarkusTest
public class PatientResourceTest {

	private static final String ENDPOINT_PATIENT = "/patient";
	private static final String VALID_FHIR_URL = "http://test.fhir.org/r4/Patient/11";

	@Test
	public void testPatientGetUnknown_returns404() {

		RestAssured.given().body("http://hapi.fhir.org/baseR4/Patient/qweqweqweqweqweqweqweqweqweqweqwe").when()
				.get(ENDPOINT_PATIENT).then().statusCode(404);
	}

	@Test
	public void testPatientPostUnknown_returns404() {

		RestAssured.given().body("http://hapi.fhir.org/baseR4/Patient/qweqweqweqweqweqweqweqweqweqweqwe").when()
				.post(ENDPOINT_PATIENT).then().statusCode(404);
	}

	@Test
	public void testPatientPostGetValid_returns200() {

		RestAssured.given().body(VALID_FHIR_URL).when().post(ENDPOINT_PATIENT).then().statusCode(200);

		RestAssured.given().body(VALID_FHIR_URL).when().get(ENDPOINT_PATIENT).then().statusCode(200);
	}

	@Test
	public void testPatientGetEmptyBody_returns400() {

		RestAssured.given().body("").when().get(ENDPOINT_PATIENT).then().statusCode(400);

		RestAssured.given().body(" ").when().get(ENDPOINT_PATIENT).then().statusCode(400);
	}

	@Test
	public void testPatientPostEmptyBody_returns400() {

		RestAssured.given().body("").when().post(ENDPOINT_PATIENT).then().statusCode(400);

		RestAssured.given().body(" ").when().post(ENDPOINT_PATIENT).then().statusCode(400);
	}

	@Test
	public void testPatientGetNullBody_returns404() {

		RestAssured.given().when().get(ENDPOINT_PATIENT).then().statusCode(400);
	}

	@Test
	public void testPatientPostNullBody_returns404() {

		RestAssured.given().when().post(ENDPOINT_PATIENT).then().statusCode(400);
	}

	@Test
	public void testPatientGetInvalidBody_returns404() {

		RestAssured.given().body("xyz").when().get(ENDPOINT_PATIENT).then().statusCode(404);

		RestAssured.given().body("@").when().get(ENDPOINT_PATIENT).then().statusCode(404);

		RestAssured.given().body("xhttp://test.fhir.org/r4/Patient/11").when().get(ENDPOINT_PATIENT).then().statusCode(404);

		RestAssured.given().body("http://xxx.fhir.org/r4/Patient/11").when().get(ENDPOINT_PATIENT).then().statusCode(404);
	}

	@Test
	public void testPatientPostInvalidBody_returns422() {

		RestAssured.given().body("xyz").when().post(ENDPOINT_PATIENT).then().statusCode(422);

		RestAssured.given().body("@").when().post(ENDPOINT_PATIENT).then().statusCode(422);

		RestAssured.given().body("xhttp://test.fhir.org/r4/Patient/11").when().post(ENDPOINT_PATIENT).then().statusCode(422);

		RestAssured.given().body("http://xxx.fhir.org/r4/Patient/11").when().post(ENDPOINT_PATIENT).then().statusCode(422);
	}
}