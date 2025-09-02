package com.saha.cucumber.test;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(io.cucumber.junit.Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = { "com.saha.cucumber.step", "com.saha.cucumber.driver" },
        plugin = { "pretty", "summary" },
        monochrome = true
)
public class CucumberRunner {}
