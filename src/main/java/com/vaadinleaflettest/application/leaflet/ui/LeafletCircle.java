package com.vaadinleaflettest.application.leaflet.ui;


import com.vaadinleaflettest.application.leaflet.layer.LeafletMapLayer;
import com.vaadinleaflettest.application.leaflet.layer.LeafletMapStyleableLayer;
import com.vaadinleaflettest.application.leaflet.map.HasMapStyle;

public class LeafletCircle extends LeafletMapStyleableLayer {

    private Integer radius;
    private LeafletPoint point;

    public LeafletCircle(LeafletPoint point, Integer radius) {
        this(point, radius, new LeafletMapStyle());
    }

    public LeafletCircle(LeafletPoint point, Integer radius, LeafletMapStyle style) {
        this.point = point;
        super.setStyle(style);
        this.radius = radius;
    }

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    public LeafletPoint getPoint() {
        return point;
    }

    public void setPoint(LeafletPoint point) {
        this.point = point;
    }

    public Double getLat() {
        return point.getLat();
    }

    public Double getLng() {
        return point.getLon();
    }

    public void setLat(Double lat) {
        point.setLat(lat);
    }

    public void setLng(Double lng) {
        point.setLon(lng);
    }
}
