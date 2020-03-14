package com.example.nhlstats.common.ui

import com.example.nhlstats.R
import com.example.nhlstats.features.teams.domain.ShortPlayer
import com.example.nhlstats.features.teams.domain.ShortTeam

private const val TEAM_RED_WINGS_ID = 17
private const val TEAM_CAPITALS_ID = 15
private const val TEAM_LEAFS_ID = 10
private const val TEAM_CALGARY_ID = 20
private const val TEAM_DEVILS_ID = 1
private const val TEAM_ISLANDERS_ID = 2
private const val TEAM_RANGERS_ID = 3

val images = mapOf(
    TEAM_CAPITALS_ID to R.drawable.ic_capitals,
    TEAM_RED_WINGS_ID to R.drawable.ic_red_wings,
    TEAM_LEAFS_ID to R.drawable.ic_toronto,
    TEAM_DEVILS_ID to R.drawable.ic_devils,
    TEAM_ISLANDERS_ID to R.drawable.ic_islanders,
    TEAM_RANGERS_ID to R.drawable.ic_rangers,
    4 to R.drawable.ic_flyers,
    19 to R.drawable.ic_blues,
    18 to R.drawable.ic_predators,
    6 to R.drawable.ic_bruins,
    13 to R.drawable.ic_panters,
    9 to R.drawable.ic_senators,
    8 to R.drawable.ic_canadiens,
    16 to R.drawable.ic_hawks,
    5 to R.drawable.ic_penguins,
    14 to R.drawable.ic_tampa,
    12 to R.drawable.ic_carolina,
    7 to R.drawable.ic_buffalo,
    21 to R.drawable.ic_colorado,
    29 to R.drawable.ic_columbus,
    26 to R.drawable.ic_kings,
    53 to R.drawable.ic_arizona,
    TEAM_CALGARY_ID to R.drawable.ic_calgary,
    28 to R.drawable.ic_sharks,
    22 to R.drawable.ic_edmonton,
    25 to R.drawable.ic_stars,
    30 to R.drawable.ic_wild,
    52 to R.drawable.ic_jets,
    54 to R.drawable.ic_vegas,
    23 to R.drawable.ic_vancouver,
    24 to R.drawable.ic_ducks
)

fun ShortTeam.toImageRes() = images[id] ?: R.drawable.ic_capitals

fun ShortPlayer.imageUrl() = "https://nhl.bamcontent.com/images/headshots/current/168x168/${id}.jpg"