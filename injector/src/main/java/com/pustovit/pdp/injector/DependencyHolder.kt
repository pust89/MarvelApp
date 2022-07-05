package com.pustovit.pdp.injector

interface DependencyHolder<D : FeatureDependencies> {
    val dependencies: D
}

abstract class DependencyHolder0<D : FeatureDependencies>(
) : DependencyHolder<D> {
    abstract val block: (DependencyHolder<D>) -> D

    override val dependencies: D
        get() = block(this)
}

abstract class DependencyHolder1<A1 : FeatureApi, D : FeatureDependencies>(
    private val api1: A1,
) : DependencyHolder<D> {
    abstract val block: (DependencyHolder<D>, A1) -> D

    override val dependencies: D
        get() = block(this, api1)
}

abstract class DependencyHolder2<A1 : FeatureApi, A2 : FeatureApi, D : FeatureDependencies>(
    private val api1: A1,
    private val api2: A2,
) : DependencyHolder<D> {
    abstract val block: (DependencyHolder<D>, A1, A2) -> D

    override val dependencies: D
        get() = block(this, api1, api2)
}

abstract class DependencyHolder3<A1 : FeatureApi, A2 : FeatureApi, A3 : FeatureApi, D : FeatureDependencies>(
    private val api1: A1,
    private val api2: A2,
    private val api3: A3,
) : DependencyHolder<D> {
    abstract val block: (dependencyHolder: DependencyHolder<D>, A1, A2, A3) -> D

    override val dependencies: D
        get() = block(this, api1, api2, api3)
}