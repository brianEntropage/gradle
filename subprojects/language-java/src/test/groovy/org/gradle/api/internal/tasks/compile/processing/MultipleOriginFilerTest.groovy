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

package org.gradle.api.internal.tasks.compile.processing

import org.gradle.api.internal.tasks.compile.incremental.processing.AnnotationProcessingResult

import javax.annotation.processing.Filer
import javax.annotation.processing.Messager

class MultipleOriginFilerTest extends IncrementalFilerTest {


    @Override
    Filer createFiler(Filer filer, AnnotationProcessingResult result, Messager messager) {
        new MultipleOriginFiler(delegate, result, messager)
    }

    def "can have zero originating elements"() {
        when:
        filer.createSourceFile("Foo")

        then:
        0 * messager._
    }

    def "does not fail when many originating elements are given"() {
        when:
        filer.createSourceFile("Foo", type("Bar"), type("Baz"))

        then:
        0 * messager._
    }

    def "adds generated types to the processing result"() {
        when:
        filer.createSourceFile("Foo", pkg("pkg"), type("A"), methodInside("B"))
        filer.createSourceFile("Bar", type("B"))

        then:
        result.generatedTypesByOrigin.isEmpty()
        result.aggregatedTypes == ["Foo", "Bar"] as Set
    }
}
