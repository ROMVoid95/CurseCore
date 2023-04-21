package de.erdbeerbaerlp.cfcore.json;

import de.erdbeerbaerlp.cfcore.lib.To;

public class CFCategory {
    public int id = -1;
    public int gameId = -1;
    public String name = "undefined";
    public String slug = "undefined";
    public String url = "undefined";
    public String iconUrl = "undefined";
    public String dateModified = "undefined";
    public boolean isClass = false;
    public int classId = -1;
    public int parentCategoryId = -1;
    public int displayIndex = -1;

    @Override
    public String toString() {
        return To.String(this);
    }
}
