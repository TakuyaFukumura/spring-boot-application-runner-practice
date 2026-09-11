package com.example.applicationrunner.presentation;

import com.example.applicationrunner.application.OrderCommandService.CommandResult;
import org.springframework.stereotype.Component;

@Component
public class OrderOutput {
    public void print(CommandResult result) {
        if (result.registered() != null) {
            var order = result.registered();
            System.out.printf("登録しました: orderId=%s, product=%s, quantity=%d%n",
                    order.orderId(), order.product(), order.quantity());
            return;
        }
        if (result.listed().isEmpty()) {
            System.out.println("注文はありません。");
            return;
        }
        result.listed().forEach(order -> System.out.printf("orderId=%s, product=%s, quantity=%d%n",
                order.orderId(), order.product(), order.quantity()));
    }
}
