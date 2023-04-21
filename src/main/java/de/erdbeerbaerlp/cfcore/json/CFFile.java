package de.erdbeerbaerlp.cfcore.json;

import de.erdbeerbaerlp.cfcore.lib.To;

public class CFFile
{
    public int id = -1;

    public int gameId = -1;

    public int modId = -1;

    public boolean isAvailable = false;

    public String displayName = "undefined";

    public String fileName = "undefined";

    public CFReleaseType releaseType = CFReleaseType.Release;

    public CFFileStatus fileStatus = CFFileStatus.Processing;

    public CFFileHash[] hashes = new CFFileHash[0];

    public String fileDate = "undefined";

    public int fileLength = -1;

    public int downloadCount = -1;

    public String downloadUrl = "undefined";

    public String[] gameVersions = new String[0];

    public SortableGameVersion[] sortableGameVersions = new SortableGameVersion[0];

    public CFFileDependency[] dependencies = new CFFileDependency[0];

    public boolean exposeAsAlternative = false;

    public int parentProjectFileId = -1;

    public int alternateFileId = -1;

    public boolean isServerPack = false;

    public int serverPackFileId = -1;

    public int fileFingerprint = -1;

    public CFFileModule[] modules = new CFFileModule[0];
    
    public MinecraftVersion getVersion()
    {
        return MinecraftVersion.from(gameVersions[0]);
    }

    public static class CFFileHash
    {
        public String value = "undefined";

        public int algo = -1;

        @Override
        public String toString()
        {
            return To.String(this);
        }
    }

    public static class CFFileModule
    {
        public String name = "undefined";

        public int fingerprint = -1;

        @Override
        public String toString()
        {
            return To.String(this);
        }
    }

    public static class CFFileDependency
    {
        public String modId = "undefined";

        public int relationType = -1;

        @Override
        public String toString()
        {
            return To.String(this);
        }
    }

    @Override
    public String toString()
    {
        return To.String(this);
    }
}
