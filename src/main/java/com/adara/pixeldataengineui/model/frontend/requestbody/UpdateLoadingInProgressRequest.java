package com.adara.pixeldataengineui.model.frontend.requestbody;

/**
 * Created by yzhao on 8/12/16.
 */
public class UpdateLoadingInProgressRequest {
    private String map_name;
    private Boolean loading_in_progress;

    public String getMap_name() {
        return map_name;
    }

    public void setMap_name(String map_name) {
        this.map_name = map_name;
    }

    public Boolean getLoading_in_progress() {
        return loading_in_progress;
    }

    public void setLoading_in_progress(Boolean loading_in_progress) {
        this.loading_in_progress = loading_in_progress;
    }
}
