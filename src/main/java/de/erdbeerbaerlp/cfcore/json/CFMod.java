package de.erdbeerbaerlp.cfcore.json;

import java.util.ArrayList;
import java.util.List;

import de.erdbeerbaerlp.cfcore.lib.To;

public class CFMod {
    public int id = -1;
    public int gameId = -1;
    public String name = "Undefined;";
    public String slug = "undefined";
    public CFLinks links = new CFLinks();
    public String summary = "undefined";
    public CFModStatus status = CFModStatus.New;
    public int downloadCount = -1;
    public boolean isFeatured = false;
    public int primaryCategoryId = -1;
    public CFCategory[] categories = new CFCategory[0];
    public int classId = -1;
    public CFAuthors[] authors = new CFAuthors[0];
    public CFLogo logo = new CFLogo();
    public CFScreenshot[] screenshots = new CFScreenshot[0];
    public int mainFileId = -1;
    public CFFile[] latestFiles = new CFFile[0];
    public CFFileIndex[] latestFilesIndexes = new CFFileIndex[0];
    public String dateCreated = "undefined";
    public String dateModified = "undefined";
    public String dateReleased = "undefined";
    public boolean allowModDistribution = false;
    public int gamePopularityRank = -1;
    public boolean isAvailable = false;
    public int thumbsUpCount = -1;
    
    public CFFile getMostRecentFileForVersion(String version)
    {
        for(CFFile file : latestFiles)
        {
            if(file.getVersion().equals(MinecraftVersion.from(version)))
            {
                return file;
            }
        }
        
        return null;
    }
    
    public CFFile getMostRecentFile()
    {
        return latestFiles[latestFiles.length - 1];
    }
    
    public List<MinecraftVersion> getAllMinecraftVersions()
    {
        List<MinecraftVersion> versions = new ArrayList<>();
        for (int i = 0; i < latestFilesIndexes.length; i++) {
            versions.add(new MinecraftVersion(latestFilesIndexes[i].gameVersion));
        }
        return versions;
    }
    
    @Override
    public String toString() {
        return To.String(this);
    }
}
