package com.example.demo.domain.service;

import java.util.List;

import com.example.demo.domain.dto.SalesSummaryConditionDto;
import com.example.demo.domain.entity.SalesSummaryDto;


/**
 * 売上集計検索サービス
 *
 * @author ando-ss
 * @version $Revision:$
 *
 */
public interface SalesSummaryGetService {
    /**
     *
     * 売上集計検索サービス
     *
     * @param salesSummaryConditionDto 検索条件
     * @return 検索結果
     */
    List<SalesSummaryDto> execute(SalesSummaryConditionDto salesSummaryConditionDto);
}

