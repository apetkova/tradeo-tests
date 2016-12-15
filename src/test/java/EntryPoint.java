import java.util.ArrayList;
import java.util.List;

import com.beust.testng.TestNG;

public class EntryPoint {
	public static void main(String[] args) {
		TestNG testNg = new TestNG();
		List<String> suites = new ArrayList<String>();
		suites.add("test_suite.xml");
		testNg.setTestSuites(suites);
		testNg.run();
	}
}
