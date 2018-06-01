package com.example.demo.domain.service.impl;

import java.util.List;

import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.dao.OrderGoodsDao;
import com.example.demo.domain.dto.SalesSummaryConditionDto;
import com.example.demo.domain.entity.SalesSummaryDto;
import com.example.demo.domain.service.SalesSummaryGetService;


/**
 * 売上集計検索サービス
 *
 * @author ando-ss
 * @version $Revision:$
 *
 */
@Service
public class SalesSummaryGetServiceImpl implements SalesSummaryGetService {

	@Autowired
	OrderGoodsDao orderGoodsDao;

    /**
     * 売上集計検索サービス
     *
     * @param salesSummaryConditionDto 検索条件
     * @return 検索結果
     */
    @Override
    public List<SalesSummaryDto> execute(SalesSummaryConditionDto salesSummaryConditionDto) {

    	//TODO:shopseqをセッションに持たせる
    	Integer shopSeq = 1001;

        return orderGoodsDao.getSalesSummaryDtoList(shopSeq, salesSummaryConditionDto);
    }


}

