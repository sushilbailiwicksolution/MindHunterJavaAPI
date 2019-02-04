package com.bailiwick.game_service.configuration;

import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;

@Configuration
public class AppConfig {

	public Gson gson() {
		return new Gson();
	}
}
