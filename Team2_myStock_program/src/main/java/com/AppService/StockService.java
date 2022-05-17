package com.AppService;

import java.util.List;

import com.model.Stock;
import com.stockDAO.StockDAO;

public class StockService {
	private static StockDAO stockDAO;
	
	
	public static void insertStock(List<Stock> stockList,String stockCode) {
		StockDAO.insertStock(stockList,stockCode);
	}

}
