package de.erdbeerbaerlp.cfcore.json;

import de.erdbeerbaerlp.cfcore.lib.To;

public class CFFileIndex {
    public String gameVersion = "undefined";
    public int fileId = -1;
    public String filename = "undefined";
    public CFReleaseType releaseType = CFReleaseType.Release;
    public int gameVersionTypeId = -1;
    public CFModLoaderType modLoader = CFModLoaderType.Any;
    
    @Override
    public String toString()
    {
        return To.String(this);
    }
}
