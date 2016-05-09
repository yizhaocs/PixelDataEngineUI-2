package com.adara.pixeldataengineui.model.frontend.requestbody;

import java.math.BigInteger;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public class InsertUpdateRequest {
    private String id;
    private Data mapping;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Data getMapping() {
        return mapping;
    }

    public void setMapping(Data mapping) {
        this.mapping = mapping;
    }

    public class Data {
        // general
        private String _id;
        // adobe
        private Integer adobe_segment_id;
        private Integer dp_key_id;
        // liveramp-dp
        private String dp_name;
        private Integer dp_id;
        private BigInteger threshold_mb;
        // liveramp-key
        private Long liveramp_segment_id;
        private String value;
        // facebook-pixel
        private BigInteger facebook_pixel_id;
        // facebook-dp
        private Integer id;
        private String name;
        private Boolean sync_facebook;
        // facebook-key
        private Integer key_id;
        private Byte enabled;
        private Byte update_interval;
        private Boolean use_image_pixel;
        // derive_combo_pixel_mappings
        private Integer advertiser_id;
        private Integer cp_id;
        // krux_dpkey_mapping
        private String krux_segment_id;
        // dbm_conversion_pixel_mappings
        private Integer conversion_pixel_id;
        private String dbm_url;
        // pde groups
        private String trigger_key_id;
        private Integer gid;
        private Integer group_type;

        public Boolean getUse_image_pixel() {
            return use_image_pixel;
        }

        public void setUse_image_pixel(Boolean use_image_pixel) {
            this.use_image_pixel = use_image_pixel;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public Integer getAdobe_segment_id() {
            return adobe_segment_id;
        }

        public void setAdobe_segment_id(Integer adobe_segment_id) {
            this.adobe_segment_id = adobe_segment_id;
        }

        public Integer getDp_key_id() {
            return dp_key_id;
        }

        public void setDp_key_id(Integer dp_key_id) {
            this.dp_key_id = dp_key_id;
        }

        public String getDp_name() {
            return dp_name;
        }

        public void setDp_name(String dp_name) {
            this.dp_name = dp_name;
        }

        public Integer getDp_id() {
            return dp_id;
        }

        public void setDp_id(Integer dp_id) {
            this.dp_id = dp_id;
        }

        public BigInteger getThreshold_mb() {
            return threshold_mb;
        }

        public void setThreshold_mb(BigInteger threshold_mb) {
            this.threshold_mb = threshold_mb;
        }

        public Long getLiveramp_segment_id() {
            return liveramp_segment_id;
        }

        public void setLiveramp_segment_id(Long liveramp_segment_id) {
            this.liveramp_segment_id = liveramp_segment_id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public BigInteger getFacebook_pixel_id() {
            return facebook_pixel_id;
        }

        public void setFacebook_pixel_id(BigInteger facebook_pixel_id) {
            this.facebook_pixel_id = facebook_pixel_id;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Boolean getSync_facebook() {
            return sync_facebook;
        }

        public void setSync_facebook(Boolean sync_facebook) {
            this.sync_facebook = sync_facebook;
        }

        public Integer getKey_id() {
            return key_id;
        }

        public void setKey_id(Integer key_id) {
            this.key_id = key_id;
        }

        public Byte getEnabled() {
            return enabled;
        }

        public void setEnabled(Byte enabled) {
            this.enabled = enabled;
        }

        public Byte getUpdate_interval() {
            return update_interval;
        }

        public void setUpdate_interval(Byte update_interval) {
            this.update_interval = update_interval;
        }

        public Integer getAdvertiser_id() {
            return advertiser_id;
        }

        public void setAdvertiser_id(Integer advertiser_id) {
            this.advertiser_id = advertiser_id;
        }

        public Integer getCp_id() {
            return cp_id;
        }

        public void setCp_id(Integer cp_id) {
            this.cp_id = cp_id;
        }

        public String getKrux_segment_id() {
            return krux_segment_id;
        }

        public void setKrux_segment_id(String krux_segment_id) {
            this.krux_segment_id = krux_segment_id;
        }

        public Integer getConversion_pixel_id() {
            return conversion_pixel_id;
        }

        public void setConversion_pixel_id(Integer conversion_pixel_id) {
            this.conversion_pixel_id = conversion_pixel_id;
        }

        public String getDbm_url() {
            return dbm_url;
        }

        public void setDbm_url(String dbm_url) {
            this.dbm_url = dbm_url;
        }


        public String getTriggering_key_id() {
            return trigger_key_id;
        }

        public void setTrigger_key_id(String trigger_key_id) {
            this.trigger_key_id = trigger_key_id;
        }

        public Integer getGid() {
            return gid;
        }

        public void setGid(Integer gid) {
            this.gid = gid;
        }

        public Integer getGroup_type() {
            return group_type;
        }

        public void setGroup_type(Integer group_type) {
            this.group_type = group_type;
        }
    }
}
