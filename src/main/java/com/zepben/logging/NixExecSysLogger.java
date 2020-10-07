/*
 * Copyright 2020 Zeppelin Bend Pty Ltd
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.zepben.logging;

import com.zepben.annotations.EverythingIsNonnullByDefault;

@EverythingIsNonnullByDefault
public class NixExecSysLogger implements ExecSysLogger {

    private static final String LOGGER_COMMAND = "logger";
    private static final String OPTIONS_PRIORITY = "-p";
    private static final String OPTIONS_TAG = "-t";

    @Override
    public ProcessBuilder processBuilder(String priority, String tag, String message) {
        return new ProcessBuilder(LOGGER_COMMAND, OPTIONS_PRIORITY, priority, OPTIONS_TAG, tag, message);
    }

}
