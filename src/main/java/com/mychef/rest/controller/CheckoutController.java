package com.mychef.rest.controller;

import java.math.BigDecimal;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.CreditCard;
import com.braintreegateway.Customer;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.Transaction.Status;
import com.mychef.rest.service.CheckoutService;
import com.mychef.rest.service.OrderService;
import com.braintreegateway.TransactionRequest;
import com.braintreegateway.ValidationError;

@Controller
@RequestMapping(value = "/braintree", produces = MediaType.APPLICATION_JSON_VALUE)
public class CheckoutController {

	@Value("${BT_ENVIRONMENT}")
	private String BT_ENVIRONMENT;

	@Value("${BT_MERCHANT_ID}")
	private String BT_MERCHANT_ID;

	@Value("${BT_PUBLIC_KEY}")
	private String BT_PUBLIC_KEY;

	@Value("${BT_PRIVATE_KEY}")
	private String BT_PRIVATE_KEY;

	private Logger logger = LogManager.getLogger(getClass());

	@Autowired
	private CheckoutService checkoutService;

	private static BraintreeGateway gateway;

	private Status[] TRANSACTION_SUCCESS_STATUSES = new Status[] { Transaction.Status.AUTHORIZED,
			Transaction.Status.AUTHORIZING, Transaction.Status.SETTLED, Transaction.Status.SETTLEMENT_CONFIRMED,
			Transaction.Status.SETTLEMENT_PENDING, Transaction.Status.SETTLING,
			Transaction.Status.SUBMITTED_FOR_SETTLEMENT };

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String root(Model model) {
		return "redirect:checkouts";
	}

	@RequestMapping(value = "/checkouts", method = RequestMethod.GET)
	public String checkout(Model model) {
		String clientToken = getGateway().clientToken().generate();
		model.addAttribute("clientToken", clientToken);

		return "checkouts/new";
	}

	@RequestMapping(value = "/checkouts", method = RequestMethod.POST)
	public String postForm(@RequestParam("amount") String amount, @RequestParam("payment_method_nonce") String nonce,@RequestParam("orderId") Long orderId,
			Model model, final RedirectAttributes redirectAttributes) {
		BigDecimal decimalAmount;
		try {
			decimalAmount = new BigDecimal(amount);
		} catch (NumberFormatException e) {
			redirectAttributes.addFlashAttribute("errorDetails", "Error: 81503: Amount is an invalid format.");
			return "redirect:checkouts";
		}

		if(StringUtils.isEmpty(orderId)) {
			redirectAttributes.addFlashAttribute("errorDetails", "Error: 81504: OrderId is mandatory");
			return "redirect:checkouts";
		}
		
		TransactionRequest request = new TransactionRequest().amount(decimalAmount).paymentMethodNonce(nonce).options()
				.submitForSettlement(true).done();

		Result<Transaction> result = getGateway().transaction().sale(request);

		String redirectPage = "redirect:checkouts";
		
		if (result.isSuccess()) {
			Transaction transaction = result.getTarget();
			redirectPage =  "redirect:checkouts/" + transaction.getId();
		} else if (result.getTransaction() != null) {
			Transaction transaction = result.getTransaction();
			redirectPage =  "redirect:checkouts/" + transaction.getId();
		} else {
			String errorString = "";
			for (ValidationError error : result.getErrors().getAllDeepValidationErrors()) {
				errorString += "Error: " + error.getCode() + ": " + error.getMessage() + "\n";
			}
			redirectAttributes.addFlashAttribute("errorDetails", errorString);
			redirectPage =  "redirect:checkouts";
		}
		
		//Insert in DB
		try {
			checkoutService.insertTransactionDetails(result, orderId);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorDetails", "Error: 81505: Error in inserting transaction Details.Error:"+e.getMessage());
			redirectPage =  "redirect:checkouts";
		}
		
		return redirectPage;
	}

	@RequestMapping(value = "/checkouts/{transactionId}")
	public String getTransaction(@PathVariable String transactionId, Model model) {
		Transaction transaction;
		CreditCard creditCard;
		Customer customer;

		try {
			transaction = getGateway().transaction().find(transactionId);
			creditCard = transaction.getCreditCard();
			customer = transaction.getCustomer();
		} catch (Exception e) {
			System.out.println("Exception: " + e);
			return "redirect:/checkouts";
		}

		model.addAttribute("isSuccess", Arrays.asList(TRANSACTION_SUCCESS_STATUSES).contains(transaction.getStatus()));
		model.addAttribute("transaction", transaction);
		model.addAttribute("creditCard", creditCard);
		model.addAttribute("customer", customer);

		return "checkouts/show";
	}

	private synchronized BraintreeGateway getGateway() {
		if (gateway == null) {
			gateway = new BraintreeGateway(BT_ENVIRONMENT, BT_MERCHANT_ID, BT_PUBLIC_KEY, BT_PRIVATE_KEY);
		}
		return gateway;
	}

}
