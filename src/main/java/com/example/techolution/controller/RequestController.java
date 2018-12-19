package com.example.techolution.controller;

import com.example.techolution.SatisfactionProcessor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {
    @RequestMapping(value = "/getMaximumSatisfaction", method = RequestMethod.GET)
    public int getMaximumSatisfaction()
    {
        return new SatisfactionProcessor("Data.txt").getMaxSatisfactioin();
    }
}
