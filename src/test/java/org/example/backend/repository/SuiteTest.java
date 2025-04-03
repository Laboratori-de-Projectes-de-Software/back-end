package org.example.backend.repository;
import org.junit.jupiter.api.*;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;




@Suite
@SuiteDisplayName("Data test suite")
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@SelectClasses({UserAdapterIntegrationTest.class,
                BotAdapterIntegrationTest.class,
                LigaAdapterIntegrationTest.class,
                PartidaAdapterIntegrationTest.class,
                MensajeAdapterIntegrationTest.class})
public class SuiteTest {
}
