package com.example.applicationrunner.application.dto;

public sealed interface OrderCommand permits RegisterOrderCommand, ListOrdersCommand {
}
