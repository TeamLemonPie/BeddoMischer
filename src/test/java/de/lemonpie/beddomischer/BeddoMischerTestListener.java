package de.lemonpie.beddomischer;

import de.lemonpie.beddomischer.validator.CardValidator;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class BeddoMischerTestListener extends RunListener {

	@Override
	public void testRunStarted(Description description) throws Exception {
	}

	@Override
	public void testRunFinished(Result result) throws Exception {
	}

	@Override
	public void testStarted(Description description) throws Exception {
		BeddoMischerMain.startUp();
		CardValidator.getInstance().clear();
	}

	@Override
	public void testFinished(Description description) throws Exception {
	}

	@Override
	public void testFailure(Failure failure) throws Exception {
	}

	@Override
	public void testAssumptionFailure(Failure failure) {
	}

	@Override
	public void testIgnored(Description description) throws Exception {
	}
}
