package carRentalSystem.in;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarRentalSystem {

	List<Car> cars;
	List<Customer> customers;
	List<Rental> rentals;

	CarRentalSystem() {
		cars = new ArrayList<>();
		customers = new ArrayList<>();
		rentals = new ArrayList<>();
	}

	public void addCar(Car car) {
		cars.add(car);
	}

	public void addCustomer(Customer customer) {
		customers.add(customer);
	}

	public void rentCar(Car car, Customer customer, int days) {
		if (car.isAvailable()) {
			car.rent();
			rentals.add(new Rental(car, customer, days));
		} else {
			System.out.println("Car is not available for Rent...!");
		}
	}

	public void returnCar(Car car) {
		car.returnCar();
		Rental rentalToRemove = null;

		for (Rental rental : rentals) {
			if (rental.getCar() == car) {
				rentalToRemove = rental;
				break;
			}
		}

		if (rentalToRemove != null) {
			rentals.remove(rentalToRemove);
		} else {
			System.out.println("Car was not Rented");
		}
	}

	public void menu() {
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("********* CAR RENTAL SYSTEM *********");
			System.out.println("1.Rent a Car");
			System.out.println("2.Return a Car");
			System.out.println("3.Exit");

			System.out.println("Enter your choice : ");
			int choice = sc.nextInt();

			switch (choice) {
			case 1: {
				System.out.println("\n.....Rent Car.....\n");
				System.out.println("Enter your Name : ");
				String customerName = sc.nextLine();

				System.out.println("\n.....Available Cars.....\n");
				for (Car car : cars) {
					if (car.isAvailable()) {
						System.out.println(car.getCarId() + " " + car.getCarModel() + " " + car.getCarBrand() + " ");
					}
				}

				System.out.print("\nEnter the car ID you want to rent: ");
				String carId = sc.nextLine();

				System.out.print("Enter the number of days for rental: ");
				int rentalDays = sc.nextInt();
				sc.nextLine(); // Consume newline

				Customer newCustomer = new Customer("CUS" + (customers.size() + 1), customerName);
				addCustomer(newCustomer);

				Car selectedCar = null;
				for (Car car : cars) {
					if (car.getCarId().equals(carId) && car.isAvailable()) {
						selectedCar = car;
						break;
					}
				}

				if (selectedCar != null) {
					double totalPrice = selectedCar.calculatePrice(rentalDays);
					System.out.println("\n== Rental Information ==\n");
					System.out.println("Customer ID: " + newCustomer.getCustomerId());
					System.out.println("Customer Name: " + newCustomer.getCustomerName());
					System.out.println("Car: " + selectedCar.getCarBrand() + " " + selectedCar.getCarModel());
					System.out.println("Rental Days: " + rentalDays);
					System.out.printf("Total Price: $%.2f%n", totalPrice);

					System.out.print("\nConfirm rental (Y/N): ");
					String confirm = sc.nextLine();

					if (confirm.equalsIgnoreCase("Y")) {
						rentCar(selectedCar, newCustomer, rentalDays);
						System.out.println("\nCar rented successfully.");
					} else {
						System.out.println("\nRental canceled.");
					}
				} else {
					System.out.println("\nInvalid car selection or car not available for rent.");
				}

			}
				break;

			case 2: {
				System.out.println("\n.....Return a Car.....\n");
				System.out.println("Enter a car ID you want to return : ");
				String carId = sc.next();

				Car carToReturn = null;
				for (Car car : cars) {
					if (car.getCarId().equals(carId) && !car.isAvailable()) {
						carToReturn = car;
						break;
					}
				}

				if (carToReturn != null) {
					Customer customer = null;
					for (Rental rental : rentals) {
						if (rental.getCar() == carToReturn) {
							customer = rental.getCustomer();
							break;
						}
					}

					if (customer != null) {
						returnCar(carToReturn);
						System.out.println("Car Rented Successfully by " + customer.getCustomerName());
					} else {
						System.out.println("Car was not Rented or rental information is missing..!");
					}
				} else {
					System.out.println("Invalid car ID or car is no rented..!");
				}
			}
				break;

			case 3: {
				System.out.println("\nThank you for using the Car Rental System...!");
				System.out.println("");
			}
				break;

			default: {
				System.out.println("Invalid choice.Please enter a valid option....!");
			}

			}
		}
	}
}
