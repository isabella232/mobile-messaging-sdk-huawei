package org.infobip.mobile.messaging.geo.mapper;


import android.support.annotation.Nullable;

import org.infobip.mobile.messaging.api.support.http.serialization.JsonSerializer;
import org.infobip.mobile.messaging.geo.Geo;

public final class GeoDataMapper {

    private static final JsonSerializer serializer = new JsonSerializer(false);

    /**
     * Serializes json data to Geo object
     *
     * @param internalDataJson geo data to deserialize
     * @return Geo object from json data
     */
    @Nullable
    public static Geo geoFromInternalData(String internalDataJson) {
        return internalDataJson != null ? serializer.deserialize(internalDataJson, Geo.class) : null;
    }

    /**
     * Serializes json data to Geo object
     *
     * @param geo geo object to serialize
     * @return String - geo data as json string
     */
    @Nullable
    public static String geoToInternalData(Geo geo) {
        return geo != null ? serializer.serialize(geo) : null;
    }
}
