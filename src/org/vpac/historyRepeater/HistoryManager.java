package org.vpac.historyRepeater;

import java.util.Date;

import org.vpac.historyRepeater.model.HistoryNode;

public interface HistoryManager {
	
	public static final int DEFAULT_NUMBER_OF_ENTRIES = 4;
	
	public void setDefaultNumberOfEntriesPerNode(int i);
	
	public int getDefaultNumberOfEntriesPerNode();
	
	public HistoryNode getHistoryNode(String key);
	
	public void addHistoryEntry(String key, String entry, Date date);
	
	public void addHistoryEntry(String key, String entry, Date date, int numberOfEntriesForParentNode);
	
//	public void addHistoryEntry(HistoryNode parent, String key, String entry, Date date);
//	 
//	public void addHistoryEntry(HistoryNode parent, String key, String entry ,Date date, int numberOfEntrieSForParentNode);

}
