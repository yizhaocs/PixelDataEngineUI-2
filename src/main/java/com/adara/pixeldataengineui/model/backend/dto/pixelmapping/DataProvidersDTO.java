package com.adara.pixeldataengineui.model.backend.dto.pixelmapping;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public class DataProvidersDTO {
    private Integer id; // int(11)
    private String name; // varchar(64)
    private Boolean sync_facebook; // tinyint(1)

    public DataProvidersDTO(){

    }

    public DataProvidersDTO(Integer id, String name, Boolean sync_facebook){
        this.id = id;
        this.name = name;
        this.sync_facebook = sync_facebook;
    }

    public Boolean getSync_facebook() {
        return sync_facebook;
    }

    public void setSync_facebook(Boolean sync_facebook) {
        this.sync_facebook = sync_facebook;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DataProvidersDTO [id=" + id + ", name=" + name
                + ", sync_facebook=" + sync_facebook + "]";
    }

}
