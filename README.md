# quotation-api

It was creadted the fucntion currencyQuotation that return the quotation of "from" to "to" 

```
public BigDecimal currencyQuotation(String from, String to, Number value, String quotationDate);
```

#### Parameters: 

_from: String with the currency name (example "USD") you want to convert;_

_to: String with the currency name (example "EUR") you want to see the result;_

_value: The value that should be converted. The currency of this value will be expressed in the “from” parameter;_

_quotationDate: A date as String in the format “dd/MM/yyyy”;_

#### Respecting the rules:

! You shall not work with non-native classes / libraries;

! If the from or to parameters are not valid, an exception must be thrown;

! If the value is smaller than zero, an exception must be thrown;

! For non-working days (Saturday and Sunday, ignoring holidays) takes the quotation from the

immediately preceding business day. If the quotation of the previous day is not available, an

exception must be thrown;

! If the quotation date is not available, an exception must be thrown;

! The data source used will be the Brazilian central bank CSV file available at:

http://www4.bcb.gov.br/pec/taxas/batch/cotacaomoedas.asp?id=txtodas

! The return value should be rounded to two decimal places.

! You must convert the currency through rate "Taxa Compra".
