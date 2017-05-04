package com.damianogiusti.cleanandroid.data.datasources;

import com.damianogiusti.cleanandroid.data.datamodel.ProvinceDataModel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Damiano Giusti on 03/05/17.
 */
public class ProvinceRestDataSource extends BaseRestDataSource {

    private static final String PROVINCES_URL = "http://damianogiusti.altervista.org/province.php";

    @Inject
    public ProvinceRestDataSource(Gson gson) {
        super(gson);
    }

    public Observable<List<ProvinceDataModel>> getProvinceList() {
        return getProvinceJson().map(this::jsonToList);
    }

    private Observable<JsonObject> getProvinceJson() {
        return Observable.defer(() -> {

            Thread.sleep(2000); // simulate some delay

            Request request = new Request.Builder().url(PROVINCES_URL).get().build();
            Response response = httpClient.newCall(request).execute();
            String body = response.body().string();
            if (response.isSuccessful()) {
                return Observable.just(gson.fromJson(body, JsonObject.class));
            } else {
                return Observable.error(new Throwable());
            }
        });
    }

    private List<ProvinceDataModel> jsonToList(JsonObject provincesJson) {
        List<ProvinceDataModel> provincesList = new ArrayList<>();

        JsonArray jsonArray = provincesJson.getAsJsonArray("values");
        for (JsonElement jsonElement : jsonArray) {
            JsonObject province = jsonElement.getAsJsonObject();
            provincesList.add(gson.fromJson(province, ProvinceDataModel.class));
        }

        return provincesList;
    }
}
