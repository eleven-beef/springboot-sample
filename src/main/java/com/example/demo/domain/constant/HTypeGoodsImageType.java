package com.example.demo.domain.constant;

import org.seasar.doma.Domain;

@Domain(valueType = String.class, factoryMethod = "of")
public enum HTypeGoodsImageType {

	/** PS1=サムネイル画像（PC） */
    THUMBNAIL_PC("PS1"),

    /** PL1=リスト画像（PC） */
    LIST_PC("PL1"),

    /** ML1=リスト画像（携帯） */
    LIST_MB("ML1"),

    /** PD1=詳細画像１　小（PC） */
    DETAIL1_PC("PD1"),

    /** PD2=詳細画像１　中（PC） */
    DETAIL2_PC("PD2"),

    /** PD3=詳細画像１　大（PC） */
    DETAIL3_PC("PD3"),

    /** MD2=詳細画像１　中（携帯） */
    DETAIL2_MB("MD2"),

    /** MD3=詳細画像１　大（携帯） */
    DETAIL3_MB("MD3"),

    /** PT1=タイトル画像（PC） */
    TITLE_PC("PT1"),

    /** MT1=タイトル画像（携帯） */
    TITLE_MB("MT1");

    private final String value;

    private HTypeGoodsImageType(String value) {
        this.value = value;
    }

    public static HTypeGoodsImageType of(String value) {
        for (HTypeGoodsImageType goodsImageType : HTypeGoodsImageType.values()) {
            if (goodsImageType.value.equals(value)) {
                return goodsImageType;
            }
        }
        throw new IllegalArgumentException(value);
    }

    public String getValue() {
        return value;
    }
}
