package com.debugnuggets.flipkz.bdd.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:com.debugneggets.flipkz.bdd.features", glue = "steps")
public class LogInTestRunner {

}
