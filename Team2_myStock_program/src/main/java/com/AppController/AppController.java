package com.AppController;

import java.util.List;

import com.AppService.StockService;
import com.model.Stock;
import com.stockDAO.StockParser;
import com.utils.DBUtils;

public class AppController {
	private StockParser stockParser;
	private StockService stockService;

	
	
	public static void insertStock(String stockCode) {
		System.out.println(stockCode);
		DBUtils.dropAndCreateTable();
		List<Stock> stockList = StockParser.stockParser(stockCode);
		System.out.println(stockList);
		StockService.insertStock(stockList,stockCode);
		
	
		
	}



	public static void signUser() {
		
		// TODO Auto-generated method stub
		
	}

}
