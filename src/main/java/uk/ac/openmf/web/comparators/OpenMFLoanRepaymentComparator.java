package uk.ac.openmf.web.comparators;

import java.util.Comparator;
import uk.ac.openmf.model.nosql.OpenMFLoanRepaymentNoSql;

public class OpenMFLoanRepaymentComparator implements Comparator<OpenMFLoanRepaymentNoSql>{

	public enum OpenMFLoanRepaymentComparatorKey {
		serialnumber("serialnumber"),
		scheduledate("scheduledate");

		private final String comparatorKey;

		private OpenMFLoanRepaymentComparatorKey(String type) {
			this.comparatorKey = type;
		}

		public boolean equalsType(String otherType) {
			return (otherType == null) ? false : comparatorKey.equals(otherType);
		}

		public String toString() {
			return comparatorKey;
		}
	}

	public enum ComparatorSortOrder{
		asc("asc"),
		des("des");

		private final String comparatorOrderKey;

		private ComparatorSortOrder(String type) {
			this.comparatorOrderKey = type;
		}

		public boolean equalsType(String otherType) {
			return (otherType == null) ? false : comparatorOrderKey.equals(otherType);
		}

		public String toString() {
			return comparatorOrderKey;
		}
	}

	private String comparatorValue = "CreationDate";
	private String comparatorOrder = "asc";

	public OpenMFLoanRepaymentComparator(String comparatorVal, String sortOrder){
		this.comparatorValue = comparatorVal;
		this.comparatorOrder = sortOrder;
	}


	@Override
	public int compare(OpenMFLoanRepaymentNoSql o1, OpenMFLoanRepaymentNoSql o2) {
		if(ComparatorSortOrder.valueOf(comparatorOrder).equals(ComparatorSortOrder.des)){
			OpenMFLoanRepaymentNoSql temp = o1;
			o1 = o2;
			o2 = temp;
		}
		if(o1.getSerialnumber() == null)
			o1.setSerialnumber("0");
		if(o2.getSerialnumber() == null)
			o2.setSerialnumber("0");
		switch (OpenMFLoanRepaymentComparatorKey.valueOf(comparatorValue)) {
		case serialnumber: return  new Integer(o1.getSerialnumber()).intValue() - new Integer(o2.getSerialnumber()).intValue();
		default: return  new Integer(o1.getSerialnumber()).intValue() - new Integer(o2.getSerialnumber()).intValue();
		}
	}

}
