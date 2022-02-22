package com.jcoello.mymovieslistv2.presentation.components

import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jcoello.mymovieslistv2.App
import com.jcoello.mymovieslistv2.R
import com.jcoello.mymovieslistv2.presentation.navigation.Screen
import com.jcoello.mymovieslistv2.ui.theme.DarkColorPalette


@Composable
fun TabsBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    selectedTabIndexState: MutableState<Int>,
    tabList: List<String>
) {
    CompositionLocalProvider(
        LocalRippleTheme provides ClearRippleTheme
    ) {
        TabRow(
            modifier = modifier,
            selectedTabIndex = selectedTabIndexState.value,
            backgroundColor = Color.Transparent,
            contentColor = DarkColorPalette.onBackground,
            indicator = {},
            divider = {}
        ) {
            tabList.forEachIndexed { index, tabName ->
                Tab(
                    text = {
                        Text(
                            text = tabName,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    selected = index == selectedTabIndexState.value,
                    onClick = {
                        if (selectedTabIndexState.value != index) {
                            selectedTabIndexState.value = index
                            when(tabName){
                                tabList[0] -> navController.navigate(route = Screen.Series.route)
                                tabList[1] -> navController.navigate(route = Screen.Movies.route)
                                //tabList[2] -> navController.navigate(route = Screen.MyList.route)
                            }
                        }
                    },
                    selectedContentColor = DarkColorPalette.secondary,
                    unselectedContentColor = DarkColorPalette.onSecondary
                )
            }
        }
    }
}

private object ClearRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor(): Color = Color.Transparent

    @Composable
    override fun rippleAlpha() = RippleAlpha(
        draggedAlpha = 0.0f,
        focusedAlpha = 0.0f,
        hoveredAlpha = 0.0f,
        pressedAlpha = 0.0f,
    )
}


@Preview(showBackground = true, widthDp = 300, heightDp = 50)
@Composable
fun TabsBarPreview() {
    val selectedTabIndexState = remember {
        mutableStateOf(1)
    }
    TabsBar(
        selectedTabIndexState = selectedTabIndexState,
        navController = rememberNavController(),
        tabList = listOf(
            stringResource(id = R.string.tab_series),
            stringResource(id = R.string.tab_movies),
            stringResource(id = R.string.tab_my_list)
        )
    )
}