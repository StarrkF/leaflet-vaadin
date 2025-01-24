package com.vaadinleaflettest.application.leaflet.map;

import com.nimbusds.jose.shaded.gson.Gson;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadinleaflettest.application.leaflet.layer.AbstractLeafletLayer;
import com.vaadinleaflettest.application.leaflet.layer.LeafletLayerGroup;
import com.vaadinleaflettest.application.leaflet.layer.LeafletMapLayer;
import com.vaadinleaflettest.application.leaflet.ui.LeafletCircle;
import com.vaadinleaflettest.application.leaflet.ui.LeafletMarker;
import com.vaadinleaflettest.application.leaflet.ui.LeafletPoint;
import com.vaadinleaflettest.application.leaflet.ui.LeafletPolygon;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.MultiPoint;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.io.WKTWriter;
import org.springframework.web.servlet.support.JstlUtils;

import java.io.Serializable;
import java.util.UUID;

@Tag("leaflet-map")
@NpmPackage(value = "leaflet", version = "1.9.4")
@NpmPackage(value = "leaflet-editable", version = "1.3.0")
@NpmPackage(value = "wicket", version = "1.3.8")
@JsModule("leaflet")
@JsModule("leaflet-editable")
@CssImport("leaflet/dist/leaflet.css")
@JavaScript("./themes/js/leaflet-vaadin.js")
public class LeafletMap extends Component implements HasSize {

    String strWkt = "POLYGON((-10 50, -10 60, 0 60, 0 50, -10 50))";


    private String id;
    private Boolean editable = false;
    Gson gson = new Gson();
    WKTReader wktReader;
    WKTWriter writer = new WKTWriter();

    public LeafletMap() {
        this("500px", null);
    }

    public LeafletMap(String height, String width) {
        id = createLayerUid();
        setId(id);
        setWidth(width != null ? width : "100%");
        setHeight(height);
        initMultipleLayerMap();
    }

    private void initMultipleLayerMap() {
        executeJsAfterPageInit("leaflet.initMultipleLayerMap($0)", id);
    }

    private void initDefaultMap() {
        executeJsAfterPageInit("leaflet.initMap($0)", id);
    }

    public LeafletMapLayer addMarker(double x, double y) {
        return addMarker(new LeafletPoint(x, y));
    }

    public LeafletMapLayer addMarker(LeafletPoint point) {
        return addMarker(new LeafletMarker(point));
    }

    public LeafletMapLayer addMarker(LeafletMarker marker) {
        executeJsAfterPageInit("leaflet.addMarker($0, $1, $2)", id, gson.toJson(marker), marker.getId());
        return marker;
    }


    public void setEditable(Boolean editable) {
        this.editable = editable;
        executeJsAfterPageInit("leaflet.setEditable($0, $1)", id, this.editable);
    }

    public Boolean isEditable() {
        return editable;
    }

    public void setOpacity(Double opacity) {
        executeJsAfterPageInit("leaflet.setOpacity($0, $1)", id, opacity);
    }

    public void setScrollWheelZoomEnabled(Boolean enabled) {
        executeJsAfterPageInit("leaflet.setScrollWheelZoomEnabled($0, $1)", id, enabled);
    }

    public void setZoomControlEnabled(Boolean enabled) {
        executeJsAfterPageInit("leaflet.setZoomControlEnabled($0, $1)", id, enabled);
    }

    public void zoomToNokta(LeafletPoint point, Integer zoomRate) {
        executeJsAfterPageInit("leaflet.zoomToPoint($0, $1, $2, $3)", id, point.getLat(), point.getLon(), zoomRate);
    }

    public void setClickListener() {
        executeJsAfterPageInit("leaflet.setMapClickListener($0)", id);
    }

    public void zoomToNokta(LeafletPoint point) {
        zoomToNokta(point, 8);
    }

    public void zoomToAnkara() {
        zoomToNokta(new LeafletPoint(38.942321, 35.419922), 6);
    }

    public void zoomToContent(LeafletMapLayer content) {
        executeJsAfterPageInit("leaflet.zoomToContent($0, $1)", id, content.getId());
    }

    public LeafletMapLayer addCircle(LeafletCircle circle) {
        executeJsAfterPageInit("leaflet.addCircle($0, $1, $2)", id, gson.toJson(circle), circle.getId());
        return circle;
    }

    public void removeAllComponent() {
        executeJsAfterPageInit("leaflet.removeAllComponent($0)", id);
    }

    public void removeLayer(LeafletMapLayer layer) {
        executeJsAfterPageInit("leaflet.removeLayer($0, $1)", id, layer.getId());
    }

    public void addLayerToGroup(LeafletLayerGroup layerGroup) {
        executeJsAfterPageInit("leaflet.addLayerToGroup($0, $1, $2)", id, layerGroup.getId(), gson.toJson(layerGroup.getLayers()));
    }

    public void addLayer(AbstractLeafletLayer layer) {
        if (layer instanceof LeafletMapLayer) {
            executeJsAfterPageInit("leaflet.addLayer($0, $1)", id, layer.getId());
        } else if (layer instanceof LeafletLayerGroup) {
            executeJsAfterPageInit("leaflet.addLayerGroup($0, $1)", id, layer.getId());
        }
    }

    public LeafletMapLayer addPolygon(LeafletPolygon polygon) {
        executeJsAfterPageInit("leaflet.addPolygon($0, $1, $2)", id,  gson.toJson(polygon), polygon.getId());
        return polygon;
    }

//    public String wktAsString(String stringGeometry) {
//        Geometry geometry = readWKT(stringGeometry);
//
//        return writer.write(geometry);
//    }

    public LeafletMapLayer wktToLeafletLayer() {


//        if (strWkt.startsWith("POINT")) {
//            org.locationtech.jts.geom.Point point = getPoint(strWkt);
//            Double lon = point.getX();
//            Double lat = point.getY();
//            CevherWktUtil
//            LLatLng latLng = new LLatLng(reg, lat, lon);
//            return  latLng;
//            //LMarker lMarker = addPointToMapAsMarker(new Point(lat, lon), null, null, VaadinIcon.SIGNAL);
//            //return lMarker;
//        } else if (strWkt.startsWith("MULTIPOINT")) {
//            return CevherWktUtil.toLayer(reg, (MultiPoint) readWKT(strWkt));
//        } else if (strWkt.startsWith("LINESTRING")) {
//            return JTSUtil.toLayer((LineString) readWKT(strWkt));
//        } else if (strWkt.startsWith("POLYGON")) {
//            return JTSUtil.toLayer((Polygon) readWKT(strWkt));
//        } else if (strWkt.startsWith("MULTIPOLYGON")) {
//            return JTSUtil.toLayer((MultiPolygon) readWKT(strWkt));
//        }
//
//        return null;
        return null;
    }

    public Geometry readWKT(Geometry wktString) {
//        try {
//            wktReader = new WKTReader();
//            return wktReader.read(wktString);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return null;
        return null;
    }

//    private org.locationtech.jts.geom.Point getPoint(String strWkt) {
//        return (org.locationtech.jts.geom.Point) readWKT(strWkt);
//    }

    private String createLayerUid() {
        return UUID.randomUUID().toString();
    }

    private void executeJsAfterPageInit(String expression, Serializable... parameters) {
        UI.getCurrent().beforeClientResponse(this, context -> UI.getCurrent().getElement().executeJs(expression, parameters));
    }
}
