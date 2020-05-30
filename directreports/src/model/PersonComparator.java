/**
 * 
 */
package model;

import java.util.Comparator;

/**
 * @author Hari Somagatta
 *
 */
public class PersonComparator implements  Comparator<Person> {

	@Override
	public int compare(Person a1, Person a2) {
		// TODO Auto-generated method stub
		if(a1.getManager().equals(a2.getManager())){
			return 0;
		}else{
			return -1;
		}
	}
	

}
