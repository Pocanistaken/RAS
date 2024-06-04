
package com.ras.entity;



public record Region (String regionName, int regionID) {

    public Region(int regionID) {
        this("Unknown", regionID);
        
    }

}


