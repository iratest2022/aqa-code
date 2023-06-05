package ru.netology.rest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import  static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

import static io.restassured.RestAssured.given;

class MobileBankApiTestV1 {
    @Test
    void shouldReturnDemoAccounts() {
      // Given - When - Then
      // Предусловия
      given()
          .baseUri("http://localhost:9999/api/v1")
      // Выполняемые действия
      .when()
          .get("/demo/accounts")
      // Проверки
      .then()
          .statusCode(200);
    }
    @Test
    void shouldReturnDemoAccounts1() {
        // Given - When - Then
        // Предусловия
        given()
                .baseUri("http://localhost:9999/api/v1")
                // Выполняемые действия
                .when()
                .get("/demo/accounts")
                // Проверки
                .then()
                .contentType(ContentType.JSON);
    }
    @Test
    void shouldReturnDemoAccounts2() { // проверка что 3 поля
        // Given - When - Then
        // Предусловия
        given()
                .baseUri("http://localhost:9999/api/v1")
                // Выполняемые действия
                .when()
                .get("/demo/accounts")
                // Проверки
                .then()
                .body("",hasSize(3));
    }
    @Test
    void shouldReturnDemoAccounts3() { // проверка валюты доллар
        // Given - When - Then
        // Предусловия
        given()
                .baseUri("http://localhost:9999/api/v1")
                // Выполняемые действия
                .when()
                .get("/demo/accounts")
                // Проверки
                .then()
                .body("[1].currency",equalTo("USD"));
    }
    @Test
    void shouldReturnDemoAccounts4() { // проверка положительного баланса
        // Given - When - Then
        // Предусловия
        given()
                .baseUri("http://localhost:9999/api/v1")
                // Выполняемые действия
                .when()
                .get("/demo/accounts")
                // Проверки
                .then()
                .body(" every { it.balance >= 0 }", is(true));
    }
    @Test
    void shouldReturnDemoAccounts5() { // проверка валидации полей
        // Given - When - Then
        // Предусловия
        given()
                .baseUri("http://localhost:9999/api/v1")
                // Выполняемые действия
                .when()
                .get("/demo/accounts")
                // Проверки
                .then()
                .body(matchesJsonSchemaInClasspath("accounts.schema.json"));
    }
}
