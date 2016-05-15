package quotation.service;

import java.util.HashMap;
import java.util.List;

import quotation.exception.NoExchangeRateForThisDateException;
import quotation.model.Currency;

public class CashService {

	private HashMap<String, List<Currency>> cashCurrencies;
	private ExchangeRateService exchangeRateService;
		
	public CashService() {
		this.cashCurrencies = new HashMap<String, List<Currency>>();
		this.exchangeRateService = new ExchangeRateService();
	}
	
	public List<Currency> getCurrencies(String quotation) throws NoExchangeRateForThisDateException {
		List<Currency> currencies = this.getCurrenciesOfMap(quotation);
		if (currencies == null) {
			currencies = exchangeRateService.getCurrencies(quotation);
			if (currencies == null) {
				throw new NoExchangeRateForThisDateException();
			}
			this.addCurrencies(quotation, currencies);
		}
		return currencies;
	}
	
	private void addCurrencies(String key, List<Currency> currencies) {
		cashCurrencies.put(key, currencies);
	}

	private List<Currency> getCurrenciesOfMap(String key){
		return cashCurrencies.get(key);
	}
	
	
}
