package com.aditya;

import java.util.*;
import java.io.*;

//Factory patttern-Creational Pattern
public class App
{
	public static void main(String args[])
	{
		
		OS_FactoryPattern ob1=new OS_FactoryPattern();
		Shop_BuilderPattern ob2=new Shop_BuilderPattern();
		School_AdapterPattern ob3=new School_AdapterPattern();
		CompositeTest ob4=new CompositeTest();		
		Youtube_ObserverPattern ob5=new Youtube_ObserverPattern();
		Customer_VisitorPattern ob6=new Customer_VisitorPattern();
		ob1.Main();ob2.Main();ob3.Main();ob4.Main();ob5.Main();ob6.Main();
	}
}
interface OS
{
	void spec();
}
class Android implements OS
{
	public void spec()
	{
		System.out.println("Most powerful OS-Android");
	}
}
class iOS implements OS
{
	public void spec()
	{
		System.out.println("Most secure OS-iOS");
	}
}
class OS_Factory
{
	OS getInstance(String str)
	{
		if(str.equals("Open"))
			return new Android();
		else
			return new iOS();
	}
}
class OS_FactoryPattern
{
	void Main()
	{
		Scanner sc=new Scanner(System.in);
		OS obj;
		OS_Factory OSf=new OS_Factory();
		System.out.println("Implementing Creational Pattern - Factory Pattern");
		System.out.print("What type of OS you want?(Open/Closed)");
		obj=OSf.getInstance(sc.next());
		obj.spec();
	}
}

//Builder Pattern-Creational Pattern
class Phone
{
	String os;int ram;String processor;int battery;double screenSize;
	Phone(String o,int r,String p,double s,int b)
	{
		os=o;ram=r;processor=p;screenSize=s;battery=b;
	}
	@Override
	public String toString()
	{
		return "Phone [os="+os+", ram="+ram+", processor="+processor+", screenSize="+screenSize+", battery="+battery+"]";
	}
}
class PhoneBuilder
{
	String os;int ram;String processor;int battery;double screenSize;
	PhoneBuilder setOs(String o)
	{
		os=o;return this;
	}
	PhoneBuilder setRam(int r)
	{
		ram=r;return this;
	}
	PhoneBuilder setProcessor(String p)
	{
		processor=p;return this;
	}
	PhoneBuilder setScreenSize(double s)
	{
		screenSize=s;return this;
	}
	PhoneBuilder setBattery(int b)
	{
		battery=b;return this;
	}
	Phone getPhone()
	{
		return new Phone(os,ram,processor,screenSize,battery);
	}

}
class Shop_BuilderPattern
{
	void Main()
	{
		//use the ones user wants,here screen size and processor is not required
		
		System.out.println("Implementing Builder Pattern");
		Phone p=new PhoneBuilder().setOs("Android").setRam(4).setBattery(5000).getPhone();
		System.out.println(p);
	}
}

//Adapter Pattern-Structural Pattern
interface Pen
{
	void write(String str);
}
class ParkerPen
{
	void mark(String str)
	{
		System.out.println(str);
	}
}
class PenAdapter implements Pen
{
	ParkerPen pp=new ParkerPen();
	
	@Override
	public void write(String str)
	{
		pp.mark(str);
	}
}		
class AssignmentWork
{
	Pen p;
	void setP(Pen p)
	{
		this.p=p;
	}
	Pen getP()
	{
		return p;
	}
	void writeAssignment(String str)
	{
		p.write(str);
	}
}		
class School_AdapterPattern
{
	void Main()
	{
		System.out.println("Adapter Pattern");
		Pen p=new PenAdapter();
		AssignmentWork aw=new AssignmentWork();
		aw.setP(p);
		aw.writeAssignment("Assignment written");
	}
}

//Composite Design Pattern-Structural Pattern
interface Component
{
	void showPrice();
}
class Leaf implements Component
{
	String name ; int price;
	Leaf(int p,String n)
	{
		name=n;price=p;
	}	
	@Override
	public void showPrice()
	{
		System.out.println(name+" : "+price); 
	}
}
class Composite implements Component
{
	String name;
	List<Component> components=new ArrayList<>();
	Composite(String n)
	{
		name=n;
	}
	void addComponent(Component com)
	{
		components.add(com);
	}

	@Override
	public void showPrice()
	{
		System.out.println(name);
		for(Component c:components)
		{
			c.showPrice();
		}
	}
}
class CompositeTest
{
	void Main()
	{
		System.out.println("Implementing Composite Pattern");
		Component hd=new Leaf(4000,"HDD");
		Component mouse=new Leaf(400,"Mouse");	
		Component monitor=new Leaf(8000,"Monitor");	
		Component ram=new Leaf(2000,"Ram");
		Component cpu=new Leaf(10000,"CPU");

		Composite ph=new Composite("Peripheral");
		Composite cabinet=new Composite("Cabinet");
		Composite mb=new Composite("MB");
		Composite computer=new Composite("Computer");

		mb.addComponent(cpu);
		mb.addComponent(ram);

		ph.addComponent(mouse);
		ph.addComponent(monitor);
		
		cabinet.addComponent(hd);
		cabinet.addComponent(mb);
		
		computer.addComponent(ph);
		computer.addComponent(cabinet);
	
		mb.showPrice();
		computer.showPrice();
	}
}

//Observer Design Pattern-Behavioural Pattern

class Channel
{
	List<Subscriber> subs=new ArrayList<>();
	String title;
	void subscribe(Subscriber sub)
	{
		subs.add(sub);
	}
	void unSubscibe(Subscriber sub)
	{
		subs.remove(sub);
	}
	void notifySubscribers()
	{
		for(Subscriber sub:subs)
		{
			sub.update();
		}
	}
	void upload(String title)
	{
		this.title=title;
		notifySubscribers();
	}	
				
}
class Subscriber
{
	String name;Channel channel=new Channel();
	Subscriber(String n)
	{
		name=n;
	}
	void update()
	{
		System.out.println("Hey "+name+" Video uploaded");
	}
	void subscribeChannel(Channel ch)
	{
		channel=ch;
	}		
}
class Youtube_ObserverPattern
{
	void Main()
	{
		System.out.println("Implementing Observer Pattern");
		Channel Aditya=new Channel();
		Subscriber s1=new Subscriber("A");
		Subscriber s2=new Subscriber("B");
		Subscriber s3=new Subscriber("C");
		Aditya.subscribe(s1);Aditya.subscribe(s2);Aditya.subscribe(s3);
		s1.subscribeChannel(Aditya);
		s2.subscribeChannel(Aditya);
		s3.subscribeChannel(Aditya);
	
		Aditya.upload("Hello world");

	}
}

//Strategy Pattern-Behavioural Pattern

interface ShoppingCartVisitor
{
	int visit(Computer computer);
	int visit(Printer printer);	
}
interface Item
{
	int accept(ShoppingCartVisitor visitor);
}
class Computer implements Item
{
	int cost;String serialNumber;
	Computer(int c,String s)
	{
		cost=c;serialNumber=s;
	}
	int getPrice()
	{
		return cost;
	}
	String getSerialNumber()
	{
		return serialNumber;
	}
	@Override
	public int accept(ShoppingCartVisitor visitor)
	{
		return visitor.visit(this);
	}
}
class Printer implements Item
{
	int priceOfCartridge ; int noOfCartridge ; String modelNumber;

	Printer(int p,int n,String model)
	{
		priceOfCartridge=p;noOfCartridge=n;modelNumber=model;
	}	
	int getPriceOfCartridge()
	{
		return priceOfCartridge;
	}
	int getNoOfCartridge()
	{
		return noOfCartridge;
	}
	String getModelNumber()
	{
		return modelNumber;
	}
	@Override
	public int accept(ShoppingCartVisitor visitor)
	{
		return visitor.visit(this);
	}
}
class ShoppingCartVisitorImp1 implements ShoppingCartVisitor
{
	@Override
	public int visit(Computer computer)
	{
		int cost=0;
		if(computer.getPrice()>20000)//apply Rs1000 discount
			cost=computer.getPrice()-1000;
		else
			cost=computer.getPrice();
		System.out.println("Computer serialNumber: "+computer.getSerialNumber()+" , cost = "+cost);

		return cost;
	}
	@Override
	public int visit(Printer printer)
	{
		int cost=printer.getPriceOfCartridge()*printer.getNoOfCartridge();
		System.out.println("Printer : "+printer.getModelNumber()+" , cost = "+cost);
		return cost;
	}
}
class Customer_VisitorPattern
{
	void  Main()
	{
		Item[] items=new Item[]
		{
			new Computer(21000,"com1234"),new Printer(1000,6,"HP 680x")
		};
		int totalCost=calculatePrice(items);
		System.out.println("Total cost: "+totalCost);
	}
	int calculatePrice(Item[] items)
	{
		ShoppingCartVisitor visitor=new ShoppingCartVisitorImp1();
		int sum=0;
		for(Item item:items)
		{
			sum+=item.accept(visitor);
		}
		return sum;
	}
}


	