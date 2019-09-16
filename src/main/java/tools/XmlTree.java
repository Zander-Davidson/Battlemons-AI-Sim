package tools;

import java.util.ArrayList;
import java.util.HashMap;

public class XmlTree {

	public class XmlNode extends XmlTree {
		// <attribute name, attribute value>
		private HashMap<String, String> fAttributes = new HashMap<String, String>();
		private String fTag; // element tag
		private String fData; // inner element string
		private int fDepth;

		public XmlNode(HashMap<String, String> attributes, String tag, String data) {
			fAttributes = attributes;
			fTag = tag;
			fData = data;
			fDepth = fParent.getDepth() + 1;
		}

		public HashMap<String, String> getAttributes() {
			return fAttributes;
		}

		public String getTag() {
			return fTag;
		}

		public String getData() {
			return fData;
		}

		public int getDepth() {
			return fDepth;
		}
	}

	private ArrayList<XmlNode> fChildren;
	private XmlNode fParent = null;
	private XmlNode fYounger = null;

	private XmlNode fRoot = null;
	private XmlNode fCurrent = null;

	private void init(HashMap<String, String> attributes, String tag, String data) {
		fRoot = new XmlNode(attributes, tag, data);
		fCurrent = fRoot;
	}

	public XmlNode currentNode() {
		return fCurrent;
	}

	// returns the next node, depth-first style
	public XmlNode nextNode(int i) {
		if (!fChildren.isEmpty()) {
			return fChildren.get(i);
		} else if (fYounger != null) {
			return fYounger;
		} else {
			return fParent.nextNode(i + 1);
		}
	}

	public void newLeaf(HashMap<String, String> attributes, String tag, String data) {
		XmlNode newLeaf = new XmlNode(attributes, tag, data);
		newLeaf.setParent(fCurrent);
		if (fCurrent.getLastChild() != null) {
			fCurrent.getLastChild().setYounger(newLeaf);
		}
		fCurrent.addChild(newLeaf);
	}

	public void newChild(HashMap<String, String> attributes, String tag, String data) {
		fCurrent.newLeaf(attributes, tag, data);
		XmlNode newChild = fCurrent.getLastChild();
		newChild.initChildren();
		fCurrent = newChild;
	}

	public void newParent(HashMap<String, String> attributes, String tag, String data) {
		fCurrent.getParent().newChild(attributes, tag, data);
	}

	public void goToParent() {
		if (fCurrent.getParent() != null) {
			fCurrent = fCurrent.getParent();
		}
	}

	public void goToRoot() {
		fCurrent = fRoot;
	}

	protected void addChild(XmlNode child) {
		fChildren.add(child);
	}

	protected void setParent(XmlNode parent) {
		fParent = parent;
	}

	protected XmlNode getParent() {
		return fParent;
	}

	protected void initChildren() {
		fChildren = new ArrayList<XmlNode>();
	}

	protected XmlNode getLastChild() {
		return fChildren.get(fChildren.size());
	}

	protected XmlNode getYounger() {
		return fYounger;
	}

	protected void setYounger(XmlNode younger) {
		fYounger = younger;
	}
}
