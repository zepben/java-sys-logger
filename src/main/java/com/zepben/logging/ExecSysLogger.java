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
public interface ExecSysLogger {

    /**
     * Constructs a process builder with the specified facility,
     * tag and message
     *
     * @param priority consists of facility.level pair
     * @param tag      to log the  message with
     * @param message  to be printed to log
     * @return ProcessBuilder with constructed logger command
     */
    ProcessBuilder processBuilder(String priority, String tag, String message);

}
