package com.samco.setdefaultwatchlist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

@Service
public class SetDefaultService {
	@Value("${redis_db.url}")
	private String redisurl;
	@Value("${GroupList}")
	private List<String> watchlistgroup;
	RedisClient redisClient = null;
	StatefulRedisConnection<String, String> redisConnection = null;
	RedisCommands<String, String> redisCommands = null;

	/*
	 * public void setDataToRedis() { try { redisClient =
	 * RedisClient.create(redisurl); redisConnection = redisClient.connect();
	 * redisCommands = redisConnection.sync();
	 * System.out.println("Redis Connection started...");
	 * 
	 * System.out.println(watchlistgroup); redisCommands.hset("Watchlist", "userid",
	 * watchlistgroup.toString()); System.out.println("Watchlist group created :::"
	 * + watchlistgroup); } catch (Exception e) {
	 * System.out.println("Failed to update the data"); } }
	 */

	public void getDataFromRedis() {
		/*
		 * Boolean result = null; Boolean result1 = null;
		 */
		try {
			redisClient = RedisClient.create(redisurl);
			redisConnection = redisClient.connect();
			redisCommands = redisConnection.sync();
			//result = redisCommands.hset("Watchlist", "userid2", watchlistgroup.toString());
			redisCommands.hset("Watchlist", "userid2", watchlistgroup.toString());
			//result1 = redisCommands.hset("Watchlist", "userid", watchlistgroup.get(1));
			System.out.println("Printing hset"+ redisCommands);
			List<String> watchlistgroup2 = watchlistgroup;
			String hget = redisCommands.hget("Watchlist", "userid");
			//System.out.println("DefaultGroup::::::" + redisCommands.hget("Watchlist", "userid"));
			System.out.println("hget:::"+hget);
			if(hget!= null && !hget.isEmpty()) {
				String input = hget;
				input = input.replaceAll("\\[|\\]", ""); // Remove brackets from the string
				String[] strArr = input.split(","); // split the string by comma delimiter
				String firstElementDefault = strArr[0].trim(); // get the first element of the array and remove any whitespace around it
				System.out.println(firstElementDefault);
			}
			else {
				System.out.println("No Data");
			}

		} catch (Exception e) {
			System.out.println("Can't get the data");
		}

	}

}