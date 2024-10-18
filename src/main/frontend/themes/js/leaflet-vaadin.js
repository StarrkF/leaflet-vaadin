// Vaadin dev mod console.log bypass
console.log = function() {};
var marker;

const createdMapObject = {};
const layers = {};
const layerGroups = {};

const initMap = (id) => {
    let map = L.map(id).setView([39.9334, 32.8597], 6);
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        zoom: 15
    }).addTo(map);
    L.control.scale().addTo(map);
    createdMapObject[id] = map;
}

const initMultipleLayerMap = (id) => {
    const map = L.map(id).setView([39.9334, 35.419922], 6);
    const googleTraffic = L.tileLayer('https://{s}.google.com/vt/lyrs=m@221097413,traffic&x={x}&y={y}&z={z}', {
        maxZoom: 20,
        minZoom: 2,
        subdomains: ['mt0', 'mt1', 'mt2', 'mt3'],
    });
    const googleStreets = L.tileLayer('https://{s}.google.com/vt/lyrs=m&x={x}&y={y}&z={z}', {
        maxZoom: 20,
        subdomains: ['mt0', 'mt1', 'mt2', 'mt3']
    });
    const sat = L.tileLayer('https://server.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer/tile/{z}/{y}/{x}', {
        maxZoom: 18,
    });
    const googleHybrid = L.tileLayer('https://{s}.google.com/vt/lyrs=s,h&x={x}&y={y}&z={z}', {
        maxZoom: 20,
        subdomains: ['mt0', 'mt1', 'mt2', 'mt3']
    });
    const googleSat = L.tileLayer('https://{s}.google.com/vt/lyrs=s&x={x}&y={y}&z={z}', {
        maxZoom: 20,
        subdomains: ['mt0', 'mt1', 'mt2', 'mt3']
    });
    const googleTerrain = L.tileLayer('https://{s}.google.com/vt/lyrs=p&x={x}&y={y}&z={z}', {
        maxZoom: 20,
        subdomains: ['mt0', 'mt1', 'mt2', 'mt3']
    });
    const osm = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {}).addTo(map);

    const baseMaps = {
        "OSM": osm,
        "ArcGIS SAT": sat,
        "Google Hybrid": googleHybrid,
        "Google SAT": googleSat,
        "Google Street": googleStreets,
        "Google Terrain": googleTerrain,
        "Google Traffic": googleTraffic
    };

    L.control.layers(baseMaps).addTo(map);
    L.control.scale().addTo(map);
    createdMapObject[id] = map;
}

const addMarker = (id, markerInfo, layerId) => {
    let map = createdMapObject[id];
    let markerData = JSON.parse(markerInfo);
    let latLng = L.latLng(markerData.point.lat,markerData.point.lon);

    if (markerData.icon) {
        let customDivIcon = L.divIcon({
            html: markerData.icon,
            iconSize: [30, 30]
        });
        marker = L.marker(latLng, { icon: customDivIcon }).addTo(map);
    } else {
        marker = L.marker(latLng).addTo(map);
    }

    let icons = document.querySelectorAll(".leaflet-div-icon");
    icons.forEach(icon => {
        icon.style.display = "flex";
        icon.style.justifyContent = "center";
        icon.style.alignItems = "center";
        icon.style.backgroundColor = "transparent";
        icon.style.border = "none";
    });

    if (markerData.popupText) marker.bindPopup(markerData.text);
    if (markerData.toolTip) marker.bindTooltip(markerData.tooltip);

    layers[layerId] = marker;
    map.setView(latLng, 10)

    marker.on('click', function () {
        createdMapObject[id].removeLayer(marker);
    });
}

const addCircle = (id, circle, layerId) => {
    let map = createdMapObject[id];
    let circleData = JSON.parse(circle)
    let layer = L.circle([circleData.point.lat, circleData.point.lon], {
        color: circleData.color,
        fillColor: circleData.fillColor,
        fillOpacity: circleData.fillOpacity,
        radius: circleData.radius
    }).addTo(map);
    layers[layerId] = layer;
    map.fitBounds(layer.getBounds());
}

const setEditable = (id, editable) => {
    let map = createdMapObject[id];
    map.editable.enable(editable);
}

const setOpacity = (id, opacity) => {
    let map = createdMapObject[id];
    map.eachLayer(function(layer) {
        if (layer instanceof L.TileLayer) {
            layer.setOpacity(opacity);
        }
    });
}

const zoomToPoint  = (id, lat, lng, zoom = 10) => {
    let map = createdMapObject[id];
    let latLng = L.latLng(lat,lng);
    map.setView(latLng, zoom);
}

const removeAllComponent = (id) => {
    let map = createdMapObject[id];
    map.eachLayer(function(layer) {
        map.removeLayer(layer);
    });
}

const removeLayer = (id, layerId) => {
    let map = createdMapObject[id];
    map.removeLayer(layers[layerId]);
}

const zoomToContent = (id, layerID) => {
    let map = createdMapObject[id];
    let layer = layers[layerID];
    if (typeof layer.getBounds === 'function') {
        map.fitBounds(layer.getBounds());
        map.setZoom(12);
    } else if (layer instanceof L.Marker) {
        map.setView(layer.getLatLng(), 10);
    }
}

const addLayerToGroup = (id, layerGroupId, layerData) => {
    let parsedData = JSON.parse(layerData);
    const finalLayerData = [];
    parsedData.forEach(layer => {
        finalLayerData.push(layers[layer.id]);
    });
    layerGroups[layerGroupId] = L.layerGroup(finalLayerData);

    let map = createdMapObject[id];
    map.removeLayer(layerGroups[layerGroupId])
    layerGroups[layerGroupId].clearLayers();
}

const addLayer = (id, layerId) => {
    let map = createdMapObject[id];
    map.addLayer(layers[layerId]);
}

const addLayerGroup = (id, layerGroupId) => {
    let map = createdMapObject[id];
    let layerGroup = layerGroups[layerGroupId];
    if (layerGroup) {
        layerGroup.addTo(map);
    }
}

const setMapClickListener = (id) => {
    let map = createdMapObject[id];
    map.on('click', (e) => {
        L.popup().setLatLng(e.latlng).setContent("You clicked the map at " + e.latlng.toString()).openOn(map);
    });
}

window.leaflet = {
    initMap: initMap,
    initMultipleLayerMap: initMultipleLayerMap,
    addMarker: addMarker,
    setEditable: setEditable,
    setOpacity: setOpacity,
    zoomToPoint: zoomToPoint,
    addCircle: addCircle,
    removeAllComponent: removeAllComponent,
    zoomToContent: zoomToContent,
    addLayerToGroup: addLayerToGroup,
    addLayer: addLayer,
    addLayerGroup: addLayerGroup,
    setMapClickListener: setMapClickListener,
    removeLayer: removeLayer
};