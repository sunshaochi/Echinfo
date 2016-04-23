package com.beyonditsm.echinfo.entity;

/**
 * 职业
 * Created by gxy on 2016/4/23.
 */
public class OccuaptionEntity {
    /**
     * "id":"1","occupationName":"工程师","fOccupationId":"0"
     */
    private String id;
    private String occupationName;
    private String fOccupationId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOccupationName() {
        return occupationName;
    }

    public void setOccupationName(String occupationName) {
        this.occupationName = occupationName;
    }

    public String getfOccupationId() {
        return fOccupationId;
    }

    public void setfOccupationId(String fOccupationId) {
        this.fOccupationId = fOccupationId;
    }
}
