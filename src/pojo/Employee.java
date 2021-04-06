package pojo;

public class Employee extends Person {
	int idEmployee;
	double timeWorked, timeLate, extraHourWorked;
	boolean late;
	
	public Employee(String name, int age, int idEmployee, Hour arrivalTime, Hour departureTime) {
		super(name, age);
		// TODO Auto-generated constructor stub
		
		this.idEmployee = idEmployee;
        this.timeLate = timeLate(arrivalTime);
        
        if(this.timeLate > 0)
            this.late=true;
        
        if(late){
            System.out.println("Employee '" + this.name + "' late. ");
        }
        
        this.timeWorked = hoursWorked(arrivalTime, departureTime);
	}
	
    
    public double timeLate(Hour arrivalTime){
        return ((arrivalTime.getHour()*60*60 + arrivalTime.getMinute()*60 + 
        		arrivalTime.getSecond()) - 8*3600.0)/3600.0;
    }
    
    public double hoursWorked(Hour arrivalTime, Hour departureTime){
        double horas = ( (departureTime.getHour()*60 + departureTime.getMinute()) - 
                       (arrivalTime.getHour()*60 + arrivalTime.getMinute()) )/60.0;
        
        if(horas < 0)
            throw new IllegalArgumentException("Hora de saída anterior a hora de chegada");
        
        return horas;
    }
    
    public double getHoursWorked(){
        return this.timeWorked;
    }
    public void setTimeWorked(double timeWorked) {
		this.timeWorked = timeWorked;
	}

	public int getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}

	
	public double getTimeLate() {
		return timeLate;
	}

	public void setTimeLate(double timeLate) {
		this.timeLate = timeLate;
	}

	public double getExtraHourWorked() {
		return extraHourWorked;
	}

	public void setExtraHourWorked(double extraHourWorked) {
		this.extraHourWorked = extraHourWorked;
	}

	public boolean isLate() {
		return late;
	}

	public void setLate(boolean late) {
		this.late = late;
	}

}
