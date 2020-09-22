package swingy.model;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import swingy.model.heroes.Hero;

public class HeroTest {
	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();

	public static boolean nameCheck(Hero player) {
		Set<ConstraintViolation<Hero>> constraintViolations = validator.validate(player);
		if (constraintViolations.size() != 0 ) {
			for (ConstraintViolation<Hero> constraintViolation : constraintViolations) {
				System.err.println(constraintViolation.getMessage());
			}
			return true;
		}
		return false;
	}
}
