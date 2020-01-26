package com.example.nhlstats.common.ui

import com.example.nhlstats.R
import com.example.nhlstats.features.teams.domain.ShortTeam


val images = mapOf(
    15 to R.drawable.ic_capitals,
    17 to R.drawable.ic_red_wings,
    10 to R.drawable.ic_toronto,
    1 to R.drawable.ic_devils,
    2 to R.drawable.ic_islanders,
    3 to R.drawable.ic_rangers,
    4 to R.drawable.ic_flyers,
    19 to R.drawable.ic_blues,
    18 to R.drawable.ic_predators,
    6 to R.drawable.ic_bruins,
    13 to R.drawable.ic_panters,
    9 to R.drawable.ic_senators,
    8 to R.drawable.ic_canadiens,
    16 to R.drawable.ic_hawks
)

fun ShortTeam.toImageRes() = images[id] ?: R.drawable.ic_capitals