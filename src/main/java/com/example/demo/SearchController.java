package com.example.demo;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

@Controller
public class SearchController {
//MEMO:@Controllerと@RestControllerの違い：https://qiita.com/tag1216/items/3680b92cf96eb5a170f0

  @Autowired
  ServerInfoDao dao;

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
  public String getSales(@ModelAttribute("form") SalesSummaryConditionDto form, Model model) {
	  log.debug("sales");
      return "sales";
  }

  @RequestMapping(value = "/sales", method = { RequestMethod.POST })
  public ModelAndView getSalesSummary(@ModelAttribute("form") @Valid SalesSummaryConditionDto from,  BindingResult result, ModelAndView mav) {
	  // TODO: mapに詰めるとその時点で必須チェックがかかるもよう　required = false　が設定できないため、別の方法にする→直接Formに受けとるか
	  // MEMO:http://ziqoo.com/wiki/index.php?Spring%20Boot%20%C6%FE%CE%CF%A5%C1%A5%A7%A5%C3%A5%AF#a1ae7818

	  // TODO:ログが出力されていないので対応する
	  if (result.hasErrors()) {
	        for(FieldError err: result.getFieldErrors()) {
	            log.debug("error code = [" + err.getCode() + "]");
	        }
	    }
	  mav.addObject("serverInfoList", "");
      mav.setViewName("sales");
      return mav;
  }

  private ObjectMapper getObjectMapper() {
      return new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
  }

  private void validate(Object form) {
      ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
      Validator validator = validatorFactory.getValidator();
      Set<ConstraintViolation<Object>> violations = validator.validate(form);
      if (!violations.isEmpty()) {
          // バリデーションエラーのときの処理
      }
  }






}
