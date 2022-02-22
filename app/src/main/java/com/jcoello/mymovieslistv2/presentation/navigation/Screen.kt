package com.jcoello.mymovieslistv2.presentation.navigation

sealed class Screen(val route: String){
    object Movies : Screen(route = "movies_screen")
    object Series : Screen(route = "series_screen")
    object Details : Screen(route = "details_screen") {
        fun mountRouteDetails(
            posterPath: String,
            title: String,
            adult: Boolean,
            isMovie: Boolean,
            gendersIds: Array<Int>,
            overview: String,
            voteAverage: Float
        ): String{

            var gendersIdTokenized = ""
            gendersIds.forEachIndexed{ index, genderId ->
                if (index == gendersIds.lastIndex) {
                    gendersIdTokenized += "$genderId"
                } else {
                    gendersIdTokenized += "$genderId."
                }
            }
            return Details.route+"/"+
                    posterPath.slice(1 until posterPath.length)+"/"+
                    title+"/"+
                    adult+"/"+
                    isMovie+"/"+
                    gendersIdTokenized+"/"+
                    overview+"/"+
                    voteAverage
        }
    }
    object MyList : Screen(route = "my_list_screen")
}
