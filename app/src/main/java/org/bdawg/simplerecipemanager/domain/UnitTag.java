package org.bdawg.simplerecipemanager.domain;

import java.io.Serializable;
import java.util.UUID;

public class UnitTag implements Serializable {
    private UUID id;
    private String tag;
    private String multipleTag;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setMultipleTag(String multipleTag) {
        this.multipleTag = multipleTag;
    }

    public String getMultipleTag() {
        return this.multipleTag;
    }

    public String getAppropriateTag(IngredientAndAmount amt) {
        if (amt.getValue() > 1 && this.multipleTag != null) {
            return this.multipleTag;
        } else {
            return this.tag;
        }
    }
}