package com.example.velotochka.entities;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class Feature {
    String name;
    String value;
    String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Transient
    Object getConvertedValue() {
        if ("double".equals(type)) {
            return Double.parseDouble(value);
        }
        // String is the default
        return value;
    }
}
