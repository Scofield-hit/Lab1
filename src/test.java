import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;

//第二次修改
//B1修改
//C4修改
//B2修改
public class test {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入表达式：\n");
		String reader = sc.nextLine();
		String oprator[] = reader.split("[+]");
		char x = 'x';
		derivative(reader,x);

	}
	public static String ddx(String s,char x){
		int couter = 0, flag = 0;
		for (int i = 0; i < s.length(); i++) {
	           char  temp =  s.charAt(i);
	           if(temp == x){
	        	   couter++;
	        	   flag = 1;
	           }
	        }
		if(flag==1)
		{
			s = s.replaceFirst(String.valueOf(x), Integer.toString(couter));
		}
		else
		{
			s = "";
		}
		return s;
	}
	public static void derivative(String reader,char x)
	{

		reader = reader.replaceAll("-", "~-");
		reader = reader.replaceAll("[+]", "~+");
		String oprator[] = reader.split("~");
		String output = "";
		for(String s: oprator) {
			   s = ddx(s,x);
			   if(s == null || s.length() <= 0)
				   {
					   continue;
				   }
			   else
				   {
				   output = output+s;
				   }
			   }

		if(output.charAt(0)=='+')
		{
			output = output.replaceFirst("[+]", "");
		}
		System.out.print("对"+x+"求导后的结果为：\n");
		System.out.print(output);
		
	
	}
}
