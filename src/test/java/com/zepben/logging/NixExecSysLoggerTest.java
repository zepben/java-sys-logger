package com.zepben.logging;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NixExecSysLoggerTest {

    @Test
    public void checkCorrectCommandBuild() {
        NixExecSysLogger nixExecSysLogger = new NixExecSysLogger();
        ProcessBuilder processBuilder = nixExecSysLogger.processBuilder("local0.err", "tag", "message");

        List<String> expectedCommandList = Arrays.asList("logger", "-p", "local0.err", "-t", "tag", "message");
        assertEquals(processBuilder.command(), expectedCommandList);
    }

}
