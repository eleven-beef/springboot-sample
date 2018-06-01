package com.example.demo;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.seasar.doma.jdbc.SelectOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.dto.SalesSummaryConditionDto;
import com.example.demo.domain.entity.SalesSummaryDto;
import com.example.demo.domain.form.SalesForm;
import com.example.demo.domain.service.SalesSummaryGetService;

@Controller
public class SearchController {
//MEMO:@Controllerと@RestControllerの違い：https://qiita.com/tag1216/items/3680b92cf96eb5a170f0

  @Autowired
  ServerInfoDao dao;

  @Autowired
  SalesSummaryGetService salesSummaryGetService;

  @Autowired
  private ModelMapper modelMapper;

  private static final Logger log = LoggerFactory.getLogger(SearchController.class);


  @RequestMapping("/")
  public List<ServerInfo> selectAll() {
	  log.debug("selectAll");
      return dao.selectAll();
  }


  @RequestMapping("/{serverName}/")
  public ModelAndView getServerList(ModelAndView mav, @PathVariable(value="serverName") String serverName) {
	  List<ServerInfo> serverInfoList = dao.selectByServerName(serverName);
	  mav.addObject("serverInfoList", serverInfoList);
      mav.setViewName("index");
      return mav;
  }


  @RequestMapping(value = "/sales" , method = { RequestMethod.GET })
  public String getSales(@ModelAttribute("form") SalesForm form, Model model) {
	  log.debug("sales");
      return "sales";
  }

  @RequestMapping(value = "/sales", method = { RequestMethod.POST })
  public ModelAndView getSalesSummary(@ModelAttribute("form") @Valid SalesForm from,  BindingResult result, ModelAndView mav) {
	  // MEMO:
	  // http://ziqoo.com/wiki/index.php?Spring%20Boot%20%C6%FE%CE%CF%A5%C1%A5%A7%A5%C3%A5%AF#a1ae7818
	  // @ValidでFromに対してバリデーションチェック実行→結果をresultへ


	  // TODO:ログが出力されていないので対応する
	  if (result.hasErrors()) {
	        for(FieldError err: result.getFieldErrors()) {
	            log.debug("error code = [" + err.getCode() + "]");
	        }	
	      mav.setViewName("sales");
	      return mav;
	    }

	  // ModelMapperのコンバータ追加例
	  Converter<String[], String> stringConverter = new AbstractConverter<String[], String>() {
		    @Override
		    protected String convert(String[] source) {
		        return (source != null && source.length > 0) ? source[0] : "";
		    }
		};
		// modelmapperに転換ルールを追加する。
		modelMapper.addConverter(stringConverter);



	  // ModelMapperでFromから移し替える
      // TODO:LocalDate→Timestampの変換必要
	  SalesSummaryConditionDto salesSummaryConditionDto = modelMapper.map(from, SalesSummaryConditionDto.class);


	  salesSummaryConditionDto.options = SelectOptions.get().offset(5).limit(10);
	  List<SalesSummaryDto> salesSummaryDtoList = salesSummaryGetService.execute(salesSummaryConditionDto);

	  mav.addObject("salesSummaryDtoList", salesSummaryDtoList);
      mav.setViewName("sales");
      return mav;
  }



}
