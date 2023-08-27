package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 아래의 클래스를 Controller로 등록한다.
public class CalCulatorController {

    @GetMapping("/add") // HTTP Method : GET /add
    public int addToNumbers(CalculatorAddRequest reauest) {
        return reauest.getNumber1() + reauest.getNumber2();
    }

}
