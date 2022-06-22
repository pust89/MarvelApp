package com.pustovit.pdp.injector

interface ComponentHolder<A : FeatureApi, D : FeatureDependencies> {

    fun init(dependencies: D)

    fun get(): A

    fun reset()
}