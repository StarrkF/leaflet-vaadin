package com.vaadinleaflettest.application.leaflet.ui;


import com.vaadinleaflettest.application.leaflet.layer.LeafletMapLayer;

public class LeafletCircle extends LeafletMapLayer {

    private String color;
    private String fillColor;
    private String fillOpacity;
    private Integer radius;
    private LeafletPoint point;

    public LeafletCircle(LeafletPoint point, String color, String fillColor, String fillOpacity, Integer radius) {
        this.point = point;
        this.color = color;
        this.fillColor = fillColor;
        this.fillOpacity = fillOpacity;
        this.radius = radius;
    }

    public LeafletCircle(LeafletPoint point, Integer radius) {
        this(point, "red", "#f03", "0.5", 500);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    public String getFillOpacity() {
        return fillOpacity;
    }

    public void setFillOpacity(String fillOpacity) {
        this.fillOpacity = fillOpacity;
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
