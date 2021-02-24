package ru.softwarecom.uspn.emulators.ecasa.util;

import org.hibernate.tool.schema.extract.internal.SequenceInformationExtractorDB2DatabaseImpl;

public class SequenceInformationExtractorDb2ZOsDatabaseImpl extends SequenceInformationExtractorDB2DatabaseImpl {

    public static final SequenceInformationExtractorDb2ZOsDatabaseImpl INSTANCE = new SequenceInformationExtractorDb2ZOsDatabaseImpl();

    @Override
    protected String sequenceNameColumn() {
        return "name";
    }

    @Override
    protected String sequenceSchemaColumn() {
        return "schema";
    }
}
