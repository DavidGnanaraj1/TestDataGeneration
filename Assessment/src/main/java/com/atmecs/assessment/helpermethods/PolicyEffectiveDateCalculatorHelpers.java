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

		LocalDate dateAfterAddingNoOfDays = localDate.plusDays(noOfDaysNeedsToBeAdded);
		String calculatedDateInStringFormat = dateAfterAddingNoOfDays.toString();
		return calculatedDateInStringFormat;
	}

	public String addingTheNoOfMonths(int noOfMonthsNeedsToBeAdded) {

		LocalDate dateAfterAddingTheMonths = localDate.plusMonths(noOfMonthsNeedsToBeAdded);
		String calculatedDateInStringFormat =dateAfterAddingTheMonths.toString();
		return calculatedDateInStringFormat;
	}
}
