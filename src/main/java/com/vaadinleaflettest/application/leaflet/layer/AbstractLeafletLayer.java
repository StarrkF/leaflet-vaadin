package com.vaadinleaflettest.application.leaflet.layer;

import java.util.UUID;

public abstract class AbstractLeafletLayer {
    private final String id;

    public AbstractLeafletLayer() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }
}
