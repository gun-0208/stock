package com.stockDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.model.Stock;
import com.utils.DBUtils;

public class StockDAO {
	private static PreparedStatement preparedStatement;
	
	public static void insertStock(List<Stock> stockList,String stockCode) {
		String insertQuery = "INSERT INTO stock (id,date,close,diff,open,high,low,volume) VALUES (?,?,?,?,?,?,?,?)";
		for (Stock stock : stockList) {				
			try (
					Connection connection = DBUtils.getConnection();
					PreparedStatement preparedStatement = createPreparedStatement(connection,insertQuery,stock,stockCode);
					)
			{	preparedStatement.executeUpdate();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static PreparedStatement createPreparedStatement(Connection connection, String sql, Stock stock,String stockCode) throws SQLException {
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, stockCode);
		preparedStatement.setString(2, stock.getDate().toString());
		preparedStatement.setInt(3, stock.getClose());
		preparedStatement.setInt(4,stock.getDiff());
		preparedStatement.setInt(5,stock.getOpen());
		preparedStatement.setInt(6,stock.getHigh());
		preparedStatement.setInt(7,stock.getLow());
		preparedStatement.setInt(8,stock.getVolume());
		
		return preparedStatement;
	}
}
