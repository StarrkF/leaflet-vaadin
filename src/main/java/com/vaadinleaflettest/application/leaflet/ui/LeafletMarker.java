package com.vaadinleaflettest.application.leaflet.ui;

import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadinleaflettest.application.leaflet.layer.LeafletMapLayer;

public class LeafletMarker extends LeafletMapLayer {
    private LeafletPoint point;
    private String popupText;
    private String toolTip;
    private String icon;

    public LeafletMarker(LeafletPoint point, String popupText, String toolTip, VaadinIcon icon) {
        this.point = point;
        this.popupText = popupText;
        this.toolTip = toolTip;
        this.icon = icon.create().getElement().getOuterHTML();
    }

    public LeafletMarker(LeafletPoint point) {
        this.point = point;
    }

    public LeafletPoint getPoint() {
        return point;
    }

    public void setPoint(LeafletPoint point) {
        this.point = point;
    }

    public String getPopupText() {
        return popupText;
    }

    public void setPopupText(String popupText) {
        this.popupText = popupText;
    }

    public String getToolTip() {
        return toolTip;
    }

    public void setToolTip(String toolTip) {
        this.toolTip = toolTip;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(VaadinIcon icon) {
        this.icon = icon.create().getElement().getOuterHTML();
    }
}
