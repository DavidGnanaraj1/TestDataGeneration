package com.atmecs.assessment.helpermethods;

import java.time.LocalDate;

public class PolicyEffectiveDateCalculatorHelpers {

	LocalDate localDate = LocalDate.now();

	public String subTractsTheNoOfMonths(int noOfMonthsNeedsToBeSubtracted) {

		LocalDate dateAfterSubtractingTheMonths = localDate.minusMonths(noOfMonthsNeedsToBeSubtracted);
		String calculatedDateInStringFormat = dateAfterSubtractingTheMonths.toString();
		return calculatedDateInStringFormat;
	}

	public String addingTheNoOfDays(int noOfDaysNeedsToBeAdded) {

		LocalDate dateAfterSubtractingTheMonths = localDate.minusMonths(noOfDaysNeedsToBeAdded);
		String calculatedDateInStringFormat = dateAfterSubtractingTheMonths.toString();
		return calculatedDateInStringFormat;
	}

	public String addingTheNoOfMonths(int noOfMonthsNeedsToBeAdded) {

		LocalDate dateAfterSubtractingTheMonths = localDate.minusMonths(noOfMonthsNeedsToBeAdded);
		String calculatedDateInStringFormat = dateAfterSubtractingTheMonths.toString();
		return calculatedDateInStringFormat;
	}
}
