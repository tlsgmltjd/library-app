package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorMultiplyRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // 아래의 클래스를 Controller로 등록한다.
public class CalCulatorController {

    @GetMapping("/add") // HTTP Method : GET /add
    public int addTwoNumbers(CalculatorAddRequest reauest) {
        return reauest.getNumber1() + reauest.getNumber2();
    }

    @PostMapping("/multiply") // HTTP Method : POST /multiply
    public int multiplyTwoNumbers(@RequestBody CalculatorMultiplyRequest request) {
        return request.getNumber1() * request.getNumber2();
    }
}
