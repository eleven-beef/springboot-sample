SELECT *
FROM (
	SELECT
		C.goodsseq
		, G.goodsgroupseq
		, C.pricetype
		, C.goodsname
		, C.unitvalue1
		, C.unitvalue2
		, C.fixedprice
		, C.goodsprice
		, C.goodscode
		, C.makerstocknumber AS catalogcode
		, SUM(CASE
			WHEN C.totalingtype = '0' AND C.salesflag = '0' THEN C.goodscount
			WHEN C.totalingtype = '2' AND C.pregoodscount < C.goodscount THEN C.goodscount - C.pregoodscount
			ELSE 0
		END) AS orderCount
		, SUM(CASE
			WHEN C.totalingtype = '2' AND C.salesflag = '0' AND C.goodscount < C.pregoodscount THEN C.pregoodscount - C.goodscount
			WHEN C.totalingtype = '9' AND C.salesflag = '0' AND C.goodscount < C.pregoodscount THEN C.pregoodscount - C.goodscount
			ELSE 0
		END) AS cancelCount
		, SUM(CASE
			WHEN C.totalingtype = '1' AND C.salesflag = '1' THEN C.goodscount
			WHEN C.totalingtype = '2' AND C.salesflag = '1' AND C.pregoodscount < C.goodscount THEN C.goodscount - C.pregoodscount
			ELSE 0
		END) AS shippingCount
		, SUM(CASE
			WHEN C.totalingtype = '2' AND C.salesflag = '1' AND C.goodscount < C.pregoodscount THEN C.pregoodscount - C.goodscount
			WHEN C.totalingtype = '9' AND C.salesflag = '1' AND C.goodscount < C.pregoodscount THEN C.pregoodscount - C.goodscount
			ELSE 0
		END) AS returnedCount
		, CASE
			WHEN G.stockmanagementflag = '1' THEN E.realstock - E.orderreservestock - F.safetystock
			ELSE NULL
			END AS stockCount
		, G.salestarttimepc
		, G.saleendtimepc
		, G.salestarttimemb
		, G.saleendtimemb
		, G.salestatuspc
		, G.salestatusmb
		, (SELECT COUNT(*) FROM informationmail H WHERE H.goodsseq = C.goodsseq AND H.informationmailsendstatus = '0') AS mailCount
		, CURRENT_TIMESTAMP AS currentTimestamp
		, '0' as orderShopType

	FROM ordergoods C
	/*%if (conditionDto.brand != null && !conditionDto.brand.equals("")) */
	INNER JOIN goodsgroup D
		ON D.goodsgroupcode = C.goodsgroupcode
		AND D.shopseq = C.shopseq
		AND D.brand = /*conditionDto.brand*/'test'
	/*%end*/
	INNER JOIN stock E
		ON E.goodsseq = C.goodsseq
		AND E.shopseq = C.shopseq
	INNER JOIN stocksetting F
		ON F.goodsseq = C.goodsseq
		AND F.shopseq = C.shopseq
	INNER JOIN goods G
		ON G.goodsseq = C.goodsseq
		AND G.shopseq = C.shopseq
	WHERE
		C.shopseq = /*shopSeq*/'1001'
		AND /*conditionDto.saleTimeFrom*/'2018/01/01 00:00:00' <= C.processtime AND C.processtime < /*conditionDto.saleTimeTo*/'2018/01/01 00:00:00'
	/*%if conditionDto.goodsCode != null */
		AND C.goodscode like '%' || /*conditionDto.goodsCode*/'test' || '%'
	/*%end*/
	/*%if conditionDto.catalogCode != null */
		AND C.makerstocknumber like '%' || /*conditionDto.catalogCode*/'test' || '%'
	/*%end*/
	/*%if conditionDto.goodsName != null */
		AND C.goodsname like '%' || /*conditionDto.goodsName*/'test' || '%'
	/*%end*/
	GROUP BY
		C.goodsseq
		, G.goodsgroupseq
		, C.pricetype
		, C.goodsname
		, C.unitvalue1
		, C.unitvalue2
		, C.fixedprice
		, C.goodsprice
		, C.goodscode
		, C.makerstocknumber
		, E.realstock
		, E.orderreservestock
		, F.safetystock
		, G.salestarttimepc
		, G.saleendtimepc
		, G.salestarttimemb
		, G.saleendtimemb
		, G.salestatuspc
		, G.salestatusmb
		, G.stockmanagementflag

	) AS OG
ORDER BY
    /*%if (conditionDto.orderField == null || conditionDto.orderField.equals("") )*/
    goodsname, goodsseq
    /*%end*/
    /*%if "goodsName".equals(conditionDto.orderField) */
    goodsname /*%if conditionDto.orderAsc*/ ASC /*%else*/ desc /*%end*/
    /*%end*/
    /*%if "goodsCode".equals(conditionDto.orderField)*/
    goodscode /*%if conditionDto.orderAsc*/ASC /*%else*/ desc/*%end*/
    /*%end*/
    /*%if "catalogCode".equals(conditionDto.orderField)*/
    catalogcode /*%if conditionDto.orderAsc*/ASC /*%else*/ desc/*%end*/
    /*%end*/
    /*%if "goodsPrice".equals(conditionDto.orderField)*/
    goodsprice /*%if conditionDto.orderAsc*/ASC /*%else*/ desc/*%end*/
    /*%end*/
    /*%if "fixedPrice".equals(conditionDto.orderField)*/
    fixedprice /*%if conditionDto.orderAsc*/ASC /*%else*/ desc/*%end*/
    /*%end*/
    /*%if "stockCount".equals(conditionDto.orderField)*/
    stockCount /*%if conditionDto.orderAsc*/ASC /*%else*/ desc/*%end*/
    /*%end*/
    /*%if "orderCount".equals(conditionDto.orderField)*/
    orderCount /*%if conditionDto.orderAsc*/ASC /*%else*/ desc/*%end*/
    /*%end*/
    /*%if "orderPrice".equals(conditionDto.orderField)*/
    orderCount*goodsprice /*%if conditionDto.orderAsc*/ASC /*%else*/ desc/*%end*/
    /*%end*/
    /*%if "cancelCount".equals(conditionDto.orderField)*/
    cancelcount /*%if conditionDto.orderAsc*/ASC /*%else*/ desc/*%end*/
    /*%end*/
    /*%if "cancelPrice".equals(conditionDto.orderField)*/
    cancelcount*goodsprice /*%if conditionDto.orderAsc*/ASC /*%else*/ desc/*%end*/
    /*%end*/
    /*%if "shippingCount".equals(conditionDto.orderField)*/
    shippingcount /*%if conditionDto.orderAsc*/ASC /*%else*/ desc/*%end*/
    /*%end*/
    /*%if "shippingPrice".equals(conditionDto.orderField)*/
    shippingcount*goodsprice /*%if conditionDto.orderAsc*/ASC /*%else*/ desc/*%end*/
    /*%end*/
    /*%if "returnedCount".equals(conditionDto.orderField)*/
    returnedcount /*%if conditionDto.orderAsc*/ASC /*%else*/ desc/*%end*/
    /*%end*/
    /*%if "returnedPrice".equals(conditionDto.orderField)*/
    returnedcount*goodsprice /*%if conditionDto.orderAsc*/ASC /*%else*/ desc/*%end*/
    /*%end*/
    /*%if "salesCount".equals(conditionDto.orderField)*/
    salescount /*%if conditionDto.orderAsc*/ASC /*%else*/ desc/*%end*/
    /*%end*/
    /*%if "salesPrice".equals(conditionDto.orderField)*/
    salescountz*goodsprice /*%if conditionDto.orderAsc*/ASC /*%else*/ desc/*%end*/
    /*%end*/
    /*%if "saleStartTimePC".equals(conditionDto.orderField)*/
    salestarttimepc /*%if conditionDto.orderAsc*/ASC /*%else*/ desc/*%end*/
    /*%end*/
    /*%if "saleEndTimePC".equals(conditionDto.orderField)*/
    saleendtimepc /*%if conditionDto.orderAsc*/ASC /*%else*/ desc/*%end*/
    /*%end*/
    /*%if "saleStatusPC".equals(conditionDto.orderField)*/
    salestatuspc /*%if conditionDto.orderAsc*/ASC /*%else*/ desc/*%end*/
    /*%end*/
    /*%if "mailCount".equals(conditionDto.orderField)*/
    mailCount /*%if conditionDto.orderAsc*/ASC /*%else*/ desc/*%end*/
    /*%end*/