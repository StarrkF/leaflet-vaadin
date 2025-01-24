package com.vaadinleaflettest.application.leaflet.ui;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LeafletMapStyle {
    static final ObjectMapper mapper = new ObjectMapper();
    private Boolean stroke = true;
    private Boolean fill = true;
    private String fillColor = "#3388ff";
    private Double fillOpacity = 0.2;
    private Integer weight = 3;
    private Double opacity = 0.5;
    private String dashArray = null;
    private String lineCap = "round";
    private String lineJoin = "round";
    private String color = "#3388ff";

    public void setFill(Boolean fill) {
        this.fill = fill;
    }

    public Boolean getFill() {
        return this.fill;
    }

    public void setOpacity(Double opacity) {
        this.opacity = opacity;
    }

    public Double getOpacity() {
        return this.opacity;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    public String getFillColor() {
        return this.fillColor;
    }

    public Double getFillOpacity() {
        return this.fillOpacity;
    }

    public void setFillOpacity(Double fillOpacity) {
        this.fillOpacity = fillOpacity;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getWeight() {
        return this.weight;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

    public Boolean getStroke() {
        return this.stroke;
    }

    public void setStroke(Boolean stroke) {
        this.stroke = stroke;
    }

    public String getDashArray() {
        return this.dashArray;
    }

    public void setDashArray(String dashArray) {
        this.dashArray = dashArray;
    }

    public String getLineCap() {
        return this.lineCap;
    }

    public void setLineCap(String lineCap) {
        this.lineCap = lineCap;
    }

    public String getLineJoin() {
        return this.lineJoin;
    }

    public void setLineJoin(String lineJoin) {
        this.lineJoin = lineJoin;
    }

    public String asJson() {
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    static {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
}
