package com.example.demo.domain.constant;

import org.seasar.doma.Domain;

@Domain(valueType = String.class, factoryMethod = "of")
public enum HTypeOrderShopType {
	HITMALL("0"),
    RMS("1");

    private final String value;

    private HTypeOrderShopType(String value) {
        this.value = value;
    }

    public static HTypeOrderShopType of(String value) {
        for (HTypeOrderShopType orderShopType : HTypeOrderShopType.values()) {
            if (orderShopType.value.equals(value)) {
                return orderShopType;
            }
        }
        throw new IllegalArgumentException(value);
    }

    public String getValue() {
        return value;
    }
}
