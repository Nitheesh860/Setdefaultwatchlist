package com.samco.setdefaultwatchlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.redis.core.RedisTemplate;

import com.samco.setdefaultwatchlist.service.SetDefaultService;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class SetdefaultwatchlistApplication implements CommandLineRunner {

	@Autowired
	SetDefaultService setDefaultService;

	public static void main(String[] args) {
		SpringApplication.run(SetdefaultwatchlistApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// setDefaultService.setDataToRedis();
		setDefaultService.getDataFromRedis();
		// checkWatchlistGroup(watchlistModel.getUserid());
		System.out.println("final;");

	}

	@Autowired
	private RedisTemplate<String, String> redisTemplate;



}
