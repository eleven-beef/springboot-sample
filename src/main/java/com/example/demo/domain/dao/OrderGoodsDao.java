package com.example.demo.domain.dao;

import java.util.List;
import java.util.stream.Collector;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.SelectType;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

import com.example.demo.domain.dto.SalesSummaryConditionDto;
import com.example.demo.domain.entity.SalesSummaryDto;




@Dao
@ConfigAutowireable
public interface OrderGoodsDao {

	/**
     * 売上集計データ取得処理
     *
     * @param shopSeq ショップSEQ
     * @param salesSummaryConditionDto 検索条件
     * @return SalesSummaryDtoのリスト
     */
	@Select
    List<SalesSummaryDto> getSalesSummaryDtoList(Integer shopSeq, SalesSummaryConditionDto conditionDto, SelectOptions options);

	/**
     * 売上集計データ取得処理
     *
     * @param shopSeq ショップSEQ
     * @param salesSummaryConditionDto 検索条件
     * @return SalesSummaryDtoのリスト
     */
	@Select(strategy = SelectType.COLLECT)
    <R> R findAll(Integer shopSeq, SalesSummaryConditionDto conditionDto, SelectOptions options, Collector<SalesSummaryDto, ?, R> collector);


}

