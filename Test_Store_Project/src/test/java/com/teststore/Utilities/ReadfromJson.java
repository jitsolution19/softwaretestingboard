package com.teststore.Utilities;

import java.io.FileReader;
import java.util.HashMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ReadfromJson {
	static HashMap<String, String> datatable = null;

	public static void extractDataFromJson() {
		datatable = new HashMap<String, String>();
		JsonParser parser = new JsonParser();
		try {
			Object obj = parser.parse(new FileReader("src/test/resources/testData/targetFileForByteArray.json"));

			JsonObject jsonObject = (JsonObject) obj;
			JsonArray root = jsonObject.get("results").getAsJsonArray();
			JsonObject jit2 = root.get(0).getAsJsonObject();
			JsonElement nameinfo = jit2.get("name");
			JsonObject info = nameinfo.getAsJsonObject();
			System.out.println(info.get("first"));
			datatable.put("FirstName", info.get("first").toString());
			System.out.println(info.get("last"));
			datatable.put("LastName", info.get("last").toString());

			JsonElement locationInfo = jit2.get("location");
			JsonObject getlocationinfo = locationInfo.getAsJsonObject();

			JsonElement streetInfo = getlocationinfo.get("street");
			JsonObject StreetInformation = streetInfo.getAsJsonObject();
			System.out.println(StreetInformation.get("number"));
			datatable.put("Street_FirstLine_Address", StreetInformation.get("number").toString());
			System.out.println(StreetInformation.get("name"));
			datatable.put("Street_SecondLine_Address", StreetInformation.get("name").toString());
			datatable.put("Street_ThirdLine_Address", StreetInformation.get("number").toString());

			System.out.println(getlocationinfo.get("city"));
			datatable.put("City", getlocationinfo.get("city").toString());
			System.out.println(getlocationinfo.get("state"));
			datatable.put("State_Prov", getlocationinfo.get("state").toString());
			System.out.println(getlocationinfo.get("country"));
			datatable.put("Country", getlocationinfo.get("country").toString());
			System.out.println(getlocationinfo.get("postcode"));
			datatable.put("PostalCode", getlocationinfo.get("postcode").toString());

			System.out.println(jit2.get("email"));
			datatable.put("Email", jit2.get("email").toString());

			System.out.println(jit2.get("phone"));
			datatable.put("PhoneNumber", jit2.get("phone").toString());

			JsonElement logininfo = jit2.get("login");
			JsonObject passwordinfo = logininfo.getAsJsonObject();
			System.out.println(passwordinfo.get("password"));
			datatable.put("Password", passwordinfo.get("password").toString() + "Jitendra@123");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String fetchdata(String value) {
		String outputValue = datatable.get(value).replace("\"", "");
		System.out.println("Fetching the data :: "+outputValue);
		return (outputValue);
	}

}
