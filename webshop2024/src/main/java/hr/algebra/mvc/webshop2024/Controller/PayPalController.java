package hr.algebra.mvc.webshop2024.Controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import hr.algebra.mvc.webshop2024.BL.Service.PayPalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

//https://medium.com/@lsampath210/paypal-integration-for-spring-boot-backend-243e71c89a74

@Controller
@RequestMapping("webShop")
public class PayPalController {
    @Autowired
    private PayPalService payPalService;

    @GetMapping("/paypal/pay")
    public String pay() {
        return "paypal/pay";
    }

    @PostMapping("/paypal/createPayment")
    public String createPayment(@RequestParam("totalAmount") BigDecimal totalAmount) {
        //need order details
        try {
            Payment payment = payPalService.createPayment(
                    totalAmount.doubleValue(),
                    "USD",
                    "paypal",
                    "sale",
                    "Payment from JWP AV",
                    "http://localhost:8080/webShop/products/list-products",
                    "http://localhost:8080/webShop/products/list-products");

            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    return "redirect:"+link.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("/cancel")
    public String cancelPay() {
        return "products/list-products";
    }

    @GetMapping("/success")
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        Payment payment = payPalService.executePayment(paymentId, payerId);
        if (payment.getState().equals("approved")) {
            return "products/list-products";
        }
        return "redirect:/";
    }
}
