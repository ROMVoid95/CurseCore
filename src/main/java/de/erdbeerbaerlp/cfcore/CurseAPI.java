package de.erdbeerbaerlp.cfcore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import de.erdbeerbaerlp.cfcore.exceptions.InvalidAPIKeyException;
import de.erdbeerbaerlp.cfcore.impl.MCVersion;
import de.erdbeerbaerlp.cfcore.json.*;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;

public final class CurseAPI
{
    private final String baseURL = "https://api.curseforge.com/v1/";

    private final Gson gson;

    private String apiKey;

    public CurseAPI(String apiKey)
    {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(CFReleaseType.class, new CFReleaseType.ReleaseTypeDeserializer());
        gsonBuilder.registerTypeAdapter(CFFileStatus.class, new CFFileStatus.FileStatusDeserializer());
        gsonBuilder.registerTypeAdapter(CFFileRelationType.class,
            new CFFileRelationType.FileRelationTypeDeserializer());
        gson = gsonBuilder.create();
        this.apiKey = apiKey;
    }

    /**
     * Fetches a mod from CurseForge
     * 
     * @param modId Mod ID to fetch
     * @return {@link CFMod} instance, or null if not found
     */
    public CFMod getModFromID(final int modId)
    {
        try {
            final URL url = buildUrl("mods/%s", modId);
            final HttpsURLConnection urlConnection = openUrlConnection(url);
            if (urlConnection.getResponseCode() != 200)
                return null;
            final InputStreamReader reader = new InputStreamReader(urlConnection.getInputStream());
            return gson.fromJson(JsonParser.parseReader(reader).getAsJsonObject().get("data").getAsJsonObject(),
                CFMod.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Fetches a mod from CurseForge
     * 
     * @param slug Slug to search for, will grab first search result
     * @return {@link CFMod} instance, or null if not found
     */
    public CFMod getModFromSlug(final String slug, int gameID)
    {
        try {
            final URL url =
                buildUrl("mods/search?slug=%s&gameId=%s", URLEncoder.encode(slug, StandardCharsets.UTF_8), gameID);
            final HttpsURLConnection urlConnection = openUrlConnection(url);
            if (urlConnection.getResponseCode() != 200)
                return null;
            final InputStreamReader reader = new InputStreamReader(urlConnection.getInputStream());
            return gson.fromJson(JsonParser.parseReader(reader).getAsJsonObject().get("data").getAsJsonArray().get(0),
                CFMod.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Fetches (first 50) categories of a specific game
     * 
     * @param gameID Game ID to load categories from
     * @return {@link CFCategory} array, or null if not found
     */
    public CFCategory[] getCategories(int gameID)
    {
        try {
            final URL url = buildUrl("categories?gameId=%s", gameID);
            final HttpsURLConnection urlConnection = openUrlConnection(url);
            if (urlConnection.getResponseCode() != 200)
                return null;
            final InputStreamReader reader = new InputStreamReader(urlConnection.getInputStream());
            return gson.fromJson(JsonParser.parseReader(reader).getAsJsonObject().get("data").getAsJsonArray(),
                CFCategory[].class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Fetches specific file from a specific mod
     * 
     * @param modID ID of the mod to get the file from
     * @param fileID File ID to fetch
     * @return {@link CFFile} instance, or null if not found
     */
    public CFFile getFileFromID(int modID, int fileID)
    {
        try {
            final URL url = buildUrl("mods/%s/files/%s", modID, fileID);
            final HttpsURLConnection urlConnection = openUrlConnection(url);
            if (urlConnection.getResponseCode() != 200)
                return null;
            final InputStreamReader reader = new InputStreamReader(urlConnection.getInputStream());
            return gson.fromJson(JsonParser.parseReader(reader).getAsJsonObject().get("data").getAsJsonObject(),
                CFFile.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Fetches (first 50) games from curseforge
     * 
     * @return {@link CFGame} array, or null if not found
     */
    public CFGame[] getGames()
    {
        try {
            final URL url = buildUrl("games");
            final HttpsURLConnection urlConnection = openUrlConnection(url);
            if (urlConnection.getResponseCode() != 200)
                return null;
            final InputStreamReader reader = new InputStreamReader(urlConnection.getInputStream());
            return gson.fromJson(JsonParser.parseReader(reader).getAsJsonObject().get("data").getAsJsonArray(),
                CFGame[].class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public MCVersion[] getMinecraftVersions()
    {
        try {
            final URL url = buildUrl("minecraft/version");
            final HttpsURLConnection urlConnection = openUrlConnection(url);
            if (urlConnection.getResponseCode() != 200)
                return null;
            final InputStreamReader reader = new InputStreamReader(urlConnection.getInputStream());
            return gson.fromJson(JsonParser.parseReader(reader).getAsJsonObject().get("data").getAsJsonArray(),
                MCVersion[].class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Fetches (first 50) search results for a mod
     * 
     * @param gameID Game ID of the search result
     * @param term Search term to use
     * @return {@link CFMod} array, or null on error
     */
    public CFMod[] searchMod(final String term, int gameID)
    {
        final String urlEncodedTerm = URLEncoder.encode(term, StandardCharsets.UTF_8);
        try {
            final URL url = buildUrl("mods/search?searchFilter=%s&gameId=%s", urlEncodedTerm, gameID);
            final HttpsURLConnection urlConnection = openUrlConnection(url);
            if (urlConnection.getResponseCode() != 200)
                return null;
            final InputStreamReader reader = new InputStreamReader(urlConnection.getInputStream());
            return gson.fromJson(JsonParser.parseReader(reader).getAsJsonObject().get("data").getAsJsonArray(),
                CFMod[].class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Fetches changelog from a file. It will be an HTML String
     * 
     * @param modID ID of the mod of the file
     * @param fileID File ID of the file to fetch changelog from
     * @return {@link CFMod} array, or null on error
     */
    public String getChangelog(int modID, int fileID)
    {
        try {
            final URL url = buildUrl("mods/%s/files/%s/changelog", modID, fileID);
            final HttpsURLConnection urlConnection = openUrlConnection(url);
            if (urlConnection.getResponseCode() != 200)
                return null;
            final InputStreamReader reader = new InputStreamReader(urlConnection.getInputStream());
            return JsonParser.parseReader(reader).getAsJsonObject().get("data").getAsString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private final URL buildUrl(String spec, Object... args)
    {
        try {
            return new URL(baseURL + spec.formatted(args));
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error building URL: %s".formatted(spec.formatted(args)));
        }
    }

    private HttpsURLConnection openUrlConnection(URL url) throws IOException
    {
        final HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("Accept", "application/json");
        urlConnection.setRequestProperty("User-Agent", "CFCore-API/https://github.com/ErdbeerbaerLP");
        urlConnection.setRequestProperty("x-api-key", apiKey);
        urlConnection.connect();
        if (urlConnection.getResponseCode() == 403)
            throw new InvalidAPIKeyException();
        return urlConnection;
    }

}
