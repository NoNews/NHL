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
    16 to R.drawable.ic_hawks,
    5 to R.drawable.ic_penguins,
    14 to R.drawable.ic_tampa,
    12 to R.drawable.ic_carolina,
    7 to R.drawable.ic_buffalo,
    21 to R.drawable.ic_colorado,
    29 to R.drawable.ic_columbus,
    26 to R.drawable.ic_kings,
    53 to R.drawable.ic_arizona,
    20 to R.drawable.ic_calgary,
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