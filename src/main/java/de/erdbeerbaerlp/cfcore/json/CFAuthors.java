package de.erdbeerbaerlp.cfcore.json;

import de.erdbeerbaerlp.cfcore.lib.To;

public class CFAuthors {
    public int id = -1;
    public String name = "undefined";
    public String url = "undefined";

    @Override
    public String toString() {
        return To.String(this);
    }
}
