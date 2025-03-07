package com.teststore.LoginPage;
import java.io.File;
import java.io.IOException;

import com.google.common.io.Files;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ExtractuserName {
	public void extractinformation() throws IOException {
		RestAssured.baseURI = "https://randomuser.me";
		Response response = RestAssured.get("/api");
				byte[] responseAsByteArray = response.asByteArray();
				File targetFileForByteArray = new File("src/test/resources/testData/targetFileForByteArray.json");
				Files.write(responseAsByteArray, targetFileForByteArray);
	}
}
