package com.jantiojo.gweather.utils.location

import android.content.Context
import android.location.Address
import android.location.Geocoder
import com.jantiojo.gweather.utils.location.model.ReverseAddressData
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import java.util.Locale
import javax.inject.Inject

class ReverseGeocoder @Inject constructor(
    @ApplicationContext
    private val context: Context
) {
    private val states = hashMapOf(
        "Alabama" to "AL",
        "Alaska" to "AK",
        "Alberta" to "AB",
        "American Samoa" to "AS",
        "Arizona" to "AZ",
        "Arkansas" to "AR",
        "Armed Forces (AE)" to "AE",
        "Armed Forces Americas" to "AA",
        "Armed Forces Pacific" to "AP",
        "British Columbia" to "BC",
        "California" to "CA",
        "Colorado" to "CO",
        "Connecticut" to "CT",
        "Delaware" to "DE",
        "District Of Columbia" to "DC",
        "Florida" to "FL",
        "Georgia" to "GA",
        "Guam" to "GU",
        "Hawaii" to "HI",
        "Idaho" to "ID",
        "Illinois" to "IL",
        "Indiana" to "IN",
        "Iowa" to "IA",
        "Kansas" to "KS",
        "Kentucky" to "KY",
        "Louisiana" to "LA",
        "Maine" to "ME",
        "Mani to ba" to "MB",
        "Maryland" to "MD",
        "Massachusetts" to "MA",
        "Michigan" to "MI",
        "Minnesota" to "MN",
        "Mississippi" to "MS",
        "Missouri" to "MO",
        "Montana" to "MT",
        "Nebraska" to "NE",
        "Nevada" to "NV",
        "New Brunswick" to "NB",
        "New Hampshire" to "NH",
        "New Jersey" to "NJ",
        "New Mexico" to "NM",
        "New York" to "NY",
        "Newfoundland" to "NF",
        "North Carolina" to "NC",
        "North Dakota" to "ND",
        "Northwest Terri to ries" to "NT",
        "Nova Scotia" to "NS",
        "Nunavut" to "NU",
        "Ohio" to "OH",
        "Oklahoma" to "OK",
        "Ontario" to "ON",
        "Oregon" to "OR",
        "Pennsylvania" to "PA",
        "Prince Edward Island" to "PE",
        "Puerto" to "PR",
        "Quebec" to "PQ",
        "Rhode Island" to "RI",
        "Saskatchewan" to "SK",
        "South Carolina" to "SC",
        "South Dakota" to "SD",
        "Tennessee" to "TN",
        "Texas" to "TX",
        "Utah" to "UT",
        "Vermont" to "VT",
        "Virgin Islands" to "VI",
        "Virginia" to "VA",
        "Washing to n" to "WA",
        "West Virginia" to "WV",
        "Wisconsin" to "WI",
        "Wyoming" to "WY",
        "Yukon Terri to ry" to "YT",
    )

    fun reverseGeocoding(latitude: Double?, longitude: Double?): ReverseAddressData? {
        val mGeocoder = Geocoder(context, Locale.getDefault())
        return runCatching {
            val addressList: List<Address> =
                mGeocoder.getFromLocation(latitude!!, longitude!!, 1) as List<Address>
            val address = addressList[0]
            // Looking up the us states to find the shortened state name otherwise using the full name
            val shortenedState = states[address.adminArea.orEmpty()] ?: address.adminArea.orEmpty()
            ReverseAddressData(
                streetNumber = address.featureName.orEmpty(),
                streetName = address.subAdminArea.orEmpty(),
                city = address.locality ?: address.subAdminArea.orEmpty(),
                state = shortenedState,
                country = address.countryCode.orEmpty(),
                zipcode = address.postalCode.orEmpty(),
                latitude = address.latitude,
                longitude = address.longitude
            )
        }.getOrElse {
            Timber.e(it, it.localizedMessage)
            null
        }
    }
}
