package db;
import java.sql.Date;

public class SousTacheData {
	
	    private String name_st;
	    private String description_st;
	    private Date deadline_st;
	    private String time_st;
	    private String done_st;
	    private Integer rateimp_st;
	    private Integer rateurg_st;
	    private Integer id_t;
	    
	    
	    public SousTacheData(String Name, String Description, Date DeadLine, String Time, String Done, Integer Rateimp_st, Integer Rateurg_st, Integer Id_t)
	    {
	    	this.name_st = Name;
	    	this.description_st = Description;
	    	this.deadline_st = DeadLine;
	    	this.time_st = Time;
	    	this.done_st = Done;
	    	this.rateimp_st = Rateimp_st;
	    	this.rateurg_st = Rateurg_st;
	    	this.id_t = Id_t;
	    	
	    	
	    	
	    	
	    }
	    
	    public String getName() {
	    	
	    	return name_st;
	    }
	    
	    public String getDescription() {
	    	
	    	return description_st;
	    }
	    
	    public Date getDeadline() {
	        return deadline_st;
	    }

	    public String getTime() {
	    	
	    	return time_st;
	    }
	    
	    public String getDone() {
	    	
	    	return done_st;
	    }
	    
	    public Integer getRateimp() {
	    	
	    	return rateimp_st;
	    }
	    
	    public Integer getRateurg() {
	    	
	    	return rateurg_st;
	    }


	   
	    public Integer getId_t() {
	    	
	    	return id_t;
	    }

	}

