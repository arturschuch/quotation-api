package quotation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import quotation.exception.IllegalDateException;
import quotation.exception.IllegalValueException;
import quotation.exception.NoExchangeRateForThisDateException;
import quotation.exception.NonexistentCurrencyException;
import quotation.model.Currency;
import quotation.service.CashService;
import quotation.utils.DateUtils;

public class Quotation {

	private CashService cashService;
	
	public Quotation() {
		this.cashService = new CashService();
	}
	
	public BigDecimal currencyQuotation(String from, String to, Number value, String quotation) throws IllegalValueException, IllegalDateException, NoExchangeRateForThisDateException, NonexistentCurrencyException {
		if (value.intValue() < 0) {
			throw new IllegalValueException();
		}
		
		Date date = DateUtils.getDate(quotation);
		quotation = changeQuotation(date);
		
		List<Currency> currencies = cashService.getCurrencies(quotation);
		Currency fromCurrency = getCurrency(currencies, from);
		Currency toCurrency = getCurrency(currencies, to);
		
		BigDecimal result = fromCurrency.getSellingRate().divide(toCurrency.getSellingRate(), RoundingMode.CEILING);
		return result.multiply(new BigDecimal(value.longValue())).setScale(2, RoundingMode.CEILING);
	}


	private String changeQuotation(Date date) {
		return DateUtils.getStringDate(DateUtils.getDateWithDayOfWeek(date));
	}
	
	private Currency getCurrency(List<Currency> currencies, String currencyName) throws NonexistentCurrencyException {
		for (Currency currency : currencies) {
			if (currency.getName().equals(currencyName)) {
				return currency;
			}
		}
		throw new NonexistentCurrencyException("name: " + currencyName);
	}
}
