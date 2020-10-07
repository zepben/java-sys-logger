/*
 * Copyright 2020 Zeppelin Bend Pty Ltd
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

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
