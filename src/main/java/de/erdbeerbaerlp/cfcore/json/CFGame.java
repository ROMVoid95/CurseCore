package de.erdbeerbaerlp.cfcore.json;

import de.erdbeerbaerlp.cfcore.lib.To;

public class CFGame {
    public long id = -1;
    public String name = "undefined";
    public String slug = "undefined";
    public String dateModified = "undefined";
    public int status = -1;
    public int apiStatus = -1;

    @Override
    public String toString() {
        return To.String(this);
    }
}
