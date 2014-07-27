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

  public String generateClientAccNumber(int nextAvail){
	  return "OMF-HO-GEN-00" + nextAvail;
  }
  
  public String generateLoanAccNumber(int nextAvail){
	  return "OMF-HO-LNA-00" + nextAvail;
  }
  
  public String generateSavingsAccNumber(int nextAvail){
	  return "OMF-HO-SVG-00" + nextAvail;
  }
}
