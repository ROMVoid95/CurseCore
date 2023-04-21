package de.erdbeerbaerlp.cfcore.json;

import de.erdbeerbaerlp.cfcore.lib.To;

public class CFScreenshot {
    public int id = -1;
    public int modId = -1;
    public String title = "undefined";
    public String description = "undefined";
    public String thumbnailUrl = "undefined";
    public String url = "undefined";

    @Override
    public String toString() {
        return To.String(this);
    }
}
