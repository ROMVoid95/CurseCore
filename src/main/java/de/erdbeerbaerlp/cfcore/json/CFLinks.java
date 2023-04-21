package de.erdbeerbaerlp.cfcore.json;

import de.erdbeerbaerlp.cfcore.lib.To;

public class CFLinks {
    public String websiteUrl = "undefined";
    public String wikiUrl = "undefined";
    public String issuesUrl = "undefined";
    public String sourceUrl = "undefined";

    @Override
    public String toString() {
        return To.String(this);
    }
}
