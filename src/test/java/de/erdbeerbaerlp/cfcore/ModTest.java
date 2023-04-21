package de.erdbeerbaerlp.cfcore;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.erdbeerbaerlp.cfcore.json.CFCategory;
import de.erdbeerbaerlp.cfcore.json.CFFile;
import de.erdbeerbaerlp.cfcore.json.CFGame;
import de.erdbeerbaerlp.cfcore.json.CFMod;
import de.erdbeerbaerlp.cfcore.json.CFReleaseType;

public class ModTest
{

    static CurseAPI api;

    @Test
    @BeforeAll
    static void setupApiClass()
    {
        System.out.println(System.getProperties().getProperty("cf.apikey"));
        Assertions.assertNotEquals(null, System.getProperties().getProperty("cf.apikey"));
        api = new CurseAPI(System.getProperties().getProperty("cf.apikey"));
    }

    @Test
    @DisplayName("Check API Key exception")
    void checkAPIKeyException()
    {
        Assertions.assertThrows(NullPointerException.class, () -> {
            api.getModFromID(1);
        });

        Assertions.assertThrows(NullPointerException.class, () -> {
            api.getModFromID(1);
        });
    }

    @Test
    @DisplayName("Get an existing mod's data from ID")
    void getExistingModID()
    {
        final CFMod mod = api.getModFromID(238222);
        Assertions.assertNotNull(mod);
        System.out.println("Output:" + mod.toString());
        Assertions.assertEquals("jei", mod.slug);
    }

    @Test
    @DisplayName("Get an existing mod's data from Slug")
    void getExistingModSlug()
    {
        final CFMod mod = api.getModFromSlug("dcintegration", 432);
        Assertions.assertNotNull(mod);
        System.out.println("Output:" + mod.toString());
        Assertions.assertEquals(324952, mod.id);
    }

    @Test
    @DisplayName("Get an nonexisting mod's data")
    void getNonexistentMod()
    {
        Assertions.assertNull(api.getModFromID(1));
    }

    @Test
    void testGameRetrieval()
    {
        final CFGame[] games = api.getGames();
        System.out.println(Arrays.toString(games));
        Assertions.assertNotNull(games);
        Assertions.assertNotNull(games[0]);
    }

    @Test
    void searchMod()
    {
        final CFMod[] results = api.searchMod("BetterPortals", 432);
        Assertions.assertNotNull(results);
        Assertions.assertNotNull(results[0]);
        Assertions.assertEquals("betterportals", results[0].slug);
        System.out.println(Arrays.toString(results));
    }

    @Test
    void fileRetrievalTest()
    {
        final CFFile file = api.getFileFromID(324952, 3846786);
        Assertions.assertNotNull(file);
        Assertions.assertEquals("dcintegration-forge-2.4.5-1.19.jar", file.fileName);
    }

    @Test
    void categoryRetrievalTest()
    {
        final CFCategory[] categories = api.getCategories(432);
        Assertions.assertNotNull(categories);
        Assertions.assertNotNull(categories[0]);
    }

    @Test
    void releaseTypeTest()
    {
        final CFFile alphaFile = api.getFileFromID(324952, 3846786);
        final CFFile betaFile = api.getFileFromID(324952, 3846775);
        final CFFile releaseFile = api.getFileFromID(324952, 3508392);
        Assertions.assertNotNull(alphaFile);
        Assertions.assertNotNull(betaFile);
        Assertions.assertNotNull(releaseFile);
        Assertions.assertEquals(CFReleaseType.Alpha, alphaFile.releaseType);
        Assertions.assertEquals(CFReleaseType.Beta, betaFile.releaseType);
        Assertions.assertEquals(CFReleaseType.Release, releaseFile.releaseType);
    }

    @Test
    void changelogTest()
    {
        final String changelog = api.getChangelog(324952, 3908725);
        System.out.println(changelog);
        Assertions.assertEquals("<p>1.19.1 port</p>", changelog);
    }
}
