package atm.atm.service;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class AtmService {
    
    //local storage to hold case
    private LinkedHashMap<Integer,Integer> cashVault = new LinkedHashMap<Integer,Integer>();

    //change this numbers to increase or decrease cash on initialization
    @PostConstruct
    public void init() {
        cashVault.put(100, 2);
        cashVault.put(50,2);
        cashVault.put(20,2);
        cashVault.put(10,2);
        cashVault.put(5,2);
    }

    public int availableCash(){
        int totalCashAvailable = 0;
        for(int denomination:cashVault.keySet()){
            totalCashAvailable = totalCashAvailable + (denomination*cashVault.get(denomination));
        }
        return totalCashAvailable;
    }

    //dispense cash on request. throw error if condition not met
    public HashMap<Integer,Integer>  dispense(int amountToDispense) throws Exception{
        HashMap<Integer,Integer> dispenseMap = new HashMap<Integer, Integer>();
		
		int dispenseDenomination = 0;
        int totalAmount = 0;
        int amount = amountToDispense;
		for (Integer denomination:  cashVault.keySet()) {
			if (denomination < amount || denomination == amount) {
                dispenseDenomination = denomination * cashVault.get(denomination) >= (amount) ? amount / denomination : (cashVault.get(denomination));
                amount = amount - (denomination *dispenseDenomination);
                dispenseMap.put(denomination,dispenseDenomination);
                totalAmount = totalAmount + (denomination*dispenseDenomination);
			}
            
    	}
        
        if(availableCash() < amountToDispense){
            throw new Exception("Unable to dispense amount for the request amount $"+amountToDispense+". Please request refill amount");
        }
        else  if(totalAmount < amountToDispense){
            throw new Exception("Unable to dispense amount for the request amount $"+amountToDispense+". Accepted denomination in $100,$50,$20,$10,$5");
        }
        if(totalAmount == amountToDispense){
            for(int denomination:dispenseMap.keySet()){
                cashVault.put(denomination, cashVault.get(denomination)-dispenseMap.get(denomination));
            }
        }
         
		return dispenseMap;
    }
}
