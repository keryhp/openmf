package uk.ac.openmf.utils;

public enum MFIAccountTypes {
	ACCOUNT_PAYABLE(0),
	ACCOUNT_RECIEVABLE(1);

	private int bit;
	MFIAccountTypes(int bit) {
		this.bit = bit;
	}

	public int getBit() {
		return bit;
	}

	public String getAccountType(){
		return toString();
	}
}
