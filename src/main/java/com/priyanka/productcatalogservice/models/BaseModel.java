package com.priyanka.productcatalogservice.models;

public class BaseModel {
    private Long id;

    private Long creatdAt;

    private Long lastupdateAt;

    private State state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatdAt() {
        return creatdAt;
    }

    public void setCreatdAt(Long creatdAt) {
        this.creatdAt = creatdAt;
    }

    public Long getLastupdateAt() {
        return lastupdateAt;
    }

    public void setLastupdateAt(Long lastupdateAt) {
        this.lastupdateAt = lastupdateAt;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
