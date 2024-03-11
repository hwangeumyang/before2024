package model;

public class CalculatorModel {
	public String sum(String op1, String op2) {
		StringBuffer buffer = new StringBuffer();
		
		int point1 = op1.indexOf(".");
		int point2 = op2.indexOf(".");
		
		if(point1 == -1) {
			op1 += ".";
			point1 = op1.length() - 1;
		}
		if(point2 == -1) {
			op2 += ".";
			point2 = op2.length() - 1;
		}
		
		
		//Á¡ ¿ÞÂÊ 0µé		
		String zeros = getZeros(point2 - point1);
		if(point1 < point2) {
			op1 = zeros + op1;
			point1 += point2 - point1;
		}
		else if(point2 < point1) {
			op2 = zeros + op2;
		}
		
		int leng1 = op1.length();
		int leng2 = op2.length();
		
		zeros = getZeros(leng2 - leng1);
		if(leng1 < leng2) {
			op1 = op1 + zeros;
		}
		else if(leng2 < leng1) {
			op2 = op2 + zeros;			
		}
//		System.out.println(point1);
//		System.out.println(op1);
//		System.out.println(point2);
//		System.out.println(op2);		
		
		int sum;
		int c = 0;
		for(int idx = op1.length() - 1; point1 < idx; --idx) {
			sum = op1.charAt(idx) + op2.charAt(idx) - 96 + c;
			
			if(sum > 9) {
				c = 1;
				sum -= 10;
			}
			else {
				c = 0;
			}
			
			buffer.insert(0, sum);
		}
		buffer.insert(0,  '.');
		
		for(int idx = point1 - 1; 0 <= idx; --idx) {
			sum = op1.charAt(idx) + op2.charAt(idx) - 96 + c;
			
			if(sum > 9) {
				c = 1;
				sum -= 10;
			}
			else {
				c = 0;
			}
			
			buffer.insert(0, sum);		
		}
		
		System.out.println(buffer.toString());
		buffer.insert(0, c);
		
		
		return buffer.toString();
	}
	
	public String sub(String op1, String op2) {
		double dop1 = Double.parseDouble(op1);
		double dop2 = Double.parseDouble(op2);
		
		return getCleanNum(dop1 - dop2 + "");		
	}
	
	public String mul(String op1, String op2) {
		double dop1 = Double.parseDouble(op1);
		double dop2 = Double.parseDouble(op2);
		
		return getCleanNum(dop1 * dop2 + "");		
	}
	public String div(String op1, String op2) {
		double dop1 = Double.parseDouble(op1);
		double dop2 = Double.parseDouble(op2);
		
		if(dop2 == 0) {
			return op1;
		}		
		
		return getCleanNum(dop1 / dop2 + "");
	}
	private String getCleanNum(String str) {
		return getCleanNum(new StringBuffer(str));
	}
	private String getCleanNum(StringBuffer buffer) {
		while(buffer.charAt(0) == '0') buffer.deleteCharAt(0);
		while(buffer.charAt(buffer.length() - 1) == '0') buffer.deleteCharAt(buffer.length() - 1);
		if(buffer.charAt(buffer.length() - 1) == '.') buffer.deleteCharAt(buffer.length() - 1);
		
		return buffer.toString();
	}
	
	private String getZeros(int num) {
		if(num < 0) num *= -1;
		StringBuffer buffer = new StringBuffer();
		for(int i=0; i<num; ++i) buffer.append("0");
				
		return buffer.toString();		
	}

}
