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
	
	private HistoryManager manager = null;
	
	public HistoryNode(HistoryManager manager) {
		this.manager = null;
	}
	
	public HistoryNode(int numberOfEntries) {
		this.numberOfEntries = numberOfEntries;
	}
	
	public void setMaxNumberOfEntries(int number) {
		this.numberOfEntries = number;
		pack();
	}
	
	public int getNumberOfEntries() {
		return this.entries.size();
	}
	
	private void pack() {

		int sizeEntries = entries.size();
		
		if ( sizeEntries > numberOfEntries ) {
			
			Date[] allDates = entries.keySet().toArray(new Date[]{});
			Date firstEntry = allDates[sizeEntries-numberOfEntries];

			entries = entries.tailMap(firstEntry);
		}
		
	}
	
	public void addEntry(String entry, Date dateOfEntry) {
		
		if ( entries.containsValue(entry) ) {
			Date keyToDelete = null;
			for ( Date key : entries.keySet() ) {
				if ( entries.get(key).equals(entry) ) {
					keyToDelete = key;
					break;
				}
			}
			entries.remove(keyToDelete);
		}

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
	
	/**
	 * Returns the last entry for this node or null if there is no entry
	 * @return the last entry or null
	 */
	public String getLastEntry() {
		String result = null;
		try {
		result = entries.get(entries.lastKey());
		} catch (Exception e) {
			return null;
		}
		return result;
	}
	
}
