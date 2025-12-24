package com.daniella.eportfolio.api;

import com.daniella.eportfolio.domain.Investment;
import com.daniella.eportfolio.domain.Portfolio;
import com.daniella.eportfolio.domain.Stock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PortfolioController {

    private final Portfolio portfolio = new Portfolio();
    private final List<GoalRequest> goals = new ArrayList<>();
    private int goalIdCounter = 1;

    @GetMapping("/portfolio")
    public Portfolio getPortfolio() {
        return portfolio;
    }

    @PostMapping("/buy")
    public String buy(@RequestBody BuyRequest request) {
        Stock stock = new Stock(
            request.getSymbol(),
            request.getName(),
            request.getQuantity(),
            request.getPrice(),
            false
        );

        portfolio.buyInvestment(stock);
        return "Bought " + stock.getSymbol();
    }

    @PostMapping("/sell")
    public String sell(@RequestBody SellRequest request) {
        double result = portfolio.sellInvestment(
            request.getSymbol(),
            request.getQuantity(),
            request.getPrice()
        );

        if (result < 0) {
            return "Sell failed";
        }

        return "Sold " + request.getQuantity() + " of " + request.getSymbol();
    }

    @PostMapping("/update-price")
    public void updatePrice(@RequestBody UpdatePriceRequest req) {
        portfolio.updatePrice(req.getSymbol(), req.getPrice());
    }

    
    @PostMapping("/search")
    public ArrayList<Investment> search(@RequestBody SearchRequest req) {
        return portfolio.search(
            req.getSymbol(),
            req.getKeywords(),
            req.getPriceRange()
        );
    }

    @GetMapping("/goals")
    public List<GoalRequest> getGoals() {
        return goals;
    }

    @PostMapping("/goals")
    public GoalRequest addGoal(@RequestBody GoalRequest goal) {
        goal.setId(goalIdCounter++);
        goals.add(goal);
        return goal;
    }

    @PutMapping("/goals/{id}")
    public GoalRequest updateGoal(@PathVariable int id, @RequestBody GoalRequest updated) {
        for (int i = 0; i < goals.size(); i++) {
            if (goals.get(i).getId() == id) {
                updated.setId(id);
                goals.set(i, updated);
                return updated;
            }
        }
        return null;
    }



    @DeleteMapping("/goals/{id}")
    public void deleteGoal(@PathVariable int id) {
        goals.removeIf(g -> g.getId() == id);
    }


}
