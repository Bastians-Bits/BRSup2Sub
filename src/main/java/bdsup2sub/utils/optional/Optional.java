/*
 * Copyright (C) 2011 The Guava Authors, Miklos Juhasz (mjuhasz)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package bdsup2sub.utils.optional;

import java.io.Serializable;

public abstract class Optional<T> implements Serializable {

    private static final long serialVersionUID = 0;

    @SuppressWarnings("unchecked")
    public static <T> Optional<T> absent() {
        return (Optional<T>) Absent.INSTANCE;
    }

    public static <T> Optional<T> of(T reference) {
        return new Present<T>(checkNotNull(reference));
    }

    private static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    public static <T> Optional<T> fromNullable(T nullableReference) {
        return (nullableReference == null)
                ? Optional.<T>absent()
                : new Present<T>(nullableReference);
    }

    Optional() {
    }

    public abstract boolean isPresent();

    public abstract T get();

    public abstract Object orNull();

    @Override
    public abstract boolean equals(Object object);

    @Override
    public abstract int hashCode();
}
