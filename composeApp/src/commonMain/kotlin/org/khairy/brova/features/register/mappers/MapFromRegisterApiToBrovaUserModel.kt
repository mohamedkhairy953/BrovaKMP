package org.khairy.brova.features.register.mappers

import org.khairy.brova.features.register.datasource.model.RegisterResponseApiModel
import org.khairy.brova.features.sharedmodels.BrovaUserModel

/**
 * ...
 *
 *
 * Copyright (c) 2025 . All rights reserved.
 *
 * @author Mohamed "mohamed" Sallam.
 * @since 1/29/2025 4:46 PM
 */


fun RegisterResponseApiModel.toBrovaUserModel(): BrovaUserModel? {
    if (this.data == null) return null
    return BrovaUserModel(
        name = this.data.name,
        email = this.data.email,
        id = this.data.id,
        phone = this.data.phone,
        pants_length = this.data.pants_length,
        Pants_thigh = this.data.Pants_thigh,
        pants_waist = this.data.pants_waist,
        tshirt_length = this.data.tshirt_length,
        tshirt_width = this.data.tshirt_width,
        status = this.data.status
    )
}