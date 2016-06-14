package edu.csula.datascience.elasticsearch;

import org.elasticsearch.node.Node;
import static org.elasticsearch.node.NodeBuilder.nodeBuilder;
import org.bson.Document;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;

public abstract class Exporter {
	protected Node node;
	protected Client client;

	public Exporter(String clusterName) {
		this.node = nodeBuilder()
				.settings(Settings.builder().put("cluster.name", clusterName).put("path.home", "elasticsearch-data"))
				.node();
		this.client = this.node.client();
	}

	abstract void exportToES();

	abstract void insertObjAsJson(Object object);

	abstract boolean validateDocument(Document document);

	protected boolean validateValue(String value) {
		boolean valueValid = false;
		if (value != null && !value.isEmpty()) {
			valueValid = true;
		}
		return valueValid;
	}
}
