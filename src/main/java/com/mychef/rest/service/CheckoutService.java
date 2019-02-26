package com.mychef.rest.service;

import com.braintreegateway.Result;
import com.braintreegateway.Transaction;

public interface CheckoutService {

	void insertTransactionDetails(Result<Transaction> result, Long orderId) throws Exception;
	
}
