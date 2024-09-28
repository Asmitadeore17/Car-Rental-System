package carRentalSystem.in;

public class Car {
	private String carId;
	private String carModel;
	private String carBrand;
	private double basePricePerDay;
	private boolean isAvailable;

	Car(String carId, String carModel, String carBrand, double basePricePerDay) {
		this.carId = carId;
		this.carModel = carModel;
		this.carBrand = carBrand;
		this.basePricePerDay = basePricePerDay;
		this.isAvailable = true;
	}

	public String getCarId() {
		return carId;
	}

	public String getCarModel() {
		return carModel;
	}

	public String getCarBrand() {
		return carBrand;
	}

	public double calculatePrice(int rentalDays) {
        return basePricePerDay * rentalDays;
    }

	public boolean isAvailable() {
		return isAvailable;
	}

	public void rent() {
		isAvailable = false;
	}

	public void returnCar() {
		isAvailable = true;
	}

}
