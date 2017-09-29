package io.whitfin.elasticsearch.bulk;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.annotation.Nullable;

/**
 * Interface for a bulk action as per the Elasticsearch API specification.
 *
 * A {@link BulkAction} is an action which can be queued into a {@link BulkOperation}
 * to carry out batch actions against Elasticsearch. Each action has a contained
 * definition as the bulk does not have any knowledge of the actions themselves (e.g.
 * it doesn't infer indices, etc).
 *
 * Almost everything in here is optional, aside from the operation name itself. This
 * is because only the index and type make sense otherwise, but they can be specified
 * in the API call itself via the Elasticsearch path and so they must be nullable.
 *
 * @see <a href="Elasticsearch Bulk API">https://www.elastic.co/guide/en/elasticsearch/reference/5.4/docs-bulk.html</a>
 */
@Value.Immutable
@Value.Style(depluralize = true)
@JsonSerialize(as = ImmutableBulkAction.class)
@JsonDeserialize(as = ImmutableBulkAction.class)
public abstract class BulkAction {

    /**
     * The type of operation to carry out inside this action.
     *
     * @return a String operation name.
     */
    abstract public String operation();

    /**
     * The index targeted by this action.
     *
     * @return a String index name.
     */
    @Nullable
    abstract public String index();

    /**
     * The index type targeted by this action.
     *
     * @return a String type name.
     */
    @Nullable
    abstract public String type();

    /**
     * The document identifier targeted by this action.
     *
     * @return a String document identifier.
     */
    @Nullable
    abstract public String id();

    /**
     * The parent identifier associated with this action.
     *
     * @return a String parent identifier.
     */
    @Nullable
    abstract public String parent();

    /**
     * The routing identifier associated with this action.
     *
     * @return a String routing identifier.
     */
    @Nullable
    abstract public String routing();

    /**
     * The source body associated with this action.
     *
     * @return a String source body.
     */
    @Nullable
    abstract public String source();

    /**
     * The document version associated with this action.
     *
     * @return an Integer document version
     */
    @Nullable
    abstract public Integer version();

    /**
     * Whether this action should cause a refresh or not.
     *
     * @return true if a refresh should be triggered.
     */
    @Nullable
    abstract public Boolean refresh();

    /**
     * Whether this action should wait for shards or not.
     *
     * @return true if the action should wait for shards.
     */
    @Nullable
    abstract public Boolean waitForActiveShards();

    /**
     * Returns a builder in order to create an action.
     *
     * @return a new {@link Builder} instance.
     */
    @SuppressWarnings("unused")
    public static Builder builder() {
        return new BulkAction.Builder();
    }

    /**
     * Builder bindings to allow for creating actions with validation.
     */
    @SuppressWarnings("WeakerAccess")
    public static class Builder extends ImmutableBulkAction.Builder { }
}
