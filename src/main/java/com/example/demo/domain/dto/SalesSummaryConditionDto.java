package com.example.demo.domain.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Size;

import org.seasar.doma.jdbc.SelectOptions;

import lombok.Getter;
import lombok.Setter;


/**
 * 売上集計検索条件
 * @author ando-ss
 *
 */
@Getter
@Setter
public class SalesSummaryConditionDto implements Serializable  {

	// MEMO:getter/setter必須の模様
	// TODO:FromToの相関チェック
	// TODO:ブランドのEnumType化
	// TODO:正規表現、前後スペース、スペシャル記号、半角コンバータ

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	/** 売上集計期間(FROM) */
    public LocalDate saleTimeFrom;


    /** 売上集計期間(TO) */
    public LocalDate saleTimeTo;

    /** ブランド */
    public String brand;

    /** 商品番号 */
    public String goodsCode;

    /** メーカー品番 */
    public String catalogCode;

    /** 商品名 */
	@Size(max=120)
	public String goodsName;

    /** 並替項目 */
	public String orderField;

    /** 並替項目 */
	public boolean orderAsc;

	public SelectOptions options;



}
