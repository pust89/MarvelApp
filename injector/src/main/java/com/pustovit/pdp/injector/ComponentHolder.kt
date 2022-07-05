package com.pustovit.pdp.injector

interface ComponentHolder<A : FeatureApi, D : FeatureDependencies> {

    var dependencyProvider: (() -> D)?

    fun get(): A

}