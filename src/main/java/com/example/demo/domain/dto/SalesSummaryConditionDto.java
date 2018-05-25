package com.example.demo.domain.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.example.demo.domain.constant.HTypeGoodsImageType;

/**
 * 売上集計検索条件
 * @author ando-ss
 *
 */
public class SalesSummaryConditionDto implements Serializable  {
// MEMO:getter/setter必須の模様

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

//    /** 売上集計期間(FROM) */
//	@NotNull
//    public Timestamp saleTimeFrom;
//    /** 売上集計期間(TO) */
//	@NotNull
//    public Timestamp saleTimeTo;
//    /** ブランド */
//    public String brand;
    /** 商品番号 */
	@NotNull
	@NotBlank
	@Size(max=20)
	private String goodsCode;
//    /** メーカー品番 */
//    public String catalogCode;
    /** 商品名 */
	@NotNull
	@NotBlank
	@Size(max=120)
	private String goodsName;

//    /** 売上集計期間(CSV出力用) */
//    public String summartyTerm;
//
//    /** 商品画像種別 */
//    public HTypeGoodsImageType imageType;
//
//	public Timestamp getSaleTimeFrom() {
//		return saleTimeFrom;
//	}
//
//	public void setSaleTimeFrom(Timestamp saleTimeFrom) {
//		this.saleTimeFrom = saleTimeFrom;
//	}
//
//	public Timestamp getSaleTimeTo() {
//		return saleTimeTo;
//	}
//
//	public void setSaleTimeTo(Timestamp saleTimeTo) {
//		this.saleTimeTo = saleTimeTo;
//	}
//
//	public String getBrand() {
//		return brand;
//	}
//
//	public void setBrand(String brand) {
//		this.brand = brand;
//	}
//
	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

//	public String getCatalogCode() {
//		return catalogCode;
//	}
//
//	public void setCatalogCode(String catalogCode) {
//		this.catalogCode = catalogCode;
//	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

//	public String getSummartyTerm() {
//		return summartyTerm;
//	}
//
//	public void setSummartyTerm(String summartyTerm) {
//		this.summartyTerm = summartyTerm;
//	}
//
//	public HTypeGoodsImageType getImageType() {
//		return imageType;
//	}
//
//	public void setImageType(HTypeGoodsImageType imageType) {
//		this.imageType = imageType;
//	}


}
