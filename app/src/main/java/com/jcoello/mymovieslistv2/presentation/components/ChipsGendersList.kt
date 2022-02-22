package com.jcoello.mymovieslistv2.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jcoello.domain.model.Gender
import com.jcoello.mymovieslistv2.App
import com.jcoello.mymovieslistv2.R

@Composable
fun ChipsGendersList(
    modifier: Modifier = Modifier,
    namesList: MutableList<Gender>,
    disabledAll: Boolean = false,
    selectedPositionState: MutableState<Int>,
    onClick: (index: Int, genderId: Int) -> Unit
) {
    val scrollState = rememberLazyListState()
    LazyRow(
        modifier = modifier,
        state = scrollState,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        itemsIndexed(namesList) { index, gender ->
            ChipGender(
                modifier =
                when (index) {
                    0 -> Modifier.padding(
                        start = dimensionResource(id = R.dimen.padding_24),
                        end = 5.dp
                    )
                    namesList.lastIndex -> Modifier.padding(
                        start = 5.dp,
                        end = dimensionResource(id = R.dimen.padding_24)
                    )
                    else -> Modifier.padding(horizontal = 5.dp)
                },
                isActivated = index == selectedPositionState.value,
                text = gender.name,
                enabled = !disabledAll
            ) {
                onClick(index, gender.id)
                if (index != selectedPositionState.value) {
                    selectedPositionState.value = index
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 300, heightDp = 50)
@Composable
fun ChipsGendersListPreview() {
    val selectedPositionState = remember { mutableStateOf(0) }
    ChipsGendersList(
        namesList = App.moviesGendersList,
        selectedPositionState = selectedPositionState
    ) { _, _ ->
        //No click listener assigned. Its a preview.
    }
}