package com.jgeun.pokedex.core.navigator.navtype

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.jgeun.pokedex.core.model.Pokemon
import com.jgeun.pokedex.core.navigator.util.parcelable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.nio.charset.StandardCharsets

class PokemonType : NavType<Pokemon?>(isNullableAllowed = false) {

    override fun get(bundle: Bundle, key: String): Pokemon? = bundle.parcelable(key)

    override fun put(bundle: Bundle, key: String, value: Pokemon?) {
        bundle.putParcelable(key, value)
    }

    override fun parseValue(value: String): Pokemon {
        val decoded = Uri.decode(value)
        return Json.decodeFromString(decoded)
    }

    companion object {
        fun encodeToString(pokemon: Pokemon): String {
            return Uri.encode(Json.encodeToString(pokemon), StandardCharsets.UTF_8.toString())
        }
    }
}