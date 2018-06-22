package com.example.demo.domain.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.seasar.doma.boot.Pageables;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    public List<SalesSummaryDto> execute(SalesSummaryConditionDto salesSummaryConditionDto, SelectOptions options) {

    	//TODO:shopseqをセッションに持たせる
    	Integer shopSeq = 1001;

        return orderGoodsDao.getSalesSummaryDtoList(shopSeq, salesSummaryConditionDto, options);
    }


    /**
     * 売上集計検索サービス
     *
     * @param salesSummaryConditionDto 検索条件
     * @return 検索結果
     */
    @Override
    public Page<SalesSummaryDto> execute(SalesSummaryConditionDto salesSummaryConditionDto, Pageable pageable) {

    	//TODO:shopseqをセッションに持たせる
    	Integer shopSeq = 1001;

    	SelectOptions options = Pageables.toSelectOptions(pageable).count();
    	List<SalesSummaryDto> salesSummaryDtoList = orderGoodsDao.findAll(shopSeq, salesSummaryConditionDto, options, toList());

    	return new PageImpl<>(salesSummaryDtoList,pageable,options.getCount());
    }

}

