package com.example.nhlstats

import android.app.Application
import com.example.nhlstats.common.data.NetworkClient
import com.example.nhlstats.features.standings.currentseasontable.CurrentSeasonViewModel
import com.example.nhlstats.features.standings.teamstanding.TeamStandingViewModel
import com.example.nhlstats.features.teams.data.TeamsRepositoryImpl
import com.example.nhlstats.features.teams.data.network.TeamsService
import com.example.nhlstats.features.teams.domain.TeamsRepository
import com.example.nhlstats.features.teams.presentation.mvi.list.TeamsViewModel
import com.example.nhlstats.features.teams.presentation.mvi.team_detail.TeamDetailViewModel
import com.example.nhlstats.features.teams.presentation.mvi.team_detail_players.TeamPlayersViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.terrakok.cicerone.Cicerone

class NhlApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        val appModule = createAppModule()
        val networkModule = createNetworkModule()
        val navigation = createNavigation()
        val teams = createTeamsModule()

        startKoin {
            modules(
                listOf(
                    appModule,
                    networkModule,
                    navigation,
                    teams,
                    declareTeamFeatureModule()
                )
            )
        }
    }

    private fun declareTeamFeatureModule(): Module {
        return module {
            viewModel { (teamId: Int) ->
                TeamDetailViewModel(
                    get(), teamId, get(
                        named(FlowKey.TEAMS)
                    )
                )
            }
            viewModel { (teamId: Int) ->
                TeamPlayersViewModel(
                    teamId,
                    get(
                        named(FlowKey.TEAMS)
                    ),
                    get()
                )
            }
            viewModel { TeamsViewModel(get(), get(named(FlowKey.TEAMS))) }
            viewModel { CurrentSeasonViewModel(get(named(FlowKey.STANDINGS))) }
            viewModel { TeamStandingViewModel(get(named(FlowKey.STANDINGS))) }
        }
    }

    private fun createNavigation(): Module = module {
        val cicerone = Cicerone.create()
        factory {
            cicerone.router
        }

        factory {
            cicerone.navigatorHolder
        }
    }

    private fun createTeamsModule(): Module = module {
        factory { provideTeamService(get()) }
        single<TeamsRepository> {
            TeamsRepositoryImpl(
                get()
            )
        }
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