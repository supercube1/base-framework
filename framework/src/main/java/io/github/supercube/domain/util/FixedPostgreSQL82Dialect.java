package io.github.supercube.domain.util;

import org.hibernate.dialect.PostgreSQL82Dialect;
import org.hibernate.type.descriptor.sql.BinaryTypeDescriptor;
import org.hibernate.type.descriptor.sql.SqlTypeDescriptor;

import java.sql.Types;

/**
 * <p>FixedPostgreSQL82Dialect class.</p>
 */
public class FixedPostgreSQL82Dialect extends PostgreSQL82Dialect {

    /**
     * <p>Constructor for FixedPostgreSQL82Dialect.</p>
     */
    public FixedPostgreSQL82Dialect() {
        super();
        registerColumnType(Types.BLOB, "bytea");
    }

    /** {@inheritDoc} */
    @Override
    public SqlTypeDescriptor remapSqlTypeDescriptor(SqlTypeDescriptor sqlTypeDescriptor) {
        if (sqlTypeDescriptor.getSqlType() == java.sql.Types.BLOB) {
            return BinaryTypeDescriptor.INSTANCE;
        }
        return super.remapSqlTypeDescriptor(sqlTypeDescriptor);
    }
}
