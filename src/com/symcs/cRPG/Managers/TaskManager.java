package com.symcs.cRPG.Managers;

import java.util.ArrayList;
import java.util.List;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.Tasks.Task;

public class TaskManager {
	
	@SuppressWarnings("unused")
	private cRPG plugin;
	private List<Task> Tasks = new ArrayList<Task>();
	
	
	public TaskManager(cRPG plugin){
		this.plugin = plugin;
	}
	
	public void registerTask(Task task){
		Tasks.add(task);
	}

	public void unregisterTask(Task task) {
		if(Tasks.contains(task)){
			Tasks.remove(task);
		}
	}
	
	public void stopAllTasks(){
		List<Task> StopThese = new ArrayList<Task>(Tasks);
		for(Task t:StopThese){
			t.cancelEarly();
		}
	}
	
	public void stopAllTasksAndWait(){
		this.stopAllTasks();
		
	}
	
	public List<Task> getAllTasks(){
		return this.Tasks;
	}
}
