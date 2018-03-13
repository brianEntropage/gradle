/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.dependency.locking.exception;

import java.util.List;

public class LockOutOfDateException extends RuntimeException {

    public static LockOutOfDateException createLockOutOfDateException(List<String> errors) {
        StringBuilder builder = new StringBuilder("Dependency lock out of date:\n");
        for (String error : errors) {
            builder.append("\t").append(error).append("\n");
        }
        return new LockOutOfDateException(builder.toString());
    }
    private LockOutOfDateException(String message) {
        super(message);
    }
}
