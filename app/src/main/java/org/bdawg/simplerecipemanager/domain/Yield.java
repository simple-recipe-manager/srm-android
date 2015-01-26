package org.bdawg.simplerecipemanager.domain;

import java.io.Serializable;
import java.util.UUID;

public class Yield implements Serializable{
    private UUID id;
    private long serves;
    private UnitTag unit;

    public Yield() {

    }

    public long getServes() {
        return serves;
    }

    public void setServes(long serves) {
        this.serves = serves;
    }

    public UnitTag getUnit() {
        return unit;
    }

    public void setUnit(UnitTag unit) {
        this.unit = unit;
    }

    public String getId() {
        return id.toString();
    }

    public void setId(String id) {
        this.id = UUID.fromString(id);
    }

    @Override
    public String toString() {
        return this.id.toString();
    }
}
