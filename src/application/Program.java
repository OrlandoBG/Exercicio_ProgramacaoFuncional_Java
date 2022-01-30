package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter a full file path: ");
		File path = new File(sc.nextLine());	
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			
			String line = br.readLine();
			
			List<Employee> employee = new ArrayList<>();
			
			while(line != null) {
				
				String[] fields = line.split(",");
				
				employee.add(new Employee(fields[0],fields[1],Double.valueOf(fields[2])));
				
				line = br.readLine();
			}
			
			System.out.print("Enter salary: ");
			double salary = sc.nextDouble();
			
			Comparator<String> comp = (p1, p2) -> p1.toUpperCase().compareTo(p2.toUpperCase());
			
			
			List<String> emailEmployee = employee.stream()
					.filter(x -> x.getSalary() > salary)
					.map(x -> x.getEmail())
					.sorted(comp)
					.collect(Collectors.toList());
			
			double sum = employee.stream()
					.filter(x -> x.getName().charAt(0) == 'M')
					.map(x -> x.getSalary())
					.reduce(0.0, (p1, p2) -> p1 + p2);
			
			System.out.println("----------------------");
					
			System.out.println("Email of people whose salary is more than " + salary);
			
			emailEmployee.forEach(System.out :: println);
			
			System.out.println("----------------------");
			
			System.out.println("Sum of salary whose name starts with 'M': " + sum);
			
			sc.close();
			
		}
		catch(IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		

	}

}
