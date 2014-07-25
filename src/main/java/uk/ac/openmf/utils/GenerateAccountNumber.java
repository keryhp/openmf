package uk.ac.openmf.utils;


/**
 * A utility class.
 *
 */
public class GenerateAccountNumber {

  private static GenerateAccountNumber generateAccNumber = null;
  
  public static GenerateAccountNumber getAccNumberService(){
	  if(generateAccNumber == null){
		  generateAccNumber = new GenerateAccountNumber();
	  }
	  return generateAccNumber;
  }

  public String generateAccNumber(int nextAvail){
	  return "OMF-HO-GEN-00" + nextAvail;
  }
}
