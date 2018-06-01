package com.example.demo.domain.form;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;


/**
 * 商品別
 * @author ando-ss
 *
 */
@Getter
@Setter
public class SalesForm implements Serializable  {

	// MEMO:getter/setter必須の模様
	// TODO:FromToの相関チェック
	// TODO:ブランドのEnumType化
	// TODO:正規表現、前後スペース、スペシャル記号、半角コンバータ

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	/** 売上集計期間(FROM) */
	@NotNull
	@DateTimeFormat(pattern = "yyyy/[]M/[]d")
	public LocalDate saleTimeFrom;


    /** 売上集計期間(TO) */
	@NotNull
	@DateTimeFormat(pattern = "yyyy/[]M/[]d")
	public LocalDate saleTimeTo;

    /** ブランド */
	public String brand;

    /** 商品番号 */
	@Size(max=20)
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


}
