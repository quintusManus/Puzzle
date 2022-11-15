package csc340.caffeinatedfoxes.puzzle.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * A class for the API.
 * @author bjwoods
 * Last Updated: 11/15/2022
 */
public class api {
    public static String quote;
    public static String author;
    public static void randomQuote() throws MalformedURLException, IOException {
        URL url = new URL("https://zenquotes.io/api/random");

        StringBuilder informationString = new StringBuilder();
        try ( Scanner scanner = new Scanner(url.openStream())) {
            while (scanner.hasNext()) {
                informationString.append(scanner.nextLine());
            }
            //Close the scanner
        }

        //System.out.println("Returned Information: " + informationString);

        String string = informationString.toString();

        JsonObject[] obj = new Gson().fromJson(string, JsonObject[].class);

        quote = obj[0].get("q").toString();

        String temp = obj[0].get("a").toString().substring(1, obj[0].get("a").toString().length() - 1);
        author = "- " + temp;

        //System.out.println("\n" + quote + "\n" + "- " + author.substring(1, author.length() - 1));

    }
}
