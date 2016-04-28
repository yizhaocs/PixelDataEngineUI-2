package com.adara.pixeldataengineui.model.backend.dto.pixelmapping;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public class LiverampDpMappingsDTO {
    private String dp_name; // varchar(80)
    private Integer dp_id; // int(11)
    private Long threshold_mb; // bigint(20)

    public Long getThreshold_mb() {
        return threshold_mb;
    }

    public void setThreshold_mb(Long threshold_mb) {
        this.threshold_mb = threshold_mb;
    }

    public Integer getDp_id() {
        return dp_id;
    }

    public void setDp_id(Integer dp_id) {
        this.dp_id = dp_id;
    }

    public String getDp_name() {
        return dp_name;
    }

    public void setDp_name(String dp_name) {
        this.dp_name = dp_name;
    }


}
