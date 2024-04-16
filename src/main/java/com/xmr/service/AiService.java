package com.xmr.service;

import com.xmr.annotation.ServiceSwitch;
import com.xmr.constant.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AiService {

   /**
    * 调用AI服务
    */
   @ServiceSwitch(switchKey = Constant.ConfigCode.AI_Service_SWITCH)
   public String createOrder() {

      // 具体业务逻辑省略....

      return "调用AI服务成功";
   }
}
