package es.victorgf87.santanderopenapiwrapper.apiclasses;

import es.victorgf87.santanderopenapiwrapper.serializedclasses.CollectionsList;
import es.victorgf87.santanderopenapiwrapper.serializedclasses.DataSet;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by V�ctor on 14/07/2015.
 */
public interface SantanderService
{

    /**
     * Returns default collections set. By default it will return 1000 elements.
     */
    @GET("/rest/collections.json")
    public CollectionsList getAllCollections();

    @GET("/rest/datasets/{collection_id}.json")
    public DataSet getCollection(@Path("collection_id")String collection_id);




}

