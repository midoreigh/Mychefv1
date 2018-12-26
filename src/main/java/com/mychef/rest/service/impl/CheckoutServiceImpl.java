package com.mychef.rest.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.ValidationError;
import com.mychef.rest.entity.Order;
import com.mychef.rest.repository.OrderRepository;
import com.mychef.rest.repository.TransactionRepository;
import com.mychef.rest.service.CheckoutService;

@Service
public class CheckoutServiceImpl implements CheckoutService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public void insertTransactionDetails(Result<Transaction> result, Long orderId) throws Exception {

		Optional<Order> orderOptional = this.orderRepository.findById(orderId);
		if (!orderOptional.isPresent()) {
			throw new Exception("Order doesn't exist with id " + orderId);
		}
		
		com.mychef.rest.entity.Transaction transaction = new com.mychef.rest.entity.Transaction();
		transaction.setSuccess(result.isSuccess());
		transaction.setOrderId(orderId);
		
		Transaction transactionFromBrainTree = null;
		if (result.isSuccess()) {
			transactionFromBrainTree = result.getTarget();
		} else if(result.getTransaction()!=null) {
			transactionFromBrainTree = result.getTransaction();
		}
		
		if(transactionFromBrainTree!=null) {
			transaction.setTransactionId(transactionFromBrainTree.getId());
			transaction.setTransactionStatus(transactionFromBrainTree.getStatus().toString());
			transaction.setTransactionType(transactionFromBrainTree.getType().toString());
			transaction.setAmount(String.valueOf(transactionFromBrainTree.getAmount().doubleValue()));
		} else {
			transaction.setTransactionId("-"+orderId);
		}
		
		if (result.getErrors() != null && !result.getErrors().getAllDeepValidationErrors().isEmpty()) {
			String errorString = "";
			for (ValidationError error : result.getErrors().getAllDeepValidationErrors()) {
				errorString += "Error: " + error.getCode() + ": " + error.getMessage() + "\n";
			}
			transaction.setErrorMessage(errorString);
		}
		
		
		this.transactionRepository.save(transaction);
		
	}

}
