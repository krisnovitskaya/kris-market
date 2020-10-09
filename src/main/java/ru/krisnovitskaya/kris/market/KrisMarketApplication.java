package ru.krisnovitskaya.kris.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KrisMarketApplication {
	// Домашнее задание:
	// 1. Добавьте отображение заказов только текущего пользователя (на странице orders)
	// * 2. Попробуйте реализовать регистрацию пользователей
	// Работаем пока с thymeleaf в ДЗ
	public static void main(String[] args) {
		SpringApplication.run(KrisMarketApplication.class, args);
	}

}
