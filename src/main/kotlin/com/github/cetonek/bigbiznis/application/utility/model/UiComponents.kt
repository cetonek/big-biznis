package com.github.cetonek.bigbiznis.application.utility.model

data class OverviewAndGraph(val overview: List<Triple<*, *, *>>,
                            val graph: List<Pair<*, *>>)
