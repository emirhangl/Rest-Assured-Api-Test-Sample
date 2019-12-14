package com.trendyol.apitest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;

public class ApiTestHelper {

    public String getIdFromMovie(String baseURI, String apiKey, String searchWord, String movieTitle) {
        RestAssured.baseURI = baseURI;
        String id = null;
        try {
        // Endpoint'e request atıp response'ımızı aldık
        Response response = getResponseFromEndPoint("/?apikey="+apiKey+"&s="+searchWord);

        // Json objemizi POJO classımıza deserialize ettik
        JsonPath path = response.jsonPath();
        List<MovieDTO> data = path.getList("Search", MovieDTO.class);

        System.out.println("\nAradığınız kelimeyle ilgili "+data.size()+" adet film bulundu!\n");

        // Title'ları eşleşen elemanımızı bulup id'sini aldık.
        for (MovieDTO singleObject : data) {
            if (singleObject.getTitle().equals(movieTitle)) {
                id = singleObject.getImdbID();
                System.out.println("\nEXTRACTED ID: " + id);
                break;
            }
        }
        return id;
        }catch (Exception ex){
            System.out.println("HATA! " + ex.getMessage());
            return null;
        }
    }

    public void searchByID(String apiKey, String id) {
        try {
        given()
                .when()
                .get("http://www.omdbapi.com/?apikey="+apiKey+"&i="+id)
                .then()
                .log()
                .all()
                .statusCode(200)
                .body("Title", not(emptyOrNullString()))
                .body("Year", not(emptyOrNullString()))
                .body("Released", not(emptyOrNullString()));
        }catch (Exception ex){
            System.out.println("HATA! "+ex.getMessage());
        }
    }

    private Response getResponseFromEndPoint(String endpoint) {
        try {
        return given()
                .when()
                .get(endpoint)
                .then()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .and()
                .body("Search.Title",not(emptyOrNullString()))
                .body("Search.Year",not(emptyOrNullString()))
                .extract()
                .response();
        }catch (Exception ex){
            System.out.println("HATA! "+ex.getMessage());
            return null;
        }
    }
}
