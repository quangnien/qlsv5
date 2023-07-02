package com.qlsv5.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class UpdatePasswordFactory {
    private Map<String, StrategyUpdatePassword> strategies;

    @Autowired
    public UpdatePasswordFactory(Set<StrategyUpdatePassword> strategies) {
        createStrategyUpdatePW(strategies);
    }

    private void createStrategyUpdatePW(Set<StrategyUpdatePassword> strategies){
        this.strategies = new HashMap<>();
        strategies.forEach(strategy -> this.strategies.put(strategy.getRoleName(), strategy));
    }

    public StrategyUpdatePassword createStrategyUpdatePW(String userRole){
        return strategies.get(userRole);
    }
}
