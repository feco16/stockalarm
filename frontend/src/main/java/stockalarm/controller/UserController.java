package stockalarm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import stockalarm.StockClient;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final StockClient stockClient;

    @GetMapping
    public String userIndex(final Model model) {
        model.addAttribute("stocks", stockClient.getAllStocks());
        return "index";
    }

}
