package com.example.demo.domain.constant;

import org.seasar.doma.Domain;

@Domain(valueType = String.class, factoryMethod = "of")
public enum HTypeGoodsSaleStatus {

	NO_SALE("0"),
	SALE("1"),
	DELETED("9");


    private final String value;

    private HTypeGoodsSaleStatus(String value) {
        this.value = value;
    }

    public static HTypeGoodsSaleStatus of(String value) {
        for (HTypeGoodsSaleStatus orderShopType : HTypeGoodsSaleStatus.values()) {
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
