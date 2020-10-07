/*
 * Copyright 2020 Zeppelin Bend Pty Ltd
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.zepben.logging;

import com.zepben.annotations.EverythingIsNonnullByDefault;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@EverythingIsNonnullByDefault
@SuppressWarnings("WeakerAccess")
public class SysLogger {

    final Executor executor;
    final LogFacility facility;
    final ExecSysLogger execSysLogger;
    final String tag;

    static final Executor defaultExecutor = Runnable::run;
    static ExecSysLoggerFactory execSysLoggerFactory = ExecSysLoggerFactory.DEFAULT_FACTORY;

    private SysLogger(Executor executor, ExecSysLogger execSysLogger, LogFacility facility, String tag) {
        this.executor = executor;
        this.execSysLogger = execSysLogger;
        this.facility = facility;
        this.tag = tag;
    }

    public static SysLogger logger(LogFacility facility, String tag) {
        return logger(defaultExecutor, execSysLoggerFactory.create(), facility, tag);
    }

    public static SysLogger logger(Executor executor, LogFacility facility, String tag) {
        return new SysLogger(executor, execSysLoggerFactory.create(), facility, tag);
    }

    public static SysLogger logger(Executor executor, ExecSysLogger execSysLogger, LogFacility facility, String tag) {
        return new SysLogger(executor, execSysLogger, facility, tag);
    }

    public LogFacility facility() {
        return facility;
    }

    public String tag() {
        return tag;
    }

    public CompletableFuture<Process> log(LogLevel level, String message) {
        return CompletableFuture.supplyAsync(() -> {
            String priority = formatPriorityValue(level);
            try {
                return execSysLogger.processBuilder(priority, tag, message).start();
            } catch (IOException e) {
                throw new SysLoggerException(priority, tag, message, e);
            }
        }, executor);
    }

    public CompletableFuture<Process> logInfo(String message) {
        return log(LogLevel.INFO, message);
    }

    public CompletableFuture<Process> logWarn(String message) {
        return log(LogLevel.WARN, message);
    }

    public CompletableFuture<Process> logError(String message) {
        return log(LogLevel.ERROR, message);
    }

    public CompletableFuture<Process> logCritical(String message) {
        return log(LogLevel.CRITICAL, message);
    }

    private String formatPriorityValue(LogLevel level) {
        return facility.toString() + "." + level.toString();
    }

}
