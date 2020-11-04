package ru.krisnovitskaya.kris.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KrisMarketApplication {
	// Домашнее задание:
	// 1. Личный кабинет пользователя с отображением профиля (имя, фамилия, телефон, email, год рождения, пол, город проживания)
	// 2. Дать возможность изменять профиль (с подтверждением паролем)

	// Планы на следующие занятия:
	// - Вернуться к вопросу об изменении цены товара перед оформлением заказа
	public static void main(String[] args) {
		SpringApplication.run(KrisMarketApplication.class, args);
	}

}
