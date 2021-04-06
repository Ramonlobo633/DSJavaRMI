package pojo;

public class Hour {
	int hour, minute, second;
	public void Hora (int hour, int minute, int second){
    
		if(hour>=0 && hour <24 )
            this.hour = hour;
        else
            throw new IllegalArgumentException("Hora inválida");
            
        if(minute >=0 && minute < 60)
            this.minute = minute;
        else
            throw new IllegalArgumentException("Minutos inválidos");
        
        if( second >=0 && second < 60)
            this.second = second;
        else
            throw new IllegalArgumentException("Segundos inválidos");
            
                
    }
	@Override
	public String toString() {
		return "Hour [hour=" + hour + ", minute=" + minute + ", second=" + second + "]";
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hours) {
		this.hour = hours;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public int getSecond() {
		return second;
	}
	public void setSecond(int second) {
		this.second = second;
	}

}
