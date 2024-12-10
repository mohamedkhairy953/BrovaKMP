package org.khairy.brova.utils

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * ...
 *
 *
 * Copyright (c) 2024 . All rights reserved.
 *
 * @author Mohamed "mohamed" Sallam.
 * @since 12/2/2024 6:03 PM
 */

@Composable
fun SpacerHeight(height: Int) = Spacer(modifier = Modifier.height(height.dp))

@Composable
fun SpacerHeight8() = Spacer(modifier = Modifier.height(8.dp))

@Composable
fun SpacerHeight16() = Spacer(modifier = Modifier.height(16.dp))

@Composable
fun SpacerHeight24() = Spacer(modifier = Modifier.height(24.dp))

@Composable
fun SpacerHeight32() = Spacer(modifier = Modifier.height(32.dp))

@Composable
fun SpacerWidth(width: Int) = Spacer(modifier = Modifier.width(width.dp))

@Composable
fun SpacerWidth8() = Spacer(modifier = Modifier.width(8.dp))

@Composable
fun SpacerWidth16() = Spacer(modifier = Modifier.width(16.dp))

@Composable
fun SpacerWidth24() = Spacer(modifier = Modifier.width(24.dp))

@Composable
fun SpacerWidth32() = Spacer(modifier = Modifier.width(32.dp))