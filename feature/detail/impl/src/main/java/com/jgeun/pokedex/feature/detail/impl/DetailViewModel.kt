package com.jgeun.pokedex.feature.detail.impl

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jgeun.pokedex.core.model.Pokemon
import com.jgeun.pokedex.core.model.PokemonInfo
import com.jgeun.pokedex.core.model.common.NetworkResult
import com.jgeun.pokedex.core.model.common.onException
import com.jgeun.pokedex.core.model.common.onFailure
import com.jgeun.pokedex.core.model.common.onSuccess
import com.jgeun.pokedex.core.navigator.DETAIL_POKEMON
import com.jgeun.pokedex.feature.detail.api.GetPokemonInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class DetailViewModel @Inject constructor(
    getPokemonInfoUseCase: GetPokemonInfoUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val pokemon = savedStateHandle.getStateFlow<Pokemon?>(DETAIL_POKEMON, null)
    val detailUiState: StateFlow<DetailUiState> =
        pokemon.filterNotNull().flatMapLatest { pokemon ->
            getPokemonInfoUseCase(
                name = pokemon.nameField.replaceFirstChar { it.lowercase() },
            ).map { result ->
                Log.e("Test@@@", "result: $result")
                when (result) {
                    is NetworkResult.Success -> {
                        DetailUiState.Success(
                            pokemon = pokemon,
                            pokemonInfo = result.data
                        )
                    }

                    is NetworkResult.Error -> {
                        DetailUiState.Failure(result.message)
                    }

                    is NetworkResult.Exception -> {
                        DetailUiState.Failure(result.t.message)
                    }
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = DetailUiState.Loading
        )
}