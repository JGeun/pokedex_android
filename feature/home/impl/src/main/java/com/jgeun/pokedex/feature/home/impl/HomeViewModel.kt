package com.jgeun.pokedex.feature.home.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jgeun.pokedex.core.model.Pokemon
import com.jgeun.pokedex.feature.home.api.usecase.GetPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val pokemonFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)
    val pokemonList: StateFlow<List<Pokemon>> = pokemonFetchingIndex.flatMapLatest { page ->
        getPokemonListUseCase(
            page = page,
            onStart = { _uiState.value = HomeUiState.Loading },
            onComplete = { _uiState.value = HomeUiState.Idle },
            onError = { _uiState.value = HomeUiState.Error(it) }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    fun fetchNextPokemonList() {
        if (uiState.value != HomeUiState.Loading) {
            pokemonFetchingIndex.value++
        }
    }
}