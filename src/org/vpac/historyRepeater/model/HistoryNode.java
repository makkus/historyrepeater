package org.vpac.historyRepeater.model;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.vpac.historyRepeater.HistoryManager;

public class HistoryNode {
	
	private int numberOfEntries = HistoryManager.DEFAULT_NUMBER_OF_ENTRIES;
	
	private SortedMap<Date, String> entries = new TreeMap<Date, String>();
	
	public HistoryNode() {
	}
	
	public HistoryNode(int numberOfEntries) {
		this.numberOfEntries = numberOfEntries;
	}
	
	public void setNumberOfEntries(int number) {
		this.numberOfEntries = number;
		pack();
	}
	
	private void pack() {

		int sizeEntries = entries.size();
		
		if ( sizeEntries > numberOfEntries ) {
			
			Date[] allDates = entries.keySet().toArray(new Date[]{});
			Date firstEntry = allDates[sizeEntries-numberOfEntries];
			Date lastEntry = allDates[sizeEntries-1];
			entries = entries.subMap(firstEntry, lastEntry);
		}
	}
	
	public void addEntry(String entry, Date dateOfEntry) {
		
		if ( entries.containsValue(entry) )
			return;
		
		entries.put(dateOfEntry, entry);
		
		pack();

	}
	
	public SortedMap<Date, String> getEntriesMap() {
		return entries;
	}

	public List<String> getEntries() {
		
		List<String> result = new LinkedList<String>();
		
		for ( String entry : entries.values() ) {
			result.add(entry);
		}
		return result;
	}
	
}
