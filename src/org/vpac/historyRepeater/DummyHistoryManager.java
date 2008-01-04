package org.vpac.historyRepeater;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.vpac.historyRepeater.model.HistoryNode;

public class DummyHistoryManager implements HistoryManager {

	public Map<String, HistoryNode> nodes = new HashMap<String, HistoryNode>();
	
	private int defaultNumberOfEntries = DEFAULT_NUMBER_OF_ENTRIES;
	
	public DummyHistoryManager() {
		
	}
	
	public void addHistoryEntry(String key, String entry, Date date) {
		
		getHistoryNode(key).addEntry(entry, date);
		
	}

	public void addHistoryEntry(String key, String entry, Date date,
			int numberOfEntriesForParentNode) {
		
		getHistoryNode(key).setNumberOfEntries(numberOfEntriesForParentNode);
		getHistoryNode(key).addEntry(entry, date);

	}

	public int getDefaultNumberOfEntriesPerNode() {
		return defaultNumberOfEntries;
	}

	public HistoryNode getHistoryNode(String key) {
		
		HistoryNode node = nodes.get(key);
		
		if ( node == null ) {
			node = new HistoryNode(defaultNumberOfEntries);
			node.addEntry("entry1", new Date());
			node.addEntry("entry2", new Date());
			node.addEntry("entry3", new Date());
			node.addEntry("entry4", new Date());
			node.addEntry("entry5", new Date());
			node.addEntry("entry6", new Date());
		}
		
		return node;
	}
	
	public List<String> getEntriesForNode(String key) {
		return getHistoryNode(key).getEntries();
	}

	public void setDefaultNumberOfEntriesPerNode(int i) {
		defaultNumberOfEntries = i;
	}

}
