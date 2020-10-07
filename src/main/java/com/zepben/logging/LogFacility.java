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
public enum LogFacility {
    SYSLOG("syslog"),
    LOCAL0("local0"),
    LOCAL1("local1"),
    LOCAL2("local2"),
    LOCAL3("local3"),
    LOCAL4("local4"),
    LOCAL5("local5"),
    LOCAL6("local6"),
    LOCAL7("local7");

    private final String facility;

    LogFacility(String facility) {
        this.facility = facility;
    }

    public String toString() {
        return this.facility;
    }
}