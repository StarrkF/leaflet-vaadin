package com.vaadinleaflettest.application.leaflet.map;

import com.vaadinleaflettest.application.leaflet.ui.LeafletMapStyle;

public interface HasMapStyle {
    LeafletMapStyle getStyle();
    void setStyle(LeafletMapStyle style);

}
