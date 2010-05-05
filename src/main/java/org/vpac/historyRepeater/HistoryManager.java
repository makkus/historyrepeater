package org.vpac.historyRepeater;

import java.util.Date;
import java.util.List;

public interface HistoryManager {

	public static final int DEFAULT_NUMBER_OF_ENTRIES = 1;

	/**
	 * Adds a history entry with the "now" time.
	 * 
	 * @param key
	 *            the key
	 * @param entry
	 *            the entry
	 */
	public void addHistoryEntry(String key, String entry);

	public void addHistoryEntry(String key, String entry, Date date);

	public void addHistoryEntry(String key, String entry, Date date,
			int numberOfEntriesForParentNode);

	public int getDefaultNumberOfEntriesPerNode();

	public List<String> getEntries(String key);

	public String getLastEntry(String key);

	public int getMaxNumberOfEntries(String key);

	public void setDefaultNumberOfEntriesPerNode(int i);

	public void setMaxNumberOfEntries(String key, int max);

	// public void addHistoryEntry(HistoryNode parent, String key, String entry,
	// Date date);
	//	 
	// public void addHistoryEntry(HistoryNode parent, String key, String entry
	// ,Date date, int numberOfEntrieSForParentNode);

}
