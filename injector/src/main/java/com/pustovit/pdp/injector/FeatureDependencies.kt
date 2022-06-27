package com.pustovit.pdp.injector

interface FeatureDependencies {

    val dependencyHolder: DependencyHolder<out FeatureDependencies>

}