	package com.everis.tests;

import org.junit.ClassRule;
import org.junit.runner.RunWith;

import com.everis.core.Hook;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", tags = "@Smart", glue = {"com.everis"},
	monochrome = true, dryRun = false, plugin = { "json:target/cucumber.json", "rerun:target/rerun.txt" })
public class Runner {

	@ClassRule
	public static Hook testRule = new Hook();
}