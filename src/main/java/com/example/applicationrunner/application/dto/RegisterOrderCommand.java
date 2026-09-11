package com.example.applicationrunner.application.dto;

public record RegisterOrderCommand(String orderId, String product, int quantity) implements OrderCommand {
}
