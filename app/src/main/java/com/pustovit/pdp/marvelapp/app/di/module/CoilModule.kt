package com.pustovit.pdp.marvelapp.app.di.module

import android.content.Context
import android.os.Build
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.decode.SvgDecoder
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.util.DebugLogger
import com.pustovit.pdp.marvelapp.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
class CoilModule {

    @Singleton
    @Provides
    fun provideCoil(context: Context): ImageLoader {
        return newImageLoader(context)
    }

    private fun newImageLoader(context: Context): ImageLoader {
        return ImageLoader.Builder(context)
            .components {
                // GIFs
                if (Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
                // SVGs
                add(SvgDecoder.Factory())
                // Video frames
                //add(VideoFrameDecoder.Factory())
            }
            .memoryCache {
                MemoryCache.Builder(context)
                    // Set the max size to 25% of the app's available memory.
                    .maxSizePercent(0.25)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(context.filesDir.resolve("image_cache"))
                    .maxSizeBytes(512L * 1024 * 1024) // 512MB
                    .build()
            }
            .okHttpClient {
                // Don't limit concurrent network requests by host.
                val dispatcher = Dispatcher().apply { maxRequestsPerHost = maxRequests }

                // Lazily create the OkHttpClient that is used for network operations.
                OkHttpClient.Builder()
                    .dispatcher(dispatcher)
                    .build()
            }
            // Show a short crossfade when loading images asynchronously.
            .crossfade(true)
            // Ignore the network cache headers and always read from/write to the disk cache.
            .respectCacheHeaders(false)
            // Enable logging to the standard Android log if this is a debug build.
            .apply { if (BuildConfig.DEBUG) logger(DebugLogger()) }
            .build()
    }
}