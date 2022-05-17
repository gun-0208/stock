package com.userService;



import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import com.model.SignUserDTO;
import com.utils.DBUtils;



public class UserDAO {

	

	//1.insert

	public int insertData(SignUserDTO dto) throws SQLException{

		

		int result = 0;

		

		Connection conn = DBUtils.getConnection();

		PreparedStatement pstmt = null;

		String sql;

		

		try {

			sql = "insert into naverMember (id,pw,name,gender,birth,email,tel) ";

			sql+= "values (?,?,?,?,?,?,?)";

			

			pstmt = conn.prepareStatement(sql);

			

			pstmt.setString(1, dto.getId());

			pstmt.setString(2, dto.getPw());

			pstmt.setString(3, dto.getName());

			pstmt.setString(4, dto.getGender());

			pstmt.setString(5, dto.getBirth());

			pstmt.setString(6, dto.getEmail());

			pstmt.setString(7, dto.getTel());

			

			result = pstmt.executeUpdate();

			

			pstmt.close();

			

		} catch (Exception e) {

			System.out.println(e.toString());

		}

		

		return result;

		

	}

	



	//2.update

	public int updateData(SignUserDTO dto) {

		

		int result = 0;

		


		PreparedStatement pstmt = null;

		String sql;

		

		try {

			Connection conn = DBUtils.getConnection();
			sql = "update naverMember set pw=?, email=?, tel=? ";

			sql+= "where id=?";

			

			pstmt = conn.prepareStatement(sql);

			

			pstmt.setString(1, dto.getPw());

			pstmt.setString(2, dto.getEmail());

			pstmt.setString(3, dto.getTel());

			pstmt.setString(4, dto.getId());

			

			result = pstmt.executeUpdate();

			

			pstmt.close();

			

		} catch (Exception e) {

			System.out.println(e.toString());

		}

		

		return result;

		

	}

	



	//3.delete

	public int deleteDate(String id, String pw) {

	

		int result = 0;


		PreparedStatement pstmt = null;

		String sql;

		

		try {

			Connection conn = DBUtils.getConnection();
			sql = "delete from naverMember where id=? and pw=?";

			

			pstmt = conn.prepareStatement(sql);

			

			pstmt.setString(1, id);

			pstmt.setString(2, pw);

			

			result = pstmt.executeUpdate();

			

			pstmt.close();

			

		} catch (Exception e) {

			System.out.println(e.toString());

		}

		

		return result;	

	

	}

	



	//4.selectAll

	public List<SignUserDTO> getList() {

		List<SignUserDTO> lists = new ArrayList<SignUserDTO>();


		PreparedStatement pstmt = null;

		ResultSet rs = null;

		String sql;

		

		try {

			Connection conn = DBUtils.getConnection();
			sql = "select id,pw,name,gender,birth,email,tel ";

			sql+= "from naverMember order by name";

			

			pstmt = conn.prepareStatement(sql);

			

			rs = pstmt.executeQuery();

			

			while(rs.next()){

				

				SignUserDTO dto = new SignUserDTO();

				

				dto.setId(rs.getString("id"));

				dto.setPw(rs.getString("pw"));

				dto.setName(rs.getString("name"));

				dto.setGender(rs.getString("gender"));

				dto.setBirth(rs.getString("birth"));

				dto.setEmail(rs.getString("email"));

				dto.setTel(rs.getString("tel"));

				

				lists.add(dto);

							

			}

			

			rs.close();

			pstmt.close();

			

		} catch (Exception e) {

			System.out.println(e.toString());

		}

		

		return lists;

		

	}



	

	//5.searchId

	public List<SignUserDTO> getList(String id){

		List<SignUserDTO> lists = new ArrayList<SignUserDTO>();


		PreparedStatement pstmt = null;

		ResultSet rs = null;

		String sql;

		

		try{
			Connection conn = DBUtils.getConnection();

			sql = "select id,pw,name,gender,birth,email,tel ";

			sql+= "from naverMember where id=?";

		

			pstmt = conn.prepareStatement(sql);

		

			pstmt.setString(1, id);

			

			rs = pstmt.executeQuery();

			

			while(rs.next()){

				

				SignUserDTO dto = new SignUserDTO();

				

				dto.setId(rs.getString("id"));

				dto.setPw(rs.getString("pw"));

				dto.setName(rs.getString("name"));

				dto.setGender(rs.getString("gender"));

				dto.setBirth(rs.getString("birth"));

				dto.setEmail(rs.getString("email"));

				dto.setTel(rs.getString("tel"));

				

				lists.add(dto);

							

			}

			

			rs.close();

			pstmt.close();

			

		}catch (Exception e) {

			System.out.println(e.toString());

		}

		

		return lists;

	}



}