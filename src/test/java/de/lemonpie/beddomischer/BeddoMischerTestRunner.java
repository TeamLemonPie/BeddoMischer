package de.lemonpie.beddomischer;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

public class BeddoMischerTestRunner extends BlockJUnit4ClassRunner {


	public BeddoMischerTestRunner(Class testClass) throws InitializationError {
		super(testClass);
	}

	@Override
	public void run(RunNotifier notifier) {
		notifier.addListener(new BeddoMischerTestListener());
		notifier.fireTestRunStarted(getDescription());
		super.run(notifier);
	}
}
