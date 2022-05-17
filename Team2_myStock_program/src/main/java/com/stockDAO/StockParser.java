package com.stockDAO;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.model.Stock;

public class StockParser {
	
	public static List<Stock> stockParser(String stockCode) {
		String URL = String.format("https://finance.naver.com/item/sise_day.naver?code=%s&page=1", stockCode);
//		String URL = "https://finance.naver.com/item/sise_day.naver?code=005930&page=1";
		Document doc = null;
		
		List<Stock> stockList = new ArrayList<>();
		
		try {
			doc = Jsoup.connect(URL).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String[] items = new String[] {"날짜","종가","전일비","시가","고가","저가","거래량"};
		Elements rows = doc.getElementsByAttributeValue("onmouseover", "mouseOver(this)");
		
		for (Element row : rows) {
			Iterator<Element> iterElem = row.getElementsByTag("td").iterator();
			StringBuilder builder = new StringBuilder();
			for (String item:items) {
				builder.append(iterElem.next().text().replace(",","")+"\t");
			}
			
			String[] arr = builder.toString().split("\t");
			Stock stock = new Stock.Builder().date(LocalDate.parse(arr[0],DateTimeFormatter.ofPattern("yyyy.MM.dd")))
					.close(Integer.parseInt(arr[1]))
					.diff(Integer.parseInt(arr[2]))
					.open(Integer.parseInt(arr[3]))
					.high(Integer.parseInt(arr[4]))
					.low(Integer.parseInt(arr[5]))
					.volume(Integer.parseInt(arr[6])).build();
//		System.out.println(stock.toString());
			stockList.add(stock);
		}
//		System.out.println(stockList);
		return stockList;
	}
}
