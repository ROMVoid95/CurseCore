package de.erdbeerbaerlp.cfcore.json;

import de.erdbeerbaerlp.cfcore.lib.To;

public class SortableGameVersion {
    public String gameVersionName = "undefined";
    public String gameVersionPadded = "undefined";
    public String gameVersion = "undefined";
    public String gameVersionReleaseDate = "undefined";
    public long gameVersionTypeId = -1;

    public MinecraftVersion minecraftVersion()
    {
        return new MinecraftVersion(gameVersion);
    }
    
    @Override
    public String toString() {
        return To.String(this);
    }
}