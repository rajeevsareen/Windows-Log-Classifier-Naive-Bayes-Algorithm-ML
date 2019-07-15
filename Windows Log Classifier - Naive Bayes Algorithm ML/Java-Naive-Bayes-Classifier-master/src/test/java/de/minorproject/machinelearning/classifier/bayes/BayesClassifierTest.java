package de.minorproject.machinelearning.classifier.bayes;
import de.minorproject.machinelearning.classifier.bayes.MyConnect;

import java.sql.*;
import java.util.*;

import de.minorproject.machinelearning.classifier.Classifier;
public class BayesClassifierTest {

	private static final String CATEGORY_HARMFUL = "harmful";
	private static final String CATEGORY_HARMLESS = "harmless";
	private Classifier<String, String> bayes;
	Connection cn=null;
	ResultSet rs=null;
	Statement stmt=null;	
	//before
	 void setUp() {
			System.out.println("Getting Trained");
		try {
		bayes = new BayesClassifier<String, String>();
		cn=MyConnect.myconnection();
		stmt=cn.createStatement();
		rs=stmt.executeQuery("select * from naive");
		System.out.println("Training Completed!");
		while(rs.next()){
		//	System.out.println(rs.getString(1)+" "+rs.getString(2));
        final String[] harmlesslog = rs.getString("1").split("\\s");
        bayes.learn(CATEGORY_HARMLESS, Arrays.asList(harmlesslog));

        final String[] harmfullog = rs.getString("2").split("\\s");
        bayes.learn(CATEGORY_HARMFUL, Arrays.asList(harmfullog));
        
	}
        
} catch (Exception e){
	}
		}
	 public static void main(String[] args) {
		 BayesClassifierTest pd=new BayesClassifierTest();// TODO: handle exception
			pd.setUp();
		
   }
}

/*	//Test
	public void testStringClassification() {
		final String[] unknownlog1 = "Unable to set the DLL search path to the servicing stack folder. C:windows may not point to a valid Windows folder. - CDISMOSServiceManager::Final_OnConnect".split("\\s");
        final String[] unknownlog2 = "Enter DismInitializeInternal - DismInitializeInternal".split("\\s");

        Assert.assertEquals(CATEGORY_HARMLESS, bayes.classify(Arrays.asList(unknownlog1)).getCategory());
        Assert.assertEquals(CATEGORY_HARMFUL, bayes.classify(Arrays.asList(unknownlog2)).getCategory());
	}
	
	//test
	/*public void testStringClassificationInDetails() {
		
		final String[] unknownlog1 = "Unable to set the DLL search path to the servicing stack folder. C:windows may not point to a valid Windows folder. - CDISMOSServiceManager::Final_OnConnect".split("\\s");
		
		Collection<Classification<String, String>> classifications = ((BayesClassifier<String, String>) bayes).classifyDetailed(
                Arrays.asList(unknownlog1));
		
		List<Classification<String, String>> list = new ArrayList<Classification<String,String>>(classifications);
		
		Assert.assertEquals(CATEGORY_HARMFUL, list.get(0).getCategory());
		Assert.assertEquals(0.0078125, list.get(0).getProbability(), EPSILON);
		
		Assert.assertEquals(CATEGORY_HARMLESS, list.get(1).getCategory());
		Assert.assertEquals(0.0234375, list.get(1).getProbability(), EPSILON);
	}*/


