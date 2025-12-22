package com.daniella.eportfolio.api;

import com.daniella.eportfolio.domain.Portfolio;
import com.daniella.eportfolio.domain.Stock;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PortfolioController {

    private final Portfolio portfolio = new Portfolio();

    @GetMapping("/portfolio")
    public Portfolio getPortfolio() {
        return portfolio;
    }

    @PostMapping("/buy")
    public String buy(@RequestBody Stock stock) {
        portfolio.buyInvestment(stock);
        return "Bought " + stock.getSymbol();
    }
}
