package org.codeslu.wpay.app.di

import org.koin.dsl.koinApplication
import org.koin.dsl.module

val appModule = module {
    includes(viewModelModule)
}