package converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import model.Person;
import model.PersonTreeNode;

/**
 * Instructions: Complete {@link #generateTree(Iterable)} method such that the
 * unit test will run and pass. Create any additional methods as needed.
 */
public class PersonToPersonTreeNodeConverter2 {

	/**
     * Given an iterable of {@link Person}s, should produce the root person tree node (the CEO)
     * and each person's list of direct reports is correct all the way down the tree.
     */
    public PersonTreeNode generateTree(Iterable<Person> allEmployees) {
        	
    	HashMap<String, List<PersonTreeNode>> managersMap = new HashMap<>();
    	PersonTreeNode orgRoot = null;
    	List<PersonTreeNode> directReports = new ArrayList<PersonTreeNode>();
    	
    	//Loop through all employee list
    	for (Person person : allEmployees) {    	
    		//Get Manager of each employee
    		Person managerPerson = person.getManager();
    		
    		//If manager is null, the person is CEO and set as Org Root and set direct reportees.
    		if(managerPerson == null){
    			System.out.println(person.getName() +" is CEO of the company");	 
    			orgRoot = new PersonTreeNode();
				orgRoot.setPerson(person);
				orgRoot.setDirectReports(directReports);
    		}else{ //if person reports to a Manager 
    			String managerName = managerPerson.getName();    		
    			System.out.println(person.getName() +" reports to " +managerName);
	    		
    			//see if you already have the manager listed in the managers map
	    		if(managersMap.containsKey(managerName)){
	    			
    				PersonTreeNode reportee = new PersonTreeNode();
    				reportee.setPerson(person);;
    			
    				// Get direct reportees and add the current employee to the list.
    				managersMap.get(managerName).add(reportee);
	    				
	    		}else{//if not, the manager person is new manager to be added to the list
	    					    				
	    			List<PersonTreeNode> directReportees = null;
	    			//check to see if the person is manager
    				//if(!managersMap.containsKey(person.getName())){
	    				directReportees = new ArrayList<PersonTreeNode>();
	    				PersonTreeNode reportee = new PersonTreeNode();
	    				reportee.setPerson(person);
	    				directReportees.add(reportee);
    			/*	}else{
    					directReportees = new ArrayList<PersonTreeNode>();
    					directReportees.add(e);
    					//directReportees = managersMap.get(person.getName());
    				}
    				
    				
    				PersonTreeNode managerNode = new PersonTreeNode();
    				managerNode.setPerson(managerPerson);
    				managerNode.setDirectReports(directReportees);*/
    					    				
    				managersMap.put(managerName, directReportees);
	    		}
    		}
    		
    	}
    	
    	/*for(Iterator i=managersMap.keySet().iterator();i.hasNext();){
    		String manager =(String) i.next();
    		List<String> reports = managersMap.get(manager);
    		System.out.println(reports.size() +"reports to"+ manager);
    		
    	}*/
    	
    	return orgRoot;
    	
    }

}