package swingy.model;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import swingy.model.heroes.Hero;
import swingy.model.heroes.Mage;

public class HeroTest {
	private static Validator validator;

	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void nameIsNull() {
		Hero hero = new Mage(null);

		Set<ConstraintViolation<Hero>> constraintViolations = validator.validate(hero);

		assertEquals(1, constraintViolations.size());
		assertEquals("may not be null", constraintViolations.iterator().next().getMessage());
	}

	@Test
	public void nameTooShort() {
		Hero hero = new Mage("\n");

		Set<ConstraintViolation<Hero>> constraintViolations = validator.validate(hero);

		assertEquals(1, constraintViolations.size());
		assertEquals("name too short", constraintViolations.iterator().next().getMessage());
	}
}
