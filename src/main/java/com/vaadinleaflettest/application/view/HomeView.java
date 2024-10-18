package com.vaadinleaflettest.application.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.vaadinleaflettest.application.leaflet.layer.LeafletLayerGroup;
import com.vaadinleaflettest.application.leaflet.layer.LeafletMapLayer;
import com.vaadinleaflettest.application.leaflet.map.LeafletMap;
import com.vaadinleaflettest.application.leaflet.ui.LeafletCircle;
import com.vaadinleaflettest.application.leaflet.ui.LeafletMarker;
import com.vaadinleaflettest.application.leaflet.ui.LeafletPoint;

import java.util.Random;


@Route(value = "")
public class HomeView extends VerticalLayout {

    Button btnAddMarker = new Button("Add Marker");
    Button btnZoom = new Button("Zoom");
    Button btnChangeOpacity = new Button("Change Opacity");
    Button btnAddCircle = new Button("Add Circle");
    Button btnDeleteLastMarker =  new Button("Marker Sil");
    Button btnFocusLastMarker =  new Button("Focus Marker");
    Button btnFocusLastCircle =  new Button("Focus Circle");
    Button btnCreateLayerGroup =  new Button(("Create Layer Group"));
    FormLayout flButtonArea = new FormLayout();
    LeafletMap map = new LeafletMap();

    LeafletMapLayer lastMark;
    LeafletMapLayer lastCircle;

    public HomeView() {
        map.setClickListener();
        setButtonClickListeners();
        buildView();
    }

    private void buildView() {
        flButtonArea.add(
                btnAddMarker,
                btnZoom,
                btnChangeOpacity,
                btnAddCircle,
                btnDeleteLastMarker,
                btnFocusLastMarker,
                btnFocusLastCircle,
                btnCreateLayerGroup
        );

        flButtonArea.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 2),
                new FormLayout.ResponsiveStep("768px", 4)
        );

        add(flButtonArea, map);
    }

    private void setButtonClickListeners() {
        btnAddMarker.addClickListener(c -> {
            LeafletPoint randomPoint = generateRandomPoint();
            VaadinIcon icon = VaadinIcon.AIRPLANE;
            icon.create().getStyle().setWidth("50px");
            icon.create().getStyle().setHeight("50px");
            lastMark = map.addMarker(new LeafletMarker(randomPoint,
                    "Marker",
                    "Marker tooltip",
                    icon
            ));
        });

        btnAddCircle.addClickListener(c -> {
            lastCircle = map.addCircle(new LeafletCircle(generateRandomPoint(), 500));
        });

        btnZoom.addClickListener(c -> {
            map.zoomToNokta(new LeafletPoint(40.0, 40.0), 8);
            map.setOpacity(0.2);
        });

        btnChangeOpacity.addClickListener(c -> {
            Random random = new Random();
            double randomOpacity = Math.round((random.nextDouble()) * 10.0) / 10.0;
            map.setOpacity(randomOpacity);
        });

        btnDeleteLastMarker.addClickListener(c -> {
            if (lastMark != null) {
                map.removeLayer(lastMark);
            }
        });

        btnFocusLastMarker.addClickListener(c -> {
            if (lastMark != null) {
                map.zoomToContent(lastMark);
            }
        });

        btnFocusLastCircle.addClickListener(c -> {
            if (lastCircle != null) {
                map.zoomToContent(lastCircle);
            }
        });

        btnCreateLayerGroup.addClickListener(c -> {
            LeafletLayerGroup layerGroup = new LeafletLayerGroup();
            LeafletMarker marker = new LeafletMarker(new LeafletPoint(40.0, 40.0));
            LeafletMarker marker2 = new LeafletMarker(new LeafletPoint(38.0, 40.0));
            LeafletMarker marker3 = new LeafletMarker(new LeafletPoint(36.0, 40.0));
            LeafletMarker marker4 = new LeafletMarker(new LeafletPoint(34.0, 40.0));
            map.addMarker(marker);
            map.addMarker(marker2);
            map.addMarker(marker3);
            map.addMarker(marker4);
            layerGroup.add(marker, marker2, marker3, marker4);
            map.addLayerToGroup(layerGroup);
        });
    }

    public static LeafletPoint generateRandomPoint() {
        Random random = new Random();

        // Türkiye lat&lng
        double minLat = 36.0;
        double maxLat = 42.0;
        double minLng = 26.0;
        double maxLng = 45.0;

        double randomLat = minLat + (maxLat - minLat) * random.nextDouble();
        double randomLng = minLng + (maxLng - minLng) * random.nextDouble();

        return new LeafletPoint(randomLat, randomLng);
    }
}