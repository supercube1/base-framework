package io.github.supercube.service.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Base class for the various attribute filters. It can be added to a criteria class as a member, to support the
 * following query parameters:
 * <pre>
 *      fieldName.equals='something'
 *      fieldName.specified=true
 *      fieldName.specified=false
 *      fieldName.in='something','other'
 * </pre>
 */
public class Filter<FIELD_TYPE> implements Serializable {

    private static final long serialVersionUID = 1L;
    private FIELD_TYPE equals;
    private Boolean specified;
    private List<FIELD_TYPE> in;

    /**
     * <p>Constructor for Filter.</p>
     */
    public Filter() {
    }

    /**
     * <p>Constructor for Filter.</p>
     *
     * @param filter a {@link io.github.supercube.service.filter.Filter} object.
     */
    public Filter(Filter<FIELD_TYPE> filter) {
        this.equals = filter.equals;
        this.specified = filter.specified;
        this.in = filter.in == null ? null : new ArrayList<>(filter.in);
    }

    /**
     * <p>copy.</p>
     *
     * @return a {@link io.github.supercube.service.filter.Filter} object.
     */
    public Filter<FIELD_TYPE> copy() {
        return new Filter<>(this);
    }

    /**
     * <p>Getter for the field <code>equals</code>.</p>
     *
     * @return a FIELD_TYPE object.
     */
    public FIELD_TYPE getEquals() {
        return equals;
    }

    /**
     * <p>Setter for the field <code>equals</code>.</p>
     *
     * @param equals a FIELD_TYPE object.
     * @return a {@link io.github.supercube.service.filter.Filter} object.
     */
    public Filter<FIELD_TYPE> setEquals(FIELD_TYPE equals) {
        this.equals = equals;
        return this;
    }

    /**
     * <p>Getter for the field <code>specified</code>.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getSpecified() {
        return specified;
    }

    /**
     * <p>Setter for the field <code>specified</code>.</p>
     *
     * @param specified a {@link java.lang.Boolean} object.
     * @return a {@link io.github.supercube.service.filter.Filter} object.
     */
    public Filter<FIELD_TYPE> setSpecified(Boolean specified) {
        this.specified = specified;
        return this;
    }

    /**
     * <p>Getter for the field <code>in</code>.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<FIELD_TYPE> getIn() {
        return in;
    }

    /**
     * <p>Setter for the field <code>in</code>.</p>
     *
     * @param in a {@link java.util.List} object.
     * @return a {@link io.github.supercube.service.filter.Filter} object.
     */
    public Filter<FIELD_TYPE> setIn(List<FIELD_TYPE> in) {
        this.in = in;
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Filter<?> filter = (Filter<?>) o;
        return Objects.equals(equals, filter.equals) &&
            Objects.equals(specified, filter.specified) &&
            Objects.equals(in, filter.in);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hash(equals, specified, in);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return getFilterName() + " ["
            + (getEquals() != null ? "equals=" + getEquals() + ", " : "")
            + (getIn() != null ? "in=" + getIn() + ", " : "")
            + (getSpecified() != null ? "specified=" + getSpecified() : "")
            + "]";
    }

    /**
     * <p>getFilterName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    protected String getFilterName() {
        return this.getClass().getSimpleName();
    }
}
