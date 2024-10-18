package com.vaadinleaflettest.application.leaflet.layer;

import java.util.ArrayList;
import java.util.List;

public class LeafletLayerGroup extends AbstractLeafletLayer {

    private final List<LeafletMapLayer> layers = new ArrayList<>();
    public void add(LeafletMapLayer...layer) {
        layers.addAll(List.of(layer));
    }

    public List<LeafletMapLayer> getLayers() {
        return layers;
    }
}
