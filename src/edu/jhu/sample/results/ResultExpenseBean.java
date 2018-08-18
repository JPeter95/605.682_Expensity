package edu.jhu.sample.results;

import java.io.Serializable;

public class ResultExpenseBean implements Serializable {

		private static final long serialVersionUID = 1L;
		private String description;
		private String amount;
		private String date;
		private String note;

		public ResultExpenseBean() {
			this.description = "";
			this.amount = "";
			this.date = "";
			this.note = "";
		}

		public ResultExpenseBean(String description, String amount, String date, String note) {
			super();
			this.description = description;
			this.amount = amount;
			this.date = date;
			this.note = note;
		}

		/**
		 * @return the description
		 */
		public String getDescription() {
			return description;
		}

		/**
		 * @param description the description to set
		 */
		public void setDescription(String description) {
			this.description = description;
		}

		/**
		 * @return the amount
		 */
		public String getAmount() {
			return amount;
		}

		/**
		 * @param amount the amount to set
		 */
		public void setAmount(String amount) {
			this.amount = amount;
		}

		/**
		 * @return the date
		 */
		public String getDate() {
			return date;
		}

		/**
		 * @param date the date to set
		 */
		public void setDate(String date) {
			this.date = date;
		}

		/**
		 * @return the note
		 */
		public String getNote() {
			return note;
		}

		/**
		 * @param note the note to set
		 */
		public void setNote(String note) {
			this.note = note;
		}
}
