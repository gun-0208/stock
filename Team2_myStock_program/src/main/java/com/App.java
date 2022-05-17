package com;

import java.util.Scanner;

import org.jsoup.nodes.Document;

import com.AppController.AppController;
import com.AppService.SignUserService;
import com.Exception.AuthenException;
import com.utils.DBUtils;

public class App {
	
	public static void main(String[] args) throws AuthenException {
//		StockController stockController = new StockController();
//		String stockCode = "005380";
//		
//		StockController.insertStock(stockCode);
		// 콘솔창을 통해 입력 받을 수 있도록 조치
				Scanner sc = new Scanner(System.in);
				// 시스템 반복 입력 요청
				boolean run = true;
				System.out.println("주식 프로그램 활성화 완료. 어서오세요.");
				System.out.println("콘솔창을 활용하여 원하는 메뉴를 선택해주세요.");
				System.out.println("기본 값으로는 삼성전자가 등록되어있습니다.");
				// defalt 값 나열
//				String stockCode = "005930";// 변수 기간을 언제까지?
				Document doc = null;
				while (run) {
					System.out.println("");
					System.out.println("-------------------------------------");
					System.out.println("1.회원가입 | 2.관심 주식 조회  | 3.내 정보 조회 | 4.내 주식 매수/매도 | 5.저장 후 종료");
					System.out.println("-------------------------------------");
					System.out.print("선택>");
					int menuNum = sc.nextInt();
					switch (menuNum) {
					case 1:
						// 컨트롤러, 회원가입
						System.out.println("회원가입을 진행하겠습니다.");
						AppController.signUser();
						Scanner in = new Scanner(System.in);
						SignUserService ob = new SignUserService();
						
						int ch;
						
						while (true) {
							
							do {
								
								System.out.print("1.입력  2.수정  3.탈퇴  4.회원전체출력  5.아이디검색  6.종료");
								System.out.print("\n-------------------------------------\n▶");
								
								ch = in.nextInt();
								
							} while (ch < 1 || ch > 6);
							
							System.out.println();
							
							switch (ch) {
							
							case 1:
								
								ob.insert();
								System.out.println();
								break;
								
							case 2:
								
								ob.update();
								
								System.out.println();
								break;
								
							case 3:
								
								ob.delete();
								System.out.println();
								break;
								
							case 4:
								
								ob.selectAll();
								System.out.println();
								break;
								
							case 5:
								
								ob.searchId();
								System.out.println();
								break;
								
							case 6:
								
								DBUtils.close();
								System.exit(0);
								break;
							}
							
						}
						
						
					case 2:
						// 컨트롤러, 관심 주식 조회
						System.out.println("관심 종목을 선택해주세요");
						System.out.println("");
						System.out.println("------------------------------");
						System.out.println("1.삼성전자 | 2.현대차  | 3.카카오");
						System.out.println("------------------------------");
						System.out.print("선택>");
						int searchNum = sc.nextInt();
						String stockCode = "";
						switch (searchNum) {
						case 1:
							stockCode = "005930";
							break;
						case 2:
							stockCode = "005380";
							break;
						case 3:
							stockCode = "005720";
							break;
						}
						AppController.insertStock(stockCode);
						break;
					case 3:
						// 컨트롤러, 내 정보 조회
						break;
					case 4:
						// 컨트롤러 매수, 매도
						break;
					case 5:
						// 저장 후 종료
						run = false;
						break;
					}
					System.out.println("프로그램을 종료하겠습니다.");
				}
		}
	}
		

		
