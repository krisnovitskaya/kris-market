package ru.krisnovitskaya.kris.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KrisMarketApplication {
	// Домашнее задание:
	// 1. Добавьте категории для продуктов
	// 2. Сделайте на фронтенде фильтр по категориям (выпадающий список с категориями)
	// 3. * Вместо пункта 2 сделайте фильтр по нескольким категориям (1+)

	// План на следующее занятие:
	// 1. Токены
	// 2. Валидация
	// 3. Оформление заказов
	public static void main(String[] args) {
		SpringApplication.run(KrisMarketApplication.class, args);
	}

}
