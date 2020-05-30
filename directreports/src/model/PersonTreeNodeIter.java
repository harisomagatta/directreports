/**
 * 
 */
package model;

import java.util.Iterator;

/**
 * @author Hari Somagatta
 *
 */
public class PersonTreeNodeIter implements Iterator<PersonTreeNode> {

	enum ProcessStages {
		ProcessParent, ProcessChildCurNode, ProcessChildSubNode
	}

	private PersonTreeNode PersonTreeNode;

	public PersonTreeNodeIter(PersonTreeNode PersonTreeNode) {
		this.PersonTreeNode = PersonTreeNode;
		this.doNext = ProcessStages.ProcessParent;
		//this.childrenCurNodeIter = PersonTreeNode.children.iterator();
	}

	private ProcessStages doNext;
	private PersonTreeNode next;
	private Iterator<PersonTreeNode> childrenCurNodeIter;
	private Iterator<PersonTreeNode> childrenSubNodeIter;

	@Override
	public boolean hasNext() {

		if (this.doNext == ProcessStages.ProcessParent) {
			this.next = this.PersonTreeNode;
			this.doNext = ProcessStages.ProcessChildCurNode;
			return true;
		}

		if (this.doNext == ProcessStages.ProcessChildCurNode) {
			if (childrenCurNodeIter.hasNext()) {
				PersonTreeNode childDirect = childrenCurNodeIter.next();
				//childrenSubNodeIter = childDirect.iterator();
				this.doNext = ProcessStages.ProcessChildSubNode;
				return hasNext();
			}

			else {
				this.doNext = null;
				return false;
			}
		}
		
		if (this.doNext == ProcessStages.ProcessChildSubNode) {
			if (childrenSubNodeIter.hasNext()) {
				this.next = childrenSubNodeIter.next();
				return true;
			}
			else {
				this.next = null;
				this.doNext = ProcessStages.ProcessChildCurNode;
				return hasNext();
			}
		}

		return false;
	}

	@Override
	public PersonTreeNode next() {
		return this.next;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
	
	


}
