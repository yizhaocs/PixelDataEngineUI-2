package com.adara.pixeldataengineui.model.frontend.requestbody;

/**
 * Created by yzhao on 8/3/16.
 */
public class GeoMapCreationRequest {
    private String mapName;
    private String description;

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
