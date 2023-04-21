package de.erdbeerbaerlp.cfcore;

import de.erdbeerbaerlp.cfcore.json.CFMod;

public class RealTest
{
    public static void main(String[] args)
    {
        CurseAPI api = new CurseAPI("$2a$10$65FCaYbixrN9BPXtmYX1Kek1pI8pVjiBYnibZdmRRgIKfranoGzyG");
        
        CFMod mod = api.getModFromID(238222);
        
        System.out.println(mod.getMostRecentFileForVersion("1.12.2").toString());
    }
}
