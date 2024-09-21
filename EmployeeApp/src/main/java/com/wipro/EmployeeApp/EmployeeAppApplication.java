package com.wipro.EmployeeApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class EmployeeAppApplication implements CommandLineRunner {
	@Autowired
	private EmployeeService employeeService; //dependency injection

	public static void main(String[] args) {
		SpringApplication.run(EmployeeAppApplication.class, args);
	}
	@Override
	public void run(String... args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("\n--- Employee Management System ---");
			System.out.println("1. Add Employee");
			System.out.println("2. View Employees");
			System.out.println("3. Update Employee");
			System.out.println("4. Delete Employee");
			System.out.println("5. Search Employee");
			System.out.println("6. Exit");
			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (choice) {
				case 1:
					// Add Employee
					System.out.print("Enter Employee Name: ");
					String name = scanner.nextLine();
					System.out.print("Enter Employee Salary: ");
					Double salary = scanner.nextDouble();
					Employee employee = new Employee();
					employee.setName(name);
					employee.setSalary(salary);
					employeeService.addEmployee(employee);
					System.out.println("Employee added successfully!");
					break;
				case 2:
					// View Employees
					List<Employee> employees = employeeService.getAllEmployees();
					System.out.println("\n--- Employee List ---");
					for (Employee emp : employees) {
						System.out.println("ID: " + emp.getId() + ", Name: " + emp.getName() + ", Salary: " + emp.getSalary());
					}
					break;
				case 3:
					// Update Employee
					System.out.print("Enter Employee ID to Update: ");
					Long idToUpdate = scanner.nextLong();
					scanner.nextLine(); // Consume newline
					System.out.print("Enter New Employee Name: ");
					String newName = scanner.nextLine();
					System.out.print("Enter New Employee Salary: ");
					Double newSalary = scanner.nextDouble();
					Employee updatedEmployee = new Employee();//object
					updatedEmployee.setName(newName);
					updatedEmployee.setSalary(newSalary);
					Employee updated = employeeService.updateEmployee(idToUpdate, updatedEmployee);
					if (updated != null) {
						System.out.println("Employee updated successfully!");
					} else {
						System.out.println("Employee not found!");
					}
					break;
				case 4:
					// Delete Employee
					System.out.print("Enter Employee ID to Delete: ");
					Long idToDelete = scanner.nextLong();
					boolean deleted = employeeService.deleteEmployee(idToDelete);
					if (deleted) {
						System.out.println("Employee deleted successfully!");
					} else {
						System.out.println("Employee not found!");
					}
					break;
				case 5:
					// Search Employee
					System.out.print("Enter Employee ID to Search: ");
					Long idToSearch = scanner.nextLong();
					Employee foundEmployee = employeeService.getEmployeeById(idToSearch);
					if (foundEmployee != null) {
						System.out.println("ID: " + foundEmployee.getId() + ", Name: " + foundEmployee.getName() + ", Salary: " + foundEmployee.getSalary());
					} else {
						System.out.println("Employee not found!");
					}
					break;
				case 6:
					// Exit
					System.out.println("Exiting...");
					scanner.close();
					System.exit(0);
				default:
					System.out.println("Invalid choice! Please try again.");
			}
		}
	}

}
