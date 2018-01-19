package io.zilker.bean;

public class phone_details {
		private long ph_number;
		private String ext_code;
		private String a_code;
		private String c_code;
		private int type;
		
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
		public long getPh_number() {
			return ph_number;
		}
		public void setPh_number(long ph_number) {
			this.ph_number = ph_number;
		}
		
		public String getExt_code() {
			return ext_code;
		}
		public void setExt_code(String ext_code) {
			this.ext_code = ext_code;
		}
		public String getA_code() {
			return a_code;
		}
		public void setA_code(String a_code) {
			this.a_code = a_code;
		}
		public String getC_code() {
			return c_code;
		}
		public void setC_code(String c_code) {
			this.c_code = c_code;
		}
		
	}
