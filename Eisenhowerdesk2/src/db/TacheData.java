package db;

import java.sql.Date;

public class TacheData {
    private String name;
    private String description;
    private Date deadline;
    private String time;
    private String done;
    private Integer rateimp;
    private Integer rateurg;
    private String folder;
    
    
    
    public TacheData(String Name, String Description, Date DeadLine, String Time, String Done, Integer Rateimp, Integer Rateurg, String Folder)
    {
    	this.name = Name;
    	this.description = Description;
    	this.deadline = DeadLine;
    	this.time = Time;
    	this.done = Done;
    	this.rateimp = Rateimp;
    	this.rateurg = Rateurg;
    	this.folder = Folder;
    	
    	
    	
    }
    
    public String getName() {
    	
    	return name;
    }
    
    public String getDescription() {
    	
    	return description;
    }
    
    public Date getDeadline() {
        return deadline;
    }

    public String getTime() {
    	
    	return time;
    }
    
    public String getDone() {
    	
    	return done;
    }
    
    public Integer getRateimp() {
    	
    	return rateimp;
    }
    
    public Integer getRateurg() {
    	
    	return rateurg;
    }

    public String getFolder() {
    	
    	return folder;
    }
    
    
}
