package com.example.nhlstats

import android.app.Application
import com.example.nhlstats.common.data.NetworkClient
import com.example.nhlstats.features.teams.data.dto.TeamsRepositoryImpl
import com.example.nhlstats.features.teams.data.network.TeamsService
import com.example.nhlstats.features.teams.domain.TeamsRepository
import com.example.nhlstats.features.teams.presentation.mvi.list.TeamsViewModel
import com.example.nhlstats.features.teams.presentation.mvi.teamDetail.TeamDetailViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

class NhlApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        val appModule = createAppModule()
        val networkModule = createNetworkModule()
        val teams = createTeamsModule()

        startKoin {
            modules(
                listOf(
                    appModule,
                    networkModule,
                    teams
                )
            )
        }
    }

    private fun createTeamsModule(): Module = module {
        factory { provideTeamService(get()) }
        single<TeamsRepository> { TeamsRepositoryImpl(get()) }
        viewModel { TeamsViewModel(get()) }
        viewModel { TeamDetailViewModel() }
    }


    private fun provideTeamService(client: NetworkClient): TeamsService =
        client.createService(TeamsService::class.java)


    private fun createNetworkModule() =
        module {
            single { provideOkHttpClient() }
            single { NetworkClient(get()) }
        }

    private fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    private fun createAppModule(): Module {
        return module {
            single { androidContext() }
        }
    }
}