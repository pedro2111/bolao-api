package br.com.alura.bolao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableScheduling
public class CacheController {
	private static final String TIME_ZONE = "America/Sao_Paulo";
	
	@Autowired
	private CacheManager cachemanager;
	
	@RequestMapping("clearCache")
	@Scheduled(cron = "0 0 1 * * ?", zone = TIME_ZONE)
	public void clearCache() {
		
		
		for(String name:cachemanager.getCacheNames()) {
			cachemanager.getCache(name).clear();
		}
		
	}

}
