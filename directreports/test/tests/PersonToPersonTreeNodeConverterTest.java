package tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.Person;
import model.PersonTreeNode;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import converter.PersonToPersonTreeNodeConverter;

public class PersonToPersonTreeNodeConverterTest {

    private List<Person> allEmployees;

    @Before
    public void setUp() throws Exception {
        
        allEmployees = new ArrayList<>();

        Person kirk = new Person("Kirk", null);
        allEmployees.add(kirk);
        Person mark = new Person("Mark", kirk);
        allEmployees.add(mark);
        Person tomP = new Person("Tom", mark);
        allEmployees.add(tomP);
        Person nick = new Person("Nick", tomP);
        allEmployees.add(nick);
        Person ben = new Person("Ben", tomP);
        allEmployees.add(ben);
        Person david = new Person("David", ben);
        allEmployees.add(david);
        Person stacey = new Person("Stacey", nick);
        allEmployees.add(stacey);
        Person corey = new Person("Corey", nick);
        allEmployees.add(corey);
        Person tom = new Person("Tom", stacey);
        allEmployees.add(tom);
        Person julie = new Person("Julie", stacey);
        allEmployees.add(julie);
        
    }
    
    private static String outputFlatTree(PersonTreeNode treeNode) {
        
        String result = treeNode.getPerson().getName();
        boolean first = true;
        
        if (!treeNode.getDirectReports().isEmpty()) {
            
            result += "{";
            List<PersonTreeNode> directReports = treeNode.getDirectReports();
            List<PersonTreeNode> sortedDirectReports = new ArrayList<PersonTreeNode>(directReports);
            
            Collections.sort(sortedDirectReports, new Comparator<PersonTreeNode>() {
                @Override
                public int compare(PersonTreeNode o1, PersonTreeNode o2) {
                    return String.CASE_INSENSITIVE_ORDER.compare(o1.getPerson().getName(), o2.getPerson().getName());
                }
            });
        
            for (PersonTreeNode childNode : sortedDirectReports) {
                if (!first) {
                    result +=",";
                }
                result += outputFlatTree(childNode);
                first = false;
            }
            
            result += "}";
        }
        
        return result;
    }

    /**
     * Verify that {@link PersonToPersonTreeNodeConverter} will produce the root person tree node (the CEO)
     * and that each persons list of direct reports is correct all the way down the tree.
     */
    @Test
    public void testGenerateTree() {
        
        ArrayList<Person> shuffledList = new ArrayList<>(allEmployees);
        Collections.shuffle(shuffledList);
        
        PersonTreeNode rootNode = new PersonToPersonTreeNodeConverter().generateTree(shuffledList);
        
        Assert.assertNotNull(rootNode);
        
        Assert.assertEquals("Kirk", rootNode.getPerson().getName());
        
        String flatTree = outputFlatTree(rootNode);
        
        Assert.assertEquals("Kirk{Mark{Tom{Ben{David},Nick{Corey,Stacey{Julie,Tom}}}}}", flatTree);
    }

}