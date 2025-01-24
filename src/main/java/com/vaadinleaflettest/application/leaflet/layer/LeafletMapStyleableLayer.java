package com.vaadinleaflettest.application.leaflet.layer;

import com.vaadinleaflettest.application.leaflet.map.HasMapStyle;
import com.vaadinleaflettest.application.leaflet.ui.LeafletMapStyle;

public class LeafletMapStyleableLayer extends LeafletMapLayer implements HasMapStyle {
    private LeafletMapStyle style;

    @Override
    public LeafletMapStyle getStyle() {
        if (this.style == null) {
            this.style = new LeafletMapStyle();
        }
        return this.style;
    }

    @Override
    public void setStyle(LeafletMapStyle style) {
        this.style = style;
    }


}
