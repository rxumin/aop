package com.xmr.Controller;
import com.xmr.service.AiService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 调用Ai接口
 */
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AiController {

   private final AiService aiService;

   @PostMapping("/Ai")
   public String createOrder() {

      return aiService.createOrder();
   }
}
