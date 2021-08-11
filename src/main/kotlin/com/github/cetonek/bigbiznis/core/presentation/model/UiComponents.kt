package com.github.cetonek.bigbiznis.core.presentation.model

data class OverviewAndGraph(val overview: List<Triple<*, *, *>>,
                            val graph: List<Pair<*, *>>)
