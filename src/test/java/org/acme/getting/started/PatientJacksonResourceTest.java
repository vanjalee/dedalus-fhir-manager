package org.acme.getting.started;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * @author Vanja Bisanovic
 */
@QuarkusTest
@SuppressWarnings("unchecked")
public class PatientJacksonResourceTest {

	private static final String APPLICATION_JSON = "application/json";
	private static final String CONTENT_TYPE = "Content-type";
	private static final String ENDPOINT_PATIENTJACKSON = "/patientjackson";
	private static final String VALID_FHIR_URL = "http://test.fhir.org/r4/Patient/11";

	@Test
	public void testPatientJacksonGetUnknown_returns404() {

		JSONObject request = new JSONObject();
		request.put("url", "http://test.fhir.org/r4/Patient/qweqweqweqweqweqweqweqweqweqweqwe");
		RestAssured.given().header(CONTENT_TYPE, APPLICATION_JSON).and().body(request.toJSONString()).when()
				.get(ENDPOINT_PATIENTJACKSON).then().statusCode(404);
	}

	@Test
	public void testPatientJacksonPostUnknown_returns404() {

		JSONObject request = new JSONObject();
		request.put("url", "http://test.fhir.org/r4/Patient/qweqweqweqweqweqweqweqweqweqweqwe");
		RestAssured.given().header(CONTENT_TYPE, APPLICATION_JSON).and().body(request.toJSONString()).when()
				.post(ENDPOINT_PATIENTJACKSON).then().statusCode(404);
	}
	
	@Test
	public void testPatientJacksonPostGetValid_returns200() {

		JSONObject request = new JSONObject();
		request.put("url", VALID_FHIR_URL);
		Response response = RestAssured.given().header(CONTENT_TYPE, APPLICATION_JSON).and()
				.body(request.toJSONString()).when().post(ENDPOINT_PATIENTJACKSON).then().extract().response();
		Assertions.assertEquals(200, response.statusCode());

		RestAssured.given().header(CONTENT_TYPE, APPLICATION_JSON).and().body(request.toJSONString()).when()
				.get(ENDPOINT_PATIENTJACKSON).then().statusCode(200);
	}

	@Test
	public void testPatientJacksonGetEmptyBody_returns400() {

		JSONObject request = new JSONObject();
		RestAssured.given().header(CONTENT_TYPE, APPLICATION_JSON).and().body(request.toJSONString()).when()
				.get(ENDPOINT_PATIENTJACKSON).then().statusCode(400);

		request = new JSONObject();
		RestAssured.given().header(CONTENT_TYPE, APPLICATION_JSON).and().body(request.toJSONString()).when()
				.get(ENDPOINT_PATIENTJACKSON).then().statusCode(400);
	}
	
	@Test
	public void testPatientJacksonPostEmptyBody_returns400() {

		JSONObject request = new JSONObject();
		RestAssured.given().header(CONTENT_TYPE, APPLICATION_JSON).and().body(request.toJSONString()).when()
				.post(ENDPOINT_PATIENTJACKSON).then().statusCode(400);

		request = new JSONObject();
		RestAssured.given().header(CONTENT_TYPE, APPLICATION_JSON).and().body(request.toJSONString()).when()
				.post(ENDPOINT_PATIENTJACKSON).then().statusCode(400);
	}

	@Test
	public void testPatientJacksonGetNullBody_returns400() {

		RestAssured.given().header(CONTENT_TYPE, APPLICATION_JSON).when().get(ENDPOINT_PATIENTJACKSON).then()
				.statusCode(400);
	}
	
	@Test
	public void testPatientJacksonPostNullBody_returns400() {

		RestAssured.given().header(CONTENT_TYPE, APPLICATION_JSON).when().post(ENDPOINT_PATIENTJACKSON).then()
				.statusCode(400);
	}

	@Test
	public void testPatientJacksonPostInvalidJsonFields_returns400() {

		JSONObject request = new JSONObject();
		request.put("test", VALID_FHIR_URL);
		RestAssured.given().header(CONTENT_TYPE, APPLICATION_JSON).and().body(request.toJSONString()).when()
				.post(ENDPOINT_PATIENTJACKSON).then().statusCode(400);

		request = new JSONObject();
		request.put("", VALID_FHIR_URL);
		RestAssured.given().header(CONTENT_TYPE, APPLICATION_JSON).and().body(request.toJSONString()).when()
				.post(ENDPOINT_PATIENTJACKSON).then().statusCode(400);

		request = new JSONObject();
		request.put("!", VALID_FHIR_URL);
		RestAssured.given().header(CONTENT_TYPE, APPLICATION_JSON).and().body(request.toJSONString()).when()
				.post(ENDPOINT_PATIENTJACKSON).then().statusCode(400);

		request = new JSONObject();
		request.put(null, VALID_FHIR_URL);
		RestAssured.given().header(CONTENT_TYPE, APPLICATION_JSON).and().body(request.toJSONString()).when()
				.post(ENDPOINT_PATIENTJACKSON).then().statusCode(400);
	}

	@Test
	public void testPatientJacksonPostInvalidFhirUrlValue_returns422() {

		JSONObject request = new JSONObject();
		request.put("url", "xyz");
		RestAssured.given().header(CONTENT_TYPE, APPLICATION_JSON).and().body(request.toJSONString()).when()
				.post(ENDPOINT_PATIENTJACKSON).then().statusCode(422);

		request = new JSONObject();
		request.put("url", "xhttp://test.fhir.org/r4/Patient/11");
		RestAssured.given().header(CONTENT_TYPE, APPLICATION_JSON).and().body(request.toJSONString()).when()
				.post(ENDPOINT_PATIENTJACKSON).then().statusCode(422);

		request = new JSONObject();
		request.put("url", "www.google.com");
		RestAssured.given().header(CONTENT_TYPE, APPLICATION_JSON).and().body(request.toJSONString()).when()
				.post(ENDPOINT_PATIENTJACKSON).then().statusCode(422);

		request = new JSONObject();
		request.put("url", "@");
		RestAssured.given().header(CONTENT_TYPE, APPLICATION_JSON).and().body(request.toJSONString()).when()
				.post(ENDPOINT_PATIENTJACKSON).then().statusCode(422);

		request = new JSONObject();
		request.put("url", "http://xxx.fhir.org/r4/Patient/11");
		RestAssured.given().header(CONTENT_TYPE, APPLICATION_JSON).and().body(request.toJSONString()).when()
				.post(ENDPOINT_PATIENTJACKSON).then().statusCode(422);
	}

	@Test
	public void testPatientJacksonGetInvalidFhirUrlValue_returns404() {

		JSONObject request = new JSONObject();
		request.put("url", "xyz");
		RestAssured.given().header(CONTENT_TYPE, APPLICATION_JSON).and().body(request.toJSONString()).when()
				.get(ENDPOINT_PATIENTJACKSON).then().statusCode(404);

		request = new JSONObject();
		request.put("url", "xhttp://test.fhir.org/r4/Patient/11");
		RestAssured.given().header(CONTENT_TYPE, APPLICATION_JSON).and().body(request.toJSONString()).when()
				.get(ENDPOINT_PATIENTJACKSON).then().statusCode(404);

		request = new JSONObject();
		request.put("url", "www.google.com");
		RestAssured.given().header(CONTENT_TYPE, APPLICATION_JSON).and().body(request.toJSONString()).when()
				.get(ENDPOINT_PATIENTJACKSON).then().statusCode(404);

		request = new JSONObject();
		request.put("url", "@");
		RestAssured.given().header(CONTENT_TYPE, APPLICATION_JSON).and().body(request.toJSONString()).when()
				.get(ENDPOINT_PATIENTJACKSON).then().statusCode(404);

		request = new JSONObject();
		request.put("url", "http://xxx.fhir.org/r4/Patient/11");
		RestAssured.given().header(CONTENT_TYPE, APPLICATION_JSON).and().body(request.toJSONString()).when()
				.get(ENDPOINT_PATIENTJACKSON).then().statusCode(404);
	}

	@Test
	public void testPatientJacksonGetInvalidJsonFields_returns400() {

		JSONObject request = new JSONObject();
		request.put("test", VALID_FHIR_URL);
		RestAssured.given().header(CONTENT_TYPE, APPLICATION_JSON).and().body(request.toJSONString()).when()
				.get(ENDPOINT_PATIENTJACKSON).then().statusCode(400);

		request = new JSONObject();
		request.put("", VALID_FHIR_URL);
		RestAssured.given().header(CONTENT_TYPE, APPLICATION_JSON).and().body(request.toJSONString()).when()
				.get(ENDPOINT_PATIENTJACKSON).then().statusCode(400);

		request = new JSONObject();
		request.put("!", VALID_FHIR_URL);
		RestAssured.given().header(CONTENT_TYPE, APPLICATION_JSON).and().body(request.toJSONString()).when()
				.get(ENDPOINT_PATIENTJACKSON).then().statusCode(400);

		request = new JSONObject();
		request.put(null, VALID_FHIR_URL);
		RestAssured.given().header(CONTENT_TYPE, APPLICATION_JSON).and().body(request.toJSONString()).when()
				.get(ENDPOINT_PATIENTJACKSON).then().statusCode(400);
	}

}