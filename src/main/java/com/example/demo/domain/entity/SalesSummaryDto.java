package com.example.demo.domain.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.seasar.doma.Entity;

import com.example.demo.domain.constant.HTypeGoodsSaleStatus;

@Entity
public class SalesSummaryDto {

	/** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** 区分 */
    public String priceType;

    /** 商品名 */
    public String goodsName;

    /** 規格1 */
    public String unitValue1;

    /** 規格2 */
    public String unitValue2;

    /** 上代 */
    public BigDecimal fixedPrice;

    /** 販売価格 */
    public BigDecimal goodsPriceInTax;

    /** 商品番号 */
    public String goodsCode;

    /** メーカー品番 */
    public String catalogCode;

    /** 受注数 */
    public BigDecimal orderCount;

    /** キャンセル数 */
    public BigDecimal cancelCount;

    /** 出荷数 */
    public BigDecimal shippingCount;

    /** 返品数 */
    public BigDecimal returnedCount;

    /** 販売可能在庫数 */
    public BigDecimal stockCount;

    /** 販売開始日時PC */
    public Timestamp saleStartTimePC;

    /** 販売開始日時携帯 */
    public Timestamp saleStartTimeMB;

    /** 販売終了日時PC */
    public Timestamp saleEndTimePC;

    /** 販売終了日時携帯 */
    public Timestamp saleEndTimeMB;

    /** 再入荷リクエスト数 */
    public BigDecimal mailCount;

    /** 販売状態PC */
    public HTypeGoodsSaleStatus saleStatusPC;


}