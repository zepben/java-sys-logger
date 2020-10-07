/*
 * Copyright 2020 Zeppelin Bend Pty Ltd
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.zepben.logging;


import org.junit.jupiter.api.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SysLoggerTest {

    @Test
    public void checkSysLoggerInitDefaultExecutorAndLogger() {
        SysLogger logger = SysLogger.logger(LogFacility.LOCAL0, "tag");

        assertNotNull(logger.executor);
        assertEquals(logger.executor, SysLogger.defaultExecutor);
        assertTrue(logger.execSysLogger instanceof NixExecSysLogger);
        assertEquals(logger.facility, LogFacility.LOCAL0);
        assertEquals(logger.tag, "tag");
    }

    @Test
    public void checkSysLoggerInitCustomExecutorAndLogger() {
        Executor customExecutor = new ForkJoinPool();
        ExecSysLogger customExecSysLogger = SysLogger.execSysLoggerFactory.create();
        SysLogger logger = SysLogger.logger(customExecutor, customExecSysLogger, LogFacility.LOCAL1, "tag1");

        assertEquals(logger.executor, customExecutor);
        assertEquals(logger.execSysLogger, customExecSysLogger);
        assertEquals(logger.facility, LogFacility.LOCAL1);
        assertEquals(logger.tag, "tag1");
    }

    @Test
    public void checkSysLoggerInitCustomExecutorAndDefaultLogger() {
        Executor customExecutor = new ForkJoinPool();
        SysLogger logger = SysLogger.logger(customExecutor, LogFacility.LOCAL6, "tag2");

        assertEquals(logger.executor, customExecutor);
        assertTrue(logger.execSysLogger instanceof NixExecSysLogger);
        assertEquals(logger.facility, LogFacility.LOCAL6);
        assertEquals(logger.tag, "tag2");
    }

    @Test
    public void checkLog() {
        ExecSysLogger execSysLogger = mock(NixExecSysLogger.class);
        SysLogger logger = SysLogger.logger(SysLogger.defaultExecutor, execSysLogger, LogFacility.LOCAL0, "tag");
        logger.log(LogLevel.ERROR, "message");

        assertTrue(logger.execSysLogger instanceof NixExecSysLogger);
        verify(execSysLogger, times(1)).processBuilder("local0.err", "tag", "message");
    }

    @Test
    public void checkLogError() {
        SysLogger logger = spy(SysLogger.logger(LogFacility.LOCAL0, "error tag"));

        logger.logError("error message");
        verify(logger, times(1)).log(LogLevel.ERROR, "error message");
    }

    @Test
    public void checkLogInfo() {
        SysLogger logger = spy(SysLogger.logger(LogFacility.LOCAL0, "info tag"));

        logger.logInfo("info message");
        verify(logger, times(1)).log(LogLevel.INFO, "info message");
    }

    @Test
    public void checkLogWarn() {
        SysLogger logger = spy(SysLogger.logger(LogFacility.LOCAL0, "warn tag"));

        logger.logWarn("warn message");
        verify(logger, times(1)).log(LogLevel.WARN, "warn message");
    }

    @Test
    public void checkLogCritical() {
        SysLogger logger = spy(SysLogger.logger(LogFacility.LOCAL0, "critical tag"));

        logger.logCritical("critical message");
        verify(logger, times(1)).log(LogLevel.CRITICAL, "critical message");
    }

}
