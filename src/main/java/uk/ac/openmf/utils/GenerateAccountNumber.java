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
  
  public String generateGroupAccNumber(int nextAvail){
	  return "OMF-HO-GRP-00" + nextAvail;
  }
  
  public String generateCoAAccNumber(int nextAvail){
	  return "OMF-HO-COA-00" + nextAvail;
  }
  
  public String generateTransactionNumber(int nextAvail){
	  return "OMF-HO-TXN-00" + nextAvail;
  }
  
  public String generateLedgerAccNumber(int nextAvail){
	  return "OMF-HO-GLD-00" + nextAvail;
  }
  
  public String generateJournalAccNumber(int nextAvail){
	  return "OMF-HO-GJR-00" + nextAvail;
  }
}
