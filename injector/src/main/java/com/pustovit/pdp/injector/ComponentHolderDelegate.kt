package com.pustovit.pdp.injector

import android.util.Log
import java.lang.ref.WeakReference
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ComponentHolderDelegate<D : FeatureDependencies, C>(
    private val componentFactory: (D) -> C
) : ReadOnlyProperty<ComponentHolder<*, D>, C> {

    // Weak reference to the component
    private var componentWeakRef: WeakReference<C>? = null

    override fun getValue(
        thisRef: ComponentHolder<*, D>,
        property: KProperty<*>
    ): C {
        return getComponent(thisRef)
    }

    private fun getComponent(thisRef: ComponentHolder<*, D>): C {
        var component: C? = null
        synchronized(this) {
            thisRef.dependencyProvider?.let { dependencyProvider ->
                component = componentWeakRef?.get()
                if (component == null) {
                    val featureDependencies = dependencyProvider.invoke()
                    component = componentFactory.invoke(featureDependencies)
                    componentWeakRef = WeakReference(component)
                    Log.d(
                        "fooDeps",
                        "${thisRef::class.java} new component=${componentWeakRef.hashCode()}"
                    )
                } else {
                    Log.d(
                        "fooDeps",
                        "${thisRef::class.java} old component=${componentWeakRef.hashCode()}"
                    )
                }
            } ?: throw IllegalStateException("${thisRef}, dependencyProvider is not initialized")
            return component
                ?: throw IllegalStateException("${thisRef}, component is not initialized")
        }
    }
}