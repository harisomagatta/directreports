package converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.Person;
import model.PersonTreeNode;

/**
 * Instructions:
 *  Complete {@link #generateTree(Iterable)} method such that the unit test will run and pass.
 *  Create any additional methods as needed.
 */
public class PersonToPersonTreeNodeConverter {

    /**
     * Given an iterable of {@link Person}s, should produce the root person tree node (the CEO)
     * and each person's list of direct reports is correct all the way down the tree.
     */
    public PersonTreeNode generateTree(Iterable<Person> allEmployees) {
    	    	
    	Collections.sort((List<Person>)allEmployees, new Comparator<Person>(){
            
            public int compare(Person a1, Person a2) {
            	if("".equals(a1.getManager()) ||  "".equals(a2.getManager())){
            		return 0;
            	}else if(a1.getManager().equals(a2.getManager())){
        			return 0;
        		}else{
        			return -1;
        		}
            }});

    	
    	for (Person person : allEmployees) {
    		System.out.println(person.getName() +" reports to " +person.getManager().getName());
    		
    	}
    	
    	/*
    	ArrayList<Person> mangers = new ArrayList<Person>();
    	PersonTreeNode ceoTreeNode = new PersonTreeNode();
    	
        
        // TODO ENTER CODE HERE
    		for (Person person : allEmployees) {
    			//String indent = createIndent(node.getLevel());
    			//System.out.println(indent + node.data);
    			
    			PersonTreeNode personTreeNode = new PersonTreeNode();
    			
    			/**
    			 * Check to see if the manager already exists in managers  list.
    			 * If exists, add the current person to managers direct report list
    			 * else addmanager to manager list
    			 
    			if(person.getManager().equals("")){	//CEO
    				ceoTreeNode.setPerson(person);
    			}else if(mangers.contains(person.getManager())){
    				PersonTreeNode personTreeNode1 = new PersonTreeNode();
    				personTreeNode1.setPerson(person);
    				personTreeNode.getDirectReports().add(personTreeNode1);
    				
    			}else
    				mangers.add(person);
    				
    		}
    	
    	*/
        return null;
    }
    
    
    private static String createIndent(int depth) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < depth; i++) {
			sb.append(' ');
		}
		return sb.toString();
	}
    
}