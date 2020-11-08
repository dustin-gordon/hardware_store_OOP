package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({HardwareStoreTest.class})
public class TestRunner {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(HardwareStoreTest.class);
		if (result.getFailures().size() == 0) {
			System.out.println("All tests passed!");
		}
		else
		{
			System.out.println("\nTotal tests failed = " +result.getFailures().size()+ ".");
			int counter = 1;
			for (Failure failure : result.getFailures()) {
				System.out.println(counter+ ": " +failure.toString());
				counter++;
			}
		}
	}
}
