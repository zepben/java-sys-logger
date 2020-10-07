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
import java.io.UncheckedIOException;

@EverythingIsNonnullByDefault
@SuppressWarnings("WeakerAccess")
public class SysLoggerException extends UncheckedIOException {

    private final String priority;
    private final String tag;
    private final String message;

    /**
     * Formats exception with info sent to logger
     *
     * @param priority consists of facility.level pair
     * @param tag      specified tag logged with message
     * @param message  to print to log
     * @param cause    cause for later retrieval
     */
    public SysLoggerException(String priority, String tag, String message, IOException cause) {
        super(String.format("Unable to log message (%s:%s) : %s", priority, tag, message), cause);

        this.priority = priority;
        this.tag = tag;
        this.message = message;
    }

    public String priority() {
        return priority;
    }

    public String tag() {
        return tag;
    }

    public String message() {
        return message;
    }

}
