package com.github.l3gcay.ktx.internal

/**
 * Marks declarations that are **internal** in ktx API, which means that should not be used outside of
 * `com.github.l3gcay`, because their signatures and semantics will change between future releases without any
 * warnings and without providing any migration aids.
 */
@MustBeDocumented
@Retention(value = AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.TYPEALIAS, AnnotationTarget.PROPERTY)
@RequiresOptIn(
  level = RequiresOptIn.Level.ERROR, message = "This is an internal ktx API that " +
    "should not be used from outside of com.github.l3gcay.ktx. No compatibility guarantees are provided. " +
    "It is recommended to report your use-case of internal API to J!nl!n, " +
    "so stable API could be provided instead"
)
public annotation class InternalApi
