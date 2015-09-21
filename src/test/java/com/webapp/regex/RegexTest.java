package com.webapp.regex;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

	/*
	 *  문자열 검색, 대체, 추출 => 정규표현식 (regular expression)
	 *    
	 *  .  ==> new line 문자를 제외한 모든 문자
	 *  ^  ==> 한 라인의 시작   line start
	 *  $  ==> 한 라인의 끝     line end
	 *  *  ==> 앞 문자가 0개이상 반복  한글자만 가능 다른 문자 혼합은 안됨   
	 *  +  ==> 앞 문자가 1개 이상 반복 
	 *  [AYZ]  ==> A,Y,Z  말고는 다른 문자는 오질 못한다.  단 1글자만 
	 *  [A-Z]  ==> A-Z 까지 아무 문자 한개만  
	 *  [A-Z0-9]
	 *  [A-Z]
	 *  {n} 앞 문자가 n개 반복 
	 *  {n,}  앞 문자가 n개 이상 반복됨 
	 *  {n,m} 앞 문자가 n개 이상 m개 이하 
	 *  [가-힣]   한글의 범위 
	 *  
	 *  
	 */
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
//		String regex = "^.$";
//		String regex = "^A*$";//A 만 반복
//		String regex = "^A+$";
//		String regex = "^[AYZ]$";
//		String regex = "^[A-Z0-9]{3,10}$";
//		String regex = "^[가-힣]{3,10}$";
		String regex = "^[A-Za-z]{3}$";
		
//		02-333-3333
//		010-6448-4897
//		011-189-4632
		
		
		
		while(true){
			String line = scan.nextLine();
			
			System.out.println("line = "+ "[" + line + "]");
			Pattern pattern = Pattern.compile(regex);
			Matcher m = pattern.matcher(line);
			System.out.println("match = " + m.find());
			
			
//			System.out.println("match = " + Pattern.matches(regex, line));
		
		}

	}

}
