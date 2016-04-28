package com.adara.pixeldataengineui.model.backend.dto.pixelmapping;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public class LiverampDpkeyMappingsDTO {
    private Long liveramp_segment_id; // bigint(20)
    private Integer dp_key_id; // int(11)
    private String value; // varchar(80)

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getDp_key_id() {
        return dp_key_id;
    }

    public void setDp_key_id(Integer dp_key_id) {
        this.dp_key_id = dp_key_id;
    }

    public Long getLiveramp_segment_id() {
        return liveramp_segment_id;
    }

    public void setLiveramp_segment_id(Long liveramp_segment_id) {
        this.liveramp_segment_id = liveramp_segment_id;
    }


}
