package com.github.l3gacy.ktx.android

import android.content.Intent
import androidx.core.content.IntentCompat
import java.io.Serializable

public inline fun <reified T : Serializable> Intent.serializable(key: String): T? =
  IntentCompat.getSerializableExtra(this, key, T::class.java)

public inline fun <reified T : Serializable> Intent.parcelable(key: String): T? =
  IntentCompat.getParcelableExtra(this, key, T::class.java)
