package ru.softwarecom.uspn.emulators.ecasa.util;

import org.hibernate.dialect.DB2Dialect;
import org.hibernate.tool.schema.extract.internal.SequenceInformationExtractorNoOpImpl;
import org.hibernate.tool.schema.extract.spi.SequenceInformationExtractor;

public class Db2ZOsDialect extends DB2Dialect {
    @Override
    public String getTableTypeString() {
        return " IN DATABASE USPNDB";
    }

    @Override
    public String getSequenceNextValString(String sequenceName) {
        return "select next value for " + sequenceName + " from sysibm.sysdummy1";
    }

    @Override
    public String getLimitString(String sql, int offset, int limit) {
        return sql + " LIMIT " + limit + " OFFSET " + offset;
    }

    @Override
    public String getQuerySequencesString() {
        return "select * from sysibm.syssequences";
    }

    @Override
    public SequenceInformationExtractor getSequenceInformationExtractor() {
        if (getQuerySequencesString() == null) {
            return SequenceInformationExtractorNoOpImpl.INSTANCE;
        } else {
            return SequenceInformationExtractorDb2ZOsDatabaseImpl.INSTANCE;
        }
    }
}
