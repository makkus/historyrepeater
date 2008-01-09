package org.vpac.historyRepeater;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.vpac.historyRepeater.model.HistoryNode;

/**
 * Just a quick and dirty implementation of the history interface.
 * Let's see whether there are proplems.
 * 
 * @author Markus Binsteiner
 *
 */
public class SimpleHistoryManager implements HistoryManager {
	
	public Map<String, HistoryNode> nodes = new HashMap<String, HistoryNode>();
	
	PropertiesConfiguration config = null;
	
	private int defaultNumberOfEntries = DEFAULT_NUMBER_OF_ENTRIES;
	
	public SimpleHistoryManager(File configFile) {
		try {
			config = new PropertiesConfiguration(configFile);
			config.setDelimiterParsingDisabled(true);
			config.setAutoSave(true);
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		if ( numberOfEntriesForParentNode > 0 ) {
			getHistoryNode(key).setMaxNumberOfEntries(numberOfEntriesForParentNode);
//			config.addProperty(key+"_max", numberOfEntriesForParentNode);
		}
		getHistoryNode(key).addEntry(entry, date);
		
		config.clearProperty(key);
		String[] new_prop = new String[getHistoryNode(key).getEntries().size()];
		int i = 0;
		for ( Date dateKey : getHistoryNode(key).getEntriesMap().keySet() ) {
			new_prop[i] = new Long(dateKey.getTime()).toString()+","+getHistoryNode(key).getEntriesMap().get(dateKey);

			config.addProperty(key, new_prop[i]);			
			i++;
		}

	}
	

	public int getDefaultNumberOfEntriesPerNode() {
		try {
			int def = config.getInt("default_max");
			return def;
		} catch (Exception e) {
			config.clearProperty("default_max");
			config.setProperty("default_max", DEFAULT_NUMBER_OF_ENTRIES);
			return DEFAULT_NUMBER_OF_ENTRIES;
		}
	}

	private HistoryNode getHistoryNode(String key) {
		
		HistoryNode node = nodes.get(key);
		
		if ( node == null ) {
			
			String[] entries = config.getStringArray(key);
			int maxEntries = getDefaultNumberOfEntriesPerNode();
//			try {
//				maxEntries = config.getInt(key+"_max");
//			} catch (Exception e) {
//				config.clearProperty(key+"_max");
//				config.setProperty(key+"_max", maxEntries);
//			}
			
			node = new HistoryNode(maxEntries);
			
			for ( String entry : entries ) {
				int index = entry.indexOf(",");
				Long date = Long.parseLong(entry.substring(0,index));
				String value = entry.substring(index+1);
				node.addEntry(value, new Date(date));
			}
			
			nodes.put(key, node);
		}
		
		return node;
	}

	public void setDefaultNumberOfEntriesPerNode(int i) {
		config.clearProperty("default_max");
		config.setProperty("default_max", i);
		
	}

	public int getMaxNumberOfEntries(String key) {
		
		int max = -1;
		max = getHistoryNode(key).getNumberOfEntries();
//		config.clearProperty(key+"_max");
//		config.setProperty(key+"_max", max);
		return max;
	}

	public void setMaxNumberOfEntries(String key, int max) {
		getHistoryNode(key).setMaxNumberOfEntries(max);
//		config.clearProperty(key+"_max");
//		config.setProperty(key+"_max", max);
	}
	
	public List<String> getEntries(String key) {
		return getHistoryNode(key).getEntries();
	}

}