package pojo;

public class Employee extends Person {
	int idEmployee;
	double timeWorked, timeLate, extraHourWorked;
	boolean late;
	
	public Employee(String name, int age) {
		super(name, age);
		// TODO Auto-generated constructor stub
	}
	

	public void Employee(String name, Hour horaChegada, Hour horaSaida){
        this.name=name;
        this.timeLate = timeLate(horaChegada);
        
        if(this.timeLate > 0)
            this.late=true;
        
        if(late){
            System.out.println("Employee '" + this.name + "' late. ");
        }
        
        this.timeWorked = hoursWorked(horaChegada, horaSaida);
    }
    
    public double timeLate(Hour horaChegada){
        return ((horaChegada.getHour()*60*60 + horaChegada.getMinute()*60 + 
                 horaChegada.getSecond()) - 8*3600.0)/3600.0;
    }
    
    public double hoursWorked(Hour horaChegada, Hour horaSaida){
        double horas = ( (horaSaida.getHour()*60 + horaSaida.getMinute()) - 
                       (horaChegada.getHour()*60 + horaChegada.getMinute()) )/60.0;
        
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
