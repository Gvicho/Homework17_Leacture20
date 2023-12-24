package com.example.homework17_leacture20.di

import com.example.homework17_leacture20.data.login.LoginRepositoryImpl
import com.example.homework17_leacture20.data.login.LoginService
import com.example.homework17_leacture20.data.registration.RegisterRepositoryImpl
import com.example.homework17_leacture20.data.registration.RegisterService
import com.example.homework17_leacture20.domain.login.LoginRepository
import com.example.homework17_leacture20.domain.registration.RegisterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideLoginRepository(loginService: LoginService):LoginRepository{
        return LoginRepositoryImpl(
            loginService = loginService
        )
    }

    @Singleton
    @Provides
    fun provideRegisterRepository(registerService: RegisterService):RegisterRepository{
        return RegisterRepositoryImpl(
            registerService = registerService
        )
    }
}