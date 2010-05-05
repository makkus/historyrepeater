package org.vpac.historyRepeater;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.vpac.historyRepeater.model.HistoryNode;

public class DummyHistoryManager implements HistoryManager {

	private final boolean debug = false;

	public Map<String, HistoryNode> nodes = new HashMap<String, HistoryNode>();

	private int defaultNumberOfEntries = DEFAULT_NUMBER_OF_ENTRIES;

	public DummyHistoryManager() {

		if (debug) {
			this.addHistoryEntry("submissionEmail", "markus@vpac.org");
			this.addHistoryEntry("submissionEmailChecked", "true");
		}

	}

	public void addHistoryEntry(String key, String entry) {

		addHistoryEntry(key, entry, new Date());

	}

	public void addHistoryEntry(String key, String entry, Date date) {

		addHistoryEntry(key, entry, date, -1);

	}

	public void addHistoryEntry(String key, String entry, Date date,
			int numberOfEntriesForParentNode) {

		if (numberOfEntriesForParentNode > 0) {
			getHistoryNode(key).setMaxNumberOfEntries(
					numberOfEntriesForParentNode);
		}
		getHistoryNode(key).addEntry(entry, date);

	}

	public int getDefaultNumberOfEntriesPerNode() {
		return defaultNumberOfEntries;
	}

	public List<String> getEntries(String key) {
		return getHistoryNode(key).getEntries();
	}

	public List<String> getEntriesForNode(String key) {
		return getHistoryNode(key).getEntries();
	}

	private HistoryNode getHistoryNode(String key) {

		HistoryNode node = nodes.get(key);

		if (node == null) {
			node = new HistoryNode(defaultNumberOfEntries);
			if (debug) {
				node.addEntry("entry_youngest", new Date(
						new Date().getTime() - 30000));
				node.addEntry("entry_2nd_youngest", new Date(new Date()
						.getTime() - 35000));
				node.addEntry("entry_medium", new Date(
						new Date().getTime() - 40000));
				node.addEntry("entry_2nd_oldest", new Date(
						new Date().getTime() - 45000));
				node.addEntry("entry_oldest", new Date(
						new Date().getTime() - 50000));
			}
			nodes.put(key, node);
		}

		return node;
	}

	public String getLastEntry(String key) {
		return getEntries(key).get(getEntries(key).size());
	}

	public int getMaxNumberOfEntries(String key) {
		return getHistoryNode(key).getNumberOfEntries();
	}

	public void setDefaultNumberOfEntriesPerNode(int i) {
		defaultNumberOfEntries = i;
	}

	public void setMaxNumberOfEntries(String key, int max) {
		getHistoryNode(key).setMaxNumberOfEntries(max);
	}

}
