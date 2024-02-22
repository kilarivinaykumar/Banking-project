package banking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import javax.swing.text.html.HTMLDocument.Iterator;

public class classA {
	void meth1() {
		int r;
		int sum=0;
		int temp;
		int n=121;
		temp=n;
		while(n>0) {
			r=n%10;
			sum=(sum*10)+r;
			n=n/10;
		}
		if(sum==n) {
			System.out.println(" it is a polindrome");
		}else {
			System.out.println(" it is not a polindrome");
		}
	}
	void meth2() {
		String s="Hello";
		char[] c=s.toCharArray();
		for(int i=c.length-1;i>=0;i--) {
			System.out.println(c[i]);
		}
	}
	void meth3() {
		String s1="hello";
		String a="";
		int x=s1.length();
		for(int i=(x-1);i>=0;i--) {
			a=a+s1.charAt(i);
			
		}
		if(s1.equals(a)) {
			System.out.println(a+" it is palindrome...");
			
		}else {
			System.out.println(a+" it is not a palindrome...");
		}
		
	}
	void meth4() {
		ArrayList<Integer> al=new ArrayList<Integer>();
		al.add(200);
		al.add(300);
		al.add(400);
		al.add(200);
		al.add(500);
		al.add(600);
		al.add(700);
		al.add(800);
		System.out.println(al);
		ListIterator<Integer> li=al.listIterator();
		while(li.hasNext()) {
			System.out.println(li.next());
		}
		System.out.println("\n");
		while(li.hasPrevious()) {
			System.out.println(li.previous());
		}
		//System.out.println();
		Collections.sort(al);
		System.out.println(al.stream().map(x->x*10).collect(Collectors.toList()));
		System.out.println("\n");
		System.out.println("\n");
		//System.out.println(al.stream().filter(x->x.length()<5).count());
		al.forEach(data->System.out.println(data));
		System.out.println("\n");
		System.out.println(al.stream().sorted().collect(Collectors.toList()));
		//Collections.sort(al);
	}
	void meth5() {
		LinkedList <Object> li=new LinkedList<Object>();
		li.add("Vinay");
		li.add("Vistnu");
		li.add("Ajay");
		li.add("Bhavani");
		li.add("Purindhar");
		li.add("Kartheek");
		System.out.println(li);
		//Collections.sort((List<T>) li);
		li.forEach(data->System.out.println(data));
		System.out.println(li.stream().sorted().collect(Collectors.toList()));
		//Iterator<Object> i=li.iterator();
		java.util.Iterator<Object> i=li.iterator();
		while(i.hasNext()) {
			System.out.println(i.next());
			System.out.println("Hello buddy....");
		}
		//ListIterator i=li.listIterator()
		
	}
	
	public static void main(String[] args) {
		//new classA().meth1();
		//new classA().meth2();
		//new classA().meth3();
		//new classA().meth4();
		new classA().meth5();
		
		
	}
}

