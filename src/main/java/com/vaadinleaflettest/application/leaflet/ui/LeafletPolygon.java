package com.vaadinleaflettest.application.leaflet.ui;

import com.vaadinleaflettest.application.leaflet.layer.LeafletMapStyleableLayer;

public class LeafletPolygon extends LeafletMapStyleableLayer {
    private String wkt;

    public LeafletPolygon(String wkt) {
        this(wkt, new LeafletMapStyle());
    }

    public LeafletPolygon(String wkt, LeafletMapStyle style) {
        this.wkt = wkt;
        super.setStyle(style);
    }

    public String getWkt() {
        return wkt;
    }

    public void setWkt(String wkt) {
        this.wkt = wkt;
    }

}
